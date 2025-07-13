package com.montecafe;

import com.montecafe.dao.ProductoDAO;
import com.montecafe.modelo.Producto;

import javax.swing.*;
import java.util.ArrayList;

public class InventarioGUI extends JFrame {

    private JTextArea txtArea;
    private ProductoDAO dao;

    public InventarioGUI() {
        dao = new ProductoDAO();

        setTitle("Inventario Montecafe");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBounds(20, 20, 160, 30);
        add(btnAgregar);

        JButton btnListar = new JButton("Listar Inventario");
        btnListar.setBounds(200, 20, 160, 30);
        add(btnListar);

        txtArea = new JTextArea();
        txtArea.setBounds(20, 70, 340, 220);
        add(txtArea);

        btnAgregar.addActionListener(e -> agregarProducto());
        btnListar.addActionListener(e -> listarProductos());
    }

    private void agregarProducto() {
        String nombre = JOptionPane.showInputDialog("Nombre del producto:");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));

        Producto p = new Producto(0, nombre, cantidad, precio);
        dao.insertar(p);
        JOptionPane.showMessageDialog(this, "Producto registrado.");
    }

    private void listarProductos() {
        ArrayList<Producto> lista = dao.listar();
        txtArea.setText("INVENTARIO:\n");
        for (Producto p : lista) {
            txtArea.append(p.getId() + " | " + p.getNombre() + " | " + p.getCantidad() + " | $" + p.getPrecio() + "\n");
        }
    }

    public static void main(String[] args) {
        new InventarioGUI().setVisible(true);
    }
}