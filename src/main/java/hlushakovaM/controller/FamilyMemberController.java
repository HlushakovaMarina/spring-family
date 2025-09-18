package hlushakovaM.controller;

import hlushakovaM.model.FamilyMember;
import hlushakovaM.service.FamilyMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/family-members")
@Tag(name = "FamilyMember", description = "Операции, связанные с членами семьи")
public class FamilyMemberController {

    @Autowired
    private FamilyMemberService familyMemberService;

    @GetMapping
    @Operation(summary = "Получить всех членов семьи")
    @ApiResponse(responseCode = "200", description = "Успешно восстановлены все члены семьи")
    public ResponseEntity<List<FamilyMember>> getAllFamilyMembers() {
        return ResponseEntity.ok(familyMemberService.getAllFamilyMembers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить члена семьи по удостоверению личности")
    @ApiResponse(responseCode = "200", description = "Член семьи успешно восстановлен")
    @ApiResponse(responseCode = "404", description = "Член семьи не найден")
    public ResponseEntity<FamilyMember> getFamilyMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(familyMemberService.getFamilyMemberById(id));
    }

    @PostMapping
    @Operation(summary = "Создать нового члена семьи")
    @ApiResponse(responseCode = "201", description = "Успешно создан новый член семьи")
    @ApiResponse(responseCode = "400", description = "Неверный ввод")
    public ResponseEntity<FamilyMember> createFamilyMember(@Valid @RequestBody FamilyMember familyMember, @RequestParam Long houseId) {
        return new ResponseEntity<>(familyMemberService.createFamilyMember(familyMember, houseId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить существующего члена семьи")
    @ApiResponse(responseCode = "200", description = "Член семьи успешно обновлен")
    @ApiResponse(responseCode = "400", description = "Неверный ввод")
    @ApiResponse(responseCode = "404", description = "Член семьи не найден")
    public ResponseEntity<FamilyMember> updateFamilyMember(@PathVariable Long id, @Valid @RequestBody FamilyMember familyMemberDetails, @RequestParam Long houseId) {
        return ResponseEntity.ok(familyMemberService.updateFamilyMember(id, familyMemberDetails, houseId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить члена семьи")
    @ApiResponse(responseCode = "204", description = "Член семьи успешно удален")
    @ApiResponse(responseCode = "404", description = "Член семьи не найден")
    public ResponseEntity<Void> deleteFamilyMember(@PathVariable Long id) {
        familyMemberService.deleteFamilyMember(id);
        return ResponseEntity.noContent().build();
    }
}