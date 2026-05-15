package com.pcer2.service_voucher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcer2.service_voucher.model.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long>{

}
