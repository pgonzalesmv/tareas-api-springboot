package com.pgs.tareas_api.repository;
import com.pgs.tareas_api.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar tareas por título o estado de completado

}
