package my.petshop.service;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
//    @RequestMapping(method=RequestMethod.GET)
//    public @ResponseBody PetShopVer1Output sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
//        return new PetShopVer1Output(counter.incrementAndGet(), String.format(template, name));
//    }
    
    /**
     * POST /create  --> Create a new pet and save it in the database.
     */
    @RequestMapping(value = "/pet/{name,category}", method= RequestMethod.POST)
    public String create(@PathVariable("name") String name, @PathVariable("category") String category) {
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
    public @ResponseBody PetShopVer1Output getById(@PathVariable("id") String id) {
      PetShopVer1Output result = new PetShopVer1Output(-1, "ERROR","ERROR","ERROR");
      System.out.println("PET ID =="+id+".");
      try {
    	PetEntity pet = petDao.findById(Long.valueOf(id));
        System.out.println("PET == "+pet);
        result = new PetShopVer1Output(pet.getId(),
        		pet.getName(),
        		pet.getCategory(),
        		pet.getStatus());
        System.out.println("return == "+pet);
      }
      catch (Exception ex) {
    	  ex.printStackTrace();
      }

      return result;
    }
}