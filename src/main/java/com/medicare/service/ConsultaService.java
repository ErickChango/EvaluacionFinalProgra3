package com.medicare.service;

import com.medicare.dto.ConsultaDTO;
import com.medicare.entity.Consulta;
import com.medicare.entity.Medico;
import com.medicare.entity.Paciente;
import com.medicare.repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    public ConsultaService(ConsultaRepository consultaRepository,
                           MedicoService medicoService,
                           PacienteService pacienteService) {
        this.consultaRepository = consultaRepository;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }

    public Consulta crear(ConsultaDTO dto) {
        Medico medico = medicoService.obtenerPorId(dto.getMedicoId());
        Paciente paciente = pacienteService.obtenerPorId(dto.getPacienteId());

        Consulta consulta = new Consulta();
        consulta.setFechaHora(dto.getFechaHora());
        consulta.setMotivo(dto.getMotivo());
        consulta.setDiagnostico(dto.getDiagnostico());
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        return consultaRepository.save(consulta);
    }

    @Transactional(readOnly = true)
    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Consulta obtenerPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id: " + id));
    }

    public Consulta actualizar(Long id, ConsultaDTO dto) {
        Consulta consulta = obtenerPorId(id);
        consulta.setFechaHora(dto.getFechaHora());
        consulta.setMotivo(dto.getMotivo());
        consulta.setDiagnostico(dto.getDiagnostico());

        if (dto.getMedicoId() != null) {
            consulta.setMedico(medicoService.obtenerPorId(dto.getMedicoId()));
        }
        if (dto.getPacienteId() != null) {
            consulta.setPaciente(pacienteService.obtenerPorId(dto.getPacienteId()));
        }

        return consultaRepository.save(consulta);
    }

    public void eliminar(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta no encontrada con id: " + id);
        }
        consultaRepository.deleteById(id);
    }
}
