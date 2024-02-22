package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.model.entity.Application;
import com.sms.model.entity.id.ApplicationId;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {

}
