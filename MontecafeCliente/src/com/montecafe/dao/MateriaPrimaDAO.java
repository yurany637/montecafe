package com.montecafe.dao;

import com.montecafe.conexion.ConexionBD;
import com.montecafe.modelo.MateriaPrima;

import java.sql.*;
import java.util.ArrayList;

public class MateriaPrimaDAO {

    public void insertar(MateriaPrima m) {
        String sql = "INSERT INTO materia_prima (nombre, cantidad, proveedor_id) VALUES (?, ?, ?)";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getCantidad());
            ps.setInt(3, m.getProveedorId());
            ps.executeUpdate();
            System.out.println("Materia prima insertada.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }

    }

    public ArrayList<MateriaPrima> listar() {
        ArrayList<MateriaPrima> lista = new ArrayList<>();
        String sql = "SELECT * FROM materia_prima";
        try (Connection con = ConexionBD.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                MateriaPrima m = new MateriaPrima(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getInt("proveedor_id")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public void actualizar(MateriaPrima m) {
        String sql = "UPDATE materia_prima SET nombre=?, cantidad=?, proveedor_id=? WHERE id=?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getCantidad());
            ps.setInt(3, m.getProveedorId());
            ps.setInt(4, m.getId());
            ps.executeUpdate();
            System.out.println("Materia prima actualizada.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM materia_prima WHERE id=?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Materia prima eliminada.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}
