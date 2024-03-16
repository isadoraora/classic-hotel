package com.classichotel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

import com.classichotel.model.LocaleDTO;
import com.classichotel.service.LocaleService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
class LocaleControllerTest {

  @Mock
  private LocaleService localeService;

  @InjectMocks
  private LocaleController localeController;

  private LocaleDTO localeDTO;

  @BeforeEach
  void setUp() {
    localeDTO = new LocaleDTO(1L, "Locale Name", Collections.emptyList());
  }

  @Test
  void createLocale_ShouldReturnNewLocale() {
    given(localeService.createLocale(any(LocaleDTO.class))).willReturn(localeDTO);

    ResponseEntity<LocaleDTO> response = localeController.createLocale(localeDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(localeDTO);
  }

  @Test
  void getAllLocales_ShouldReturnLocalesList() {
    given(localeService.findAllLocales()).willReturn(Collections.singletonList(localeDTO));

    ResponseEntity<List<LocaleDTO>> response = localeController.getAllLocales();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(1);
    assertThat(response.getBody().get(0)).isEqualTo(localeDTO);
  }

  @Test
  void getLocaleById_ShouldReturnLocale() {
    Long localeId = 1L;
    given(localeService.findLocaleById(localeId)).willReturn(localeDTO);

    ResponseEntity<LocaleDTO> response = localeController.getLocaleById(localeId);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(localeDTO);
  }

  @Test
  void updateLocale_ShouldReturnUpdatedLocale() {
    Long localeId = 1L;
    LocaleDTO updatedLocaleDTO = new LocaleDTO(localeId, "Updated Locale Name", Collections.emptyList());
    given(localeService.updateLocale(eq(localeId), any(LocaleDTO.class))).willReturn(updatedLocaleDTO);

    ResponseEntity<LocaleDTO> response = localeController.updateLocale(localeId, localeDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(updatedLocaleDTO);
  }

  @Test
  void deleteLocale_ShouldReturnNoContent() {
    Long localeId = 1L;
    doNothing().when(localeService).deleteLocale(localeId);

    ResponseEntity<Void> response = localeController.deleteLocale(localeId);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    Mockito.verify(localeService, times(1)).deleteLocale(localeId);
  }
}
