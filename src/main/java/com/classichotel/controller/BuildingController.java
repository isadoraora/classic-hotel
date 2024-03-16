package com.classichotel.controller;

import com.classichotel.model.BuildingDTO;
import com.classichotel.service.BuildingService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/buildings")
public class BuildingController {

  private final BuildingService buildingService;

  @PostMapping
  public ResponseEntity<BuildingDTO> createBuilding(@Valid @RequestBody BuildingDTO buildingDTO) {
    BuildingDTO newBuilding = buildingService.createBuilding(buildingDTO);
    return new ResponseEntity<>(newBuilding, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<BuildingDTO>> getAllBuildings() {
    List<BuildingDTO> buildings = buildingService.findAllBuildings();
    return ResponseEntity.ok(buildings);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable Long id) {
    BuildingDTO building = buildingService.findBuildingById(id);
    return ResponseEntity.ok(building);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BuildingDTO> updateBuilding(@PathVariable Long id, @Valid @RequestBody BuildingDTO buildingDTO) {
    BuildingDTO updatedBuilding = buildingService.updateBuilding(id, buildingDTO);
    return ResponseEntity.ok(updatedBuilding);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
    buildingService.deleteBuilding(id);
    return ResponseEntity.noContent().build();
  }
}
