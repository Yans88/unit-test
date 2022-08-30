package com.yansen.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Getter
@Setter
public class CreateKota implements Serializable {
    @JsonProperty("id_kota")
    private long kotaId;

    @JsonProperty("id_provinsi")
    private long idProvinsi;

    @JsonProperty("nama_kota")
    private String nama;

    @JsonProperty("nama_provinsi")
    private String namaProvinsi;

    @JsonIgnore
    private Date created_at;

    @JsonIgnore
    private Date updated_at;
}
