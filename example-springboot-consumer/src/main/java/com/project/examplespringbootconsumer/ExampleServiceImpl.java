package com.project.examplespringbootconsumer;

import com.project.common.model.User;
import com.project.common.service.UserService;
import com.project.hurpcspringbootstarter.annotation.RpcReference;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test() {
        User user = new User();
        user.setName("yupi");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }

}
