package my.petshop.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import my.petshop.models.PetDAO;
import my.petshop.models.PetEntity;

@Controller
public class PetShopVer1 {
	

    @Autowired
    public PetDAO petDao;

    /**
     * POST /create  --> Create a new pet and save it in the database.
     */
    @RequestMapping(value = "/pet/{name,category}", method= RequestMethod.POST)
    public @ResponseBody String create(@PathVariable("name") String name, @PathVariable("category") String category) {
      String petId = "";
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
    
    /**
     * DELETE /delete  --> Delete the pet having the passed id.
     */
    @RequestMapping(value = "/pet/{id}", method= RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") String id) {
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
    public @ResponseBody PetShopVer2Output getById(@PathVariable("id") String id) {
    	
    	PetShopVer2Output output = new PetShopVer2Output();
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