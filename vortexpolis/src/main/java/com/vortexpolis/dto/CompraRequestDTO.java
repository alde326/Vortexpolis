// CompraRequestDTO.java
package com.vortexpolis.dto;

public class CompraRequestDTO {
    private Long clienteId;
    private Long metodoPagoId;
    private Long funcionId;
    private Integer cantidad;

    // Getters y Setters
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(Long metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public Long getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(Long funcionId) {
        this.funcionId = funcionId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
