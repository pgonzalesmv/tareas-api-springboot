package com.pgs.tareas_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgs.tareas_api.model.Tarea;
import com.pgs.tareas_api.repository.TareaRepository;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;
    public List<Tarea> listarTodas(){
        return tareaRepository.findAll();
    }
    public Optional<Tarea> buscarPorId(Long id){
        return tareaRepository.findById(id);
    }
    public Tarea guardar(Tarea tarea){
        return tareaRepository.save(tarea);
    }
    public void eliminarTarea(Long id){
        tareaRepository.deleteById(id);
    }
}
