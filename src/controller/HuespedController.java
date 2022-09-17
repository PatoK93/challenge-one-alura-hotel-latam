package controller;

import factory.ConnectionFactory;
import java.util.List;
import dao.HuespedDAO;
import modelo.Huesped;

public class HuespedController {

    private HuespedDAO huespedDao;
    
    public HuespedController() {
    	ConnectionFactory factory = new ConnectionFactory();
        this.huespedDao = new HuespedDAO(factory.recuperaConexion());
    }

    public void guardar (Huesped huesped) {
    	huespedDao.guardar(huesped);
    }
    
    public List<Huesped> listar(){
    	return this.huespedDao.listar();
    }
    
    public Huesped listarHuesped(String mail, String pass) {
    	return huespedDao.listarHuesped(mail, pass);
    }
    
    public int eliminar(String mail) {
        return huespedDao.eliminar(mail);
    }
    
    public int modificar(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, String mail, String pass, Integer id) {
        return huespedDao.modificar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, mail, pass, id);
    }
    
    public List<Huesped> listarConReservas() {
        return this.huespedDao.listarConReservas();
    }
	
}
