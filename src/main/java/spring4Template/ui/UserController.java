package spring4Template.ui;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_VIEW;

@Controller
@RequestMapping(value = "user")
public class UserController {

    static final String USER_LIST_VIEW = "user/user_list";

    @Secured(ROLE_USER_VIEW)
    @Transactional
    @RequestMapping(value = "/list", method = GET)
    public String list() {
        return USER_LIST_VIEW;
    }

}
