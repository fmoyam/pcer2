package com.pcer2.service_hardware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pcer2.service_hardware.model.Hardware;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
}