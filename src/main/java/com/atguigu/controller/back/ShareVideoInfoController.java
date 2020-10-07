package com.atguigu.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.atguigu.bean.FileEntity;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.bean.ShareOperationRecord;
import com.atguigu.bean.ShareVideoInfo;
import com.atguigu.common.Msg;
import com.atguigu.service.FileService;
import com.atguigu.service.ShareOperationRecordService;
import com.atguigu.service.ShareVideoInfoService;
import com.atguigu.service.UploadService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.FileUploadTool;
import com.atguigu.utils.ImageNameUtil;
import com.atguigu.utils.URLLocationUtils;

@Controller
@RequestMapping("/ShareVideoInfo")
public class ShareVideoInfoController {
	
	@Autowired
	ShareVideoInfoService shareVideoInfoService;
	
	@Autowired
	ShareOperationRecordService shareOperationRecordService;
	
	@Autowired
	private FileService service;
	
	@Autowired
	UploadService uploadService;
	
	/**
	 * 1.1	zsh200904
	 * to分类toVideoInfoPage列表页面
	 * @return jsp
	 * */
	@RequestMapping("/toVideoInfoPage")
	public String toVideoInfoPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		if(mlbackAdmin==null){
			//mlbackAdmin对象为空
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
			
			//存储本条造作记录
			ShareOperationRecord shareOperationRecord = new ShareOperationRecord();
			shareOperationRecord.setOperationRecordAdminid(mlbackAdmin.getAdminId());
			shareOperationRecord.setOperationRecordAdminName(mlbackAdmin.getAdminAccname()+"--"+mlbackAdmin.getAdminOperatername());
			shareOperationRecord.setOperationRecordDataType(0);
			shareOperationRecord.setOperationRecordDataName("新建文件夹");
			shareOperationRecord.setOperationRecordDesc("新建");
			shareOperationRecord.setOperationRecordCreatetime(nowTime);
			
			shareOperationRecordService.insertSelective(shareOperationRecord);
			System.out.println(shareOperationRecord.toString());
			
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
			
			
			//存储本条造作记录
			ShareOperationRecord shareOperationRecord = new ShareOperationRecord();
			shareOperationRecord.setOperationRecordAdminid(mlbackAdmin.getAdminId());
			shareOperationRecord.setOperationRecordAdminName(mlbackAdmin.getAdminAccname()+"--"+mlbackAdmin.getAdminOperatername());
			shareOperationRecord.setOperationRecordDataType(0);
			shareOperationRecord.setOperationRecordDataName(shareVideoInfo.getTbShareVideoinfoName());
			shareOperationRecord.setOperationRecordDesc("更新");
			shareOperationRecord.setOperationRecordCreatetime(nowTime);
			
			shareOperationRecordService.insertSelective(shareOperationRecord);
			System.out.println(shareOperationRecord.toString());
			
			return Msg.success().add("resMsg", "更新成功").add("adminPower", adminPower).add("shareVideoInfo", shareVideoInfo);
		}
	}
	
	/**3.1	zsh200904
	 * ShareVideoInfo	remove
	 * @param ShareVideoInfo
	 */
	@RequestMapping(value="/removeFileLocal",method=RequestMethod.POST)
	@ResponseBody
	public Msg removeFileLocal(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareVideoInfo shareVideoInfo){
		
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
			
			//存储本条造作记录
			ShareOperationRecord shareOperationRecord = new ShareOperationRecord();
			shareOperationRecord.setOperationRecordAdminid(mlbackAdmin.getAdminId());
			shareOperationRecord.setOperationRecordAdminName(mlbackAdmin.getAdminAccname()+"--"+mlbackAdmin.getAdminOperatername());
			shareOperationRecord.setOperationRecordDataType(shareVideoInfo.getTbShareVideoinfoType());
			shareOperationRecord.setOperationRecordDataName(shareVideoInfo.getTbShareVideoinfoName());
			shareOperationRecord.setOperationRecordDesc("移动");
			shareOperationRecord.setOperationRecordCreatetime(nowTime);
			shareOperationRecordService.insertSelective(shareOperationRecord);
			System.out.println(shareOperationRecord.toString());
			
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
	
	/**
	 * 	zsh200908
	 * 视频的第一帧封面图
	 * */
	@RequestMapping(value="/imageInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg imageInfo(@RequestParam("image")CommonsMultipartFile file,
			@RequestParam("parentid")Integer parentid,@RequestParam("parentname")String parentname,
			HttpSession session,HttpServletResponse rep,HttpServletRequest res){
		
		//判断参数,确定信息
		String typeName = file.getOriginalFilename();
		
		String parentIdStr = parentid+"";
		String imgName = ImageNameUtil.getImageInfofilename(typeName,parentIdStr,parentname);
		
		String uploadPath = "static/shareUpload/Video/img";
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		
		//当前服务器路径
		String basePathStr = URLLocationUtils.getbasePathStr(rep,res);
        System.out.println("basePathStr:"+basePathStr);
		
		String imageUrl ="";
		String sqlimageUrl="";
		try {
			
			imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath,imgName);//图片原图路径
			sqlimageUrl=basePathStr+imageUrl;
			System.out.println("sqlimageUrl:"+sqlimageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String nowTime = DateUtil.strTime14s();
		ShareVideoInfo shareVideoInfo = new ShareVideoInfo();
		shareVideoInfo.setTbShareVideoinfoParentid(parentid);
		shareVideoInfo.setTbShareVideoinfoParentname(parentname);
		shareVideoInfo.setTbShareVideoinfoType(1);
		shareVideoInfo.setTbShareVideoinfoName(imgName);
		shareVideoInfo.setTbShareVideoinfoVideoimgurl(sqlimageUrl);
		shareVideoInfo.setTbShareVideoinfoCreatetime(nowTime);
		shareVideoInfoService.insertSelective(shareVideoInfo);
		System.out.println("shareVideoInfo上传完毕"+shareVideoInfo.toString());
		
		return Msg.success().add("resMsg", "上传").add("shareVideoInfo", shareVideoInfo);
	}
	
	@RequestMapping(value = "/uploadVideo")
	@ResponseBody
	public Msg upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
			@RequestParam("videoId")Integer videoId,HttpServletRequest request,HttpServletResponse response,ModelMap map) {
	    String message = "";
	    FileEntity entity = new FileEntity();
	    String logoPathDir = request.getParameter("shipin");
	    System.out.println("-------" + logoPathDir + "----------------------------------");
	    FileUploadTool fileUploadTool = new FileUploadTool();
	    //服务器路径+端口号http://localhost:8080/HuaShuoOnline
	    String basePathStr = URLLocationUtils.getbasePathStr(response,request);  //出来是真实的
	    try {
	      entity = fileUploadTool.createFile(basePathStr,logoPathDir, multipartFile, request);
	      if (entity != null) {
	        service.saveFile(entity);
	        message ="上传成功";
	        map.put("entity", entity);
	        map.put("result", message);
	      } else {
	        message ="上传失败";
	        map.put("result", message);
	      }

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    ShareVideoInfo shareVideoInfoReq = new ShareVideoInfo();
	    shareVideoInfoReq.setTbShareVideoinfoId(videoId);
	    shareVideoInfoReq.setTbShareVideoinfoSize(entity.getSize());
	    shareVideoInfoReq.setTbShareVideoinfoPath(entity.getPath());
	    shareVideoInfoReq.setTbShareVideoinfoVideourl(entity.getPath());
	    shareVideoInfoReq.setTbShareVideoinfoTitleorig(entity.getTitleOrig());
		shareVideoInfoReq.setTbShareVideoinfoTitlealter(entity.getTitleAlter());
		shareVideoInfoReq.setTbShareVideoinfoVideotype(entity.getType());
		shareVideoInfoService.updateByPrimaryKeySelective(shareVideoInfoReq);
	    return Msg.success().add("resMsg", "ProVideo上传成功").add("shareVideoInfo", shareVideoInfoReq);
	}
	
	/**6.0	zsh201006
	 * 后台ShareVideoInfo列表all-list数据
	 * @return
	 */
	@RequestMapping(value="/getShareVideoInfoListAll",method=RequestMethod.POST)
	@ResponseBody
	public Msg getShareVideoInfoListAll(HttpSession session,HttpServletResponse rep,HttpServletRequest res) {
		
		List<ShareVideoInfo> shareVideoInfoList = shareVideoInfoService.selectShareVideoInfolistAll();
		
		return Msg.success().add("shareVideoInfoList", shareVideoInfoList);
	}
	
}
