package com.bytebreeze.usercenterboot.service;

import com.bytebreeze.usercenterboot.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddUser() {
        //测试插入
        User user = new User();
        user.setUsername("maka");
        user.setAccount("123456");
        user.setPassword("123456");
        user.setAvatar("https://baomidou.com/assets/asset.cIbiVTt_.svg");
        user.setGender(1);
        user.setPhone("10096");
        user.setEmail("2022@qq.com");
        user.setStatus(0);
        user.setIsDelete(0);
        boolean save = userService.save(user);
        System.out.println(user);
        System.out.println(user.getId());
        Assertions.assertTrue(save);

    }

    @Test
    void testUserRegister() {
        //测试注册功能

        //1.测试非空
        String account = "maka";
        String password = "";
        String checkPassword = "123456";
        long result = userService.userRegister(account, password, checkPassword);
        //account = "maka", password = null, checkPassword = "123456"
        Assertions.assertEquals(-1, result);

        //2.测试账号不小于4位
        account = "dog";
        password = "123456";
        result = userService.userRegister(account, password, checkPassword);
        //account = "dog", password = "123456", checkPassword = "123456"
        Assertions.assertEquals(-1, result);

        //3.测试密码不小于8位
        account = "maka";
        result = userService.userRegister(account, password, checkPassword);
        //account = "maka", password = "123456", checkPassword = "123456"
        Assertions.assertEquals(-1, result);

        //4.测试账号不包含特殊字符
        account = "maka baka";
        password = "12345678";
        checkPassword = "12345678";
        result = userService.userRegister(account, password, checkPassword);
        //account = "maka baka", password = "12345678", checkPassword = "12345678"
        Assertions.assertEquals(-1, result);

        //5.测试密码和校验密码
        account = "makabaka";
        checkPassword = "123456789";
        result = userService.userRegister(account, password, checkPassword);
        //account = "makabaka", password = "12345678", checkPassword = "123456789"
        Assertions.assertEquals(-1, result);

        //6.测试账号重复
        account = "789789";
        checkPassword = "12345678";
        result = userService.userRegister(account, password, checkPassword);
        //account = "789789, password = "12345678", checkPassword = "12345678"
        Assertions.assertEquals(-1, result);

        //插入
        account = "makabaka";
        result = userService.userRegister(account, password, checkPassword);
        //account = "makabaka", password = "12345678", checkPassword = "12345678"
        Assertions.assertTrue(result > 0);
    }

}
