package com.vortexpolis.dto;

import java.time.LocalDateTime;

public class FuncionDTO {

    private Long id;
    private Long peliculaId;
    private Long salaId;
    private LocalDateTime fechaHora;
    private Boolean estado;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Long peliculaId) {
        this.peliculaId = peliculaId;
    }

    public Long getSalaId() {
        return salaId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setSalaId(Long salaId) {
        this.salaId = salaId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
