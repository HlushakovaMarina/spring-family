package hlushakovaM.service;

import hlushakovaM.model.FamilyMember;
import hlushakovaM.model.House;
import hlushakovaM.repository.FamilyMemberRepository;
import hlushakovaM.repository.HouseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FamilyMemberService implements FamilyMemberServiceInterface {

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Autowired
    private HouseRepository houseRepository;

    public FamilyMemberService(FamilyMemberRepository familyMemberRepository, HouseRepository houseRepository) {
        this.familyMemberRepository = familyMemberRepository;
        this.houseRepository = houseRepository;
    }

    @Transactional
    public List<FamilyMember> getAllFamilyMembers() {
        return familyMemberRepository.findAll();
    }

    @Transactional
    public FamilyMember getFamilyMemberById(Long id) {
        return familyMemberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Член семьи не найден по идентификатору: " + id));
    }

    public FamilyMember createFamilyMember(FamilyMember familyMember, Long houseId) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new EntityNotFoundException("Дом не найден по идентификатору: " + houseId));
        familyMember.setHouse(house);
        return familyMemberRepository.save(familyMember);
    }

    public FamilyMember updateFamilyMember(Long id, FamilyMember familyMemberDetails, Long houseId) {
        FamilyMember familyMember = familyMemberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Член семьи не найден по идентификатору: " + id));

        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new EntityNotFoundException("Дом не найден по идентификатору: " + houseId));

        familyMember.setName(familyMemberDetails.getName());
        familyMember.setAge(familyMemberDetails.getAge());
        familyMember.setRole(familyMemberDetails.getRole());
        familyMember.setHouse(house);

        return familyMemberRepository.save(familyMember);
    }

    public void deleteFamilyMember(Long id) {
        familyMemberRepository.deleteById(id);
    }
}
