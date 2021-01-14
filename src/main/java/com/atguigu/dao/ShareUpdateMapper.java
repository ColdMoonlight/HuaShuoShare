package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.ShareUpdate;

public interface ShareUpdateMapper {

    int insert(ShareUpdate record);
    
    ShareUpdate selectByPrimaryKey(Integer tbShareUpdateId);

    int updateByPrimaryKey(ShareUpdate record);
    
    int insertSelective(ShareUpdate record);
    
    int deleteByPrimaryKey(Integer tbShareUpdateId);
    
    int updateByPrimaryKeySelective(ShareUpdate record);

    List<ShareUpdate> selectShareUpdateById(ShareUpdate shareUpdate);
    
	List<ShareUpdate> selectShareUpdatelistAll();

}