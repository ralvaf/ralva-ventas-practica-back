package com.alva.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DTOCliente {

    private Integer codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Date fechaRegistro;
}
