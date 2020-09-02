package com.atguigu.dao;

import com.atguigu.bean.ShareImageLabel;

public interface ShareImageLabelMapper {

    int insert(ShareImageLabel record);

    ShareImageLabel selectByPrimaryKey(Integer imageLabelId);

    int updateByPrimaryKey(ShareImageLabel record);
    
    int insertSelective(ShareImageLabel record);
    
    int deleteByPrimaryKey(Integer imageLabelId);
    
    int updateByPrimaryKeySelective(ShareImageLabel record);
    
}