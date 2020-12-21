package com.example.javamvnspringbtblank.dao;

import com.example.javamvnspringbtblank.model.BasicNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MySQL Database Data Access Object (DAO) CRUD (Create Read Update Delete) repository.
 */
@Repository
public interface NotificationDao extends CrudRepository<BasicNotification, Long> {
}
