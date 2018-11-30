package com.eappcat.mail.sender;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConditionalOnProperty(value = "index",prefix = "sender",havingValue = "enabled",matchIfMissing = true)
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "redirect:/sender.html";
    }
}
