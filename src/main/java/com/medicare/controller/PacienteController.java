package com.medicare.controller;

import com.medicare.dto.PacienteDTO;
import com.medicare.entity.Paciente;
import com.medicare.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CRUD completo para la entidad Paciente.
 * Base: /api/pacientes
 * Consultas personalizadas:
 *   GET /api/pacientes/buscar?nombre={texto}
 */
@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /** POST /api/pacientes - Crear paciente (solo ADMIN) */
    @PostMapping
    public ResponseEntity<Paciente> crear(@RequestBody PacienteDTO dto) {
        Paciente creado = pacienteService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /** GET /api/pacientes - Listar todos */
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    /** GET /api/pacientes/{id} - Obtener por id */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obtenerPorId(id));
    }

    /** PUT /api/pacientes/{id} - Actualizar (solo ADMIN) */
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(
            @PathVariable Long id,
            @RequestBody PacienteDTO dto) {
        return ResponseEntity.ok(pacienteService.actualizar(id, dto));
    }

    /** DELETE /api/pacientes/{id} - Eliminar (solo ADMIN) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/pacientes/buscar?nombre=juan
     * Consulta personalizada (b): pacientes cuyo nombre contiene el texto.
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Paciente>> buscarPorNombre(
            @RequestParam String nombre) {
        return ResponseEntity.ok(pacienteService.buscarPorNombre(nombre));
    }
}
