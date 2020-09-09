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
import com.atguigu.bean.ShareVideoInfo;
import com.atguigu.common.Msg;
import com.atguigu.service.ShareVideoInfoService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/ShareVideoInfo")
public class ShareVideoInfoController {
	
	@Autowired
	ShareVideoInfoService shareVideoInfoService;
	
//	/**
//	 * 1.0	zsh200904
//	 * to分类MlbackAreafreight列表页面
//	 * @param jsp
//	 * @return 
//	 * */
//	@RequestMapping("/toImageInfoPage")
//	public String toImageInfoPage(HttpSession session) throws Exception{
//		
//		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
//		if(mlbackAdmin==null){
//			//SysUsers对象为空
//			return "back/mlbackAdminLogin";
//		}else{
//			session.setAttribute("AdminUser", mlbackAdmin);
//			return "back/imageInfoPage";
//		}
//	}
	
	/**
	 * 1.1	zsh200904
	 * to分类MlbackAreafreight列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toVideoInfoPage")
	public String toVideoInfoPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			session.setAttribute("AdminUser", mlbackAdmin);
			return "back/videoInfoPage";
		}
	}
	
	/**2.0	zsh200904
	 * ShareVideoInfo	initializaFileNameInfo
	 * @param ShareVideoInfo
	 * @return
	 */
	@RequestMapping(value="/initializaFileNameInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaFileNameInfo(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareVideoInfo shareVideoInfo){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		session.setAttribute("AdminUser", mlbackAdmin);
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			System.out.println("当前的登陆客户:"+mlbackAdmin.toString());
			
			//接受参数信息
			Integer	infoParentid = shareVideoInfo.getTbShareVideoinfoParentid();
			String shareVideoinfoParentName=shareVideoInfo.getTbShareVideoinfoDesc();
			Integer	infoParentidReq = 0;
			String shareVideoinfoParentNameReq="";
			String desc = "新建文件夹";
			if(infoParentid>0){
				infoParentidReq = infoParentid;
				shareVideoinfoParentNameReq = shareVideoinfoParentName;
				desc = shareVideoinfoParentName+">新建文件夹";
			}else{
				infoParentidReq = 0;
				shareVideoinfoParentNameReq = "";
				desc = "新建文件夹";
			}
			ShareVideoInfo shareVideoInfoReq = new ShareVideoInfo();
			//判断归属是否为none
			shareVideoInfoReq.setTbShareVideoinfoType(0);//0文件夹图片
			shareVideoInfoReq.setTbShareVideoinfoParentid(infoParentidReq);
			shareVideoInfoReq.setTbShareVideoinfoParentname(shareVideoinfoParentName);
			shareVideoInfoReq.setTbShareVideoinfoDesc(desc);
			shareVideoInfoReq.setTbShareVideoinfoName("新建文件夹");
			//取出id
			String nowTime = DateUtil.strTime14s();
			shareVideoInfoReq.setTbShareVideoinfoCreatetime(nowTime);
			//无id,insert
			System.out.println("插入前"+shareVideoInfoReq.toString());
			shareVideoInfoService.insertSelective(shareVideoInfoReq);
			System.out.println("插入后"+shareVideoInfoReq.toString());
			return Msg.success().add("resMsg", "imageInfo初始化成功").add("adminPower", adminPower).add("shareVideoInfoReq", shareVideoInfoReq);
		}
	}
	
	/**3.0	zsh200904
	 * ShareVideoInfo	save
	 * @param ShareVideoInfo
	 */
	@RequestMapping(value="/updateFileName",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateFileName(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareVideoInfo shareVideoInfo){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		session.setAttribute("AdminUser", mlbackAdmin);
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			//接受参数信息
			System.out.println("shareVideoInfo:"+shareVideoInfo.toString());
			//取出id
			String nowTime = DateUtil.strTime14s();
			shareVideoInfo.setTbShareVideoinfoCreatetime(nowTime);
			//有id，update
			shareVideoInfoService.updateByPrimaryKeySelective(shareVideoInfo);
			return Msg.success().add("resMsg", "更新成功").add("adminPower", adminPower).add("shareVideoInfo", shareVideoInfo);
		}
	}
	
	/**4.0	zsh200904
	 * 后台ShareVideoInfo列表某一级别页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getShareVideoInfoListByPid")
	@ResponseBody
	public Msg getShareVideoInfoListByPid(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareVideoInfo shareVideoInfo) {
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			Integer imageInfoPid = shareVideoInfo.getTbShareVideoinfoParentid();
			ShareVideoInfo shareVideoInfoReq = new ShareVideoInfo();
			shareVideoInfoReq.setTbShareVideoinfoParentid(imageInfoPid);
			
			List<ShareVideoInfo> shareVideoInfoList = shareVideoInfoService.selectShareVideoInfolistByPid(shareVideoInfoReq);
			
			return Msg.success().add("adminPower", adminPower).add("shareVideoInfoList", shareVideoInfoList).add("parentIdPage", imageInfoPid);
		}
	}
	
	/**5.0	zsh200904
	 * ShareVideoInfo	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareVideoInfo shareVideoInfo){
		
		String adminPower = getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			int shareVideoInfoIdInt = shareVideoInfo.getTbShareVideoinfoId();
			shareVideoInfoService.deleteByPrimaryKey(shareVideoInfoIdInt);
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
	
}
