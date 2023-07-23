package com.sebastianus.reczy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRequestMahasiswa {
    private String id;
    private String nim;
    private String nama;
    private String email;

}
