package com.example.demo.controller;

import com.example.demo.pojo.ResponseBo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/login")
    private String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    private ResponseBo login(String username,String password){
        //登录需要一个token对象，根据你的用户名密码生成token对象
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try{
            //根据这个方法是否产生异常   判断登录认证
            subject.login(usernamePasswordToken);

        }catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }


        return null;
    }
}
