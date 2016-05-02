package spring4Template.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring4Template.configuration.SerializableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Properties;

@RestController
@RequestMapping("/ws/messageBundle/properties")
public class SerializableMessageBundleController {

    @Autowired private SerializableResourceBundleMessageSource messageBundle;

    @RequestMapping(method = RequestMethod.GET)
    public Properties properties(@RequestParam String lang) {
        return messageBundle.getAllProperties(new Locale(lang));
    }

}