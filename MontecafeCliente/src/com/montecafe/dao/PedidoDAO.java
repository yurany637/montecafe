package com.montecafe.dao;

import com.montecafe.conexion.ConexionBD;
import com.montecafe.modelo.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class PedidoDAO {

    public void insertar(Pedido p) {
        String sql = "INSERT INTO pedido (fecha, cliente_id, producto, cantidad, valor_unitario) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(p.getFecha().getTime()));
            ps.setInt(2, p.getClienteId());
            ps.setString(3, p.getProducto());
            ps.setInt(4, p.getCantidad());
            ps.setDouble(5, p.getValorUnitario());
            ps.executeUpdate();
            System.out.println("Pedido registrado.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public ArrayList<Pedido> listar() {
        ArrayList<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pedido p = new Pedido(
                    rs.getInt("id"),
                    new Date(rs.getTimestamp("fecha").getTime()),
                    rs.getInt("cliente_id"),
                    rs.getString("producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("valor_unitario")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
}