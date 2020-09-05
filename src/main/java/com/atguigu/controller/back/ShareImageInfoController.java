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
import com.atguigu.bean.ShareImageInfo;
import com.atguigu.common.Msg;
import com.atguigu.service.ShareImageInfoService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.SessionUtil;

@Controller
@RequestMapping("/ShareImageInfo")
public class ShareImageInfoController {
	
	@Autowired
	ShareImageInfoService shareImageInfoService;
	
	/**
	 * 1.0	zsh200904
	 * to分类MlbackAreafreight列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toImageInfoPage")
	public String toImageInfoPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/imageInfoPage";
		}
	}
	
	/**2.0	zsh200904
	 * ShareImageInfo	initializaFileNameInfo
	 * @param ShareImageInfo
	 * @return
	 */
	@RequestMapping(value="/initializaFileNameInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg initializaFileNameInfo(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareImageInfo shareImageInfo){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		
		String adminPower = SessionUtil.getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			System.out.println("当前的登陆客户:"+mlbackAdmin.toString());
			
			//接受参数信息
			Integer	infoParentid = shareImageInfo.getTbShareImageinfoParentid();
			String shareImageinfoParentName=shareImageInfo.getTbShareImageinfoDesc();
			Integer	infoParentidReq = 0;
			String shareImageinfoParentNameReq="";
			String desc = "新建文件夹";
			if(infoParentid>0){
				infoParentidReq = infoParentid;
				shareImageinfoParentNameReq = shareImageinfoParentName;
				desc = shareImageinfoParentName+">新建文件夹";
			}else{
				infoParentidReq = 0;
				shareImageinfoParentNameReq = "";
				desc = "新建文件夹";
			}
			ShareImageInfo shareImageInfoReq = new ShareImageInfo();
			//判断归属是否为none
			shareImageInfoReq.setTbShareImageinfoType(0);//0文件夹图片
			shareImageInfoReq.setTbShareImageinfoParentid(infoParentidReq);
			shareImageInfoReq.setTbShareImageinfoParentname(shareImageinfoParentName);
			shareImageInfoReq.setTbShareImageinfoDesc(desc);
			shareImageInfoReq.setTbShareImageinfoName("新建文件夹");
			//取出id
			String nowTime = DateUtil.strTime14s();
			shareImageInfoReq.setTbShareImageinfoCreatetime(nowTime);
			//无id,insert
			System.out.println("插入前"+shareImageInfoReq.toString());
			shareImageInfoService.insertSelective(shareImageInfoReq);
			System.out.println("插入后"+shareImageInfoReq.toString());
			return Msg.success().add("resMsg", "imageInfo初始化成功").add("adminPower", adminPower).add("shareImageInfoReq", shareImageInfoReq);
		}
	}
	
	/**3.0	zsh200904
	 * MlbackAreafreight	save
	 * @param MlbackAreafreight
	 */
	@RequestMapping(value="/updateFileName",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateFileName(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareImageInfo shareImageInfo){
		
		String adminPower = SessionUtil.getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			//接受参数信息
			System.out.println("shareImageInfo:"+shareImageInfo.toString());
			//取出id
			String nowTime = DateUtil.strTime14s();
			shareImageInfo.setTbShareImageinfoCreatetime(nowTime);
			//有id，update
			shareImageInfoService.updateByPrimaryKeySelective(shareImageInfo);
			return Msg.success().add("resMsg", "更新成功").add("adminPower", adminPower).add("shareImageInfo", shareImageInfo);
		}
	}
	
	/**4.0	zsh200904
	 * 后台ShareImageInfo列表某一级别页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getShareImageInfoListByPid")
	@ResponseBody
	public Msg getShareImageInfoListByPid(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareImageInfo shareImageInfo) {
		
		String adminPower = SessionUtil.getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			Integer imageInfoPid = shareImageInfo.getTbShareImageinfoParentid();
			ShareImageInfo shareImageInfoReq = new ShareImageInfo();
			shareImageInfoReq.setTbShareImageinfoParentid(imageInfoPid);
			
			List<ShareImageInfo> shareImageInfoList = shareImageInfoService.selectShareImageInfolistByPid(shareImageInfoReq);
			
			return Msg.success().add("adminPower", adminPower).add("shareImageInfoList", shareImageInfoList).add("parentIdPage", imageInfoPid);
		}
	}
	
	/**5.0	zsh200904
	 * MlbackAreafreight	delete
	 * @param id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareImageInfo shareImageInfo){
		
		String adminPower = SessionUtil.getAdminInfo(session);
		if("0000".equals(adminPower)){
			return Msg.success().add("resMsg", "请重新登陆").add("adminPower", adminPower);
		}else{
			int shareImageinfoIdInt = shareImageInfo.getTbShareImageinfoId();
			shareImageInfoService.deleteByPrimaryKey(shareImageinfoIdInt);
			return Msg.success().add("resMsg", "delete success").add("adminPower", adminPower);
		}
	}
	
}
