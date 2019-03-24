package cn.yan.study.springboot.service;

import cn.yan.study.springboot.bean.UserInfoDto;
import cn.yan.study.springboot.domain.Group;
import cn.yan.study.springboot.param.LoginParam;
import cn.yan.study.springboot.param.RegisterParam;
import cn.yan.study.springboot.result.BaseResult;

import java.util.List;

/** 用户服务
 * Created by gentlemen_yan on 2019/3/23.
 */
public interface UserService {

    /**
     * 用户注册
     */
    BaseResult register(RegisterParam param);

    /**
     * 登录
     */
    BaseResult login(LoginParam param);

    /**
     * 退出登录
     */
    BaseResult loginOut();

    /**
     * 获取自己得所有好友分组
     */
    BaseResult<List<Group>> getGroupList();

    /**
     * 获取自己分组得好友列表
     */
    BaseResult<List<UserInfoDto>> getGroupFriendList(long groupId);

    /**
     * 获取自己得所有好友列表
     */
    BaseResult<List<UserInfoDto>> getAllFriendList();

    /**
     * 将某个人加入黑名单
     */
    BaseResult addUserToBlackList(long userId);

    /**
     * 拒收某个人得消息
     */
    BaseResult refuseUserMsg(long userId);
}
