package my.petshop.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface PetDAO extends CrudRepository<PetEntity, Long> {

  /**
   * This method will find an Pet instance in the database by its id.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */
  public PetEntity findById(long id);

}