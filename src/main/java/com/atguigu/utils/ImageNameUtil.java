package com.atguigu.utils;

public class ImageNameUtil {

	public static String getImageInfofilename(String typeName, String parentIdStr, String parentname) {
		//String newfilename = parentIdStr +parentname+typeName;
		String newfilename = parentIdStr + parentname + typeName;
		System.out.println(newfilename);
		return newfilename;
	}

}
