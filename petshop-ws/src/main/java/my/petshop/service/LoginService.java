package my.petshop.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import my.petshop.common.MyConstantes;

@RestController
public class LoginService {
	
	
    /**
     * POST /login  --> Check the couple login/pwd does exist
     * That is only a MOCK-UP
     */
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public UserHolder create(@RequestBody UserHolder pUserHolder) {
    
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
    	System.out.println("Check User : " + pUserHolder);
    	return pUserHolder;
    }


}
