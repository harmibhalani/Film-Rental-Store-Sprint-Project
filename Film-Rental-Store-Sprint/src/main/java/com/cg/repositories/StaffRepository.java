package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Short> {
    
    List<Staff> findByLastName(String lastName);
}