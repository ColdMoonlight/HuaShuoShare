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
import com.atguigu.bean.ShareImageLabel;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.service.ShareImageLabelService;
import com.atguigu.utils.DateUtil;

@Controller
@RequestMapping("/ShareImageLabel")
public class ShareImageLabelController {
	
	@Autowired
	ShareImageLabelService shareImageLabelService;
	
	/**
	 * 1.0	onuse	20191225	检查
	 * to分类MlbackAreafreight列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toImageLabelPage")
	public String toImageLabelPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/imageLabelPage";
		}
	}
	
	/**
	 * 1.0	onuse	20191225	检查
	 * to分类MlbackAreafreight列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toImageLabelUploadPage")
	public String toImageLabelUploadPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/imageLabelUploadPage";
		}
	}
	
	/**
	 * 1.0	onuse	20191225	检查
	 * to分类MlbackAreafreight列表页面
	 * @param jsp
	 * @return 
	 * */
	@RequestMapping("/toImageLabelDownloadPage")
	public String toImageLabelDownloadPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/imageLabelDownloadPage";
		}
	}
	
	/**2.0	onuse	20191225	检查
	 * 分类MlbackAreafreight列表分页list数据
	 * @param pn
	 * @return
	 */
	@RequestMapping(value="/getShareImageLabelAll")
	@ResponseBody
	public Msg getShareImageLabelAll(HttpSession session) {

		List<ShareImageLabel> shareImageLabelList = shareImageLabelService.selectImageLabelAll();
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
		return Msg.success().add("list", shareImageLabelList);
	}
	
	/**3.0	onuse	20191225	检查
	 * MlbackAreafreight	save
	 * @param MlbackAreafreight
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSelective(HttpServletResponse rep,HttpServletRequest res,@RequestBody ShareImageLabel shareImageLabel){
		//接受参数信息
		System.out.println("shareImageLabel:"+shareImageLabel);
		//取出id
		Integer imageLabelId = shareImageLabel.getImageLabelId();
		String nowTime = DateUtil.strTime14s();
		shareImageLabel.setImageLabelCreatetime(nowTime);
		if(imageLabelId==null){
			//无id，insert
			shareImageLabelService.insertSelective(shareImageLabel);
			//System.out.println("后台操作:insert,mlbackAreafreight,success+intResult："+intResult);
			return Msg.success().add("resMsg", "插入成功").add("shareImageLabel", shareImageLabel);
		}else{
			//有id，update
			shareImageLabelService.updateByPrimaryKeySelective(shareImageLabel);
			//System.out.println("后台操作:update,mlbackAreafreight,success+intResult："+intResult);
			return Msg.success().add("resMsg", "更新成功").add("shareImageLabel", shareImageLabel);
		}		
	}
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
