package com.atguigu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.atguigu.bean.ShareImageInfo;
import com.atguigu.common.Msg;
import com.atguigu.service.ShareImageInfoService;
import com.atguigu.service.UploadService;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.ImageNameUtil;
import com.atguigu.utils.URLLocationUtils;

/**
 * ImageUpload
 * @author	zsh
 */
@Controller
@RequestMapping("/ImageUpload")
public class ImageUploadController {
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	ShareImageInfoService shareImageInfoService;
	
	/**
	 * 	zsh200908
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
		
		String uploadPath = "static/shareUpload/img";
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
		ShareImageInfo shareImageInfo = new ShareImageInfo();
		shareImageInfo.setTbShareImageinfoParentid(parentid);
		shareImageInfo.setTbShareImageinfoParentname(parentname);
		shareImageInfo.setTbShareImageinfoType(1);
		shareImageInfo.setTbShareImageinfoName(imgName);
		shareImageInfo.setTbShareImageinfoUrl(imageUrl);
		shareImageInfo.setTbShareImageinfoCreatetime(nowTime);
		shareImageInfoService.insertSelective(shareImageInfo);
		System.out.println("shareImageInfo上传完毕"+shareImageInfo.toString());
		
		return Msg.success().add("resMsg", "上传").add("shareImageInfo", shareImageInfo);
	}

}
