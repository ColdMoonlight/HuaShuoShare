package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.ShareUpdate;
import com.atguigu.dao.ShareUpdateMapper;

@Service
public class ShareUpdateService {
	
	@Autowired
	ShareUpdateMapper shareUpdateMapper;
	
	/**
	 * @author Shinelon
	 * @param ShareUpdate
	 * @exception add方法
	 * */
	public int insertSelective(ShareUpdate shareUpdate) {
		int intReslut = shareUpdateMapper.insertSelective(shareUpdate);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareUpdate
	 * @exception	update方法
	 * */
	public int updateByPrimaryKeySelective(ShareUpdate shareUpdate) {
		int intReslut = shareUpdateMapper.updateByPrimaryKeySelective(shareUpdate);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareUpdate
	 * @exception	update方法
	 * */
	public void deleteByPrimaryKey(Integer imageLabelId) {
		shareUpdateMapper.deleteByPrimaryKey(imageLabelId);
	}
	
	/**
	 * @author Shinelon
	 * @param ShareUpdate
	 * @exception	selectShareUpdatelistAll方法
	 * */
	public List<ShareUpdate> selectShareUpdateById(ShareUpdate shareUpdate) {
		List<ShareUpdate> shareUpdateList = shareUpdateMapper.selectShareUpdateById(shareUpdate);
		return shareUpdateList;
	}

	/**
	 * @author Shinelon
	 * @param ShareUpdate
	 * @exception	selectShareUpdatelistAll方法
	 * */
	public List<ShareUpdate> selectShareUpdatelistAll() {
		List<ShareUpdate> shareUpdateList = shareUpdateMapper.selectShareUpdatelistAll();
		return shareUpdateList;
	}

}
