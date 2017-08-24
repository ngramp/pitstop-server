package com.hawkeye.pitstop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class PitStopListController {
	@RequestMapping(value = "/" )
    public String go(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        String x = sc.getRealPath("/");
        System.out.println(x);
        return "index";
    }
}
