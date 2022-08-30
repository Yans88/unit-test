package com.yansen.services;

import com.yansen.dtos.request.KotaRequest;
import com.yansen.dtos.responses.CreateKota;
import com.yansen.entities.Kota;
import com.yansen.repositories.KotaRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class KotaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KotaRepo kotaRepo;

    public CreateKota save(KotaRequest request) {
        Kota kota = convertToEntity(request);
        /*final Provinsi provinsi = provinsiService.findOne(request.getId_provinsi());
        if(provinsi != null){
           kota.getProvinsi_id().add
        }*/
        return convertToDto(kotaRepo.save(kota));
    }

    public List<CreateKota> getAllProvinsi() {
        List<Kota> kota = kotaRepo.findAll();
        if (!kota.isEmpty()) {
            return kota.stream()
                    .map(kt -> modelMapper.map(kt, CreateKota.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Kota findOne(Long id) {
        Optional<Kota> kota = kotaRepo.findById(id);
        if (!kota.isPresent()) {
            return null;
        }
        return kota.get();
    }

    public void removeOne(Long id) {
        kotaRepo.deleteById(id);
    }

    private Kota convertToEntity(KotaRequest request) {
        return modelMapper.map(request, Kota.class);
    }

    private CreateKota convertToDto(Kota kota) {
        return modelMapper.map(kota, CreateKota.class);
    }
}
