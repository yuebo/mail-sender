package com.eappcat.mail.sender.repository;

import com.eappcat.mail.sender.entity.SystemParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemParameterRepository extends JpaRepository<SystemParameter,String> {
}
