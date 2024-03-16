package com.classichotel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.classichotel.entity.Locale;
import com.classichotel.model.LocaleDTO;
import com.classichotel.repository.LocaleRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LocaleServiceTest {

  @Mock
  private LocaleRepository localeRepository;

  @InjectMocks
  private LocaleService localeService;

  private Locale locale;
  private LocaleDTO localeDTO;

  @BeforeEach
  void setUp() {
    locale = new Locale();
    locale.setId(1L);
    locale.setName("Test Locale");
    localeDTO = new LocaleDTO(1L, "Test Locale", Collections.emptyList());

    lenient().when(localeRepository.save(any(Locale.class))).thenReturn(locale);
    lenient().when(localeRepository.findById(any(Long.class))).thenReturn(Optional.of(locale));
  }

  @Test
  void createLocale_ShouldReturnLocaleDTO() {
    LocaleDTO savedLocaleDTO = localeService.createLocale(localeDTO);

    assertNotNull(savedLocaleDTO);
    assertEquals(localeDTO.name(), savedLocaleDTO.name());
  }

  @Test
  void findAllLocales_ShouldReturnListOfLocaleDTOs() {
    given(localeRepository.findAll()).willReturn(Collections.singletonList(locale));

    List<LocaleDTO> locales = localeService.findAllLocales();

    assertNotNull(locales);
    assertEquals(1, locales.size());
  }

  @Test
  void findLocaleById_ShouldReturnLocaleDTO() {
    LocaleDTO foundLocaleDTO = localeService.findLocaleById(1L);

    assertNotNull(foundLocaleDTO);
    assertEquals(localeDTO.name(), foundLocaleDTO.name());
  }

  @Test
  void updateLocale_ShouldReturnUpdatedLocaleDTO() {
    given(localeRepository.existsById(anyLong())).willReturn(true);

    LocaleDTO updatedLocaleDTO = localeService.updateLocale(1L, localeDTO);

    assertNotNull(updatedLocaleDTO);
    assertEquals(localeDTO.name(), updatedLocaleDTO.name());
  }

  @Test
  void deleteLocale_ShouldSucceed() {
    given(localeRepository.existsById(anyLong())).willReturn(true);

    localeService.deleteLocale(1L);

    verify(localeRepository, times(1)).deleteById(1L);
  }

  @Test
  void findLocaleById_ShouldThrowException_WhenNotFound() {
    given(localeRepository.findById(any(Long.class))).willReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> localeService.findLocaleById(1L));
  }

  @Test
  void updateLocale_ShouldThrowException_WhenNotFound() {
    given(localeRepository.existsById(any(Long.class))).willReturn(false);

    assertThrows(RuntimeException.class, () -> localeService.updateLocale(1L, localeDTO));
  }

  @Test
  void deleteLocale_ShouldThrowException_WhenNotFound() {
    given(localeRepository.existsById(any(Long.class))).willReturn(false);

    assertThrows(RuntimeException.class, () -> localeService.deleteLocale(1L));
  }
}
