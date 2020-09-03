package com.atguigu.dao;

import com.atguigu.bean.ShareImageInfo;

public interface ShareImageInfoMapper {
    int deleteByPrimaryKey(Integer tbShareImageinfoId);

    int insert(ShareImageInfo record);

    int insertSelective(ShareImageInfo record);

    ShareImageInfo selectByPrimaryKey(Integer tbShareImageinfoId);

    int updateByPrimaryKeySelective(ShareImageInfo record);

    int updateByPrimaryKey(ShareImageInfo record);
}