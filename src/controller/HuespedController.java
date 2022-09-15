package controller;

import factory.ConnectionFactory;
import dao.HuespedDAO;
import modelo.Huesped;

public class HuespedController {

    private HuespedDAO huespedDao;
    
    public HuespedController() {
        var factory = new ConnectionFactory();
        this.huespedDao = new HuespedDAO(factory.recuperaConexion());
    }

    //solo se va a utilizar cuando dejemos registrar!
    public void guardar (Huesped huesped) {
    	huespedDao.guardar(huesped);
    }
    
    public Huesped listarHuesped(String mail) {
    	return huespedDao.listarHuesped(mail);
    }
    
    public int eliminar(String mail) {
        return huespedDao.eliminar(mail);
    }
    
    public int modificar(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, String mail, String pass, Integer id) {
        return huespedDao.modificar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, mail, pass, id);
    }
	
}
