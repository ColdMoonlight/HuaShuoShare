package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.ShareVideoInfo;
import com.atguigu.dao.ShareVideoInfoMapper;

@Service
public class ShareVideoInfoService {
	
	@Autowired
	ShareVideoInfoMapper shareVideoInfoMapper;
	
	/**
	 * @author Shinelon
	 * @param ShareVideoInfo
	 * @exception add方法
	 * */
	public int insertSelective(ShareVideoInfo shareVideoInfo) {
		int intReslut = shareVideoInfoMapper.insertSelective(shareVideoInfo);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareVideoInfo
	 * @exception	update方法
	 * */
	public int updateByPrimaryKeySelective(ShareVideoInfo shareVideoInfo) {
		int intReslut = shareVideoInfoMapper.updateByPrimaryKeySelective(shareVideoInfo);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareVideoInfo
	 * @exception	update方法
	 * */
	public void deleteByPrimaryKey(Integer VideoLabelId) {
		shareVideoInfoMapper.deleteByPrimaryKey(VideoLabelId);
	}
	
	/**
	 * @author Shinelon
	 * @param ShareVideoInfo
	 * @exception	selectShareVideoInfolistByPid方法
	 * */
	public List<ShareVideoInfo> selectShareVideoInfolistByPid(ShareVideoInfo shareVideoInfo) {
		List<ShareVideoInfo> shareVideoInfoList = shareVideoInfoMapper.selectShareVideoInfolistByPid(shareVideoInfo);
		return shareVideoInfoList;
	}

	/**
	 * @author Shinelon
	 * @param ShareVideoInfo
	 * @exception	selectShareVideoInfolistAll方法
	 * */
	public List<ShareVideoInfo> selectShareVideoInfolistAll() {
		List<ShareVideoInfo> shareVideoInfoList = shareVideoInfoMapper.selectShareVideoInfolistAll();
		return shareVideoInfoList;
	}

	public List<ShareVideoInfo> selectShareVideoInfoById(ShareVideoInfo shareVideoInfoReq) {
		List<ShareVideoInfo> shareVideoInfoList = shareVideoInfoMapper.selectShareVideoInfoById(shareVideoInfoReq);
		return shareVideoInfoList;
	}
	
}
