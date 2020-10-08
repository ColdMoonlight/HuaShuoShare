package com.atguigu.controller.back;

import java.util.List;
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

	/**
	 * 2.0	zsh 201008
	 * 中控台获取操作记录明细
	 * */
	@RequestMapping(value="/getBackHomeOperationRecord",method=RequestMethod.POST)
	@ResponseBody
	public Msg getBackHomeOperationRecord(HttpSession session,@RequestBody ShareOperationRecord shareOperationRecord) throws Exception{
		
		List<ShareOperationRecord> mlfrontPayInfoList =  shareOperationRecordService.selectShareOperationRecordByDate(shareOperationRecord);
		System.out.println("mlfrontPayInfoList.size():"+mlfrontPayInfoList.size());
		return Msg.success().add("resMsg", "单位时间所查信息").add("mlfrontPayInfoList", mlfrontPayInfoList);
	}
	
}
