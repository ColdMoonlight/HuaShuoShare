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
import com.atguigu.bean.ShareUpdate;
import com.atguigu.common.Msg;
import com.atguigu.service.ShareUpdateService;
import com.atguigu.service.ShareOperationRecordService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/ShareUpdate")
public class ShareUpdateController {
	
	@Autowired
	ShareUpdateService shareUpdateService;
	
	@Autowired
	ShareOperationRecordService shareOperationRecordService;
	
	/**2.0	zsh200904
	 * ShareUpdate	initializaFileNameInfo
	 * @param ShareUpdate
	 * @return
	 */
	@RequestMapping(value="/initializaUpdateInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaUpdateInfo(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareUpdate shareUpdate){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		session.setAttribute("AdminUser", mlbackAdmin);
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			System.out.println("当前的登陆客户:"+mlbackAdmin.toString());
			//接受参数信息
			Integer	updateId = shareUpdate.getTbShareUpdateId();
			String nowTime = DateUtil.strTime14s();
			shareUpdate.setTbShareUpdateModifytime(nowTime);
			if(updateId==null){
				shareUpdate.setTbShareUpdateCreatetime(nowTime);
				shareUpdate.setTbShareUpdateAdminid(mlbackAdmin.getAdminId());
				shareUpdate.setTbShareUpdateAdminname(mlbackAdmin.getAdminAccname()+"--"+mlbackAdmin.getAdminOperatername());
				//无id,insert
				shareUpdateService.insertSelective(shareUpdate);
			}else{
				//有id,update
				shareUpdateService.updateByPrimaryKeySelective(shareUpdate);
			}
			
			return Msg.success().add("resMsg", "Update初始化成功").add("adminPower", adminPower).add("shareUpdateRes", shareUpdate);
		}
	}
	
	/**5.0	zsh200904
	 * MlbackAreafreight	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareUpdate shareUpdate){
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			int shareUpdateIdInt = shareUpdate.getTbShareUpdateId();
			//操作完毕,执行删除
			shareUpdateService.deleteByPrimaryKey(shareUpdateIdInt);
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
	 * 后台ShareUpdate列表all-list数据
	 * @return
	 */
	@RequestMapping(value="/getShareUpdateListAll",method=RequestMethod.POST)
	@ResponseBody
	public Msg getShareUpdateListAll(HttpSession session,HttpServletResponse rep,HttpServletRequest res) {
		
		List<ShareUpdate> shareUpdateList = shareUpdateService.selectShareUpdatelistAll();
		
		return Msg.success().add("shareUpdateList", shareUpdateList);
	}
	
}
