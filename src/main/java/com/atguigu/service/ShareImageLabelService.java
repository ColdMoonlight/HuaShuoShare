package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.ShareImageLabel;
import com.atguigu.dao.ShareImageLabelMapper;

@Service
public class ShareImageLabelService {
	
	
	@Autowired
	ShareImageLabelMapper shareImageLabelMapper;
	
	/**
	 * @author Shinelon
	 * @param ShareImageLabel
	 * @exception add方法
	 * 
	 * */
	public int insertSelective(ShareImageLabel shareImageLabel) {
		int intReslut = shareImageLabelMapper.insertSelective(shareImageLabel);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareImageLabel
	 * @exception	update方法
	 * */
	public int updateByPrimaryKeySelective(ShareImageLabel shareImageLabel) {
		int intReslut = shareImageLabelMapper.updateByPrimaryKeySelective(shareImageLabel);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareImageLabel
	 * @exception	update方法
	 * */
	public void deleteByPrimaryKey(Integer imageLabelId) {
		shareImageLabelMapper.deleteByPrimaryKey(imageLabelId);
	}

	public List<ShareImageLabel> selectImageLabelAll() {
		List<ShareImageLabel>  shareImageLabelList = shareImageLabelMapper.selectImageLabelAll();
		return shareImageLabelList;
	}

}
