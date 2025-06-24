package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.User;
import com.example.demo.Enum.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
   
	User findByEmail(String email);                         

    User findByEmailAndPassword(String email, String password); 

    List<User> findByRole(UserRole role);                    

    boolean existsByEmail(String email);
	
	
}
