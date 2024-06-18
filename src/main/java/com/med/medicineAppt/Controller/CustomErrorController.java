package com.med.medicineAppt.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String notFoundError(@ModelAttribute("status") Object status,
                                @ModelAttribute("message") Object message,
                                Model model) {

        model.addAttribute("status", status != null ? status : "404");
        model.addAttribute("message", message != null ? message : "404 Page not found");

        return "error_page";
    }

}
