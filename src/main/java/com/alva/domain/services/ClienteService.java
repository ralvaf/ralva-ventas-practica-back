package com.alva.domain.services;

import com.alva.domain.dto.DTOCliente;
import com.alva.util.RaResponseService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ClienteService {

    RaResponseService<List<DTOCliente>> listarCliente();
    RaResponseService<DTOCliente> agregar(DTOCliente dtoCliente);
    RaResponseService<Integer> eliminar(Integer id);

    RaResponseService<DTOCliente> actualizar(@RequestBody DTOCliente dtoCliente);
    RaResponseService<List<DTOCliente>> listarClientesCondiciones(String busqueda);
    //RaResponseService<List<DTOCliente>> filtrar(@RequestBody DTOCliente dtoCliente);

    JasperPrint generarPdf(@RequestBody DTOCliente dtoCliente) throws JRException, IOException, SQLException;
}
