package com.medicare.controller;

import com.medicare.dto.MedicoDTO;
import com.medicare.entity.Medico;
import com.medicare.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// CRUD de medicos y consultas personalizadas
@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<Medico> crear(@RequestBody MedicoDTO dto) {
        Medico creado = medicoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(@PathVariable Long id, @RequestBody MedicoDTO dto) {
        return ResponseEntity.ok(medicoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        medicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // consulta personalizada: filtrar por especialidad
    @GetMapping("/especialidad")
    public ResponseEntity<List<Medico>> porEspecialidad(@RequestParam String valor) {
        return ResponseEntity.ok(medicoService.buscarPorEspecialidad(valor));
    }
}
