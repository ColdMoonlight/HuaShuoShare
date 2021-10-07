package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.MlbackAdmin;

public interface MlbackAdminMapper {
	
    int deleteByPrimaryKey(Integer adminId);

    int insert(MlbackAdmin record);

    int insertSelective(MlbackAdmin record);

    MlbackAdmin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(MlbackAdmin record);

    int updateByPrimaryKey(MlbackAdmin record);
    //查询管理员账户
    List<MlbackAdmin> selectMlbackAdmin(MlbackAdmin example);
    
    int updateByAdminAccountSelective(MlbackAdmin record);
    
  //根据条件查询用户
    List<MlbackAdmin> selectMlbackAdminByParameter(MlbackAdmin record);
    
  //获取用户分页列表
    List<MlbackAdmin> selectMlbackAdminByPage();
}