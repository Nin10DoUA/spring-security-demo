package com.mycompany.securitydemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome()    {
        return "home";
    }

    @RequestMapping(value = "/managers", method = RequestMethod.GET)
    public String showManagersPage()    {
        return "managers";
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public String showAdminsPage()    {
        return "admins";
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String showDeniedPage()    {
        return "access-denied";
    }

}
