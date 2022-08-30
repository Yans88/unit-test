package com.yansen.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class KotaRequest implements Serializable {
    @JsonProperty("id_kota")
    private Long idKota;

    @JsonProperty("id_provinsi")
    private Long idProvinsi;

    @NotEmpty(message = "Nama Kota is required")
    @JsonProperty("nama_kota")
    private String namaKota;
}
