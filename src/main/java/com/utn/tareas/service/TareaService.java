package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository repository;

    // Inyección de Propiedades con @Value
    @Value("${app.nombre}")
    private String nombreAplicacion;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;


    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    /**
     * Agrega una nueva tarea.
     * Incluye validación del límite de max-tareas.
     */
    public Tarea agregarTarea(String descripcion, Prioridad prioridad) {
        // Validación de límite de tareas
        if (repository.findAll().size() >= maxTareas) {
            System.err.println("ERROR: No se puede agregar la tarea. Se ha superado el límite máximo de " + maxTareas + " tareas.");
            return null; // Indica que la operación falló debido al límite
        }

        Tarea nuevaTarea = new Tarea(descripcion, prioridad);
        return repository.save(nuevaTarea);
    }

    // Métodos de Listado

    public List<Tarea> listarTodas() {
        return repository.findAll();
    }

    public List<Tarea> listarPendientes() {
        return repository.findAll().stream()
                .filter(tarea -> !tarea.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> listarCompletadas() {
        return repository.findAll().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    public Optional<Tarea> marcarComoCompletada(Long id) {
        Optional<Tarea> tareaOptional = repository.findById(id);

        if (tareaOptional.isPresent()) {
            Tarea tarea = tareaOptional.get();
            tarea.setCompletada(true);
            repository.save(tarea);
            return Optional.of(tarea);
        }
        return Optional.empty();
    }


    /**
     * Obtiene estadísticas de las tareas (total, completadas, pendientes).
     * Solo se muestra si 'app.mostrar-estadisticas' es true.
     */
    public String obtenerEstadisticas() {
        if (!mostrarEstadisticas) {
            return "\n--- Estadísticas ---\nLas estadísticas están deshabilitadas por configuración (app.mostrar-estadisticas=false).\n--------------------";
        }

        List<Tarea> todas = repository.findAll();
        long total = todas.size();
        long completadas = todas.stream().filter(Tarea::isCompletada).count();
        long pendientes = total - completadas;

        return String.format(
                "\n--- Estadísticas de Tareas (%s) ---\n" +
                        "Total: %d\n" +
                        "Completadas: %d\n" +
                        "Pendientes: %d\n" +
                        "------------------------------",
                nombreAplicacion, total, completadas, pendientes
        );
    }

    /**
     * Imprime las propiedades de configuración inyectadas.
     */
    public void imprimirConfiguracion() {
        System.out.println("\n==============================================");
        System.out.println("         CONFIGURACIÓN DE LA APLICACIÓN       ");
        System.out.println("==============================================");
        System.out.println("Nombre: " + nombreAplicacion);
        System.out.println("Límite Máximo de Tareas (app.max-tareas): " + maxTareas);
        System.out.println("Mostrar Estadísticas (app.mostrar-estadisticas): " + mostrarEstadisticas);
        System.out.println("==============================================");
    }

}
