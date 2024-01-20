package com.microservices.one.services.impl;

import com.microservices.one.models.dto.ExpensesDto;
import com.microservices.one.models.entities.ExpensesEntity;
import com.microservices.one.repositories.dao.ExpensesDao;
import com.microservices.one.services.IExpensivesServices;
import com.microservices.one.utils.Mappers.MapperObjectsCountry;
import com.microservices.one.utils.Mappers.MapperObjectsExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensiveServiceImpl implements IExpensivesServices {

    private final ExpensesDao expenseRepository;

    @Autowired
    public ExpensiveServiceImpl(ExpensesDao expenseRepository){
        this.expenseRepository = expenseRepository;
    }
    @Override
    public List<ExpensesDto> findAll() {
        List<ExpensesEntity> expensesEntityList = expenseRepository.findAll();
        List<ExpensesDto> expensesDtoList = new ArrayList<>();
            for(ExpensesEntity entity : expensesEntityList)
                expensesDtoList.add(MapperObjectsExpenses.ExpensesEntityToExpensesDto(entity));
        return expensesDtoList;
    }

    @Override
    public ExpensesDto findById(Long id) {
        Optional<ExpensesEntity> entity = expenseRepository.findById(id);
        ExpensesDto expensesDto = null;

        if(entity.isPresent())
            expensesDto = MapperObjectsExpenses.ExpensesEntityToExpensesDto(entity.get());
        return expensesDto;
    }

    @Override
    public ExpensesDto createExpenses(ExpensesDto expensesDto) {
        Optional<ExpensesEntity> entity = expenseRepository.findById(expensesDto.getId());
        ExpensesEntity expensesEntity = null;
        if(!entity.isPresent()){
            expensesEntity = expenseRepository.save(MapperObjectsExpenses.ExpensesDtoToExpensesEntity(expensesDto));
        }
        return MapperObjectsExpenses.ExpensesEntityToExpensesDto(expensesEntity);
    }

    @Override
    public void deleteExpenses(Long id) {
        Optional<ExpensesEntity> expensesEntity = expenseRepository.findById(id);

        if(expensesEntity.isPresent())
            expensesEntity.get().setStatus(0);
            expenseRepository.save(expensesEntity.get());
    }

    @Override
    public boolean existsById(long id) {
        return expenseRepository.existsById(id);
    }
}
