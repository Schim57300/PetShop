package my.petshop.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface PetDAO extends CrudRepository<PetEntity, Long> {

  public PetEntity findById(long pet_id);

}