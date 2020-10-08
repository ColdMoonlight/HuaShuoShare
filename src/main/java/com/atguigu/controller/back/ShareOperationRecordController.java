package com.atguigu.controller.back;

import java.util.ArrayList;
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
import com.atguigu.bean.ShareOperationRecord;
import com.atguigu.common.Msg;
import com.atguigu.service.ShareOperationRecordService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/ShareOperationRecord")
public class ShareOperationRecordController {
	
	@Autowired
	ShareOperationRecordService shareOperationRecordService;
	
	/**
	 * 1.0	zsh200904
	 * to分类MlbackAreafreight列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toshareImageInfoPage")
	public String toshareImageInfoPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			session.setAttribute("AdminUser", mlbackAdmin);
			return "back/shareImageInfoPage";
		}
	}

	
	/**4.0	zsh200904
	 * 后台ShareOperationRecord列表某一级别页list数据
	 * @param pn
	 * @return
	 */
//	@RequestMapping(value="/getShareOperationRecordListByPid")
//	@ResponseBody
//	public Msg getShareOperationRecordListByPid(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareOperationRecord shareOperationRecord) {
//		
//		String adminPower = getAdminInfo(session);
//		if("0000".equals(adminPower)){
//			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
//		}else{
//			Integer imageInfoPid = shareOperationRecord.getTbShareOperationRecordParentid();
//			ShareOperationRecord shareOperationRecordReq = new ShareOperationRecord();
//			shareOperationRecordReq.setTbShareOperationRecordParentid(imageInfoPid);
//			
//			List<ShareOperationRecord> shareOperationRecordList = shareOperationRecordService.selectShareOperationRecordlistByPid(shareOperationRecordReq);
//			
//			return Msg.success().add("adminPower", adminPower).add("shareOperationRecordList", shareOperationRecordList).add("parentIdPage", imageInfoPid);
//		}
//	}
//	
//	/**5.0	zsh200904
//	 * MlbackAreafreight	delete
//	 * @param id
//	 */
//	@RequestMapping(value="/delete",method=RequestMethod.POST)
//	@ResponseBody
//	public Msg delete(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareOperationRecord shareOperationRecord){
//		
//		String adminPower = getAdminInfo(session);
//		if("0000".equals(adminPower)){
//			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
//		}else{
//			int shareOperationRecordIdInt = shareOperationRecord.getTbShareOperationRecordId();
//			shareOperationRecordService.deleteByPrimaryKey(shareOperationRecordIdInt);
//			return Msg.success().add("resMsg", "delete success").add("adminPower", adminPower);
//		}
//	}
//	
//	public String getAdminInfo(HttpSession session) {
//		
//		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
//		
//		String adminPower = "0";
//		if(mlbackAdmin==null){
//			return "0000";
//		}else{
//			adminPower = mlbackAdmin.getAdminPower();
//			return adminPower;
//		}
//	}
//	
	/**
	 * zsh 200730
	 * 中控台获取付款总金额钱数,总单数
	 * */
	@RequestMapping(value="/getBackHomeOperationRecord",method=RequestMethod.POST)
	@ResponseBody
	public Msg getBackHomeOperationRecord(HttpSession session,@RequestBody ShareOperationRecord shareOperationRecord) throws Exception{
		
		List<ShareOperationRecord> mlfrontPayInfoList =  shareOperationRecordService.selectShareOperationRecordByDate(shareOperationRecord);
		System.out.println("mlfrontPayInfoList.size():"+mlfrontPayInfoList.size());
		return Msg.success().add("resMsg", "单位时间所查信息").add("mlfrontPayInfoList", mlfrontPayInfoList);
	}
	
}
