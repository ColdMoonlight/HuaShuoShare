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
import com.atguigu.bean.ShareOperationRecord;
import com.atguigu.common.Const;
import com.atguigu.common.Msg;
import com.atguigu.common.TokenCache;
import com.atguigu.service.MlbackAdminService;
import com.atguigu.service.ShareOperationRecordService;
import com.atguigu.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/MlbackAdmin")
public class MlbackAdminController {
	
	@Autowired
	MlbackAdminService mlbackAdminService;
	
	@Autowired
	ShareOperationRecordService shareOperationRecordService;
	
	/**
	 * 1.0
	 * @author zsh
	 * @param MlbackAdmin
	 * @exception 管理员帐号推出
	 * 20200428
	 * */
	@RequestMapping("/exitIndex")
	public String exitindex(HttpSession session) throws Exception{
		session.removeAttribute(Const.ADMIN_USER);
		session.invalidate();
		return "back/mlbackAdminLogin";
	}
	
	/**
	 * 20211007
	 * MlbackAdmin 首页
	 * */
	@RequestMapping("/ToMlbackAdminPage")
	public String toCrmWebanalyticsPage(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/mlbackAdminPage";
		}
	}
	
	/**
	 * 2.0
	 * @author 20210810
	 * @param MlbackAdmin
	 * @exception 创建新用户
	 * */
	@RequestMapping(value="/Add",method=RequestMethod.POST)
	@ResponseBody
	public Msg insertSelective(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestBody MlbackAdmin crmAdminReq){
		//接收参数信息 
		
		//先查询账号是否重复
		MlbackAdmin crmAdminRepeate = new MlbackAdmin();
		crmAdminRepeate.setAdminAccount(crmAdminReq.getAdminAccount());
		List<MlbackAdmin> crmAdminList = mlbackAdminService.selectMlbackAdminByParameter(crmAdminRepeate);
		if(crmAdminList.size() >0){
			return Msg.fail().add("resMsg", "账号重复");
		}
		String nowTime = DateUtil.strTime14s();
		crmAdminReq.setAdminCreatetime(nowTime);
		crmAdminReq.setAdminMotifytime(nowTime);
		mlbackAdminService.insertSelective(crmAdminReq);
		
		if(crmAdminReq.getAdminId() != null){
			return Msg.success().add("resMsg", "创建成功");
		}else{
			return Msg.fail().add("resMsg", "系统错误，请联系管理员");
		}
	}
	
	/**
	 * 2.0
	 * @author 20210810
	 * @param MlbackAdmin
	 * @exception 创建新用户
	 * */
	@RequestMapping(value="/Delete",method=RequestMethod.POST)
	@ResponseBody
	public Msg delete(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestBody MlbackAdmin crmAdminReq){
		//接收参数信息 
		String nowTime = DateUtil.strTime14s();
		
		MlbackAdmin crmAdminUpdate = new MlbackAdmin();
		crmAdminUpdate.setAdminMotifytime(nowTime);
		crmAdminUpdate.setAdminStatus(0);//失效
		crmAdminUpdate.setAdminId(crmAdminReq.getAdminId());
		mlbackAdminService.updateByPrimaryKeySelective(crmAdminUpdate);
		
		return Msg.success().add("resMsg", "删除成功");
	}
	
	/**
	 * 2.0
	 * @author 20210810
	 * @param MlbackAdmin
	 * @exception 更新用户信息
	 * */
	@RequestMapping(value="/Update",method=RequestMethod.POST)
	@ResponseBody
	public Msg updateByPrimaryKeySelective(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestBody MlbackAdmin crmAdminReq){
		//接收参数信息 
		String nowTime = DateUtil.strTime14s();
		crmAdminReq.setAdminMotifytime(nowTime);
		mlbackAdminService.updateByPrimaryKeySelective(crmAdminReq);
		return Msg.success().add("resMsg", "修改成功");
	}
	
	/**
	 * 2.0
	 * @author 20210810
	 * @param MlbackAdmin
	 * @exception 查看单个用户
	 * */
	@RequestMapping(value="/GetOneAdminDetailById",method=RequestMethod.POST)
	@ResponseBody
	public Msg GetOneAdminDetailById(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestBody MlbackAdmin crmAdminReq){
		
		//通过id 查询单个用户详情
		MlbackAdmin crmAdminRes = mlbackAdminService.selectByPrimaryKey(crmAdminReq.getAdminId());
		return Msg.success().add("crmAdmin", crmAdminRes);
		
	}
	
	/**
	 * 2.0
	 * @author 20210810
	 * @param CrmAdmin
	 * @exception 获取用户分页列表
	 * */
	@RequestMapping(value="/GetMlbackAdminByPage",method=RequestMethod.POST)
	@ResponseBody
	public Msg getcrmCatalogByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpSession session) {

		int PagNum = Const.PAGE_NUM_DEFAULT;
		PageHelper.startPage(pn, PagNum);
		List<MlbackAdmin> crmAdminList = mlbackAdminService.selectMlbackAdminByPage();
		PageInfo<MlbackAdmin> page = new PageInfo<MlbackAdmin>(crmAdminList, PagNum);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 2.0
	 * @author 20200908zsh
	 * @param MlbackAdmin
	 * @exception 管理员帐号登陆
	 * */
	@RequestMapping(value="/CheakAdminUser",method=RequestMethod.POST)
	@ResponseBody
	public Msg checkuser(HttpServletResponse rep,HttpServletRequest res,HttpSession session,
			@RequestBody MlbackAdmin MlbackAdminReq){
		//接收参数信息 
		MlbackAdmin mlbackAdminGet = new MlbackAdmin();
		mlbackAdminGet.setAdminAccount(MlbackAdminReq.getAdminAccount());
		List<MlbackAdmin> mlbackAdminGetresList = mlbackAdminService.selectMlbackAdmin(mlbackAdminGet);
		if(!(mlbackAdminGetresList.size()>0)){
			return Msg.fail().add("resMsg", "账号不存在");
		}
		mlbackAdminGet.setAdminPassword(MlbackAdminReq.getAdminPassword());
		List<MlbackAdmin> MlbackAdminListNameAndPwd = mlbackAdminService.selectMlbackAdmin(mlbackAdminGet);
		if(MlbackAdminListNameAndPwd.size()>0){
			//将登陆状态放入session对象
			session.setAttribute(Const.ADMIN_USER, MlbackAdminListNameAndPwd.get(0));
			System.out.println("CheakAdminUser--mlbackAdminGet:"+MlbackAdminListNameAndPwd.get(0).toString());
			TokenCache.setKey(Const.TOKEN_PREFIX+MlbackAdminReq.getAdminAccount(), "String");
			
			
			String nowTime = DateUtil.strTime14s();
			ShareOperationRecord shareOperationRecord = new ShareOperationRecord();
			shareOperationRecord.setOperationRecordAdminid(mlbackAdminGetresList.get(0).getAdminId());
			shareOperationRecord.setOperationRecordAdminName(mlbackAdminGetresList.get(0).getAdminAccount()+"--"+mlbackAdminGetresList.get(0).getAdminOperatername());
			shareOperationRecord.setOperationRecordDataType(0);
			shareOperationRecord.setOperationRecordDataName("登录成功");
			shareOperationRecord.setOperationRecordDesc("登录");
			shareOperationRecord.setOperationRecordCreatetime(nowTime);
			shareOperationRecordService.insertSelective(shareOperationRecord);
			
			return Msg.success().add("resMsg", "登陆成功");
		}else{
			return Msg.fail().add("resMsg", "密码错误登录失败");
		}
	}
	
	
	/**
	 * 3.0
	 * @author zsh
	 * @param MlbackAdmin
	 * @exception 管理员帐号修改密码
	 * 20200429
	 * */
	@RequestMapping(value="/UpdateAdminUserInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg UpdateAdminUserInfo(HttpServletResponse rep,HttpServletRequest res,HttpSession session,@RequestBody MlbackAdmin MlbackAdminReq){
		//1接收参数
		//2用账户+旧密码查账户(查到,update新密码,没查到,旧密码不对)
		MlbackAdmin mlbackAdminGet = new MlbackAdmin();
		mlbackAdminGet.setAdminAccount(MlbackAdminReq.getAdminAccount());
		List<MlbackAdmin> mlbackAdminGetresList = mlbackAdminService.selectMlbackAdmin(mlbackAdminGet);
		if(!(mlbackAdminGetresList.size()>0)){
			return Msg.fail().add("resMsg", "账号不存在");
		}
		mlbackAdminGet.setAdminPassword(MlbackAdminReq.getAdminPassword());
		List<MlbackAdmin> MlbackAdminListNameAndPwd = mlbackAdminService.selectMlbackAdmin(mlbackAdminGet);
		if(MlbackAdminListNameAndPwd.size()>0){
			//mlbackAdminGet
			mlbackAdminGet.setAdminPassword(MlbackAdminReq.getAdminOperatername());
			mlbackAdminService.updateByAdminAccountSelective(mlbackAdminGet);
			System.out.println("UpdateAdminUserInfo--mlbackAdminGet:"+MlbackAdminListNameAndPwd.get(0).toString());
			session.setAttribute(Const.ADMIN_USER, MlbackAdminListNameAndPwd.get(0));
			return Msg.success().add("resMsg", "密码修改成功");
		}else{
			return Msg.fail().add("resMsg", "旧密码错误");
		}
	}

	/**
	 * 4.0
	 * @author zsh	20200429
	 * @param MlbackAdmin
	 * @exception adminIfLogin
	 * */
	@RequestMapping(value="/adminIfLogin",method=RequestMethod.POST)
	@ResponseBody
	public Msg adminIfLogin(HttpServletResponse rep,HttpServletRequest res,HttpSession session){
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin!=null){
			System.out.println("mlbackAdmin:"+mlbackAdmin.toString());
			return Msg.success().add("resMsg", "登陆中"+mlbackAdmin.toString());
		}else{
			return Msg.fail().add("resMsg", "无登录信息");
		}
	}
	
}
