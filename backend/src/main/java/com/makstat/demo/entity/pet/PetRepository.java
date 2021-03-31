package com.makstat.demo.entity.pet;

import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {

    Pet findPetByName(String name);
}
