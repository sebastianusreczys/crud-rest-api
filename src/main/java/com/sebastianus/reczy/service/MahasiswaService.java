package com.sebastianus.reczy.service;

import com.sebastianus.reczy.dao.MahasiswaDao;
import com.sebastianus.reczy.dto.CreateRequestMahasiswa;
import com.sebastianus.reczy.dto.CreateRequestUpdate;
import com.sebastianus.reczy.entity.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class MahasiswaService {

    @Autowired
    private MahasiswaDao mahasiswaDao;

    @Autowired
    private ValidationService validationService;

    public Mahasiswa add (CreateRequestMahasiswa request){
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setId(UUID.randomUUID().toString());
        mahasiswa.setNama(request.getNama());
        mahasiswa.setNim(request.getNim());
        mahasiswa.setEmail(request.getEmail());
        return mahasiswaDao.save(mahasiswa);
    }

    public Mahasiswa get(String nim){
        Mahasiswa mahasiswa = mahasiswaDao.findMahasiswaByNim(nim).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Nim Tidak ditemukan"));
        return mahasiswa;
    }
    public Page<Mahasiswa> getStudentsByPageAndSize(Pageable pageable) {
        return mahasiswaDao.findAll(pageable);
    }

    public Mahasiswa update(CreateRequestUpdate request){
        validationService.validate(request);
        Mahasiswa mahasiswa = mahasiswaDao.findById(request.getId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"id mahasiswa tidak ditemukan"));
        mahasiswa.setNama(request.getNama());
        mahasiswa.setEmail(request.getEmail());
        return mahasiswaDao.save(mahasiswa);
    }

    public void remove(String mahasiswaId){
        Mahasiswa mahasiswa = mahasiswaDao.findById(mahasiswaId).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"id mahasiswa tidak ditemukan"));
        mahasiswaDao.delete(mahasiswa);
    }
}
