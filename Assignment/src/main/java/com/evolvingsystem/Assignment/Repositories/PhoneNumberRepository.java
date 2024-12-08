package com.evolvingsystem.Assignment.Repositories;

import com.evolvingsystem.Assignment.Entity.PhoneNumber;
import com.evolvingsystem.Assignment.Entity.PhoneNumberCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> , JpaSpecificationExecutor<PhoneNumber> {
    List<PhoneNumber> findByAreaCodeAndStateAndCategory(String areaCode, String state, PhoneNumberCategory category);
    Optional<PhoneNumber> findByNumber(String number);
}

