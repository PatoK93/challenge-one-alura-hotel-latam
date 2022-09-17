package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class ReservaDAO {
	
    private Connection con;

    public ReservaDAO(Connection con) {
        this.con = con;
    }
    
    public void guardar(Reserva reserva) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO reservas "
                        + "(fechaEntrada, fechaSalida, valor, formaPago, idHuesped)"
                        + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setString(1, reserva.getFechaEntrada());
                statement.setString(2, reserva.getFechaSalida());
                statement.setInt(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());
                statement.setInt(5, reserva.getId());
    
                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                    	reserva.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertada la reserva: %s", reserva));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Reserva> listarReservasPorHuesped(Integer idHuesped) {
    	List<Reserva> reservas = new ArrayList<>();
    	
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT fechaEntrada, fechaSalida, valor, formaPago FROM reservas WHERE idHuesped = ?");

            try (statement) {
                statement.setInt(1, idHuesped);
                statement.execute();
                
                final ResultSet resultSet = statement.getResultSet();
                
                try (resultSet) {
                    while (resultSet.next()) {
                    	reservas.add(new Reserva(
                                resultSet.getString("fechaEntrada"),
                                resultSet.getString("fechaSalida"),
                                resultSet.getInt("valor"),
                                resultSet.getString("formaPago")));
            }
        }
                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
        }
            

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM resservas WHERE id = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(Integer id, String fechaEntrada, String fechaSalida, String formaPago) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE reservas SET "
                    + " fechaEntrada = ?, "
                    + " fechaSalida = ?,"
                    + " formaPago = ?,"
                    + " WHERE id = ?");

            try (statement) {
                statement.setString(1, fechaEntrada);
                statement.setString(2, fechaSalida);
                statement.setString(3, formaPago);
                statement.setInt(4, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    }
