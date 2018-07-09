package com.auttle.shiro.controller;

import com.auttle.shiro.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    public String login(HttpServletRequest request, User user, Model model){


        return "login";

    }
}
