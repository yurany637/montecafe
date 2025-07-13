
package com.montecafe.dao;

import com.montecafe.conexion.ConexionBD;
import com.montecafe.modelo.Proveedor;

import java.sql.*;
import java.util.ArrayList;

public class ProveedorDAO {
    
  public void insertar(Proveedor p) {
        String sql = "INSERT INTO proveedor (nombre, nit, contacto, telefono) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getNit());
            ps.setString(3, p.getContacto());
            ps.setString(4, p.getTelefono());
            ps.executeUpdate();
            System.out.println("Proveedor insertado.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public ArrayList<Proveedor> listar() {
        ArrayList<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Proveedor p = new Proveedor(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("nit"),
                    rs.getString("contacto"),
                    rs.getString("telefono")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public void actualizar(Proveedor p) {
        String sql = "UPDATE proveedor SET nombre=?, nit=?, contacto=?, telefono=? WHERE id=?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getNit());
            ps.setString(3, p.getContacto());
            ps.setString(4, p.getTelefono());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
            System.out.println("Proveedor actualizado.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM proveedor WHERE id=?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Proveedor eliminado.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }  
    
}
