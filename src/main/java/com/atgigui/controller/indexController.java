package com.atgigui.controller;

import com.atgigui.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class indexController {
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String main(User user, HttpSession session, Model model){

        // 需要判断登入才能访问
        if(StringUtils.hasLength(user.getUsername()) && "123456".equals(user.getPassword())){
            // 把登入成功的用户保存起来
            session.setAttribute("loginUser", user);
            System.out.println("sadgawegawe");
            // 登入成功重定向到index.html 防止表单重复提交
            return "redirect:/index.html";
        }else{
            model.addAttribute("msg", "账号密码错误");
            System.out.println("afgagwq");
            return "login";
        }
    }

    @GetMapping("/index.html")
    public String mainPage(HttpSession session,Model model){
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null){
            return "index";
        }else{
            model.addAttribute("msg", "请重新登入");
            System.out.println("afgagwq");
            return "login";
        }

    }
}
