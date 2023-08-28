package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class HeaderController {

    @GetMapping("/showUserAgent")
//    @ResponseBody
    public String userAgent(Model model, @RequestHeader("user-agent") String userAgent) {
        model.addAttribute("userAgent", userAgent);
//        return "user-agent = "  + userAgent;
        return "userAgent";
    }
}
