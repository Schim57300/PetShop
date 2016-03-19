package my.petshop.service;

import my.petshop.models.PetEntity;

public class PetShopVer1Output {

    private final long id;
    private final String name;
    private final String category;
    private final String status;
    

    public PetShopVer1Output(long id, String name, String category, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
    }

    public PetShopVer1Output(PetEntity pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.category =pet.getCategory();
        this.status = pet.getStatus();
    }

	public long getId() {
        return id;
    }

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getStatus() {
		return status;
	}

   
    
}
