package com.montecafe;

import com.montecafe.dao.PedidoDAO;
import com.montecafe.modelo.Pedido;

import javax.swing.*;
import java.util.Date;

public class VentaPedidoGUI extends JFrame {

    private JTextArea txtArea;
    private PedidoDAO pdao;

    public VentaPedidoGUI() {
        pdao = new PedidoDAO();

        setTitle("Pedidos y Ventas Resumen");
        setSize(450, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnPedido = new JButton("Registrar Pedido");
        btnPedido.setBounds(20, 20, 180, 30);
        add(btnPedido);

        JButton btnResumen = new JButton("Resumen de Ventas");
        btnResumen.setBounds(220, 20, 180, 30);
        add(btnResumen);

        txtArea = new JTextArea();
        txtArea.setBounds(20, 70, 380, 280);
        add(txtArea);

        btnPedido.addActionListener(e -> registrarPedido());
        btnResumen.addActionListener(e -> mostrarResumen());
    }

    private void registrarPedido() {
        int clienteId = Integer.parseInt(JOptionPane.showInputDialog("ID Cliente:"));
        String producto = JOptionPane.showInputDialog("Producto:");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
        double valorUnitario = Double.parseDouble(JOptionPane.showInputDialog("Valor unitario:"));

        Pedido p = new Pedido(0, new Date(), clienteId, producto, cantidad, valorUnitario);
        pdao.insertar(p);

        JOptionPane.showMessageDialog(this, "Pedido registrado exitosamente.");
    }

    private void mostrarResumen() {
        txtArea.setText("RESUMEN DE VENTAS:\n");
        for (Pedido p : pdao.listar()) {
            double total = p.getCantidad() * p.getValorUnitario();
            txtArea.append("Cliente ID: " + p.getClienteId() + " | " +
                    p.getProducto() + " | Cantidad: " + p.getCantidad() +
                    " | Valor Total: " + total + "\n");
        }
    }

    public static void main(String[] args) {
        new VentaPedidoGUI().setVisible(true);
    }
}