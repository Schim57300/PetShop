package my.petshop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@Column(name = "id")
	private long id;

	@NotNull
	@Column(name = "name")
	private String name;

}
