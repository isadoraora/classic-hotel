package com.classichotel.service;

import com.classichotel.entity.Building;
import com.classichotel.exception.NotFoundException;
import com.classichotel.mapper.BuildingMapper;
import com.classichotel.model.BuildingDTO;
import com.classichotel.repository.BuildingRepository;
import com.classichotel.repository.LocaleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BuildingService {

  private final BuildingRepository buildingRepository;
  private final LocaleRepository localeRepository;

  @Transactional
  public BuildingDTO createBuilding(BuildingDTO buildingDTO) {
    Building building = new Building();
    building.setName(buildingDTO.name());
    building.setLocale(localeRepository.findById(buildingDTO.locale().id())
        .orElseThrow(() -> new RuntimeException("Locale not found")));
    building = buildingRepository.save(building);
    return BuildingMapper.fromEntity(building);
  }

  @Transactional(readOnly = true)
  public List<BuildingDTO> findAllBuildings() {
    return buildingRepository.findAll().stream()
        .map(BuildingMapper::fromEntity)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public BuildingDTO findBuildingById(Long id) {
    Building building = buildingRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Building not found with id: " + id));
    return BuildingMapper.fromEntity(building);
  }

  @Transactional
  public BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO) {
    if (!buildingRepository.existsById(id)) {
      throw new NotFoundException("Building not found with id: " + id);
    }
    Building building = BuildingMapper.toEntity(buildingDTO);
    building.setId(id);
    building.setLocale(localeRepository.findById(buildingDTO.locale().id())
        .orElseThrow(() -> new NotFoundException("Locale not found with id: " + buildingDTO.locale())));
    building = buildingRepository.save(building);
    return BuildingMapper.fromEntity(building);
  }

  @Transactional
  public void deleteBuilding(Long id) {
    if (!buildingRepository.existsById(id)) {
      throw new RuntimeException("Building not found with id: " + id);
    }
    buildingRepository.deleteById(id);
  }
}

