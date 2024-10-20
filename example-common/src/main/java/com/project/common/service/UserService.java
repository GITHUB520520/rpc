package com.project.common.service;

import com.project.common.model.User;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 获取用户
     *
     * @param user
     * @return
     */
    User getUser(User user);

    default int getNumber(){
        return 1;
    }
}
