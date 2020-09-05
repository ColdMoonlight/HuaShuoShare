package com.atguigu.utils;

import javax.servlet.http.HttpSession;

import com.atguigu.bean.MlbackAdmin;

public class SessionUtil {

	public static String getAdminInfo(HttpSession session) {
		
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
