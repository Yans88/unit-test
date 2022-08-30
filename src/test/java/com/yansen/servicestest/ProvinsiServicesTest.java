package com.yansen.servicestest;

import com.yansen.dtos.request.ProvinsiRequest;
import com.yansen.dtos.responses.CreateProvinsi;
import com.yansen.entities.Provinsi;
import com.yansen.repositories.ProvinsiRepo;
import com.yansen.services.ProvinsiService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProvinsiServicesTest {

    @Mock
    ProvinsiRepo provinsiRepo;

    ModelMapper modelMapper = spy(new ModelMapper());

    @InjectMocks
    ProvinsiService serviceUnderTest = spy(new ProvinsiService());

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidRequest_whenCreateNewProvinsi_thenShouldBeCreated() {
        ProvinsiRequest request = new ProvinsiRequest();
        request.setNama_provinsi("Bali");
        Provinsi provinsi = modelMapper.map(request, Provinsi.class);
        when(provinsiRepo.save(any(Provinsi.class)))
                .thenReturn(provinsi);
        CreateProvinsi response = serviceUnderTest.save(request);
        assertThat(response.getIdProvinsi()).isNotNull();
        assertThat(response.getNamaProvinsi()).isEqualTo(request.getNama_provinsi());
    }

    @Test
    public void getAllProvinsiService() {
        List<CreateProvinsi> response = serviceUnderTest.getAllProvinsi();
        verify(provinsiRepo).findAll();
    }

}
