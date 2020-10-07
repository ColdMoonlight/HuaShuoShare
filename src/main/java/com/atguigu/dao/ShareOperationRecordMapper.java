package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.ShareOperationRecord;

public interface ShareOperationRecordMapper {
	
    int insert(ShareOperationRecord record);

    ShareOperationRecord selectByPrimaryKey(Integer operationRecordId);

    int updateByPrimaryKey(ShareOperationRecord record);
    
    //------------------
    int insertSelective(ShareOperationRecord record);
    
    int deleteByPrimaryKey(Integer operationRecordId);
    
    int updateByPrimaryKeySelective(ShareOperationRecord record);

	List<ShareOperationRecord> selectShareOperationRecordByDate(ShareOperationRecord shareOperationRecord);
}