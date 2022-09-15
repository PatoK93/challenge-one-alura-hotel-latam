package controller;

import java.util.List;

import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reserva;
import dao.ReservaDAO;

public class ReservaController {
	
    private ReservaDAO reservaDAO;

    public ReservaController() {
        var factory = new ConnectionFactory();
        this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
    }

    public void guardar (Reserva reserva) {
    	this.reservaDAO.guardar(reserva);
    }
    
    public List<Reserva> listarReservasPorHuesped(Integer idHuesped) {
    	return this.reservaDAO.listarReservasPorHuesped(idHuesped);
    }
    
    public int eliminar (Integer id) {
    	return this.reservaDAO.eliminar(id);
    }

    public int modificar(Integer id, String fechaEntrada, String fechaSalida, String formaPago) {
        return this.reservaDAO.modificar(id, fechaEntrada, fechaSalida, formaPago);
    }

}
