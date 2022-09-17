package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Huesped;
import modelo.Reserva;

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
    
    public Huesped listarHuesped(String mail, String pass) {
    	Huesped huesped = new Huesped();
    	
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT nombre, apellido, fechaNacimiento, nacionalidad, telefono, mail, pass FROM huespedes WHERE mail = ? AND pass = ?");

            try (statement) {
                statement.setString(1, mail);
                statement.setString(2, pass);
                statement.execute();
                
                final ResultSet resultSet = statement.getResultSet();
                
                try (resultSet) {
                    while (resultSet.next()) {
                    	huesped.setNombre(resultSet.getString("nombre"));
                    	huesped.setApellido(resultSet.getString("apellido"));
                    	huesped.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                    	huesped.setNacionalidad(resultSet.getString("nacionalidad"));
                    	huesped.setTelefono(resultSet.getString("telefono"));
                    	huesped.setMail(resultSet.getString("mail"));
                    	huesped.setPass(resultSet.getString("pass"));
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
    
    public List<Huesped> listarConReservas() {
        List<Huesped> resultado = new ArrayList<>();

        try {
            String sql = "SELECT r.fechaEntrada, r.fechaSalida, r.valor, r.formaPago, h.id ,h.nombre, h.apellido FROM reservas AS r "
            		+ "INNER JOIN huespedes AS h ON h.id = r.idHuesped";
            
            final PreparedStatement statement = con
                    .prepareStatement(sql);

            try (statement) {
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        int huespedId = resultSet.getInt("h.id");
                        String huespedNombre = resultSet.getString("h.nombre");
                        String huespedApellido = resultSet.getString("h.apellido");
                        
                        Huesped huesped = resultado
                            .stream()
                            .filter(hue -> hue.getId().equals(huespedId))
                            .findAny().orElseGet(() -> {
                                Huesped hue = new Huesped(
                                		huespedId, huespedNombre, huespedApellido);
                                resultado.add(hue);
                                
                                return hue;
                            });
                        
                        Reserva reserva = new Reserva(
                                resultSet.getString("r.fechaEntrada"),
                                resultSet.getString("r.fechaSalida"),
                                resultSet.getInt("r.valor"),
                                resultSet.getString("r.formaPago"));
                        
                        huesped.agregar(reserva);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
    
}
