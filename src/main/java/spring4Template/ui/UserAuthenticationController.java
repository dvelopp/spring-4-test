package spring4Template.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserAuthenticationController {

    @RequestMapping("/user")
    public @ResponseBody Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/login", method = GET)
    public String index() {
        return "login";
    }

}
