package cn.yan.study.websocket.mychat.service.impl;

import cn.yan.study.websocket.mychat.entity.MyChatUser;
import cn.yan.study.websocket.mychat.mapper.MyChatUserMapper;
import cn.yan.study.websocket.mychat.service.IMyChatUserService;
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
public class MyChatUserServiceImpl extends ServiceImpl<MyChatUserMapper, MyChatUser> implements IMyChatUserService {

}
