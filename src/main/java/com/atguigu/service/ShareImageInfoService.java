package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.ShareImageInfo;
import com.atguigu.dao.ShareImageInfoMapper;

@Service
public class ShareImageInfoService {
	
	@Autowired
	ShareImageInfoMapper shareImageInfoMapper;
	
	/**
	 * @author Shinelon
	 * @param ShareImageInfo
	 * @exception add方法
	 * */
	public int insertSelective(ShareImageInfo shareImageInfo) {
		int intReslut = shareImageInfoMapper.insertSelective(shareImageInfo);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareImageInfo
	 * @exception	update方法
	 * */
	public int updateByPrimaryKeySelective(ShareImageInfo shareImageInfo) {
		int intReslut = shareImageInfoMapper.updateByPrimaryKeySelective(shareImageInfo);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareImageInfo
	 * @exception	update方法
	 * */
	public void deleteByPrimaryKey(Integer imageLabelId) {
		shareImageInfoMapper.deleteByPrimaryKey(imageLabelId);
	}
	
	/**
	 * @author Shinelon
	 * @param ShareImageInfo
	 * @exception	selectShareImageInfolistByPid方法
	 * */
	public List<ShareImageInfo> selectShareImageInfolistByPid(ShareImageInfo shareImageInfo) {
		List<ShareImageInfo> shareImageInfoList = shareImageInfoMapper.selectShareImageInfolistByPid(shareImageInfo);
		return shareImageInfoList;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareImageInfo
	 * @exception	selectShareImageInfolistAll方法
	 * */
	public List<ShareImageInfo> selectShareImageInfoById(ShareImageInfo shareImageInfo) {
		List<ShareImageInfo> shareImageInfoList = shareImageInfoMapper.selectShareImageInfoById(shareImageInfo);
		return shareImageInfoList;
	}

	/**
	 * @author Shinelon
	 * @param ShareImageInfo
	 * @exception	selectShareImageInfolistAll方法
	 * */
	public List<ShareImageInfo> selectShareImageInfolistAll() {
		List<ShareImageInfo> shareImageInfoList = shareImageInfoMapper.selectShareImageInfolistAll();
		return shareImageInfoList;
	}
	

}
