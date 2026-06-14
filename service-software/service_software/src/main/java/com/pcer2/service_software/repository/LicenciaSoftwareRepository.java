package com.pcer2.service_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcer2.service_software.model.LicenciaSoftware;

@Repository
public interface LicenciaSoftwareRepository extends JpaRepository<LicenciaSoftware, Long> {

}