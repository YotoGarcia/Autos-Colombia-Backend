package com.autoscolombia.parqueadero.config;

import com.autoscolombia.parqueadero.model.Rol;
import com.autoscolombia.parqueadero.model.Usuario;
import com.autoscolombia.parqueadero.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    private final UsuarioService usuarioService;

    public DataInitializer(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Bean
    public CommandLineRunner initAdmin() {
        return args -> {
            if (usuarioService.buscarPorEmail("admin@admin.com").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador Principal");
                admin.setEmail("admin@admin.com");
                admin.setPassword("admin123"); // Se encripta dentro del service
                admin.setRol(Rol.ADMINISTRADOR);
                usuarioService.registrar(admin);
                System.out.println("Usuario administrador creado: admin@admin.com / admin123");
            }
        };
    }
}
