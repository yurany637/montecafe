package com.montecafe;

import com.montecafe.dao.ClienteDAO;
import com.montecafe.dao.PedidoDAO;
import com.montecafe.dao.ProductoDAO;
import com.montecafe.modelo.Cliente;
import com.montecafe.modelo.Pedido;
import com.montecafe.modelo.Producto;

import javax.swing.*;
import java.util.ArrayList;

public class ReporteGUI extends JFrame {

    private JTextArea txtArea;

    public ReporteGUI() {
        setTitle("Reportes Montecafe");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnClientes = new JButton("Clientes");
        btnClientes.setBounds(20, 20, 120, 30);
        add(btnClientes);

        JButton btnPedidos = new JButton("Pedidos");
        btnPedidos.setBounds(160, 20, 120, 30);
        add(btnPedidos);

        JButton btnInventario = new JButton("Inventario");
        btnInventario.setBounds(300, 20, 120, 30);
        add(btnInventario);

        txtArea = new JTextArea();
        txtArea.setBounds(20, 70, 440, 280);
        add(txtArea);

        btnClientes.addActionListener(e -> mostrarClientes());
        btnPedidos.addActionListener(e -> mostrarPedidos());
        btnInventario.addActionListener(e -> mostrarInventario());
    }

    private void mostrarClientes() {
        ClienteDAO dao = new ClienteDAO();
        txtArea.setText("CLIENTES:\n");
        for (Cliente c : dao.listarClientes()) {
            txtArea.append(c.getId() + " | " + c.getNombre() + " | " + c.getCorreo() + "\n");
        }
    }

    private void mostrarPedidos() {
        PedidoDAO dao = new PedidoDAO();
        txtArea.setText("PEDIDOS:\n");
        for (Pedido p : dao.listar()) {
            double total = p.getCantidad() * p.getValorUnitario();
            txtArea.append("Cliente: " + p.getClienteId() + " | " + p.getProducto() + " | Cant: " + p.getCantidad() + " | Total: " + total + "\n");
        }
    }

    private void mostrarInventario() {
        ProductoDAO dao = new ProductoDAO();
        txtArea.setText("INVENTARIO:\n");
        for (Producto p : dao.listar()) {
            txtArea.append(p.getId() + " | " + p.getNombre() + " | Cantidad: " + p.getCantidad() + "\n");
        }
    }

    public static void main(String[] args) {
        new ReporteGUI().setVisible(true);
    }
}