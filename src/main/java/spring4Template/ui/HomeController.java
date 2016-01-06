package spring4Template.ui;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import static spring4Template.domain.entities.UserAuthorities.ROLE_DEFAULT;

@Controller
public class HomeController {

    @Secured(ROLE_DEFAULT)
    @Transactional
    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

}
