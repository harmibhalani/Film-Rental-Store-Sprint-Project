package com.cg.repositories;
 
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.cg.model.Address;
 
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
 
 
}