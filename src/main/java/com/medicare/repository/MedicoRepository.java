package com.medicare.repository;

import com.medicare.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Medico.
 * Incluye consulta personalizada para buscar por especialidad.
 */
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    /**
     * Consulta personalizada (a): Lista todos los médicos de una especialidad dada.
     * Spring Data JPA navega Medico -> perfil (PerfilMedico) -> especialidad.
     *
     * @param especialidad la especialidad a filtrar (case-insensitive)
     * @return lista de médicos con esa especialidad
     */
    List<Medico> findByPerfil_EspecialidadIgnoreCase(String especialidad);
}
