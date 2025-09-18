package hlushakovaM.service;

import hlushakovaM.model.House;

import java.util.List;

public interface HouseServiceInterface {

    List<House> getAllHouses();

    House getHouseById(Long id);

    House createHouse(House house);

    House updateHouse(Long id, House houseDetails);

    void deleteHouse(Long id);
}
