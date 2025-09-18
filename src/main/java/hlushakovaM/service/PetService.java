package hlushakovaM.service;

import hlushakovaM.model.House;
import hlushakovaM.model.Pet;
import hlushakovaM.repository.HouseRepository;
import hlushakovaM.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PetService implements PetServiceInterface {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HouseRepository houseRepository;

    public PetService(PetRepository petRepository, HouseRepository houseRepository) {
        this.petRepository = petRepository;
        this.houseRepository = houseRepository;
    }

    @Transactional
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Transactional
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Питомец с идентификатором не найден: " + id));
    }

    public Pet createPet(Pet pet, Long houseId) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new EntityNotFoundException("Дом не найден по идентификатору: " + houseId));
        pet.setHouse(house);
        return petRepository.save(pet);
    }

    public Pet updatePet(Long id, Pet petDetails, Long houseId) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Питомец с идентификатором не найден: " + id));

        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new EntityNotFoundException("Дом не найден по идентификатору: " + houseId));

        pet.setName(petDetails.getName());
        pet.setAge(petDetails.getAge());
        pet.setSpecies(petDetails.getSpecies());
        pet.setHouse(house);

        return petRepository.save(pet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
