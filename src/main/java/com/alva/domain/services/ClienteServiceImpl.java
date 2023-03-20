package com.alva.domain.services;

import com.alva.domain.dto.DTOCliente;
import com.alva.domain.infraestructure_data.repository.ClienteRepository;
import com.alva.util.RaResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
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

//    @Override
//    public RaResponseService<List<DTOCliente>> filtrar(DTOCliente dtoCliente) {
//        RaResponseService<List<DTOCliente>> raResponseService = new RaResponseService<>();
//        try {
//            raResponseService.content = clienteRepository.filtrarCliente(dtoCliente);
//        }catch (Exception e){
//            raResponseService.mensajeError = e.getMessage();
//        }
//        return raResponseService;
//    }
}
