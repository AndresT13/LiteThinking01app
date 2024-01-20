package com.microservices.one.controllers;

import com.microservices.one.exception.BadRequestException;
import com.microservices.one.exception.ResourceNotFoundException;
import com.microservices.one.models.dto.ClientDto;
import com.microservices.one.models.dto.CountryDto;
import com.microservices.one.models.payload.MessageResponse;
import com.microservices.one.services.ICountryService;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CountryController {

    private final ICountryService countryService;

    @Autowired
    public CountryController(ICountryService countryService){
        this.countryService = countryService;
    }

@GetMapping("/countries")
    public ResponseEntity<?> getCountry() {
        List<CountryDto> getList = countryService.findAll();
        if(getList == null || getList.isEmpty()){
            throw new ResourceNotFoundException("Paises");
        }
        return  new ResponseEntity<>(MessageResponse.builder()
                .message("")
                .object("")
                .build(),
                HttpStatus.OK);
    }


    @GetMapping(path = "/country/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCountry(@PathVariable Long id) {
        CountryDto country = countryService.findById(id);

        if(country == null){
            throw new ResourceNotFoundException("Pais", "id",id);
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(CountryDto.builder()
                                .id(country.getId())
                                .nameCountry(country.getNameCountry())
                                .codeCountry(country.getCodeCountry())
                                .status(country.getStatus())
                                .build())
                        .build(),HttpStatus.OK
        );
    }

    @PostMapping(path ="country/create/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  createCountry(@RequestBody CountryDto country){
        CountryDto  countrySave = null;

        try{
            countrySave = countryService.createCountry(country);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Guardado correctamente")
                    .object(CountryDto.builder()
                            .id(countrySave.getId())
                            .nameCountry(countrySave.getNameCountry())
                            .codeCountry(countrySave.getCodeCountry())
                            .status(countrySave.getStatus())
                            .build())
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());
        }
    }


    @PutMapping(path = "/country/update/{id}")
    public ResponseEntity<?> updateCountry(@RequestBody CountryDto countryDto,@PathVariable Long id){
        CountryDto countryUpdate = null;

        try {
            countryDto.setId(id);
            countryUpdate = countryService.createCountry(countryDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Actualizado Correctamente")
                    .object(CountryDto.builder()
                            .id(countryUpdate.getId())
                            .nameCountry(countryUpdate.getNameCountry())
                            .codeCountry(countryUpdate.getCodeCountry())
                            .status(countryUpdate.getStatus())
                            .build())
                    .build(), HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }

    public ResponseEntity<?> removeCountry(@PathVariable Long id) {

        try {
            CountryDto country = countryService.findById(id);
            countryService.deleteCountry(country.getId());
            return new ResponseEntity<>(country, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt){
            throw new BadRequestException(exDt.getMessage());

        }
    }









}
