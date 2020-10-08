package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.ShareOperationRecord;
import com.atguigu.dao.ShareOperationRecordMapper;

@Service
public class ShareOperationRecordService {
	
	@Autowired
	ShareOperationRecordMapper shareOperationRecordMapper;
	
	/**
	 * @author Shinelon
	 * @param shareOperationRecord
	 * @exception add方法
	 * */
	public int insertSelective(ShareOperationRecord shareOperationRecord) {
		int intReslut = shareOperationRecordMapper.insertSelective(shareOperationRecord);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param shareOperationRecord
	 * @exception	update方法
	 * */
	public int updateByPrimaryKeySelective(ShareOperationRecord shareOperationRecord) {
		int intReslut = shareOperationRecordMapper.updateByPrimaryKeySelective(shareOperationRecord);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param shareOperationRecord
	 * @exception	update方法
	 * */
	public void deleteByPrimaryKey(Integer VideoLabelId) {
		shareOperationRecordMapper.deleteByPrimaryKey(VideoLabelId);
	}

	public List<ShareOperationRecord> selectShareOperationRecordByDate(ShareOperationRecord shareOperationRecord) {
		List<ShareOperationRecord> ShareOperationRecordList = shareOperationRecordMapper.selectShareOperationRecordByDate(shareOperationRecord);
		return ShareOperationRecordList;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareOperationRecord
	 * @exception	selectShareOperationRecordByDateAndType方法
	 * */
	public List<ShareOperationRecord> selectShareOperationRecordByDateAndType(ShareOperationRecord shareOperationRecord) {
		List<ShareOperationRecord> ShareOperationRecordList = shareOperationRecordMapper.selectShareOperationRecordByDateAndType(shareOperationRecord);
		return ShareOperationRecordList;
	}

}
