package com.classichotel.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.classichotel.entity.Building;
import com.classichotel.entity.Locale;
import com.classichotel.exception.NotFoundException;
import com.classichotel.model.BuildingDTO;
import com.classichotel.model.LocaleDTO;
import com.classichotel.repository.BuildingRepository;
import com.classichotel.repository.LocaleRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class BuildingServiceTest {

  @Mock
  private BuildingRepository buildingRepository;

  @Mock
  private LocaleRepository localeRepository;

  @InjectMocks
  private BuildingService buildingService;

  private BuildingDTO buildingDTO;
  private Locale locale;
  private Building building;

  @BeforeEach
  void setUp() {
    locale = new Locale();
    locale.setId(1L);
    locale.setName("Test Locale");
    locale.setBuildingList(new ArrayList<>());

    building = new Building();
    building.setId(1L);
    building.setName("Test Building");
    building.setLocale(locale);
    building.setRoomList(new ArrayList<>());

    buildingDTO = new BuildingDTO(building.getId(), building.getName(), new LocaleDTO(locale.getId(), locale.getName(), new ArrayList<>()), Collections.emptyList());

    given(localeRepository.findById(anyLong())).willReturn(Optional.of(locale));
    given(buildingRepository.save(any(Building.class))).willReturn(building);
  }

  @Test
  void createBuilding_ShouldReturnNewBuildingDTO() {
    given(localeRepository.findById(locale.getId())).willReturn(Optional.of(locale));

    given(buildingRepository.save(any(Building.class))).willAnswer(invocation -> invocation.getArgument(0));

    BuildingDTO resultDTO = buildingService.createBuilding(buildingDTO);

    assertNotNull(resultDTO);
    assertEquals(buildingDTO.name(), resultDTO.name());
    assertEquals(buildingDTO.locale().id(), resultDTO.locale().id());
  }

  @Test
  void findAllBuildings_ShouldReturnListOfBuildings() {
    given(buildingRepository.findAll()).willReturn(Collections.singletonList(building));

    List<BuildingDTO> buildings = buildingService.findAllBuildings();

    assertNotNull(buildings);
    assertFalse(buildings.isEmpty());
    assertEquals(1, buildings.size());
  }

  @Test
  void findBuildingById_ShouldReturnBuildingDTO() {
    given(buildingRepository.findById(anyLong())).willReturn(Optional.of(building));

    BuildingDTO foundBuildingDTO = buildingService.findBuildingById(1L);

    assertNotNull(foundBuildingDTO);
    assertEquals(buildingDTO.name(), foundBuildingDTO.name());
  }

  @Test
  void updateBuilding_ShouldReturnUpdatedBuildingDTO() {
    given(buildingRepository.existsById(anyLong())).willReturn(true);
    given(buildingRepository.save(any(Building.class))).willReturn(building);

    BuildingDTO updatedBuildingDTO = buildingService.updateBuilding(1L, buildingDTO);

    assertNotNull(updatedBuildingDTO);
    assertEquals(buildingDTO.name(), updatedBuildingDTO.name());
  }

  @Test
  void updateBuilding_ShouldThrowNotFoundException_WhenBuildingNotFound() {
    given(buildingRepository.existsById(anyLong())).willReturn(false);
    assertThrows(NotFoundException.class, () -> buildingService.updateBuilding(1L, buildingDTO),
        "Building not found with id: 1");
  }


  @Test
  void deleteBuilding_ShouldNotThrowException() {
    given(buildingRepository.existsById(anyLong())).willReturn(true);

    assertDoesNotThrow(() -> buildingService.deleteBuilding(1L));
    verify(buildingRepository, times(1)).deleteById(1L);
  }
}

