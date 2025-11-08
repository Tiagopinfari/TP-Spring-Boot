package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

// Se activa solo cuando el profile 'prod' está activo
@Service
@Profile("prod")
public class MensajeProdService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("\nAplicación de Tareas INICIADA en ENTORNO DE PRODUCCIÓN.");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("\nAplicación de Tareas DETENIDA.");
    }
}
