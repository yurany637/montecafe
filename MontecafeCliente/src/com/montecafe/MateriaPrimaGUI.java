package com.montecafe;

import com.montecafe.dao.MateriaPrimaDAO;
import com.montecafe.modelo.MateriaPrima;

import javax.swing.*;

public class MateriaPrimaGUI extends JFrame {

    private JTextField txtId, txtNombre, txtCantidad, txtProveedorId;
    private JTextArea txtArea;
    private MateriaPrimaDAO dao;

    public MateriaPrimaGUI() {
        dao = new MateriaPrimaDAO();

        setTitle("Gestión de Materia Prima");
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

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 80, 80, 25);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(100, 80, 200, 25);
        add(txtCantidad);

        JLabel lblProveedorId = new JLabel("ID Proveedor:");
        lblProveedorId.setBounds(20, 110, 100, 25);
        add(lblProveedorId);

        txtProveedorId = new JTextField();
        txtProveedorId.setBounds(120, 110, 180, 25);
        add(txtProveedorId);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.setBounds(20, 150, 100, 30);
        add(btnInsertar);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(130, 150, 80, 30);
        add(btnListar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(220, 150, 110, 30);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(20, 190, 100, 30);
        add(btnEliminar);

        txtArea = new JTextArea();
        txtArea.setBounds(20, 230, 340, 120);
        add(txtArea);

        // Acciones
        btnInsertar.addActionListener(e -> {
            MateriaPrima m = new MateriaPrima(0, txtNombre.getText(),
                    Integer.parseInt(txtCantidad.getText()),
                    Integer.parseInt(txtProveedorId.getText()));
            dao.insertar(m);
            limpiarCampos();
            listar();
        });

        btnListar.addActionListener(e -> listar());

        btnActualizar.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            MateriaPrima m = new MateriaPrima(id, txtNombre.getText(),
                    Integer.parseInt(txtCantidad.getText()),
                    Integer.parseInt(txtProveedorId.getText()));
            dao.actualizar(m);
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
        for (MateriaPrima m : dao.listar()) {
            txtArea.append(m.getId() + " | " + m.getNombre() + " | " + m.getCantidad() + " | Proveedor: " + m.getProveedorId() + "\n");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        txtProveedorId.setText("");
    }

    public static void main(String[] args) {
        new MateriaPrimaGUI().setVisible(true);
    }

}
