package cn.yan.test.spring.boot.database.dao;

import cn.yan.test.spring.boot.database.domain.Message;
import cn.yan.test.spring.boot.database.domain.MessageKey;

public interface MessageMapper {
    int deleteByPrimaryKey(MessageKey key);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(MessageKey key);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
}