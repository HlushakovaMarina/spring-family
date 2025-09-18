package hlushakovaM.service;

import hlushakovaM.model.House;
import hlushakovaM.repository.HouseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HouseService implements HouseServiceInterface {
    @Autowired
    private HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Transactional
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    @Transactional
    public House getHouseById(Long id) {
        return houseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Дом не найден по идентификатору: " + id));
    }

    public House createHouse(House house) {
        return houseRepository.save(house);
    }

    public House updateHouse(Long id, House houseDetails) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Дом не найден по идентификатору: " + id));

        house.setAddress(houseDetails.getAddress());
        house.setDescription(houseDetails.getDescription());

        return houseRepository.save(house);
    }

    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }
}
