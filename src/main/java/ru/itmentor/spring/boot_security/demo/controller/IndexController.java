package ru.itmentor.spring.boot_security.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import ru.itmentor.spring.boot_security.demo.configs.exception.LoginException;
import ru.itmentor.spring.boot_security.demo.service.AppService;


@Controller
@RequestMapping("/")
public class IndexController {

    private final AppService appService;

    @Autowired
    public IndexController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("")
    public String welcomePage(Model model, HttpSession session,
                              @SessionAttribute(required = false, name = "Authentication-Exception") LoginException authenticationException,
                              @SessionAttribute(required = false, name = "Authentication-Name") String authenticationName) {
        appService.authenticateOrLogout(model, session, authenticationException, authenticationName);
        return "index";
    }

    @GetMapping("/403")
    public String accessDeniedPage() {
        return "access-denied";
    }

}
