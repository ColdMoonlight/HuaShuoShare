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
import com.atguigu.bean.ShareImageInfo;
import com.atguigu.common.Msg;
import com.atguigu.common.TokenCache;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.ShareImageInfoService;
import com.atguigu.utils.DateUtil;

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
		return Msg.success().add("resMsg", "imageInfo初始化成功").add("shareImageInfoReq", shareImageInfoReq);
	}
	
	/**3.0	zsh200904
	 * MlbackAreafreight	save
	 * @param MlbackAreafreight
	 */
	@RequestMapping(value="/updateFileName",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateFileName(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareImageInfo shareImageInfo){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		
		System.out.println("当前的登陆客户:"+mlbackAdmin.toString());
		//接受参数信息
		System.out.println("shareImageInfo:"+shareImageInfo.toString());
		//取出id
		String nowTime = DateUtil.strTime14s();
		shareImageInfo.setTbShareImageinfoCreatetime(nowTime);
		//有id，update
		shareImageInfoService.updateByPrimaryKeySelective(shareImageInfo);
		//System.out.println("后台操作:update,mlbackAreafreight,success+intResult："+intResult);
		return Msg.success().add("resMsg", "更新成功").add("shareImageInfo", shareImageInfo);
	}
	
	/**4.0	zsh200904
	 * 后台ShareImageInfo列表某一级别页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getShareImageInfoListByPid")
	@ResponseBody
	public Msg getShareImageInfoListByPid(HttpSession session,HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareImageInfo shareImageInfo) {
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		
		System.out.println("当前的登陆客户:"+mlbackAdmin.toString());

		Integer imageInfoPid = shareImageInfo.getTbShareImageinfoParentid();
		
		ShareImageInfo shareImageInfoReq = new ShareImageInfo();
		shareImageInfoReq.setTbShareImageinfoParentid(imageInfoPid);
		
		List<ShareImageInfo> shareImageInfoList = shareImageInfoService.selectShareImageInfolistByPid(shareImageInfoReq);
		
		return Msg.success().add("shareImageInfoList", shareImageInfoList).add("parentIdPage", imageInfoPid);
	}
	
//	/**2.0	zsh200904
//	 * 分类MlbackAreafreight列表分页list数据
//	 * @param pn
//	 * @return
//	 */
//	@RequestMapping(value="/getShareImageLabelAll")
//	@ResponseBody
//	public Msg getShareInfoInfoAll(ShareImageLabel shareImageLabel,HttpSession session) {
//		
//		shareImageInfoService.set
//
//		List<ShareImageLabel> shareImageLabelList = shareImageInfoService.selectImageLabelAll();
//		List<ShareImageLabel> shareImageLabelOneList = new ArrayList<ShareImageLabel>();
//		List<ShareImageLabel> shareImageLabelTwoList = new ArrayList<ShareImageLabel>();
//		List<ShareImageLabel> shareImageLabelThreeList = new ArrayList<ShareImageLabel>();
//		List<ShareImageLabel> shareImageLabelFourList = new ArrayList<ShareImageLabel>();
//		List<ShareImageLabel> shareImageLabelFiveList = new ArrayList<ShareImageLabel>();
//		for(ShareImageLabel shareImageLabel:shareImageLabelList){
//			String imageLabelHang = shareImageLabel.getImageLabelHang();
//			if("1".equals(imageLabelHang)){
//				shareImageLabelOneList.add(shareImageLabel);
//			}else if("2".equals(imageLabelHang)){
//				shareImageLabelTwoList.add(shareImageLabel);
//			}else if("3".equals(imageLabelHang)){
//				shareImageLabelThreeList.add(shareImageLabel);
//			}else if("4".equals(imageLabelHang)){
//				shareImageLabelFourList.add(shareImageLabel);
//			}else if("5".equals(imageLabelHang)){
//				shareImageLabelFiveList.add(shareImageLabel);
//			}
//		}
//		return Msg.success().add("list", shareImageLabelList);
//	}
	

//	
//	/**4.0	onuse	20191225	check
//	 * MlbackAreafreight	delete
//	 * @param id
//	 */
//	@RequestMapping(value="/delete",method=RequestMethod.POST)
//	@ResponseBody
//	public Msg delete(@RequestBody MlbackAreafreight mlbackAreafreight){
//		//接收id信息
//		int areafreightIdInt = mlbackAreafreight.getAreafreightId();
//		mlbackAreafreightService.deleteByPrimaryKey(areafreightIdInt);
//		return Msg.success().add("resMsg", "delete success");
//	}
//	
//	
//	/**
//	 * 5.0	onuse	20191225	check
//	 * 查看单条MlbackAreafreight详情
//	 * @param MlbackAreafreight
//	 * @return 
//	 */
//	@RequestMapping(value="/getOneMlbackAreafreightDetail",method=RequestMethod.POST)
//	@ResponseBody
//	public Msg getOneMlbackAreafreightDetail(@RequestParam(value = "areafreightId") Integer areafreightId){
//		
//		//接受categoryId
//		MlbackAreafreight mlbackAreafreightReq = new MlbackAreafreight();
//		mlbackAreafreightReq.setAreafreightId(areafreightId);
//		//查询本条
//		List<MlbackAreafreight> mlbackAreafreightResList =mlbackAreafreightService.selectMlbackAreafreightByParam(mlbackAreafreightReq);
//		MlbackAreafreight mlbackAreafreightOne = new MlbackAreafreight();
//		if(mlbackAreafreightResList.size()>0){
//			mlbackAreafreightOne =mlbackAreafreightResList.get(0);
//		}else{
//			mlbackAreafreightOne = null;
//		}
//		return Msg.success().add("resMsg", "查看单条mlbackAreafreight的详情细节完毕")
//					.add("mlbackAreafreightOne", mlbackAreafreightOne);
//	}
	
}
