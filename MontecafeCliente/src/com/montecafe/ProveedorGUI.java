
package com.montecafe;

import com.montecafe.dao.ProveedorDAO;
import com.montecafe.modelo.Proveedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProveedorGUI extends JFrame {
    private JTextField txtId, txtNombre, txtNit, txtContacto, txtTelefono;
    private JTextArea txtArea;
    private ProveedorDAO dao;

    public ProveedorGUI() {
        dao = new ProveedorDAO();

        setTitle("Gestión de Proveedores");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 80, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 20, 200, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 50, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 50, 200, 25);
        add(txtNombre);

        JLabel lblNit = new JLabel("NIT:");
        lblNit.setBounds(20, 80, 80, 25);
        add(lblNit);

        txtNit = new JTextField();
        txtNit.setBounds(100, 80, 200, 25);
        add(txtNit);

        JLabel lblContacto = new JLabel("Contacto:");
        lblContacto.setBounds(20, 110, 80, 25);
        add(lblContacto);

        txtContacto = new JTextField();
        txtContacto.setBounds(100, 110, 200, 25);
        add(txtContacto);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 140, 80, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 140, 200, 25);
        add(txtTelefono);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(20, 180, 100, 30);
        add(btnInsertar);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(130, 180, 80, 30);
        add(btnListar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(220, 180, 110, 30);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(20, 220, 100, 30);
        add(btnEliminar);

        txtArea = new JTextArea();
        txtArea.setBounds(20, 260, 340, 100);
        add(txtArea);

        // Acciones

        btnInsertar.addActionListener(e -> {
            Proveedor p = new Proveedor(0, txtNombre.getText(), txtNit.getText(), txtContacto.getText(), txtTelefono.getText());
            dao.insertar(p);
            limpiarCampos();
            listar();
        });

        btnListar.addActionListener(e -> listar());

        btnActualizar.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            Proveedor p = new Proveedor(id, txtNombre.getText(), txtNit.getText(), txtContacto.getText(), txtTelefono.getText());
            dao.actualizar(p);
            limpiarCampos();
            listar();
        });

        btnEliminar.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            dao.eliminar(id);
            limpiarCampos();
            listar();
        });
    }

    private void listar() {
        txtArea.setText("");
        for (Proveedor p : dao.listar()) {
            txtArea.append(p.getId() + " | " + p.getNombre() + " | " + p.getNit() + " | " + p.getContacto() + " | " + p.getTelefono() + "\n");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtNit.setText("");
        txtContacto.setText("");
        txtTelefono.setText("");
    }

    public static void main(String[] args) {
        new ProveedorGUI().setVisible(true);
    }
}
