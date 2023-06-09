package com.alva.rest.controller;

import com.alva.domain.dto.DTOCliente;
import com.alva.domain.services.ClienteService;
import com.alva.util.RaResponseService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = { "*" }, allowedHeaders = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // 1 Agregue clientes a una lista de clientes. Use lista de clientes. (Listado)
    @GetMapping ("/listar")
    public RaResponseService<List<DTOCliente>> listarCliente(){
        return clienteService.listarCliente();
    }

    //2.  Agregar nuevos clientes en la tabla mediante una ventana Swing.
    @PostMapping ("/agregar")
    public RaResponseService<DTOCliente> agregar(@RequestBody DTOCliente dtoCliente){
        return clienteService.agregar(dtoCliente);
    }


    // 3. Eliminar clientes por código.
    @DeleteMapping ("eliminar/{id}")
    public RaResponseService<Integer> eliminar(@PathVariable Integer id){
        return clienteService.eliminar(id);
    }

    // 4. Actualizar los datos de un registro de la tabla clientes mediante una ventana Swing que haga la búsqueda del registro a ser actualizado
    @PostMapping ("/actualizar")
    public RaResponseService<DTOCliente> actualizar(@RequestBody DTOCliente dtoCliente){
        return clienteService.actualizar(dtoCliente);
    }

//    @PostMapping ("/filtrar")
//    public RaResponseService<List<DTOCliente>> filtrar(@RequestBody DTOCliente dtoCliente){
//        return clienteService.filtrar(dtoCliente);
//    }

    // 5. Listar Clientes por: código, nombre o apellido.
    @GetMapping ("/listarcondicion/{busqueda}")
    public RaResponseService<List<DTOCliente>> listarClienteCondiciones(@PathVariable String busqueda){
        return clienteService.listarClientesCondiciones(busqueda);
    }
    // 6 listar
    @GetMapping ("/listarTabla")
    public RaResponseService<List<DTOCliente>> listarTabla(){
        return clienteService.listarCliente();
    }



    @PostMapping("/descargarpdfPago")
    public void descargarpdfPago(@RequestBody DTOCliente dtoCliente, ExpiresFilter.XHttpServletResponse response)
            throws IOException, JRException, SQLException {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=\"OrdenPagoObligado.pdf\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(clienteService.generarPdf(dtoCliente), out);
    }

}
