package com.medicare.service;

import com.medicare.dto.MedicoDTO;
import com.medicare.entity.Medico;
import com.medicare.entity.PerfilMedico;
import com.medicare.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Medico crear(MedicoDTO dto) {
        Medico medico = new Medico();
        medico.setNombre(dto.getNombre());
        medico.setApellido(dto.getApellido());
        medico.setLicencia(dto.getLicencia());

        if (dto.getPerfil() != null) {
            PerfilMedico perfil = new PerfilMedico();
            perfil.setEspecialidad(dto.getPerfil().getEspecialidad());
            perfil.setTelefono(dto.getPerfil().getTelefono());
            perfil.setEmail(dto.getPerfil().getEmail());
            perfil.setDireccion(dto.getPerfil().getDireccion());
            medico.setPerfil(perfil);
        }

        return medicoRepository.save(medico);
    }

    @Transactional(readOnly = true)
    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Medico obtenerPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con id: " + id));
    }

    public Medico actualizar(Long id, MedicoDTO dto) {
        Medico medico = obtenerPorId(id);
        medico.setNombre(dto.getNombre());
        medico.setApellido(dto.getApellido());
        medico.setLicencia(dto.getLicencia());

        if (dto.getPerfil() != null) {
            PerfilMedico perfil = medico.getPerfil();
            if (perfil == null) {
                perfil = new PerfilMedico();
            }
            perfil.setEspecialidad(dto.getPerfil().getEspecialidad());
            perfil.setTelefono(dto.getPerfil().getTelefono());
            perfil.setEmail(dto.getPerfil().getEmail());
            perfil.setDireccion(dto.getPerfil().getDireccion());
            medico.setPerfil(perfil);
        }

        return medicoRepository.save(medico);
    }

    public void eliminar(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico no encontrado con id: " + id);
        }
        medicoRepository.deleteById(id);
    }

    /** Consulta personalizada (a): médicos por especialidad. */
    @Transactional(readOnly = true)
    public List<Medico> buscarPorEspecialidad(String especialidad) {
        return medicoRepository.findByPerfil_EspecialidadIgnoreCase(especialidad);
    }
}
