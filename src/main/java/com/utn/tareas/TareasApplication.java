package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
// Implementamos CommandLineRunner para ejecutar lógica al inicio
public class TareasApplication implements CommandLineRunner {

    // Inyectamos TareaService y MensajeService por constructor
    private final TareaService tareaService;
    private final MensajeService mensajeService;

    /**
     * Inyección de dependencias por constructor para los servicios.
     * Spring inyectará automáticamente la implementación de MensajeService
     * (MensajeDevService o MensajeProdService) según el profile activo.
     */
    public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }

    /**
     * Método que se ejecuta justo después de que la aplicación Spring Boot se inicia.
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n------------------------------------------------");
        System.out.println("          INICIO DE PRUEBAS (CommandLineRunner) ");
        System.out.println("------------------------------------------------");

        // 1. Mostrar mensaje de bienvenida (usando MensajeService)
        mensajeService.mostrarBienvenida();

        // 2. Mostrar la configuración actual
        tareaService.imprimirConfiguracion();

        // 3. Listar todas las tareas iniciales
        System.out.println("\n--- 3. Tareas Iniciales ---");
        listarTareas(tareaService.listarTodas());

        // 4. Agregar una nueva tarea
        System.out.println("\n--- 4. Agregando Tarea de ALTA Prioridad ---");
        Tarea nuevaTarea = tareaService.agregarTarea("Terminar el Trabajo Práctico de Programación III", Prioridad.ALTA);
        if (nuevaTarea != null) {
            System.out.println("Tarea agregada: " + nuevaTarea);
        }
        // Intentamos agregar otra tarea para probar el límite (en DEV el límite es 10, en PROD es 1000)
        System.out.println("\n--- 4. Prueba de límite de tareas ---");
        tareaService.agregarTarea("Tarea extra para probar el límite", Prioridad.BAJA);


        // 5. Listar tareas pendientes
        System.out.println("\n--- 5. Tareas Pendientes ---");
        listarTareas(tareaService.listarPendientes());

        // 6. Marcar una tarea como completada (usaremos el ID 1 de las tareas iniciales)
        System.out.println("\n--- 6. Marcando Tarea con ID 1 como completada ---");
        tareaService.marcarComoCompletada(1L)
                .ifPresent(t -> System.out.println("Tarea completada: " + t));

        // 7. Mostrar estadísticas
        System.out.println("\n--- 7. Mostrando Estadísticas ---");
        System.out.println(tareaService.obtenerEstadisticas());

        // 8. Listar tareas completadas
        System.out.println("\n--- 8. Tareas Completadas ---");
        listarTareas(tareaService.listarCompletadas());

        // 9. Mostrar mensaje de despedida
        mensajeService.mostrarDespedida();

        System.out.println("\n------------------------------------------------");
        System.out.println("          FIN DE PRUEBAS (CommandLineRunner)      ");
        System.out.println("------------------------------------------------");
    }

    // Método auxiliar para imprimir listas de tareas de forma clara
    private void listarTareas(List<Tarea> tareas) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas en esta lista.");
            return;
        }
        tareas.forEach(System.out::println);
    }
}
