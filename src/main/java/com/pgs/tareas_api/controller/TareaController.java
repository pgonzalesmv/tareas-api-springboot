package com.pgs.tareas_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.pgs.tareas_api.model.Tarea;
import com.pgs.tareas_api.service.TareaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*")
public class TareaController {
    @Autowired
    private TareaService tareaService;
    @GetMapping
    public List<Tarea> getAll() {
        return tareaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getById(@PathVariable Long id) {
        return tareaService.buscarPorId(id)
                .map(tarea -> ResponseEntity.ok().body(tarea))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Tarea> create(@Valid @RequestBody Tarea tarea) {
        Tarea nueva = tareaService.guardar(tarea);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> update(@PathVariable Long id, @Valid @RequestBody Tarea tarea) {
        return tareaService.buscarPorId(id).map(t -> {
                t.setTitulo(tarea.getTitulo());
                t.setDescripcion(tarea.getDescripcion());
                t.setCompletada(tarea.isCompletada());
                Tarea actualizada = tareaService.guardar(t);
                return ResponseEntity.ok(actualizada);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(tareaService.buscarPorId(id).isPresent()){
            tareaService.eliminarTarea(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
