package com.employee.dao;

import com.employee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("from User u where u.username=:username and u.deleted=0")
	Optional<User> findByUsername(@Param("username") String username);

	Boolean existsByUsername(@Param("username") String username);

	Boolean existsByEmail(String email);

	@Query("from User u where u.deleted=0")
	List<User> getAllActiveUsers();

}
