package com.medicare.controller;

import com.medicare.dto.ConsultaDTO;
import com.medicare.entity.Consulta;
import com.medicare.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CRUD completo para la entidad Consulta.
 * Base: /api/consultas
 */
@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    /** POST /api/consultas - Crear consulta (solo ADMIN) */
    @PostMapping
    public ResponseEntity<Consulta> crear(@RequestBody ConsultaDTO dto) {
        Consulta creada = consultaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    /** GET /api/consultas - Listar todas */
    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas() {
        return ResponseEntity.ok(consultaService.listarTodas());
    }

    /** GET /api/consultas/{id} - Obtener por id */
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.obtenerPorId(id));
    }

    /** PUT /api/consultas/{id} - Actualizar (solo ADMIN) */
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> actualizar(
            @PathVariable Long id,
            @RequestBody ConsultaDTO dto) {
        return ResponseEntity.ok(consultaService.actualizar(id, dto));
    }

    /** DELETE /api/consultas/{id} - Eliminar (solo ADMIN) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        consultaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
