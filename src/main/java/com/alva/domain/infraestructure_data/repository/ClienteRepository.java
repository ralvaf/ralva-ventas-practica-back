package com.alva.domain.infraestructure_data.repository;

import com.alva.domain.dto.DTOCliente;
import com.alva.domain.infraestructure_data.mapper.ClienteMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface ClienteRepository {
    @SelectProvider(type = ClienteMapper.class,method = "listarCliente")
    List<DTOCliente> listarClientes();


    @Select(value = "{CALL SP_AGREGAR("
            +"#{nombre,     javaType=String,	jdbcType=VARCHAR,	mode=IN},"
            +"#{apellido,   javaType=String,	jdbcType=VARCHAR,	mode=IN},"
            +"#{email,      javaType=String,	jdbcType=VARCHAR,	mode=IN},"
            +"#{telefono,   javaType=String,	jdbcType=VARCHAR,	mode=IN}"
            +")}")
    @Options(statementType = StatementType.CALLABLE)
    DTOCliente agregar( DTOCliente dtoCliente);

    @Select(value = "{CALL SP_ELIMINAR("
            +"#{id,     javaType=Integer,	jdbcType=INTEGER,	mode=IN}"
            +")}")
    @Options(statementType = StatementType.CALLABLE)
    DTOCliente elimina( Integer id);

    @Select(value = "{CALL SP_ACTUALIZAR("
            +"#{codigo,     javaType=Integer,	jdbcType=INTEGER,	mode=IN},"
            +"#{nombre,     javaType=String,	jdbcType=VARCHAR,	mode=IN},"
            +"#{apellido,   javaType=String,	jdbcType=VARCHAR,	mode=IN},"
            +"#{email,      javaType=String,	jdbcType=VARCHAR,	mode=IN},"
            +"#{telefono,   javaType=String,	jdbcType=VARCHAR,	mode=IN}"
            +")}")
    @Options(statementType = StatementType.CALLABLE)
    DTOCliente actualizar( DTOCliente dtoCliente);


    @Select("select * from cliente where codigo=#{busqueda} or nombre=#{busqueda} or apellido=#{busqueda} ")
    List<DTOCliente> listarClientesCondicioes(@Param("busqueda") String busqueda);

//    @SelectProvider(type = ClienteMapper.class,method = "filtrarCliente")
//    List<DTOCliente> filtrarCliente(DTOCliente dtoCliente);
}
