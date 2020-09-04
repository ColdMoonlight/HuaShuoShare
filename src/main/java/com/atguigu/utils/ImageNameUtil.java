package com.atguigu.utils;

public class ImageNameUtil {

	public static String getImageInfofilename(String typeName, String parentIdStr, String parentname) {
		String newfilename = parentIdStr +parentname+typeName+".jpg";
		System.out.println(newfilename);
		return newfilename;
	}

}
