package com.medicare.repository;

import com.medicare.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Paciente.
 * Incluye consulta personalizada para buscar por nombre (contiene texto).
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    /**
     * Consulta personalizada (b): Lista todos los pacientes cuyo nombre
     * contenga el texto dado, sin distinguir mayúsculas de minúsculas.
     *
     * @param nombre fragmento de nombre a buscar
     * @return lista de pacientes que coinciden
     */
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);
}
