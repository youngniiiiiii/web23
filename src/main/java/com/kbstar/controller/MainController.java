package com.kbstar.controller;


import com.kbstar.dto.Cust;
import com.kbstar.service.CustService;
import com.kbstar.util.WeatherUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class MainController {

    @Value("${adminserver}")
    String adminserver;
    @Autowired
    CustService custService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping("/")
    public String main(Model model) throws Exception {
        String result = WeatherUtil.getWeather1("109");
        model.addAttribute("weatherinfo", result);
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("center", "login");
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "index";
    }

    @RequestMapping("/loginimpl")
    public String loginimpl(Model model, String id, String pwd, HttpSession session) throws Exception {
        log.info("----------------" + id + pwd);
        Cust cust = null;
        String nextPage = "loginfail";
        try {
            cust = custService.get(id);
            if (cust != null && encoder.matches(pwd, cust.getPwd())) {
                nextPage = "loginok";
                session.setMaxInactiveInterval(100000);
                session.setAttribute("logincust", cust);
            }
        } catch (Exception e) {
            throw new Exception("시스템 장애 잠시후 다시 로그인 하세요");
        }

        model.addAttribute("center", nextPage);
        return "index";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("center", "register");
        return "index";
    }

    @RequestMapping("/registerimpl")
    public String registerimpl(Model model, Cust cust, HttpSession session) throws Exception {

        try {
            cust.setPwd(encoder.encode(cust.getPwd()));
            custService.register(cust);
//            회원가입 즉시 로그인처리 로그인된 화면 보여주기...
            session.setAttribute("logincust", cust);

        } catch (Exception e) {
            throw new Exception("가입 오류");
        }

        model.addAttribute("rcust", cust);
        model.addAttribute("center", "registerok");
        return "index";
    }

    @RequestMapping("/custinfo")
    public String custinfo(Model model, String id) throws Exception {
        Cust cust = null;
        try {
            cust = custService.get(id);
        } catch (Exception e) {
            throw new Exception("시스템장애");
        }
        model.addAttribute("custinfo", cust);
        model.addAttribute("center", "custinfo");
        return "index";
    }

    @RequestMapping("/custinfoimpl")
    public String custinfoimpl(Model model, Cust cust) throws Exception {

        try {
            cust.setPwd(encoder.encode(cust.getPwd()));
            custService.modify(cust);
        } catch (Exception e) {
            throw new Exception("시스템장애");
        }
        return "redirect:/custinfo?id=" + cust.getId();
    }

    @RequestMapping("/pic")
    public String pic(Model model) {
        model.addAttribute("center", "pic");
        return "index";
    }

    @RequestMapping("/websocket")
    public String websocket(Model model) {
        model.addAttribute("adminserver", adminserver);
        model.addAttribute("center", "websocket");
        return "index";
    }

    @RequestMapping("/cfr1")
    public String cfr1(Model model) {
        model.addAttribute("center", "cfr1");
        return "index";
    }

    @RequestMapping("/cfr2")
    public String cfr2(Model model) {
        model.addAttribute("center", "cfr2");
        return "index";
    }

    @RequestMapping("/ocr1")
    public String ocr1(Model model) {
        model.addAttribute("center", "ocr1");
        return "index";
    }

    @RequestMapping("/ocr2")
    public String ocr2(Model model) {
        model.addAttribute("center", "ocr2");
        return "index";
    }

    @RequestMapping("/chatbot")
    public String chatbot(Model model, HttpSession session) {
        if (session.getAttribute("logincust") == null) {
            return "redirect:/login";
        }
        model.addAttribute("adminserver", adminserver);
        model.addAttribute("center", "chatbot");
        return "index";
    }

    @RequestMapping("/callcenter")
    public String callcenter(Model model, HttpSession session) {
        if (session.getAttribute("logincust") == null) {
            return "redirect:/login";
        }
        model.addAttribute("adminserver", adminserver);
        model.addAttribute("center", "callcenter");
        return "index";
    }
}