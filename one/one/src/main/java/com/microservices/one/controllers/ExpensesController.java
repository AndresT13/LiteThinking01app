package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.dto.ExpensesDto;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.IClientService;
import com.microservices.one.services.IExpensivesServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
public class ExpensesController {
    private final IExpensivesServices expensivesServices;

    @Autowired
    public ExpensesController(IExpensivesServices expensivesServices) {
        this.expensivesServices = expensivesServices;
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getClients() {
        List<ExpensesDto> getList = expensivesServices.findAll();
        if (getList == null || getList.isEmpty()) {
            throw new ResourceNotFoundException("Gastos");
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);

    }


    @GetMapping(path="/expenses/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClient(@PathVariable Long id) {

        ExpensesDto gastos = expensivesServices.findById(id);

        if (gastos == null) {
            throw new ResourceNotFoundException("Gastos", "id", id);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(ExpensesDto.builder()
                                .id(gastos.getId())
                                .amount(gastos.getAmount())
                                .concept(gastos.getConcept())
                                .codeExpenses(gastos.getCodeExpenses())
                                .price(gastos.getPrice())
                                .status(gastos.getStatus())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping(path = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClient(@RequestBody @Valid ExpensesDto expenses) {

        ExpensesDto expensesSave = null;
        try {
            expensesSave = expensivesServices.createExpenses(expenses);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(ExpensesDto.builder()
                            .id(expensesSave.getId())
                            .amount(expensesSave.getAmount())
                            .concept(expensesSave.getConcept())
                            .price(expenses.getPrice())
                            .status(expensesSave.getStatus())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }

    @PutMapping(path = "expenses/update/{numberDocument}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateClient(@RequestBody ExpensesDto expensesDto, @PathVariable("id") Long id) {
        ExpensesDto expensesUpdate = null;

        try {
            expensesDto.setId(id);
            expensesUpdate = expensivesServices.createExpenses(expensesDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Actualizado correctamente")
                    .object(ExpensesDto.builder()
                            .id(expensesUpdate.getId())
                            .amount(expensesUpdate.getAmount())
                            .concept(expensesUpdate.getConcept())
                            .price(expensesUpdate.getPrice())
                            .status(expensesUpdate.getStatus())
                            .build())
                    .build(),
                    HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());

        }

    }

    @DeleteMapping("expenses/delete/{id}")
    public  ResponseEntity<?> removeClient(@PathVariable Long id) throws Exception {

        try {
            ExpensesDto expenses = expensivesServices.findById(id);
            expensivesServices.deleteExpenses(expenses.getId());
            return new ResponseEntity<>(expenses, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());

        }
    }



}
