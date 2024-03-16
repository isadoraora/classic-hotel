package com.classichotel.controller;

import com.classichotel.model.LocaleDTO;
import com.classichotel.service.LocaleService;
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
@AllArgsConstructor
@RequestMapping("/locales")
public class LocaleController {

  private final LocaleService localeService;

  @PostMapping
  public ResponseEntity<LocaleDTO> createLocale(@RequestBody LocaleDTO localeDTO) {
    LocaleDTO newLocale = localeService.createLocale(localeDTO);
    return new ResponseEntity<>(newLocale, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<LocaleDTO>> getAllLocales() {
    List<LocaleDTO> locales = localeService.findAllLocales();
    return ResponseEntity.ok(locales);
  }

  @GetMapping("/{id}")
  public ResponseEntity<LocaleDTO> getLocaleById(@PathVariable Long id) {
    LocaleDTO locale = localeService.findLocaleById(id);
    return ResponseEntity.ok(locale);
  }

  @PutMapping("/{id}")
  public ResponseEntity<LocaleDTO> updateLocale(@PathVariable Long id, @RequestBody LocaleDTO localeDTO) {
    LocaleDTO updatedLocale = localeService.updateLocale(id, localeDTO);
    return ResponseEntity.ok(updatedLocale);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLocale(@PathVariable Long id) {
    localeService.deleteLocale(id);
    return ResponseEntity.noContent().build();
  }
}
