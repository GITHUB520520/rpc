package com.project.examplespringbootprovider;

import com.project.common.model.User;
import com.project.common.service.UserService;
import com.project.hurpcspringbootstarter.annotation.RpcService;
import org.springframework.stereotype.Service;

@RpcService
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println(user.getName());
        return user;
    }
}
