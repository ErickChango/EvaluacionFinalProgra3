package com.medicare.controller;

import com.medicare.dto.MedicoDTO;
import com.medicare.entity.Medico;
import com.medicare.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CRUD completo para la entidad Médico.
 * Base: /api/medicos
 * Consultas personalizadas:
 *   GET /api/medicos/especialidad?valor={especialidad}
 */
@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    /** POST /api/medicos - Crear médico (solo ADMIN) */
    @PostMapping
    public ResponseEntity<Medico> crear(@RequestBody MedicoDTO dto) {
        Medico creado = medicoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /** GET /api/medicos - Listar todos */
    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    /** GET /api/medicos/{id} - Obtener por id */
    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.obtenerPorId(id));
    }

    /** PUT /api/medicos/{id} - Actualizar (solo ADMIN) */
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(
            @PathVariable Long id,
            @RequestBody MedicoDTO dto) {
        return ResponseEntity.ok(medicoService.actualizar(id, dto));
    }

    /** DELETE /api/medicos/{id} - Eliminar (solo ADMIN) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        medicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/medicos/especialidad?valor=Cardiología
     * Consulta personalizada (a): médicos por especialidad.
     */
    @GetMapping("/especialidad")
    public ResponseEntity<List<Medico>> porEspecialidad(
            @RequestParam String valor) {
        return ResponseEntity.ok(medicoService.buscarPorEspecialidad(valor));
    }
}
