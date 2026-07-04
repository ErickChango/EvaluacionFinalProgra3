package com.medicare.repository;

import com.medicare.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // buscar usuario por nombre de usuario para el login
    Optional<Usuario> findByUsername(String username);
}
