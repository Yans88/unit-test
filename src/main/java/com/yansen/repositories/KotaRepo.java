package com.yansen.repositories;

import com.yansen.entities.Kota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KotaRepo extends JpaRepository<Kota, Long> {
}
