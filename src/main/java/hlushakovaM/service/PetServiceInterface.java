package hlushakovaM.service;

import hlushakovaM.model.Pet;

import java.util.List;

public interface PetServiceInterface {

    List<Pet> getAllPets();

    Pet getPetById(Long id);

    Pet createPet(Pet pet, Long houseId);

    Pet updatePet(Long id, Pet petDetails, Long houseId);

    void deletePet(Long id);
}
