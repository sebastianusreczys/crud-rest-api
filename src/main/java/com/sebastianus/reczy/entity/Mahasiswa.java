package com.sebastianus.reczy.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Mahasiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "uuid")
    private String id;

    @Column(name = "nim", length = 9, nullable = false)
    private String nim;

    @Column(name = "nama", length = 100, nullable = false)
    private String nama;

    @Column(name = "email", length = 100, nullable = false)
    private String email;
}
