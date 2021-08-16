package com.ase.springboot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ase.springboot.persistence.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{

}