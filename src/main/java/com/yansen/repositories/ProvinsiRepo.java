package com.yansen.repositories;

import com.yansen.entities.Provinsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinsiRepo extends JpaRepository<Provinsi, Long> {
}
