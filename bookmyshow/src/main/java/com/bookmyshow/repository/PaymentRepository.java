package com.bookmyshow.repository;

import com.bookmyshow.entity.Actor;
import com.bookmyshow.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
