package hlushakovaM.service;

import hlushakovaM.model.FamilyMember;

import java.util.List;

public interface FamilyMemberServiceInterface {

    List<FamilyMember> getAllFamilyMembers();

    FamilyMember getFamilyMemberById(Long id);

    FamilyMember createFamilyMember(FamilyMember familyMember, Long houseId);

    FamilyMember updateFamilyMember(Long id, FamilyMember familyMemberDetails, Long houseId);

    void deleteFamilyMember(Long id);
}
