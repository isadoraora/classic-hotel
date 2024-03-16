package com.classichotel.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.classichotel.entity.ServiceOptionType;
import com.classichotel.model.ServiceOptionDTO;
import com.classichotel.service.ServiceOptionService;
import java.math.BigDecimal;
import java.util.Arrays;
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
class ServiceOptionControllerTest {

  @Mock
  private ServiceOptionService serviceOptionService;

  @InjectMocks
  private ServiceOptionController serviceOptionController;

  private ServiceOptionDTO serviceOptionDTO;

  @BeforeEach
  void setUp() {
    serviceOptionDTO = new ServiceOptionDTO(1L, "Massage", new BigDecimal("100.00"), ServiceOptionType.SERVICE);
  }

  @Test
  void createServiceOption_ShouldReturnNewServiceOption() {
    given(serviceOptionService.createServiceOption(serviceOptionDTO)).willReturn(serviceOptionDTO);
    ResponseEntity<ServiceOptionDTO> response = serviceOptionController.createServiceOption(serviceOptionDTO);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(serviceOptionDTO);
  }

  @Test
  void getAllServiceOptions_ShouldReturnServiceOptionsList() {
    given(serviceOptionService.findAllServiceOptions()).willReturn(Arrays.asList(serviceOptionDTO));
    ResponseEntity<List<ServiceOptionDTO>> response = serviceOptionController.getAllServiceOptions();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).containsExactly(serviceOptionDTO);
  }

  @Test
  void getServiceOptionById_ShouldReturnServiceOption() {
    given(serviceOptionService.findServiceOptionById(anyLong())).willReturn(serviceOptionDTO);
    ResponseEntity<ServiceOptionDTO> response = serviceOptionController.getServiceOptionById(1L);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(serviceOptionDTO);
  }

  @Test
  void updateServiceOption_ShouldReturnUpdatedServiceOption() {
    given(serviceOptionService.updateServiceOption(anyLong(), eq(serviceOptionDTO))).willReturn(serviceOptionDTO);
    ResponseEntity<ServiceOptionDTO> response = serviceOptionController.updateServiceOption(1L, serviceOptionDTO);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(serviceOptionDTO);
  }

  @Test
  void deleteServiceOption_ShouldReturnNoContent() {
    doNothing().when(serviceOptionService).deleteServiceOption(anyLong());
    ResponseEntity<Void> response = serviceOptionController.deleteServiceOption(1L);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    verify(serviceOptionService).deleteServiceOption(1L);
  }
}
