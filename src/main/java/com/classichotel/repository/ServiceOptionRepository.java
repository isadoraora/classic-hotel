package com.classichotel.repository;

import com.classichotel.entity.ServiceOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOptionRepository extends JpaRepository<ServiceOption, Long> {
}

