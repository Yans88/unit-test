package com.yansen.services;

import com.yansen.dtos.request.ProvinsiRequest;
import com.yansen.dtos.responses.CreateProvinsi;
import com.yansen.entities.Provinsi;
import com.yansen.repositories.ProvinsiRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvinsiService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private ProvinsiRepo provinsiRepo;

    public CreateProvinsi save(ProvinsiRequest request) {
        Provinsi provinsi = convertToEntity(request);
        return convertToDto(provinsiRepo.save(provinsi));
    }

    public List<CreateProvinsi> getAllProvinsi() {
        List<Provinsi> provinsi = provinsiRepo.findAll();
        if (!provinsi.isEmpty()) {
            return provinsi.stream()
                    .map(prov -> modelMapper.map(prov, CreateProvinsi.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Provinsi findOne(Long id) {
        Optional<Provinsi> provinsi = provinsiRepo.findById(id);
        if (!provinsi.isPresent()) {
            return null;
        }
        return provinsi.get();
    }

    public void removeOne(Long id) {
        provinsiRepo.deleteById(id);
    }

    private Provinsi convertToEntity(ProvinsiRequest request) {
        return modelMapper.map(request, Provinsi.class);
    }

    private CreateProvinsi convertToDto(Provinsi provinsi) {
        return modelMapper.map(provinsi, CreateProvinsi.class);
    }
}
