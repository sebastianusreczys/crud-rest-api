package com.sebastianus.reczy.controller;

import com.sebastianus.reczy.dto.CreateRequestMahasiswa;
import com.sebastianus.reczy.dto.CreateRequestUpdate;
import com.sebastianus.reczy.entity.Mahasiswa;
import com.sebastianus.reczy.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MahasiswaApiController {

    @Autowired
    private MahasiswaService mahasiswaService;

    @PostMapping(path = "/api/mahasiswa",
                        consumes = MediaType.APPLICATION_JSON_VALUE,
                        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMahasiswa(@RequestBody CreateRequestMahasiswa request){
        Mahasiswa mahasiswa = mahasiswaService.add(request);
        return ResponseEntity.ok(mahasiswa);
    }

    @GetMapping(
            path = "/api/mahasiswa/{nimMahasiswa}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMahasiswaByNim(@PathVariable(value = "nimMahasiswa") String nimMahasiswa){
        return ResponseEntity.ok(mahasiswaService.get(nimMahasiswa));
    }

    @GetMapping(
            path = "/api/mahasiswa/list",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllMahasiswa(Pageable pageable){
        return ResponseEntity.ok(mahasiswaService.getStudentsByPageAndSize(pageable));
    }

    @PutMapping(
            path = "/api/mahasiswa/{mahasiswaId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateMahasiswaByid(@RequestBody CreateRequestUpdate request,
                                                 @PathVariable String mahasiswaId){
        request.setId(mahasiswaId);
        return ResponseEntity.ok(mahasiswaService.update(request));
    }

    @DeleteMapping(
            path = "/api/mahasiswa/{mahasiswaId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> deleteMahasiswaByid(@PathVariable String mahasiswaId){
        mahasiswaService.remove(mahasiswaId);
        return ResponseEntity.ok("ok");
    }
}
