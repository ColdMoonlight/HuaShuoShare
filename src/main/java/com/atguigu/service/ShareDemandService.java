package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.ShareDemand;
import com.atguigu.dao.ShareDemandMapper;

@Service
public class ShareDemandService {
	
	@Autowired
	ShareDemandMapper shareDemandMapper;
	
	/**
	 * @author Shinelon
	 * @param ShareDemand
	 * @exception add方法
	 * */
	public int insertSelective(ShareDemand shareDemand) {
		int intReslut = shareDemandMapper.insertSelective(shareDemand);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareDemand
	 * @exception	Demand方法
	 * */
	public int updateByPrimaryKeySelective(ShareDemand shareDemand) {
		int intReslut = shareDemandMapper.updateByPrimaryKeySelective(shareDemand);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareDemand
	 * @exception	Demand方法
	 * */
	public void deleteByPrimaryKey(Integer imageLabelId) {
		shareDemandMapper.deleteByPrimaryKey(imageLabelId);
	}
	
	/**
	 * @author Shinelon
	 * @param ShareDemand
	 * @exception	selectShareDemandlistAll方法
	 * */
	public List<ShareDemand> selectShareDemandById(ShareDemand shareDemand) {
		List<ShareDemand> shareDemandList = shareDemandMapper.selectShareDemandById(shareDemand);
		return shareDemandList;
	}

	/**
	 * @author Shinelon
	 * @param ShareDemand
	 * @exception	selectShareDemandlistAll方法
	 * */
	public List<ShareDemand> selectShareDemandlistAll() {
		List<ShareDemand> shareDemandList = shareDemandMapper.selectShareDemandlistAll();
		return shareDemandList;
	}

}
