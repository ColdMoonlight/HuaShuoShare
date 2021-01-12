package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.EmailNoneed;
import com.atguigu.dao.EmailNoneedMapper;

@Service
public class EmailNoneedService {
	
	@Autowired
	EmailNoneedMapper emailNoneedMapper;
	
	/**
	 * @author Shinelon
	 * @param EmailNoneed
	 * @exception add方法用户信息是否存在
	 * 	3.0
	 * */
	public int insertSelective(EmailNoneed EmailNoneed) {
		int intReslut = emailNoneedMapper.insertSelective(EmailNoneed);
		return intReslut;
	}

	/**
	 * @author
	 * @param 
	 * @exception 删除本条信息
	 * 	3.0
	 * */
	public int deleteByPrimaryKey(int CatalogId) {
		int  intReslut = emailNoneedMapper.deleteByPrimaryKey(CatalogId);
		return intReslut;
	}

	/**
	 * @author	3.0
	 * @param 
	 * @exception 更新本条信息
	 * */
	public int updateByPrimaryKeySelective(EmailNoneed EmailNoneed) {
		int  intReslut = emailNoneedMapper.updateByPrimaryKeySelective(EmailNoneed);
		return intReslut;
	}

	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * */
	public List<EmailNoneed> selectALl(EmailNoneed EmailNoneed) {
		List<EmailNoneed>  EmailNoneedList = emailNoneedMapper.selectALl(EmailNoneed);
		return EmailNoneedList;
	}
	
//	/**
//	 * @author Shinelon
//	 * @param 
//	 * @exception 查看全部用户信息
//	 * */
//	public List<EmailNoneed> selectByEmail(EmailNoneed EmailNoneed) {
//		List<EmailNoneed>  EmailNoneedList = emailNoneedMapper.selectByEmail(EmailNoneed);
//		return EmailNoneedList;
//	}

}
