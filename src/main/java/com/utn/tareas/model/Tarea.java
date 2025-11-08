package com.utn.tareas.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
    private Long id;
    private String descripcion;
    private boolean completada;
    private Prioridad prioridad;

    public Tarea(String descripcion, Prioridad prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.completada = false; // Por defecto, una nueva tarea no está completada
    }
}
