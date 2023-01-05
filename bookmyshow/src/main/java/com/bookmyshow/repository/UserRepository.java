package com.bookmyshow.repository;

import com.bookmyshow.entity.Ticket;
import com.bookmyshow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
