package my.petshop.service;

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
