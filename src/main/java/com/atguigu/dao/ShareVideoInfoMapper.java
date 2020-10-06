package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.ShareVideoInfo;

public interface ShareVideoInfoMapper {

    int insert(ShareVideoInfo record);

    ShareVideoInfo selectByPrimaryKey(Integer tbShareVideoinfoId);

    int updateByPrimaryKey(ShareVideoInfo record);
    
    int insertSelective(ShareVideoInfo record);
    
    int deleteByPrimaryKey(Integer tbShareVideoinfoId);
    
    int updateByPrimaryKeySelective(ShareVideoInfo record);

	List<ShareVideoInfo> selectShareVideoInfolistByPid(ShareVideoInfo shareVideoInfo);

	List<ShareVideoInfo> selectShareVideoInfolistAll();
}