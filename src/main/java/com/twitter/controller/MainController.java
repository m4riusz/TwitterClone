package com.twitter.controller;

import com.twitter.route.Route;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = Route.VIEW_USER, method = RequestMethod.GET)
    public String userView() {
        return "user";
    }

    @RequestMapping(value = Route.VIEW_FOLLOWERS, method = RequestMethod.GET)
    public String followersView() {
        return "followers";
    }

    @RequestMapping(value = Route.VIEW_FOLLOWING, method = RequestMethod.GET)
    public String followingView() {
        return "following";
    }

    @RequestMapping(value = Route.VIEW_TWEETS, method = RequestMethod.GET)
    public String latestTweetsView() {
        return "tweets";
    }

    @RequestMapping(value = Route.VIEW_TWEET, method = RequestMethod.GET)
    public String tweetView(@PathVariable int tweetId) {
        return "tweet";
    }


}
