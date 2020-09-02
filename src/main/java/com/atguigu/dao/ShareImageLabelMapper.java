package com.atguigu.dao;

import com.atguigu.bean.ShareImageLabel;

public interface ShareImageLabelMapper {
    int deleteByPrimaryKey(Integer imageLabelId);

    int insert(ShareImageLabel record);

    int insertSelective(ShareImageLabel record);

    ShareImageLabel selectByPrimaryKey(Integer imageLabelId);

    int updateByPrimaryKeySelective(ShareImageLabel record);

    int updateByPrimaryKey(ShareImageLabel record);
}