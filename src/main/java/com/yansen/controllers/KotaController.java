package com.yansen.controllers;

import com.yansen.dtos.request.KotaRequest;
import com.yansen.dtos.responses.CreateKota;
import com.yansen.dtos.responses.ResponseData;
import com.yansen.entities.Kota;
import com.yansen.services.KotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/kota")
public class KotaController {

    @Autowired
    private KotaService kotaService;

    @PostMapping
    public ResponseEntity<ResponseData<CreateKota>> addData(@Valid @RequestBody KotaRequest request, Errors errors) {
        ResponseData<CreateKota> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        CreateKota response = kotaService.save(request);
        responseData.setPayload(response);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CreateKota>> getAllData() {
        List<CreateKota> response = kotaService.getAllProvinsi();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Kota findOne(@PathVariable("id") Long id) {
        return kotaService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<CreateKota>> updateData(@Valid @RequestBody KotaRequest request, Errors errors) {
        ResponseData<CreateKota> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        CreateKota response = kotaService.save(request);
        responseData.setPayload(response);
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable Long id) {
        kotaService.removeOne(id);
    }
}
