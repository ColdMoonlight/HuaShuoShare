package com.atguigu.dao;

import java.util.List;
import com.atguigu.bean.EmailNoneed;

public interface EmailNoneedMapper {
    int deleteByPrimaryKey(Integer emailnoneedId);

    int insert(EmailNoneed record);

    int insertSelective(EmailNoneed record);

    EmailNoneed selectByPrimaryKey(Integer emailnoneedId);

    int updateByPrimaryKeySelective(EmailNoneed record);

    int updateByPrimaryKey(EmailNoneed record);
    
    List<EmailNoneed> selectALl(EmailNoneed record);
}