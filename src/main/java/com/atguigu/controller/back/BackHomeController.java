package com.atguigu.controller.back;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.MlbackAdmin;
import com.atguigu.common.Msg;

/**
 * HomePage
 * @author
 */
@Controller
@RequestMapping("/BackHome")
public class BackHomeController {
	
	
	/**
	 * zsh 200730
	 * 中控台首页
	 * */
	@RequestMapping("/tologin")
	public String tologin(HttpSession session) throws Exception{
		
		return "back/mlbackAdminLogin";
	}
	
	/**
	 * zsh 200730
	 * 中控台首页
	 * */
	@RequestMapping("/BackHomePage")
	public String backHome(HttpSession session) throws Exception{
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute("AdminUser");
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/mlbackHomePage";
		}
	}

}
