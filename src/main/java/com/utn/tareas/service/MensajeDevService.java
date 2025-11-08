package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

// Se activa solo cuando el profile 'dev' está activo
@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("\nBIENVENIDO al entorno de DESARROLLO (DEV)");
        System.out.println("Aquí puedes probar la aplicación con un límite de tareas amplio y logging detallado.");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("\nGracias por probar en DEV.");
    }
}
