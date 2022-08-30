package com.yansen.controllers;

import com.yansen.dtos.request.ProvinsiRequest;
import com.yansen.dtos.responses.CreateProvinsi;
import com.yansen.dtos.responses.ResponseData;
import com.yansen.entities.Provinsi;
import com.yansen.services.ProvinsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/provinsi")
public class ProvinsiController {

    @Autowired
    private ProvinsiService provinsiService;

    @PostMapping
    public ResponseEntity<ResponseData<CreateProvinsi>> addData(@Valid @RequestBody ProvinsiRequest request, Errors errors) {
        ResponseData<CreateProvinsi> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        CreateProvinsi response = provinsiService.save(request);
        responseData.setPayload(response);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CreateProvinsi>> getAllData() {
        List<CreateProvinsi> response = provinsiService.getAllProvinsi();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Provinsi findOne(@PathVariable("id") Long id) {
        return provinsiService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<CreateProvinsi>> updateData(@Valid @RequestBody ProvinsiRequest request, Errors errors) {
        ResponseData<CreateProvinsi> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        CreateProvinsi response = provinsiService.save(request);
        responseData.setPayload(response);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable Long id) {
        provinsiService.removeOne(id);
    }
}
