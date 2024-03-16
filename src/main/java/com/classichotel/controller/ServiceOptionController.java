package com.classichotel.controller;

import com.classichotel.model.ServiceOptionDTO;
import com.classichotel.service.ServiceOptionService;
import java.util.List;
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
@RequestMapping("/service-options")
@AllArgsConstructor
public class ServiceOptionController {

  private final ServiceOptionService service;

  @PostMapping
  public ResponseEntity<ServiceOptionDTO> createServiceOption(@RequestBody ServiceOptionDTO serviceOptionDTO) {
    ServiceOptionDTO newServiceOption = service.createServiceOption(serviceOptionDTO);
    return new ResponseEntity<>(newServiceOption, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ServiceOptionDTO>> getAllServiceOptions() {
    List<ServiceOptionDTO> serviceOptions = service.findAllServiceOptions();
    return ResponseEntity.ok(serviceOptions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ServiceOptionDTO> getServiceOptionById(@PathVariable Long id) {
    ServiceOptionDTO serviceOptionDTO = service.findServiceOptionById(id);
    return ResponseEntity.ok(serviceOptionDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ServiceOptionDTO> updateServiceOption(@PathVariable Long id, @RequestBody ServiceOptionDTO serviceOptionDTO) {
    ServiceOptionDTO updatedServiceOption = service.updateServiceOption(id, serviceOptionDTO);
    return ResponseEntity.ok(updatedServiceOption);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteServiceOption(@PathVariable Long id) {
    service.deleteServiceOption(id);
    return ResponseEntity.noContent().build();
  }
}
