package my.petshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import my.petshop.MyPetShop;
import my.petshop.common.MyConstantes;

@RestController
public class LoginService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyPetShop.class);
	
    /**
     * POST /login  --> Check the couple login/pwd does exist
     * That is only a MOCK-UP
     */
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public UserHolder authenticate(@RequestBody UserHolder pUserHolder) {

   		LOGGER.debug("In authenticate");	
    	
    	if (pUserHolder.getUserLogin().equalsIgnoreCase(MyConstantes.ERIC)) {
    		pUserHolder.setProfile(MyConstantes.PROFILE_ADMINISTRATOR);
    		pUserHolder.setUserName(MyConstantes.ERIC);
    	} else if (pUserHolder.getUserLogin().equalsIgnoreCase(MyConstantes.ADMIN)) {
    		pUserHolder.setProfile(MyConstantes.PROFILE_ADMINISTRATOR);
    		pUserHolder.setUserName(MyConstantes.ADMIN);
    	} else if (pUserHolder.getUserLogin().equalsIgnoreCase(MyConstantes.GUEST)) {
    		pUserHolder.setProfile(MyConstantes.PROFILE_GUEST);
    		pUserHolder.setUserName(MyConstantes.GUEST);
    	} else {
    		pUserHolder.setProfile(MyConstantes.PROFILE_UNKNOWN_USER);
    	}
    	LOGGER.debug("Checked User : " + pUserHolder);
    	return pUserHolder;
    }


}
