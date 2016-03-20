package my.petshop.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import my.petshop.models.PetDAO;
import my.petshop.models.PetEntity;

@RestController
public class PetShopService {
	

    @Autowired
    public PetDAO petDao;

    /**
     * POST /create  --> Create a new pet and save it in the database.
     */
    @RequestMapping(value = "/pet", method= RequestMethod.POST)
    public String create(@RequestBody PetHolder pPetHolder, HttpServletResponse response) {
    
    	if (pPetHolder == null ||
    			pPetHolder.getList().isEmpty() ||
    			pPetHolder.getList().get(0).getClass().isInstance(PetEntity.class)){
    		//Will return HTTP Code 405
    		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    		System.out.println("Invalid input ["+pPetHolder.getList()+"]");
    		return "KO";
    	}
    		
      try {
        PetEntity pet = pPetHolder.getList().get(0);
        pet = petDao.save(pet);
    } catch (Exception ex) {
	  	  //Will return HTTP Code 500
	  	  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	  	  System.out.println("Exception raised");
	  	  ex.printStackTrace();
    }
      return "OK";
    }

    /**
     * DELETE /delete  --> Delete the pet having the passed id.
     */
    @RequestMapping(value = "/pet/{id}", method= RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id, HttpServletResponse response) {
      try {
    	PetEntity pet = petDao.findById(Long.valueOf(id));
    	if (pet == null){
        	//Will return HTTP Code 404
    		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		System.out.println("No pet found with id ["+id+"]");
    	} else {
    		petDao.delete(pet);
    	}
      }catch (NumberFormatException nfe) {
    	//Will return HTTP Code 400
    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST );
    	System.out.println("NumberFormatException ["+id+"] is not a number");
      } catch (Exception ex) {
    	  //Will return HTTP Code 500
    	  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    	  System.out.println("Exception raised ["+id+"]");
    	  ex.printStackTrace();
      }
      return "OK";
    }
    
    /**
     * GET /findById --> Return the pet having the passed id.
     * In case there is no id, the service returns all the pets
     */
    @RequestMapping(value = "/pet/{id}", method= RequestMethod.GET)
    public PetHolder getById(@PathVariable("id") String id, HttpServletResponse response) {
    	
    	PetHolder output = new PetHolder();
    	try {
    	if (id.trim().equalsIgnoreCase("*")){
    		output.add((ArrayList<PetEntity>)petDao.findAll());
    	}else {
    		output.add(petDao.findById(Long.valueOf(id)));
    	}
    	if (output.getList().isEmpty() || output.getList().get(0) == null){
        	//Will return HTTP Code 404
    		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    		System.out.println("No pet found with id ["+id+"]");
    	}
      }catch (NumberFormatException nfe) {
    	//Will return HTTP Code 400
    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST );
    	System.out.println("NumberFormatException ["+id+"] is not a number");
      } catch (Exception ex) {
    	  //Will return HTTP Code 500
    	  response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    	  System.out.println("Exception raised ["+id+"]");
    	  ex.printStackTrace();
      }
      return output;
    }
}