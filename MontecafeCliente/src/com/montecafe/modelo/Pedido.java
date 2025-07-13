package com.montecafe.modelo;

import java.util.Date;

public class Pedido {
    private int id;
    private Date fecha;
    private int clienteId;
    private String producto;
    private int cantidad;
    private double valorUnitario;

    public Pedido() {}

    public Pedido(int id, Date fecha, int clienteId, String producto, int cantidad, double valorUnitario) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.producto = producto;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(double valorUnitario) { this.valorUnitario = valorUnitario; }
}