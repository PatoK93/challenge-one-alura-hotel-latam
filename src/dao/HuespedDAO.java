package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;

public class HuespedDAO {
	
    private Connection con;

    public HuespedDAO(Connection con) {
        this.con = con;
    }
    
    //solo se va a utilizar cuando dejemos registrar!
    public void guardar(Huesped huesped) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO huespedes "
                        + "(nombre, apellido, fechaNacimiento, Nacionalidad, Telefono, mail, pass)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setString(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setString(6, huesped.getMail());
                statement.setString(7, huesped.getPass());
    
                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        huesped.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el huesped: %s", huesped));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Lista todo, no sirve para este fin!
    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT nombre, apellido, fechaNacimiento, nacionalidad, telefono, mail, pass FROM huespedes");
    
            try (statement) {
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getString("fechaNacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getString("mail"),
                                resultSet.getString("pass")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
    
    public Huesped listarHuesped(String mail) {
    	Huesped huesped = new Huesped();
    	
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT nombre, apellido, fechaNacimiento, nacionalidad, telefono, mail, pass FROM huespedes WHERE mail = ?");

            try (statement) {
                statement.setString(1, mail);
                statement.execute();
                
                final ResultSet resultSet = statement.getResultSet();
                
                try (resultSet) {
                    while (resultSet.next()) {
                    	huesped.setNombre(resultSet.getString("nombre"));
                    	huesped.setNombre(resultSet.getString("apellido"));
                    	huesped.setNombre(resultSet.getString("fechaNacimiento"));
                    	huesped.setNombre(resultSet.getString("nacionalidad"));
                    	huesped.setNombre(resultSet.getString("telefono"));
                    	huesped.setNombre(resultSet.getString("mail"));
                    	huesped.setNombre(resultSet.getString("pass"));
            }
        }
                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return huesped;
        }
            
//TODO hacer que haga un borrado en cascada al eliminar un huesped, es decir, que elimine sus reservas!
    public int eliminar(String mail) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE mail = ?");

            try (statement) {
                statement.setString(1, mail);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, String mail, String pass, Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE huespedes SET "
                    + " nombre = ?, "
                    + " apellido = ?,"
                    + " fechaNacimiento = ?,"
                    + " nacionalidad = ?,"
                    + " telefono = ?,"
                    + " mail = ?,"
                    + " pass = ?"
                    + " WHERE id = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, fechaNacimiento);
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setString(6, mail);
                statement.setString(7, pass);
                statement.setInt(8, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
