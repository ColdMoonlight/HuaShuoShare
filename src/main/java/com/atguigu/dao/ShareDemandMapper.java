package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.ShareDemand;

public interface ShareDemandMapper {
    int deleteByPrimaryKey(Integer tbShareDemandId);

    int insert(ShareDemand record);

    int insertSelective(ShareDemand record);

    ShareDemand selectByPrimaryKey(Integer tbShareDemandId);

    int updateByPrimaryKeySelective(ShareDemand record);

    int updateByPrimaryKey(ShareDemand record);

	List<ShareDemand> selectShareDemandById(ShareDemand shareDemand);

	List<ShareDemand> selectShareDemandlistAll();
}