package com.medicare.security;

import com.medicare.entity.Usuario;
import com.medicare.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

// carga el usuario desde la base de datos para que spring security lo autentique
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + username));

        // agrego el prefijo ROLE_ que necesita spring security
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name());

        return new User(usuario.getUsername(), usuario.getPassword(), List.of(authority));
    }
}
