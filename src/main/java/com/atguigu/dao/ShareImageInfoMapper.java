package com.atguigu.dao;

import com.atguigu.bean.ShareImageInfo;

public interface ShareImageInfoMapper {

    int insert(ShareImageInfo record);

    ShareImageInfo selectByPrimaryKey(Integer tbShareImageinfoId);
    
    int updateByPrimaryKey(ShareImageInfo record);
    
    int insertSelective(ShareImageInfo record);

    int deleteByPrimaryKey(Integer tbShareImageinfoId);

    int updateByPrimaryKeySelective(ShareImageInfo record);
}