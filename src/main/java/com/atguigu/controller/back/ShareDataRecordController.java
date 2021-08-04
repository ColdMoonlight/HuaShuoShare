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
	
	/**2.0	zsh210804
	 * ShareDataRecord	getShareDataRecordByPage
	 * @param ShareDataRecord
	 * @return
	 */
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
	
	/**3.0	zsh210115
	 * ShareDataRecord	initializaFileNameInfo
	 * @param ShareDataRecord
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
//			ShareDataRecord.setDatarecordAdminid(mlbackAdmin.getAdminId());
//			ShareDataRecord.setDatarecordAdminname(mlbackAdmin.getAdminAccname()+"--"+mlbackAdmin.getAdminOperatername());
			shareDataRecordService.insertSelective(ShareDataRecord);
			
			return Msg.success().add("resMsg", "Update初始化成功").add("adminPower", adminPower).add("ShareDataRecordRes", ShareDataRecord);
		}
	}
	
	/**4.0	zsh210804
	 * ShareDataRecord	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareDataRecord ShareDataRecord){
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			int ShareDatarecordIdInt = ShareDataRecord.getDatarecordId();
			//操作完毕,执行删除
			shareDataRecordService.deleteByPrimaryKey(ShareDatarecordIdInt);
			return Msg.success().add("resMsg", "delete success").add("adminPower", adminPower);
		}
	}
	
	
	/**5.0	onuse	zsh210804	检查
	 * ShareDataRecord	save
	 * @param ShareDataRecord
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
	
	/**6.0	zsh210804
	 * 后台ShareDataRecord列表all-list数据
	 * @return
	 */
	@RequestMapping(value="/getShareDataRecordDetailById",method=RequestMethod.POST)
	@ResponseBody
	public Msg getShareDataRecordDetailById(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareDataRecord ShareDataRecord) {
		
		Integer datarecordId = ShareDataRecord.getDatarecordId();
		
		ShareDataRecord shareDataRecordReq = new ShareDataRecord();
		shareDataRecordReq.setDatarecordId(datarecordId);
		List<ShareDataRecord> shareDataRecordResList =shareDataRecordService.selectShareDataRecordById(shareDataRecordReq);
		ShareDataRecord shareDataRecordOne = new ShareDataRecord();
		if(shareDataRecordResList.size()>0){
			//如果用这个id查到,就拿出来.
			shareDataRecordOne = shareDataRecordResList.get(0);
		}else{
			//如果用这个id没查到,就取出当前所有产品最新上的那款.
			shareDataRecordResList = shareDataRecordService.selectShareDataRecordGetAll();
			shareDataRecordOne = shareDataRecordResList.get(0);
		}
		return Msg.success().add("resMsg", "查看单个产品详情完毕").add("shareDataRecordOne", shareDataRecordOne);
	}

}
