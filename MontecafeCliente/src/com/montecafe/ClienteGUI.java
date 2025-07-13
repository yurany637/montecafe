
package com.montecafe;

import com.montecafe.dao.ClienteDAO;
import com.montecafe.modelo.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClienteGUI extends JFrame {
    private JTextField txtNombre, txtCorreo, txtTelefono, txtId;
    private JTextArea txtArea;
    private ClienteDAO dao;

    public ClienteGUI() {
        dao = new ClienteDAO();

        setTitle("Gestión de Clientes - Montecafe");
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

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 80, 80, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(100, 80, 200, 25);
        add(txtCorreo);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 110, 80, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 110, 200, 25);
        add(txtTelefono);

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
        txtArea.setBounds(20, 230, 340, 100);
        add(txtArea);

        // Acciones

        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cliente c = new Cliente(0, txtNombre.getText(), txtCorreo.getText(), txtTelefono.getText());
                dao.insertarCliente(c);
                limpiarCampos();
                listarClientes();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                Cliente c = new Cliente(id, txtNombre.getText(), txtCorreo.getText(), txtTelefono.getText());
                dao.actualizarCliente(c);
                limpiarCampos();
                listarClientes();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                dao.eliminarCliente(id);
                limpiarCampos();
                listarClientes();
            }
        });
    }

    private void listarClientes() {
        txtArea.setText("");
        for (Cliente c : dao.listarClientes()) {
            txtArea.append(c.getId() + " | " + c.getNombre() + " | " + c.getCorreo() + " | " + c.getTelefono() + "\n");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }

    public static void main(String[] args) {
        new ClienteGUI().setVisible(true);
    }
}
