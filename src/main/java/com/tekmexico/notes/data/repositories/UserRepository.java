package com.tekmexico.notes.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekmexico.notes.data.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByNameAndPassword(String name, String password);
}
