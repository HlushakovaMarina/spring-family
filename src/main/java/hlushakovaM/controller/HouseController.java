package hlushakovaM.controller;

import hlushakovaM.model.House;
import hlushakovaM.service.HouseService;
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
@RequestMapping("/api/houses")
@Tag(name = "House", description = "Операции, связанные с домами")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping
    @Operation(summary = "Получить все дома")
    @ApiResponse(responseCode = "200", description = "Успешно получены все дома")
    public ResponseEntity<List<House>> getAllHouses() {
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить дом по ID")
    @ApiResponse(responseCode = "200", description = "Дом успешно найден")
    @ApiResponse(responseCode = "404", description = "Дом не найден")
    public ResponseEntity<House> getHouseById(@PathVariable Long id) {
        return ResponseEntity.ok(houseService.getHouseById(id));
    }

    @PostMapping
    @Operation(summary = "Создать новый дом")
    @ApiResponse(responseCode = "201", description = "Успешно создан новый дом")
    @ApiResponse(responseCode = "400", description = "Неверный ввод")
    public ResponseEntity<House> createHouse(@Valid @RequestBody House house) {
        return new ResponseEntity<>(houseService.createHouse(house), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить существующий дом")
    @ApiResponse(responseCode = "200", description = "Дом успешно обновлен")
    @ApiResponse(responseCode = "400", description = "Неверный ввод")
    @ApiResponse(responseCode = "404", description = "Дом не найден")
    public ResponseEntity<House> updateHouse(@PathVariable Long id, @Valid @RequestBody House houseDetails) {
        return ResponseEntity.ok(houseService.updateHouse(id, houseDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить дом")
    @ApiResponse(responseCode = "204", description = "Дом успешно удалён")
    @ApiResponse(responseCode = "404", description = "Дом не найден")
    public ResponseEntity<Void> deleteHouse(@PathVariable Long id) {
        houseService.deleteHouse(id);
        return ResponseEntity.noContent().build();
    }
}
