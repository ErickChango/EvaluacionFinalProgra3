package com.medicare.repository;

import com.medicare.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // buscar medicos por especialidad sin importar mayusculas
    List<Medico> findByPerfil_EspecialidadIgnoreCase(String especialidad);
}
