package com.vortexpolis.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CompraDTO {

    private Long id;
    private LocalDateTime fecha;
    private Double total;
    private String estado;

    private Long clienteId; // Solo referenciamos el ID del cliente

    private List<Long> entradasIds; // Lista de IDs de entradas asociadas

    private Long pagoId; // ID del pago asociado

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<Long> getEntradasIds() {
        return entradasIds;
    }

    public void setEntradasIds(List<Long> entradasIds) {
        this.entradasIds = entradasIds;
    }

    public Long getPagoId() {
        return pagoId;
    }

    public void setPagoId(Long pagoId) {
        this.pagoId = pagoId;
    }
}
