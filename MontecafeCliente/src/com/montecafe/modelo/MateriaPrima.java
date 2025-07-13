
package com.montecafe.modelo;


public class MateriaPrima {
    
private int id;
    private String nombre;
    private int cantidad;
    private int proveedorId;

    public MateriaPrima() {}

    public MateriaPrima(int id, String nombre, int cantidad, int proveedorId) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.proveedorId = proveedorId;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getProveedorId() { return proveedorId; }
    public void setProveedorId(int proveedorId) { this.proveedorId = proveedorId; }
}
