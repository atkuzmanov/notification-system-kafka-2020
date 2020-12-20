package com.example.javamvnspringbtblank.dao;

import com.example.javamvnspringbtblank.model.BasicNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends CrudRepository<BasicNotification, Long> {
}
