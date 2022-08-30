package com.yansen.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class ProvinsiRequest implements Serializable {
    private Long id_provinsi;

    @NotEmpty(message = "Nama Provinsi is required")
    private String nama_provinsi;
}
