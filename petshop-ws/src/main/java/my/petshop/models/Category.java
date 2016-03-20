package my.petshop.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Category {
	
	@Column(name = "category_id")
	private long id;

	@NotNull
	@Column(name = "category_name")
	private String name;
 
	public long getId() {
    	System.out.println("CATEGORY.getId");
		return id;
	}

	public void setId(long id) {
		System.out.println("CATEGORY.setId"+id);
		this.id = id;
	}

	public String getName() {
		System.out.println("CATEGORY.getName");
		return name;
	}

	public void setName(String name) {
		System.out.println("CATEGORY.setName"+name);
		this.name = name;
	}
//
//	 @ElementCollection(targetClass=PetEntity.class)
//	private Set<PetEntity> pets;
//	 
//	@OneToMany(cascade = CascadeType.ALL, targetEntity = PetEntity.class)
//	@JoinTable(
//	        name = "CATEGORY_PET",
//	        joinColumns = @JoinColumn(name = "id"),
//	        inverseJoinColumns = @JoinColumn(name = "id")
//	)
//	public Set<PetEntity> getArticles() {
//	    return pets;
//	}
	public String toString(){
		return ("Category:{id : " + id + ", name : " + name + "}");
	}
}
