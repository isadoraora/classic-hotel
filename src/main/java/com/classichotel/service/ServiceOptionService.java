package com.classichotel.service;

import com.classichotel.entity.ServiceOption;
import com.classichotel.model.ServiceOptionDTO;
import com.classichotel.repository.ServiceOptionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ServiceOptionService {

  private final ServiceOptionRepository repository;

  @Transactional
  public ServiceOptionDTO createServiceOption(ServiceOptionDTO dto) {
    ServiceOption serviceOption = new ServiceOption();
    serviceOption.setName(dto.name());
    serviceOption.setPrice(dto.price());
    serviceOption.setType(dto.type());
    serviceOption = repository.save(serviceOption);
    return new ServiceOptionDTO(serviceOption.getId(), serviceOption.getName(), serviceOption.getPrice(), serviceOption.getType());
  }

  @Transactional(readOnly = true)
  public List<ServiceOptionDTO> findAllServiceOptions() {
    return repository.findAll().stream()
        .map(serviceOption -> new ServiceOptionDTO(serviceOption.getId(), serviceOption.getName(), serviceOption.getPrice(),
            serviceOption.getType()))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public ServiceOptionDTO findServiceOptionById(Long id) {
    ServiceOption serviceOption = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Service option not found"));
    return new ServiceOptionDTO(serviceOption.getId(), serviceOption.getName(), serviceOption.getPrice(), serviceOption.getType());
  }

  @Transactional
  public ServiceOptionDTO updateServiceOption(Long id, ServiceOptionDTO dto) {
    ServiceOption serviceOption = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Service option not found"));
    serviceOption.setName(dto.name());
    serviceOption.setPrice(dto.price());
    serviceOption.setType(dto.type());
    repository.save(serviceOption);
    return dto;
  }

  @Transactional
  public void deleteServiceOption(Long id) {
    repository.deleteById(id);
  }

}

