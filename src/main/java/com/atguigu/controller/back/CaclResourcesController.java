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
import com.atguigu.service.ShareImageInfoService;
import com.atguigu.service.ShareOperationRecordService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/CaclResources")
public class CaclResourcesController {
	
	@Autowired
	ShareImageInfoService shareImageInfoService;
	
	@Autowired
	ShareOperationRecordService shareOperationRecordService;
	
	/**1.0	zsh201008
	 * ShareOperationRecord	caclDownloadImg
	 * @param ShareOperationRecord
	 * @return
	 */
	@RequestMapping(value="/caclDownloadImg",method=RequestMethod.POST)
	@ResponseBody
	public Msg caclDownloadImg(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareOperationRecord shareOperationRecordInto){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		session.setAttribute("AdminUser", mlbackAdmin);
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			System.out.println("当前的登陆客户:"+mlbackAdmin.toString());
			String nowTime = DateUtil.strTime14s();
			//存储本条造作记录--新增文件夹名字
			ShareOperationRecord shareOperationRecordReq = new ShareOperationRecord();
			shareOperationRecordReq.setOperationRecordAdminid(mlbackAdmin.getAdminId());
			shareOperationRecordReq.setOperationRecordAdminName(mlbackAdmin.getAdminAccname()+"--"+mlbackAdmin.getAdminOperatername());
			shareOperationRecordReq.setOperationRecordDataType(shareOperationRecordInto.getOperationRecordDataType());
			shareOperationRecordReq.setOperationRecordDataName(shareOperationRecordInto.getOperationRecordDataName());
			shareOperationRecordReq.setOperationRecordDesc("下载");
			shareOperationRecordReq.setOperationRecordCreatetime(nowTime);
			shareOperationRecordService.insertSelective(shareOperationRecordReq);
			return Msg.success().add("resMsg", "imageInfo初始化成功").add("adminPower", adminPower).add("shareOperationRecordReq", shareOperationRecordReq);
		}
	}
	
	/**2.0	zsh201008
	 * ShareOperationRecord	caclDownloadVideo
	 * @param ShareOperationRecord
	 * @return	Msg
	 */
	@RequestMapping(value="/caclDownloadVideo",method=RequestMethod.POST)
	@ResponseBody
	public Msg caclDownloadVideo(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareOperationRecord shareOperationRecordInto){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		session.setAttribute("AdminUser", mlbackAdmin);
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			System.out.println("当前的登陆客户:"+mlbackAdmin.toString());
			String nowTime = DateUtil.strTime14s();
			//存储本条造作记录--新增文件夹名字
			ShareOperationRecord shareOperationRecordReq = new ShareOperationRecord();
			shareOperationRecordReq.setOperationRecordAdminid(mlbackAdmin.getAdminId());
			shareOperationRecordReq.setOperationRecordAdminName(mlbackAdmin.getAdminAccname()+"--"+mlbackAdmin.getAdminOperatername());
			shareOperationRecordReq.setOperationRecordDataType(shareOperationRecordInto.getOperationRecordDataType());
			shareOperationRecordReq.setOperationRecordDataName(shareOperationRecordInto.getOperationRecordDataName());
			shareOperationRecordReq.setOperationRecordDesc("下载");
			shareOperationRecordReq.setOperationRecordCreatetime(nowTime);
			shareOperationRecordService.insertSelective(shareOperationRecordReq);
			return Msg.success().add("resMsg", "imageInfo初始化成功").add("adminPower", adminPower).add("shareOperationRecordReq", shareOperationRecordReq);
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
	
	/**
	 * cacl下载量
	 * */
	@RequestMapping(value="/caclNumByDateAndDesc",method=RequestMethod.POST)
	@ResponseBody
	public Msg caclNumByDateAndDesc(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareOperationRecord shareOperationRecordInto){
		
		//查询时间端参数,动作0-文件夹/1-img/2-video,描述字段(0上传,1下载,2新建,3更新,4移动,5删除)
		ShareOperationRecord shareOperationRecordReq = new ShareOperationRecord();
		shareOperationRecordReq.setOperationRecordCreatetime(shareOperationRecordInto.getOperationRecordCreatetime());
		shareOperationRecordReq.setOperationRecordMotifyTime(shareOperationRecordInto.getOperationRecordMotifyTime());
		List<ShareOperationRecord> shareOperationRecordList =  shareOperationRecordService.selectShareOperationRecordByDateAndType(shareOperationRecordReq);
		List<ShareOperationRecord> fileList = new ArrayList<ShareOperationRecord>();
		List<ShareOperationRecord> imgList = new ArrayList<ShareOperationRecord>();
		List<ShareOperationRecord> videoList = new ArrayList<ShareOperationRecord>();
		for(ShareOperationRecord shareOperationRecordOne:shareOperationRecordList){
			Integer type = shareOperationRecordOne.getOperationRecordDataType();
			if(type==0){
				fileList.add(shareOperationRecordOne);
			}else if(type==1){
				imgList.add(shareOperationRecordOne);
			}else if(type==2){
				videoList.add(shareOperationRecordOne);
			}
		}
		System.out.println("shareOperationRecordFileList.size():"+fileList.size());
		System.out.println("shareOperationRecordImgList.size():"+imgList.size());
		System.out.println("shareOperationRecordVideoList.size():"+videoList.size());
		return Msg.success().add("resMsg", "imageInfo初始化成功").add("fileList", fileList).add("imgList", imgList).add("videoList", videoList);
	}
	
}
