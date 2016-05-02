package spring4Template.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring4Template.service.AuthProvider;
import spring4Template.ws.schema.NavigationResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static spring4Template.domain.entities.UserAuthorities.ROLE_CONFIGURATION_VIEW;

@RestController
@RequestMapping("ws/navigation/attributes")
public class NavigationWebService {

    @Autowired private AuthProvider authProvider;

    @RequestMapping(method = GET)
    public NavigationResponse attributes() {
        NavigationResponse response = new NavigationResponse();
        response.setHasConfigurationViewAccess(authProvider.hasRole(ROLE_CONFIGURATION_VIEW));
        return response;
    }

}
