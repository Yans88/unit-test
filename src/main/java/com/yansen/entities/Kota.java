package com.yansen.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "kota")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Kota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kota")
    private Long kotaId;

    @Column(name = "nama_kota", length = 150)
    private String nama;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinsi_id")
    private Provinsi provinsi;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
