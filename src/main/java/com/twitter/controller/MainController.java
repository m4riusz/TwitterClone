package com.twitter.controller;

import com.twitter.route.Route;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mariusz on 09.02.16.
 */
@Controller
public class MainController {

    @RequestMapping(value = Route.VIEW_MAIN, method = RequestMethod.GET)
    public String mainView() {
        return "index";
    }

    @RequestMapping(value = Route.VIEW_USERS, method = RequestMethod.GET)
    public String usersView() {
        return "users";
    }
}
