package com.atguigu.dao;

import com.atguigu.bean.ShareUpdate;

public interface ShareUpdateMapper {
    int deleteByPrimaryKey(Integer tbShareUpdateId);

    int insert(ShareUpdate record);

    int insertSelective(ShareUpdate record);

    ShareUpdate selectByPrimaryKey(Integer tbShareUpdateId);

    int updateByPrimaryKeySelective(ShareUpdate record);

    int updateByPrimaryKey(ShareUpdate record);
}