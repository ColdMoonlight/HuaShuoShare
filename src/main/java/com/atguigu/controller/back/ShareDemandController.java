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
import org.springframework.web.bind.annotation.ResponseBody;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.ShareDemand;
import com.atguigu.common.Msg;
import com.atguigu.service.ShareDemandService;
import com.atguigu.service.ShareOperationRecordService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/ShareDemand")
public class ShareDemandController {
	
	@Autowired
	ShareDemandService shareDemandService;
	
	@Autowired
	ShareOperationRecordService shareOperationRecordService;
	
	/**2.0	zsh210114
	 * ShareDemand	initializaFileNameInfo
	 * @param ShareDemand
	 * @return
	 */
	@RequestMapping(value="/initializaDemandInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaDemandInfo(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareDemand ShareDemand){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		session.setAttribute("AdminUser", mlbackAdmin);
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			System.out.println("当前的登陆客户:"+mlbackAdmin.toString());
			//接受参数信息
			Integer	demandId = ShareDemand.getTbShareDemandId();
			String nowTime = DateUtil.strTime14s();
			ShareDemand.setTbShareDemandModifytime(nowTime);
			if(demandId==null){
				ShareDemand.setTbShareDemandCreatetime(nowTime);
				ShareDemand.setTbShareDemandAdminid(mlbackAdmin.getAdminId());
				ShareDemand.setTbShareDemandAdminname(mlbackAdmin.getAdminAccname()+"--"+mlbackAdmin.getAdminOperatername());
				//无id,insert
				shareDemandService.insertSelective(ShareDemand);
			}else{
				//有id,update
				shareDemandService.updateByPrimaryKeySelective(ShareDemand);
			}
			
			return Msg.success().add("resMsg", "Update初始化成功").add("adminPower", adminPower).add("ShareDemandRes", ShareDemand);
		}
	}
	
	/**5.0	zsh200904
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
	
	/**6.0	zsh200930
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
