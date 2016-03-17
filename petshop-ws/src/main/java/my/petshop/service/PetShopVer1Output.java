package my.petshop.service;

public class PetShopVer1Output {

    private final long id;
    private final String content;

    public PetShopVer1Output(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
}
