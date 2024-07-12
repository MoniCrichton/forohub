package com.alura.forohub.controller;

import com.alura.forohub.exception.TopicoNotFoundException;
import com.alura.forohub.model.Topico;
import com.alura.forohub.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<Topico>> listarTodos(
            @PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.ASC, size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTodos(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerPorId(@PathVariable Long id) {
        Topico topico = topicoService.obtenerPorId(id)
                .orElseThrow(() -> new TopicoNotFoundException("Tópico no encontrado con ID: " + id));
        return ResponseEntity.ok(topico);
    }

    @PostMapping
    public ResponseEntity<Topico> crear(@RequestBody Topico topico) {
        return ResponseEntity.ok(topicoService.crear(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizar(@PathVariable Long id, @RequestBody Topico topico) {
        Optional<Topico> topicoActualizado = topicoService.actualizar(id, topico);
        if (topicoActualizado.isPresent()) {
            return ResponseEntity.ok(topicoActualizado.get());
        } else {
            throw new TopicoNotFoundException("Tópico no encontrado con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (topicoService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            throw new TopicoNotFoundException("Tópico no encontrado con ID: " + id);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Topico>> buscarPorCursoYAnio(
            @RequestParam(required = false) String cursoNombre,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.ASC, size = 10) Pageable paginacion) {
        if (cursoNombre != null && anio != null) {
            return ResponseEntity.ok(topicoService.listarPorCursoYAnio(cursoNombre, anio, paginacion));
        } else if (cursoNombre != null) {
            return ResponseEntity.ok(topicoService.listarPorCurso(cursoNombre, paginacion));
        } else if (anio != null) {
            return ResponseEntity.ok(topicoService.listarPorAnio(anio, paginacion));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
