package com.classichotel.repository;

import com.classichotel.entity.Locale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaleRepository extends JpaRepository<Locale, Long> {
}
