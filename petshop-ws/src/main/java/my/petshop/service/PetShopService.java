package my.petshop.service;


import java.util.ArrayList;

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
    public String create(@RequestBody PetHolder pPetHolder) {
    
    	if (pPetHolder == null ||
    			pPetHolder.getList().isEmpty() ||
    			pPetHolder.getList().get(0).getClass().isInstance(PetEntity.class)){
    		return "ERROR DURING CREATION";
    	}
    		
      try {
        PetEntity pet = pPetHolder.getList().get(0);
        pet = petDao.save(pet);
      }
      catch (Exception ex) {
    	  ex.printStackTrace();
        return "Error creating the pet: " + ex.toString();
      }
      return "Pet succesfully created";
    }

    /**
     * DELETE /delete  --> Delete the pet having the passed id.
     */
    @RequestMapping(value = "/pet/{id}", method= RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id) {
      try {
    	PetEntity pet = new PetEntity(Long.valueOf(id));
        petDao.delete(pet);
      }
      catch (Exception ex) {
        return "Error deleting the pet:" + ex.toString();
      }
      return "Pet succesfully deleted!";
    }
    
    /**
     * GET /findById --> Return the pet having the passed id.
     * In case there is no id, the service returns all the pets
     */
    @RequestMapping(value = "/pet/{id}", method= RequestMethod.GET)
    public PetHolder getById(@PathVariable("id") String id) {
    	
    	PetHolder output = new PetHolder();
    	try {
    	if (id.trim().equalsIgnoreCase("*")){
    		output.add((ArrayList<PetEntity>)petDao.findAll());
    	}else {
    		output.add(petDao.findById(Long.valueOf(id)));
    	}
      }
      catch (Exception ex) {
    	  ex.printStackTrace();
      }

      return output;
    }
}