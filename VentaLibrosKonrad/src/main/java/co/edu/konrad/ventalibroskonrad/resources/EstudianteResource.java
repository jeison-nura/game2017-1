/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.konrad.ventalibroskonrad.resources;

import co.edu.konrad.ventalibroskonrad.dto.EstudianteDTO;
import co.edu.konrad.ventalibroskonrad.entities.EstudianteEntity;
import co.edu.konrad.ventalibroskonrad.logic.EstudianteLogic;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Recurso/Servicio REST para manejo de Estudiantes
 * @author Cindy H.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/estudiantes")
public class EstudianteResource {
    
    @EJB
    private EstudianteLogic estudianteLogic;
    
    /**
     * Metodo HTTP - GET para obtener todos los estudiantes
     * @return Lista de estudiantes
     */
    @GET
    public List<EstudianteDTO> getEstudianteList(){
        List<EstudianteEntity> estudiantes = estudianteLogic.obtenerEstudiantes();
        return EstudianteDTO.toEstudianteList(estudiantes);
    }
    
    /**
     * Metodo HTTP-GET obtener un estudiante a traves de su id.
     * Hace uso de parametros se usan las anotaciones @Path para 
     * personalizar la ruta de acceso, y @PathParam para indicar
     * cual es el parametro. En el @Path es necesario determinar 
     * una expresion regular que defina el tipo de caracteres que 
     * pueden ingresar en la URI para el parametro configurado.
     * @param id
     * @return estudiante
     */
    @GET
    @Path("{id: \\d+}")
    public EstudianteDTO getEstudiante(@PathParam("id") Long id){
        EstudianteEntity estudiante = estudianteLogic.obtenerEstudiante(id);
        if(estudiante == null){
            throw new RuntimeException("El estudiante solicitado no existe.");
        }
        return new EstudianteDTO(estudiante);
    }
    
    /**
     * Metodo HTTP -POST usado para crear un nuevo objeto Estudiante
     * @param estudianteDTO
     * @return estudianteDTO
     */
    @POST
    public EstudianteDTO createEstudiante(EstudianteDTO estudianteDTO){
        return new EstudianteDTO(estudianteLogic.crearEstudiante(estudianteDTO.toEntity()));
    }
    
    /**
     * Metodo HTTP - PUT permite actualizar un objeto estudiante.
     * OJO: El nombre del parametro pesonalizado en el @Path y el @PathParam
     * deben ser IGUALES.
     * @param id
     * @param estudianteDTO
     * @return 
     */
    @PUT
    @Path("{id: \\d+}")
    public EstudianteDTO updateEstudiante(@PathParam("id") Long id, EstudianteDTO estudianteDTO ){
        EstudianteEntity entity = estudianteLogic.obtenerEstudiante(id);
        if(entity == null){
            throw new RuntimeException("El estudiante solicitado no existe.");
        }
        return new EstudianteDTO(estudianteLogic.actualizarEstudiante(id, estudianteDTO.toEntity()));
    }
    
    /**
     * Metodo HTTP-DELETE usado para eliminar un objeto de Estudiante
     * @param id 
     */
    @DELETE
    @Path("{estudianteId: \\d+}")
    public void deleteEstudiante(@PathParam("estudianteId") Long id){
        EstudianteEntity entity = estudianteLogic.obtenerEstudiante(id);
        if(entity == null){
            throw new RuntimeException("El estudiante solicitado no existe.");
        }
        estudianteLogic.eliminarEstudiante(id);
    }
}
