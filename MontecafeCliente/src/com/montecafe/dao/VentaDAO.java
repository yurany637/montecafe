
package com.montecafe.dao;

import com.montecafe.conexion.ConexionBD;
import com.montecafe.modelo.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class VentaDAO {

    public void insertar(Venta v) {
        String sql = "INSERT INTO venta (fecha, total) VALUES (?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(v.getFecha().getTime()));
            ps.setDouble(2, v.getTotal());
            ps.executeUpdate();
            System.out.println("Venta registrada.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public ArrayList<Venta> listar() {
        ArrayList<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta";
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Venta v = new Venta(
                    rs.getInt("id"),
                    new Date(rs.getTimestamp("fecha").getTime()),
                    rs.getDouble("total")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
}