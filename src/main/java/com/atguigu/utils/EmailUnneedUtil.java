package com.atguigu.utils;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.bean.EmailAddress;
import com.atguigu.bean.EmailNoneed;

public class EmailUnneedUtil {

	public static List<String> removeNoUseEmail(List<String> successList, List<EmailNoneed> emailNoneedList) {
		
		List<String> needList = new ArrayList<String>();
		
		List<String> noNeedList = new ArrayList<String>();
		
		for(EmailNoneed emailNoneed:emailNoneedList){
			String emailNoneedStr = emailNoneed.getEmailnoneedEmail();
			noNeedList.add(emailNoneedStr);
		}
		
		//遍历当前付款的,去无效email中查询,如果查到-跳过.如果查到,跳过.没查到-add进billingList中;
		for(String successEmail:successList){
			Integer ifHave = 0;
			//
			String email = successEmail;
			ifHave = getIfIntoEmail(email,noNeedList);
			if(ifHave>0){
				//包含了
				System.out.println("这个邮箱已经包含了");
			}else{
				needList.add(email);
			}
		}
		
		List<String> resultList = new ArrayList<String>();
		//清理垃圾格式
		resultList = removeUnType(needList);
		
		return resultList;
	}
	
	
	private static List<String> removeUnType(List<String> needList) {
		
		List<String> resEmailListOne = new ArrayList<String>();
		List<String> resEmailListTwo = new ArrayList<String>();
		
		//5.1-清理包含@的到垃圾
		for(String oneEmail1:needList){
			if(oneEmail1.contains("@")){
				//System.out.println("这是正确的邮箱格式");
				resEmailListOne.add(oneEmail1);
			}
		}
		
		//5.2-清理包含qq的到垃圾
		for(String oneEmail2:resEmailListOne){
			if(oneEmail2.contains("qq.")){
				//System.out.println("这是谁的qq邮箱");
			}else if(oneEmail2.contains("QQ.")){
				//System.out.println("这是谁的qq邮箱");
			}else{
				resEmailListTwo.add(oneEmail2);
			}
		}
		
		return resEmailListTwo;
	}


	private static Integer getIfIntoEmail(String email, List<String> noNeedList) {
		Integer ifHave = 0;
		for(String noNeedEmail:noNeedList){
			//如果查到有相同的
			if(noNeedEmail.equals(email)){
				ifHave++;
				break;
			}
		}
		if(ifHave>0){
			return 1;
		}else{
			return 0; 
		}
	}

}
