package com.alva.domain.infraestructure_data.mapper;

import com.alva.domain.dto.DTOCliente;
import org.apache.ibatis.jdbc.SQL;

public class ClienteMapper {

    public String listarCliente() {
        return new SQL() {
            {
                SELECT("c.codigo    codigo");
                SELECT("c.nombre    nombre");
                SELECT("c.apellido  apellido ");
                SELECT("c.email     email");
                SELECT("c.telefono  telefono");
                SELECT("c.fecha_registro  fechaRegistro");
                FROM("cliente c ");

            }
        }.toString();
    }

    public String filtrarCliente(DTOCliente dtoCliente) {
        return new SQL() {
            {
                SELECT("c.codigo    codigo");
                SELECT("c.nombre    nombre");
                SELECT("c.apellido  apellido ");
                SELECT("c.email     email");
                SELECT("c.telefono  telefono");
                SELECT("c.fecha_registro  fechaRegistro");
                FROM("cliente c ");
                WHERE("c.codigo = #{codigo, jdbcType=INTEGER} " +
                        " OR c.nombre = #{nombre, jdbcType=VARCHAR} " +
                        " OR c.apellido = #{apellido, jdbcType=VARCHAR} ");
            }
        }.toString();
    }
}


