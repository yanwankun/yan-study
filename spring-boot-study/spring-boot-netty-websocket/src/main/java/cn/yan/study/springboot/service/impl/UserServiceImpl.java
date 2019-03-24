package cn.yan.study.springboot.service.impl;

import cn.yan.study.springboot.bean.UserInfoDto;
import cn.yan.study.springboot.domain.Group;
import cn.yan.study.springboot.domain.User;
import cn.yan.study.springboot.param.LoginParam;
import cn.yan.study.springboot.param.RegisterParam;
import cn.yan.study.springboot.result.BaseResult;
import cn.yan.study.springboot.service.UserService;
import cn.yan.study.springboot.store.UserRedisUtils;
import cn.yan.study.springboot.utils.SHA256Util;
import cn.yan.study.springboot.utils.YanStringUtils;

import java.util.Date;
import java.util.List;

/** 用户服务实现
 * Created by gentlemen_yan on 2019/3/23.
 */
public class UserServiceImpl implements UserService {

    private static long user_count_base = 100000L;

    @Override
    public BaseResult register(RegisterParam param) {
        long userId = UserRedisUtils.getExistUserCount();
        String accountNo = String.valueOf(user_count_base + userId);
        String salt = YanStringUtils.getRandomStr(20);
        String passWd = SHA256Util.getSHA256StrJava(param.getPassWd() + salt);
        Date now = new Date();
        User user = User.builder()
                .id(userId)
                .loginName(accountNo)
                .nickName(param.getNickName())
                .passWd(passWd)
                .salt(salt)
                .registerTime(now)
                .build();
        UserRedisUtils.saveAccountInfo(user);
        return new BaseResult<String>().makeSuccessResult(accountNo);
    }

    @Override
    public BaseResult login(LoginParam param) {
        BaseResult result = new BaseResult();
        User existUser = UserRedisUtils.getExistUser(param.getLoginName());
        if (existUser == null) {
            return result.makeFailedResult("用户信息不存在");
        }

        String pwd = SHA256Util.getSHA256StrJava(param.getPassWd() + existUser.getSalt());
        if (!pwd.equals(existUser.getPassWd())) {
            return result.makeFailedResult("账号密码错误");
        }


        return result.makeSuccessResult();
    }

    @Override
    public BaseResult loginOut() {
        return null;
    }

    @Override
    public BaseResult<List<Group>> getGroupList() {
        return null;
    }

    @Override
    public BaseResult<List<UserInfoDto>> getGroupFriendList(long groupId) {
        return null;
    }

    @Override
    public BaseResult<List<UserInfoDto>> getAllFriendList() {
        return null;
    }

    @Override
    public BaseResult addUserToBlackList(long userId) {
        return null;
    }

    @Override
    public BaseResult refuseUserMsg(long userId) {
        return null;
    }
}
