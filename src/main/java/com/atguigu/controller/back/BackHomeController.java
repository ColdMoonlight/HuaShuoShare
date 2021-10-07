package com.atguigu.controller.back;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.atguigu.bean.MlbackAdmin;
import com.atguigu.common.Const;

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
		
		MlbackAdmin mlbackAdmin =(MlbackAdmin) session.getAttribute(Const.ADMIN_USER);
		if(mlbackAdmin==null){
			//SysUsers对象为空
			return "back/mlbackAdminLogin";
		}else{
			return "back/mlbackHomePage";
		}
	}

}
