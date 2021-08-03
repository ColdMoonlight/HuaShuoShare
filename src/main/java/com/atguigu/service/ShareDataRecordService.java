package com.atguigu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.ShareDataRecord;
import com.atguigu.dao.ShareDataRecordMapper;

@Service
public class ShareDataRecordService {
	
	@Autowired
	ShareDataRecordMapper ShareDataRecordMapper;
	
	/**
	 * @author Shinelon
	 * @param ShareDataRecord
	 * @exception add方法
	 * */
	public int insertSelective(ShareDataRecord ShareDataRecord) {
		int intReslut = ShareDataRecordMapper.insertSelective(ShareDataRecord);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareDataRecord
	 * @exception	Demand方法
	 * */
	public int updateByPrimaryKeySelective(ShareDataRecord ShareDataRecord) {
		int intReslut = ShareDataRecordMapper.updateByPrimaryKeySelective(ShareDataRecord);
		return intReslut;
	}
	
	/**
	 * @author Shinelon
	 * @param ShareDataRecord
	 * @exception	Demand方法
	 * */
	public void deleteByPrimaryKey(Integer imageLabelId) {
		ShareDataRecordMapper.deleteByPrimaryKey(imageLabelId);
	}
	
	/**
	 * @author Shinelon
	 * @param ShareDataRecord
	 * @exception	selectShareDataRecordlistAll方法
	 * */
	public List<ShareDataRecord> selectShareDataRecordById(ShareDataRecord ShareDataRecord) {
		List<ShareDataRecord> ShareDataRecordList = ShareDataRecordMapper.selectShareDataRecordById(ShareDataRecord);
		return ShareDataRecordList;
	}

	/**
	 * @author Shinelon
	 * @param ShareDataRecord
	 * @exception	selectShareDataRecordlistAll方法
	 * */
	public List<ShareDataRecord> selectShareDataRecordGetAll() {
		List<ShareDataRecord> ShareDataRecordList = ShareDataRecordMapper.selectShareDataRecordGetAll();
		return ShareDataRecordList;
	}

}
