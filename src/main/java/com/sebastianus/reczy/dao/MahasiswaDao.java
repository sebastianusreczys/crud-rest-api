package com.sebastianus.reczy.dao;

import com.sebastianus.reczy.entity.Mahasiswa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MahasiswaDao extends CrudRepository<Mahasiswa, String> {
    Page<Mahasiswa> findAll(Pageable pageable);
    Optional<Mahasiswa> findMahasiswaByNim(String nim);
}
