package com.alva.domain.services;

import com.alva.domain.dto.DTOCliente;
import com.alva.domain.infraestructure_data.repository.ClienteRepository;
import com.alva.util.RaResponseService;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import org.apache.ibatis.session.SqlSessionFactory;
import net.sf.jasperreports.engine.JRException;


@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Override
    public RaResponseService<List<DTOCliente>> listarCliente() {

        RaResponseService<List<DTOCliente>> raResponseService = new RaResponseService<>();
        try {
            raResponseService.content = clienteRepository.listarClientes();
        }catch (Exception e){
            raResponseService.mensajeError = e.getMessage();
        }
        return raResponseService;
    }

    @Override
    public RaResponseService<DTOCliente> agregar(DTOCliente dtoCliente) {

        RaResponseService<DTOCliente> raResponseService = new RaResponseService<>();
        try {
            clienteRepository.agregar(dtoCliente);
            raResponseService.content = dtoCliente;
        }catch (Exception e){
            System.out.println(e);
        }
        return raResponseService;
    }

    @Override
    public RaResponseService<Integer> eliminar(Integer id) {
        RaResponseService<Integer> raResponseService = new RaResponseService<>();
        try {
            clienteRepository.elimina(id);
            raResponseService.content = id;
        }catch (Exception e){
            raResponseService.mensajeError = e.getMessage();
        }
        return raResponseService;
    }

    @Override
    public RaResponseService<DTOCliente> actualizar(DTOCliente dtoCliente) {
        RaResponseService<DTOCliente> raResponseService = new RaResponseService<>();
        try {
            clienteRepository.actualizar(dtoCliente);
            raResponseService.content = dtoCliente;
        }catch (Exception e){
            raResponseService.mensajeError = e.getMessage();
        }
        return raResponseService;
    }

    @Override
    public RaResponseService<List<DTOCliente>> listarClientesCondiciones(String busqueda) {

        RaResponseService<List<DTOCliente>> raResponseService = new RaResponseService<>();
        try {
            raResponseService.content = clienteRepository.listarClientesCondicioes(busqueda);
        }catch (Exception e){
            System.out.println(e);
        }
        return raResponseService;
    }

    @Override
    public JasperPrint generarPdf(DTOCliente dtoCliente) throws JRException, IOException, SQLException{

        String dato;
        InputStream inputFile;
        String parametro2 = "";
        String parametro1;
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");


        inputFile = this.getClass().getResourceAsStream("/reporte_pago_all.jasper");

        ///////////////////// FORMA RECOMENDADA DE ABRIR EL RECURSO Y LA CONEXION A LA
        ///////////////////// BD
        try (var session = sqlSessionFactory.openSession(); Connection conexion =
                session.getConnection()) {
            BufferedImage image = ImageIO
                    .read(Objects.requireNonNull(getClass().getResource("/oefa_logo.png")));
            Map<String, Object> parametrosReport = new HashMap<>();
            parametrosReport.put("logo", image);
            /*parametrosReport.put("pagoFechaInicio", dtopago.pagoFechaInicio);
            parametrosReport.put("pagoFechafin", dtopago.pagoFechafin);
            parametrosReport.put("codigoAutogenerado", dtopago.codigoAutogenerado);
            parametrosReport.put("concepto", dtopago.concepto);
            parametrosReport.put("param1", parametro1);
            parametrosReport.put("param2", parametro2);
            parametrosReport.put("ruc", dtopago.ruc);*/
            return JasperFillManager.fillReport(inputFile, parametrosReport, conexion);
        }
    }

}
