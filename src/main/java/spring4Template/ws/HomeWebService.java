package spring4Template.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spring4Template.service.AuthProvider;
import spring4Template.ws.schema.HomeResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_VIEW;

@RestController
@RequestMapping("ws/home")
public class HomeWebService {

    @Autowired private AuthProvider authProvider;

    @RequestMapping(method = GET)
    public @ResponseBody HomeResponse homeData() {
        HomeResponse response = new HomeResponse();
        response.setHasUserViewAccess(authProvider.hasRole(ROLE_USER_VIEW));
        return response;
    }

}
