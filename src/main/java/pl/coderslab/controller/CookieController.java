package pl.coderslab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CookieController {

    @GetMapping("add-cookies")
    @ResponseBody
    public String addCookies(HttpServletResponse response){
        Cookie user = new Cookie("user", "Jan");
        user.setMaxAge(10*60);
        Cookie uid = new Cookie("uid", "ccb1b154-c4ff");
        uid.setMaxAge(24*60*60*10);
        Cookie IDE = new Cookie("IDE", "IntelliJ");
        IDE.setMaxAge(24*60*60*30);
        response.addCookie(user);
        response.addCookie(uid);
        response.addCookie(IDE);
        return "cookies have been added";
    }

    //1 sposób

  /*  @GetMapping("all-cookies")
    public String allCookies(@CookieValue("user") Cookie userCookie, @CookieValue("uid") Cookie uidCookie, @CookieValue("IDE") Cookie ideCookie, Model model){
        List<Cookie> listOfCookies = List.of(userCookie,uidCookie,ideCookie);
        for(Cookie c:listOfCookies){
            System.out.println(c);
        }
        model.addAttribute("listOfCookies", listOfCookies);
        return "allCookies";


    }
    */


    //2 sposób

    @GetMapping("all-cookies")
    public String allCookies(HttpServletRequest request, Model model){
        List<Cookie> listOfCookies = new ArrayList<>();
        listOfCookies.add(WebUtils.getCookie(request,"user"));
        listOfCookies.add(WebUtils.getCookie(request,"uid"));
        listOfCookies.add(WebUtils.getCookie(request,"IDE"));
        model.addAttribute("listOfCookies", listOfCookies);
        return "allCookies";
    }
}
