package com.microservices.one.services;

import com.microservices.one.models.dto.ExpensesDto;
import com.microservices.one.utils.Mappers.MapperObjectsExpenses;

import java.util.List;

public interface IExpensivesServices extends MapperObjectsExpenses {

    List<ExpensesDto> findAll();

    ExpensesDto findById(Long id);

    ExpensesDto createExpenses(ExpensesDto expensesDto);

    void deleteExpenses(Long id);

    boolean existsById(long id);
}
