package com.medicare.repository;

import com.medicare.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // buscar pacientes cuyo nombre contenga el texto, sin distinguir mayusculas
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);
}
