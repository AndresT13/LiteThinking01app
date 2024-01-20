package com.microservices.one.repositories.dao;

import com.microservices.one.models.entities.ExpensesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesDao extends JpaRepository<ExpensesEntity, Long> {
}
