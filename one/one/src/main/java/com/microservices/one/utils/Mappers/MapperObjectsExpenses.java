package com.microservices.one.utils.Mappers;

import com.microservices.one.models.dto.ExpensesDto;
import com.microservices.one.models.entities.ExpensesEntity;

public interface MapperObjectsExpenses {

    public static ExpensesDto ExpensesEntityToExpensesDto(ExpensesEntity expensesEntity){
        return ExpensesDto.builder()
                .id(expensesEntity.getId())
                .codeExpenses(expensesEntity.getCodeExpenses())
                .price(expensesEntity.getPrice())
                .amount(expensesEntity.getAmount())
                .concept(expensesEntity.getConcept())
                .status(expensesEntity.getStatus())
                .build();
    }

    public static ExpensesEntity ExpensesDtoToExpensesEntity(ExpensesDto expensesDto) {
        return ExpensesEntity.builder()
                .id(expensesDto.getId())
                .codeExpenses(expensesDto.getCodeExpenses())
                .price(expensesDto.getPrice())
                .amount(expensesDto.getAmount())
                .concept(expensesDto.getConcept())
                .status(expensesDto.getStatus())
                .build();
    }
}
