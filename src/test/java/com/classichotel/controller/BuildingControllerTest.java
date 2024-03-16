package com.classichotel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.classichotel.model.BuildingDTO;
import com.classichotel.service.BuildingService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class BuildingControllerTest {

  @Mock
  private BuildingService buildingService;

  @InjectMocks
  private BuildingController buildingController;

  private BuildingDTO buildingDTO;

  @BeforeEach
  void setUp() {
    buildingDTO = new BuildingDTO(1L, "Building Name", null, Collections.emptyList());
  }

  @Test
  void createBuilding_ShouldReturnNewBuilding() {
    given(buildingService.createBuilding(any(BuildingDTO.class))).willReturn(buildingDTO);

    ResponseEntity<BuildingDTO> response = buildingController.createBuilding(buildingDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(buildingDTO);
  }

  @Test
  void getAllBuildings_ShouldReturnBuildingsList() {
    given(buildingService.findAllBuildings()).willReturn(Collections.singletonList(buildingDTO));

    ResponseEntity<List<BuildingDTO>> response = buildingController.getAllBuildings();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(1);
    assertThat(response.getBody().get(0)).isEqualTo(buildingDTO);
  }

  @Test
  void getBuildingById_ShouldReturnBuilding() {
    Long buildingId = 1L;
    given(buildingService.findBuildingById(buildingId)).willReturn(buildingDTO);

    ResponseEntity<BuildingDTO> response = buildingController.getBuildingById(buildingId);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(buildingDTO);
  }

  @Test
  void updateBuilding_ShouldReturnUpdatedBuilding() {
    Long buildingId = 1L;
    BuildingDTO updatedBuildingDTO = new BuildingDTO(buildingId, "Updated Building Name", null, Collections.emptyList());
    given(buildingService.updateBuilding(eq(buildingId), any(BuildingDTO.class))).willReturn(updatedBuildingDTO);

    ResponseEntity<BuildingDTO> response = buildingController.updateBuilding(buildingId, buildingDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(updatedBuildingDTO);
  }

  @Test
  void deleteBuilding_ShouldReturnNoContent() {
    Long buildingId = 1L;
    doNothing().when(buildingService).deleteBuilding(buildingId);

    ResponseEntity<Void> response = buildingController.deleteBuilding(buildingId);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    verify(buildingService, times(1)).deleteBuilding(buildingId);
  }

}

