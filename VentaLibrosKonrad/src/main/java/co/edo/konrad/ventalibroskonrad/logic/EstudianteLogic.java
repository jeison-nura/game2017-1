/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edo.konrad.ventalibroskonrad.logic;

import co.edu.konrad.ventalibroskonrad.entities.EstudianteEntity;
import co.edu.konrad.ventalibroskonrad.persistence.EstudiantePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *clase que controla la logica del estudiante
 * @author Jeison
 */
@Stateless
public class EstudianteLogic {
    
    /**
     * inyueccion del persistence de estudiante
     */
    @Inject
    private EstudiantePersistence estudiantepersistence;
    
    
    /**
     * obtener todos los estudiantes
     * @return 
     */
    public List<EstudianteEntity> obtenerestudiante(){
        List<EstudianteEntity> estudiante = estudiantepersistence.findAll();
        return estudiante;
    }
    
    
    /**
     * obtener un estudiante apartir del entity
     * @param id
     * @return estudiante encontrado
     */
    public EstudianteEntity obtenerestudiante(long id){
        EstudianteEntity estudiante = estudiantepersistence.find(id);
        if(estudiante == null){
            throw new IllegalArgumentException("Estudiante solicitado no existe.");
        }
        return estudiante;
    }
    
    /**
     * crea un nuevo estudiante
     * @param estEntity
     * @return 
     */
    public EstudianteEntity crearEstudiante(EstudianteEntity estEntity){
        estudiantepersistence.create(estEntity);
        return estEntity;
    }
    
    
    /**
     * actualiza un estudiante
     * @param estEntity
     * @param id
     * @return 
     */
    public EstudianteEntity ectualizarEstudiante(EstudianteEntity estEntity, long id){
        EstudianteEntity estudiante = estudiantepersistence.update(estEntity);
        return estudiante;
    }
    
    
    /**
     * eliminar estudiante
     * @param id 
     */
    public void eliminarEstudiante(long id){
        estudiantepersistence.delete(id);
    }
}
