package com.yansen.controllerstest;

import com.yansen.dtos.request.ProvinsiRequest;
import com.yansen.repositories.ProvinsiRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProvinsiControllerTest {

    @Autowired
    private ProvinsiRepo provinsiRepo;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        provinsiRepo.findAll();
    }

    @Test
    void validProvinsiRequest_whenCreateProvinsi_thenReturnLastInsertID() throws Exception {
        ProvinsiRequest request = new ProvinsiRequest();
        request.setNama_provinsi("Lampung");
        mockMvc.perform(post("/api/provinsi")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("$.payload.id_provinsi").value(1))
                .andExpect(jsonPath("$.payload.nama_provinsi").value("Lampung"));
    }

    @Test
    void shouldReturn400WhenCreateNewProvinsiWithoutNamaProvinsi() throws Exception {
        ProvinsiRequest request = new ProvinsiRequest();
        request.setNama_provinsi("");
        mockMvc.perform(post("/api/provinsi")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.messages[0]").value("Nama Provinsi is required"));
        //.andExpect(jsonPath("$.payload").value(IsNull.nullValue(null)));
    }

    @Test
    public void getProvinsiList() throws Exception {
        String uri = "/api/provinsi";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
    }


}
