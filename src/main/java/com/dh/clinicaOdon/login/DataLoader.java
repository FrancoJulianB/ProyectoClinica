package com.dh.clinicaOdon.login;


import com.dh.clinicaOdon.login.entities.Rol;
import com.dh.clinicaOdon.login.entities.Usuario;
import com.dh.clinicaOdon.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UsuarioRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("user");
        repository.save(new Usuario("Franco", "user", "user", password, Rol.USER));

        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String password2 = passwordEncoder2.encode("admin");
        repository.save(new Usuario("Franco", "admin", "admin", password2, Rol.ADMIN));
    }
}
