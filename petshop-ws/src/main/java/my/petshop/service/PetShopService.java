package my.petshop.service;


import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = "/pet", method= RequestMethod.POST, headers = "Content-type: application/*")
    public String create(@RequestBody String name, @RequestBody String category) {
      String petId = "";
      System.out.println("In POST");
      try {
        PetEntity pet = new PetEntity(category, name);
        pet = petDao.save(pet);
        petId = String.valueOf(pet.getId());
      }
      catch (Exception ex) {
    	  ex.printStackTrace();
        return "Error creating the pet: " + ex.toString();
      }
      return "Pet succesfully created with id = " + petId;
    }
    
    @RequestMapping(value = "/pet", method = RequestMethod.POST)
    public void add(@RequestBody List<String> tags) {
    	System.out.println("In POST2");
        System.out.println(tags);
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
     * GET /findById --> Return the name and category for the pet having the passed
     * id.
     */
    @RequestMapping(value = "/pet/{id}", method= RequestMethod.GET)
    public PetHolder getById(@PathVariable("id") String id) {
    	
    	PetHolder output = new PetHolder();
      System.out.println("PET ID =="+id+".");
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