package hlushakovaM.controller;

import hlushakovaM.model.Pet;
import hlushakovaM.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@Tag(name = "Pet", description = "Операции, связанные с домашними животными")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    @Operation(summary = "Получить всех домашних животных")
    @ApiResponse(responseCode = "200", description = "Все домашние животные успешно получены")
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить питомца по ID")
    @ApiResponse(responseCode = "200", description = "Питомец успешно получен")
    @ApiResponse(responseCode = "404", description = "Питомец не найден")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PostMapping
    @Operation(summary = "Создать нового питомца")
    @ApiResponse(responseCode = "201", description = "Успешно создан новый питомец")
    @ApiResponse(responseCode = "400", description = "Неверный ввод")
    public ResponseEntity<Pet> createPet(@Valid @RequestBody Pet pet, @RequestParam Long houseId) {
        return new ResponseEntity<>(petService.createPet(pet, houseId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить существующего питомца")
    @ApiResponse(responseCode = "200", description = "Питомец успешно обновлён")
    @ApiResponse(responseCode = "400", description = "Неверный ввод")
    @ApiResponse(responseCode = "404", description = "Питомец не найден")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @Valid @RequestBody Pet petDetails, @RequestParam Long houseId) {
        return ResponseEntity.ok(petService.updatePet(id, petDetails, houseId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить питомца")
    @ApiResponse(responseCode = "204", description = "Питомец успешно удалён")
    @ApiResponse(responseCode = "404", description = "Питомец не найден")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}