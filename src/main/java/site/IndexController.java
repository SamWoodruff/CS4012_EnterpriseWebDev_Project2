package site;

import POJOs.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Map<String, Object> model)
    {
       return "redirect:/Register";
    }
}
