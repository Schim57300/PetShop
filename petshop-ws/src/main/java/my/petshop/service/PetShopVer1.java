package my.petshop.service;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import my.petshop.models.PetDAO;
import my.petshop.models.PetEntity;

@Controller
public class PetShopVer1 {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    public PetDAO petDao;
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody PetShopVer1Output sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new PetShopVer1Output(counter.incrementAndGet(), String.format(template, name));
    }
    
    /**
     * GET /create  --> Create a new pet and save it in the database.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String name, String category) {
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
     * GET /delete  --> Delete the pet having the passed id.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
      try {
    	PetEntity pet = new PetEntity(id);
        petDao.delete(pet);
      }
      catch (Exception ex) {
        return "Error deleting the pet:" + ex.toString();
      }
      return "Pet succesfully deleted!";
    }
    
    /**
     * GET /get-by-id  --> Return the name and category for the pet having the passed
     * id.
     */
    @RequestMapping("/findById")
    @ResponseBody
    public String getById(long id) {
      PetEntity pet;
      try {
        pet = petDao.findById(id);
      }
      catch (Exception ex) {
        return "Pet not found";
      }
      return "The pet is: " + pet;
    }
}