package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.ShareDataRecord;

public interface ShareDataRecordMapper {

    int insert(ShareDataRecord record);

    ShareDataRecord selectByPrimaryKey(Integer datarecordId);

    int updateByPrimaryKey(ShareDataRecord record);
    
    int insertSelective(ShareDataRecord record);
    
    int deleteByPrimaryKey(Integer datarecordId);
    
    int updateByPrimaryKeySelective(ShareDataRecord record);
    
    //查询全部
	List<ShareDataRecord> selectShareDataRecordGetAll();

	List<com.atguigu.bean.ShareDataRecord> selectShareDataRecordById(ShareDataRecord shareDataRecord);
    
}