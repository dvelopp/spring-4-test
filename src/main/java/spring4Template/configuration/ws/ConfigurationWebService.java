package spring4Template.configuration.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring4Template.system.service.AuthProvider;
import spring4Template.configuration.ws.schema.ConfigurationResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static spring4Template.users.domain.entities.UserAuthorities.ROLE_USER_VIEW;

@RestController
@RequestMapping("ws/configuration/attributes")
public class ConfigurationWebService {

    @Autowired private AuthProvider authProvider;

    @RequestMapping(method = GET)
    public ConfigurationResponse attributes() {
        ConfigurationResponse response = new ConfigurationResponse();
        response.setHasUserViewAccess(authProvider.hasRole(ROLE_USER_VIEW));
        return response;
    }

}
