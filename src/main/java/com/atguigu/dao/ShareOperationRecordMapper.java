package com.atguigu.dao;

import com.atguigu.bean.ShareOperationRecord;

public interface ShareOperationRecordMapper {
    int deleteByPrimaryKey(Integer operationRecordId);

    int insert(ShareOperationRecord record);

    int insertSelective(ShareOperationRecord record);

    ShareOperationRecord selectByPrimaryKey(Integer operationRecordId);

    int updateByPrimaryKeySelective(ShareOperationRecord record);

    int updateByPrimaryKey(ShareOperationRecord record);
}