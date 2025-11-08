package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

// @Repository es un estereotipo que marca la clase como un componente de acceso a datos
@Repository
public class TareaRepository {

    // Lista en memoria para almacenar las tareas (simula una base de datos)
    private final List<Tarea> tareasEnMemoria = new ArrayList<>();

    // Contador atómico para generar IDs de manera segura y automática
    private final AtomicLong contadorId = new AtomicLong(0);

    /**
     * Constructor para inicializar la lista con tareas de ejemplo.
     */
    public TareaRepository() {
        save(new Tarea("Configurar el proyecto base de Spring Boot", Prioridad.ALTA));
        save(new Tarea("Crear el modelo Tarea y el Enum Prioridad", Prioridad.ALTA));
        save(new Tarea("Implementar TareaRepository en memoria", Prioridad.MEDIA));
        save(new Tarea("Comprar café", Prioridad.BAJA));
    }

    /**
     * Guarda una tarea. Si el ID es nulo, genera uno nuevo y lo agrega;
     * de lo contrario, actualiza la tarea existente.
     */
    public Tarea save(Tarea tarea) {
        if (tarea.getId() == null) {
            // Genera ID automático y agrega la tarea
            tarea.setId(contadorId.incrementAndGet());
            tareasEnMemoria.add(tarea);
        } else {
            // Lógica de actualización (elimina el viejo y agrega el nuevo)
            deleteById(tarea.getId());
            tareasEnMemoria.add(tarea);
        }
        return tarea;
    }

    /**
     * Obtiene todas las tareas.
     * @return Lista de todas las tareas.
     */
    public List<Tarea> findAll() {
        return new ArrayList<>(tareasEnMemoria); // Retorna una copia para evitar modificación externa
    }

    /**
     * Busca una tarea por su ID.
     */
    public Optional<Tarea> findById(Long id) {
        return tareasEnMemoria.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    /**
     * Elimina una tarea por su ID.
     */
    public void deleteById(Long id) {
        tareasEnMemoria.removeIf(t -> t.getId().equals(id));
    }
}
