package com.dh.clinicaOdon.login.service;

import com.dh.clinicaOdon.login.entities.Usuario;
import com.dh.clinicaOdon.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {


    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return usuarioRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("no encontre el mail"));
    }

    public Usuario guardar(Usuario usuario){
        return usuarioRepository.save(usuario);
    };
}
