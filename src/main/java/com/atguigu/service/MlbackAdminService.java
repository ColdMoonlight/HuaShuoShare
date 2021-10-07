package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MlbackAdmin;
import com.atguigu.dao.MlbackAdminMapper;


@Service
public class MlbackAdminService {
	
	
	@Autowired
	MlbackAdminMapper mlbackAdminMapper;

	/**
	 * @author Shinelon
	 * @param MlbackAdmin
	 * @exception 新增单条用户信息
	 * 
	 * */
	public int insertSelective(MlbackAdmin crmAdmin) {
		int intReslut = mlbackAdminMapper.insertSelective(crmAdmin);
		return intReslut;
	}
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 更新用户信息
	 * 
	 * */
	public int updateByPrimaryKeySelective(MlbackAdmin crmAdmin) {
		int intReslut = mlbackAdminMapper.updateByPrimaryKeySelective(crmAdmin);
		return intReslut;
	}
	/**
	 * @author Shinelon
	 * @param MlbackAdmin
	 * @exception 根据条件查询用户信息
	 * 
	 * */
	public List<MlbackAdmin> selectMlbackAdminByParameter(MlbackAdmin crmAdmin) {
		List<MlbackAdmin> crmAdminList = mlbackAdminMapper.selectMlbackAdminByParameter(crmAdmin);
		return crmAdminList;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查询单条用户信息
	 * 
	 * */
	public MlbackAdmin selectByPrimaryKey(Integer id) {
		MlbackAdmin crmAdmin = mlbackAdminMapper.selectByPrimaryKey(id);
		return crmAdmin;
	}
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 获取用户分页信息
	 * 
	 * */
	public List<MlbackAdmin> selectMlbackAdminByPage() {
		List<MlbackAdmin> crmAdminList = mlbackAdminMapper.selectMlbackAdminByPage();
		return crmAdminList;
	}
	
	/**
	 * @author Shinelon
	 * @param MlbackAdmin
	 * @exception 查看用户信息是否存在
	 * 
	 * */
	public List<MlbackAdmin> selectMlbackAdmin(MlbackAdmin mlbackAdmin) {
		List<MlbackAdmin>  MlbackAdminList = mlbackAdminMapper.selectMlbackAdmin(mlbackAdmin);
		return MlbackAdminList;
	}
	
	
	/**
	 * @author Shinelon
	 * @param 
	 * @exception 查看全部用户信息
	 * 
	 * */
	public int updateByAdminAccountSelective(MlbackAdmin mlbackAdmin) {
		int intReslut = mlbackAdminMapper.updateByAdminAccountSelective(mlbackAdmin);
		return intReslut;
	}

}
