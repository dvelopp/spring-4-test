package spring4Template.ui;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_DELETE;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_EDIT;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_VIEW;

@Controller
@RequestMapping(value = "user")
public class UserController {

    static final String USER_LIST_VIEW = "user/user_list";
    static final String USER_EDIT_VIEW = "user/user_edit";
    static final String USER_DELETE_VIEW = "user/user_delete";

    @Secured(ROLE_USER_VIEW)
    @Transactional
    @RequestMapping(value = "/list", method = GET)
    public String list() {
        return USER_LIST_VIEW;
    }

    @Secured(ROLE_USER_EDIT)
    @Transactional
    @RequestMapping(value = "/edit", method = GET)
    public String edit() {
        return USER_EDIT_VIEW;
    }

    @Secured(ROLE_USER_DELETE)
    @Transactional
    @RequestMapping(value = "/delete", method = GET)
    public String delete() {
        return USER_DELETE_VIEW;
    }

}
