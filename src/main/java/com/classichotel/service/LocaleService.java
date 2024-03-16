package com.classichotel.service;

import com.classichotel.entity.Locale;
import com.classichotel.exception.NotFoundException;
import com.classichotel.mapper.LocaleMapper;
import com.classichotel.model.LocaleDTO;
import com.classichotel.repository.LocaleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LocaleService {

  private final LocaleRepository localeRepository;

  @Transactional
  public LocaleDTO createLocale(LocaleDTO localeDTO) {
    Locale locale = LocaleMapper.toEntity(localeDTO);
    locale = localeRepository.save(locale);
    return LocaleMapper.fromEntity(locale);
  }

  @Transactional(readOnly = true)
  public List<LocaleDTO> findAllLocales() {
    return localeRepository.findAll().stream()
        .map(LocaleMapper::fromEntity)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public LocaleDTO findLocaleById(Long id) {
    Locale locale = localeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Locale not found"));
    return LocaleMapper.fromEntity(locale);
  }

  @Transactional
  public LocaleDTO updateLocale(Long id, LocaleDTO localeDTO) {
    if (!localeRepository.existsById(id)) {
      throw new NotFoundException("Locale not found");
    }
    Locale locale = LocaleMapper.toEntity(localeDTO);
    locale.setId(id);
    locale = localeRepository.save(locale);
    return LocaleMapper.fromEntity(locale);
  }

  @Transactional
  public void deleteLocale(Long id) {
    if (!localeRepository.existsById(id)) {
      throw new NotFoundException("Locale not found");
    }
    localeRepository.deleteById(id);
  }
}
