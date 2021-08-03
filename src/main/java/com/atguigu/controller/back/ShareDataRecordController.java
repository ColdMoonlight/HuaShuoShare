package com.atguigu.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.ShareDataRecord;
import com.atguigu.bean.ShareDemand;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.ShareDataRecordService;
import com.atguigu.service.ShareDemandService;
import com.atguigu.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/ShareDataRecord")
public class ShareDataRecordController {
	
	@Autowired
	ShareDemandService shareDemandService;
	
	@Autowired
	ShareDataRecordService shareDataRecordService;
	
	/**
	 * 1.0	zsh200904
	 * to分类shareDataRecordPage列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toShareDataRecordPage")
	public String toShareDataRecordPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			session.setAttribute("AdminUser", mlbackAdmin);
			return "back/shareDataRecordPage";
		}
	}
	
	
	@RequestMapping(value="/getShareDataRecordByPage")
	@ResponseBody
	public Msg getShareDataRecordByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {
//		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("adminuser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return Msg.fail().add("resMsg", "session中adminuser对象为空");
//		}else{
			int PagNum = Const.PAGE_NUM_PAYINFO;
			PageHelper.startPage(pn, PagNum);
			List<ShareDataRecord> shareDataRecordList = shareDataRecordService.selectShareDataRecordGetAll();
			PageInfo page = new PageInfo(shareDataRecordList, PagNum);
			return Msg.success().add("dataRecordInfo", page);
//		}
	}
	
	/**1.0	zsh210115
	 * ShareDemand	initializaFileNameInfo
	 * @param ShareDemand
	 * @return
	 */
	@RequestMapping(value="/initializaDataRecordInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaDataRecordInfo(HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		session.setAttribute("AdminUser", mlbackAdmin);
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			System.out.println("当前的登陆客户:"+mlbackAdmin.toString());
			ShareDataRecord ShareDataRecord = new ShareDataRecord();
			//接受参数信息
			String nowTime = DateUtil.strTime14s();
			ShareDataRecord.setDatarecordMotifytime(nowTime);
			ShareDataRecord.setDatarecordCreatetime(nowTime);
			ShareDataRecord.setDatarecordAdminid(mlbackAdmin.getAdminId());
			ShareDataRecord.setDatarecordAdminname(mlbackAdmin.getAdminAccname()+"--"+mlbackAdmin.getAdminOperatername());
			shareDataRecordService.insertSelective(ShareDataRecord);
			
			return Msg.success().add("resMsg", "Update初始化成功").add("adminPower", adminPower).add("ShareDataRecordRes", ShareDataRecord);
		}
	}
	
	/**2.0	zsh210115
	 * MlbackAreafreight	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareDemand ShareDemand){
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			int ShareDemandIdInt = ShareDemand.getTbShareDemandId();
			//操作完毕,执行删除
			shareDemandService.deleteByPrimaryKey(ShareDemandIdInt);
			return Msg.success().add("resMsg", "delete success").add("adminPower", adminPower);
		}
	}
	
	
	/**3.0	onuse	20191225	检查
	 * MlbackAreafreight	save
	 * @param MlbackAreafreight
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareDataRecord ShareDataRecord){
		//接受参数信息
		//取出id
		String nowTime = DateUtil.strTime14s();
		ShareDataRecord.setDatarecordMotifytime(nowTime);
		//有id,update
		shareDataRecordService.updateByPrimaryKeySelective(ShareDataRecord);
		
		return Msg.success().add("resMsg", "更新成功").add("ShareDataRecord", ShareDataRecord);
	}
	
	public String getAdminInfo(HttpSession session) {
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		
		String adminPower = "0";
		if(mlbackAdmin==null){
			return "0000";
		}else{
			adminPower = mlbackAdmin.getAdminPower();
			return adminPower;
		}
	}
	
	/**3.0	zsh210115
	 * 后台ShareDemand列表all-list数据
	 * @return
	 */
	@RequestMapping(value="/getShareDemandListAll",method=RequestMethod.POST)
	@ResponseBody
	public Msg getShareDemandListAll(HttpSession session,HttpServletResponse rep,HttpServletRequest res) {
		
		List<ShareDemand> ShareDemandList = shareDemandService.selectShareDemandlistAll();
		
		return Msg.success().add("ShareDemandList", ShareDemandList);
	}
	
}
