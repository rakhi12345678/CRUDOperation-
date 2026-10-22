package com.nsec.CRUDOperation.repository;

import com.nsec.CRUDOperation.model.RegUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RegUser,  Long> {

}