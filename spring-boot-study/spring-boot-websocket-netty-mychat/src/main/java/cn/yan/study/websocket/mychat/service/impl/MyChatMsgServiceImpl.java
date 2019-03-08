package cn.yan.study.websocket.mychat.service.impl;

import cn.yan.study.websocket.mychat.entity.MyChatMsg;
import cn.yan.study.websocket.mychat.mapper.MyChatMsgMapper;
import cn.yan.study.websocket.mychat.service.IMyChatMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-03-02
 */
@Service
public class MyChatMsgServiceImpl extends ServiceImpl<MyChatMsgMapper, MyChatMsg> implements IMyChatMsgService {

}
