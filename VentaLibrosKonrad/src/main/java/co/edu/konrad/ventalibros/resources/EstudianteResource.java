/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.konrad.ventalibros.resources;

import co.edo.konrad.ventalibroskonrad.logic.EstudianteLogic;
import co.edu.konrad.ventalibroskonrad.dto.EstudianteDTO;
import co.edu.konrad.ventalibroskonrad.entities.EstudianteEntity;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Entity;

/**
 * recurso o servicio Rest para el manejo del estudiante
 *
 * @author Jeison
 */
@Path("/estudiantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteResource {

    @Inject
    private EstudianteLogic estudiantelogic;

    /**
     * Metodo HHTP /get para obtener todos los estudiantes
     * @param id
     * @return lista de estudiantes
     */
    
    @GET
    public List<EstudianteDTO> getEstudianteList() {
        List<EstudianteEntity> estudiantes = estudiantelogic.obtenerestudiante();
        EstudianteDTO estudiante = new EstudianteDTO();
        return estudiante.toEstudianteList(estudiantes);
    }
    
    /**
     * metodo hhtp para obtener un estudiante a traves de su id
     * hace uso de parametros se usan las anotaciones @path para personalizar la ruta de acceso
     * y @ pathparam para indicar cual es el parametro
     * en el @path es necesario deternerminar una expresion regular que defina el tipo de caracteres que pueden
     * ingresar en la uri para el parametro configuracion
     * @return 
     */
    @GET
    @Path("{id: \\d+}")
    public EstudianteDTO getEstudiante(@PathParam("id") Long id) {
        EstudianteEntity estudiante = estudiantelogic.obtenerestudiante(id);
        if (estudiante == null) {
            throw new RuntimeException("El estudiante SOlicitado no existe");
        }
        return new EstudianteDTO(estudiante);

    }
    
    
    /**
     * metodo http post usado para crear un nuevo objeto estudiante
     * @param estudiante
     * @return esrtudiante
     */
    @POST
    public EstudianteDTO createEstudiante(EstudianteDTO estudiante){
        return new EstudianteDTO(estudiantelogic.crearEstudiante(estudiante.toEntity()));
    }
    
    
    /**
     * metodo http put permite actualizar un objeto estudiante
     * ojo el nombre del parametro personalizado en el @path y el @pathparam deben ser iguales
     * @param id
     * @param estudiante
     * @return 
     */
    @PUT
    @Path("{id: \\d+}")
    public EstudianteDTO updateEstudiante(@PathParam("id") Long id, EstudianteDTO estudiante){
        EstudianteEntity entity= estudiantelogic.obtenerestudiante(id);
        if(entity == null){
            throw new RuntimeException("el estudiante solicitado no existe");
        }
        return new EstudianteDTO(estudiantelogic.ectualizarEstudiante(estudiante.toEntity(), id));
    }
    
    /**
     * metodo http delete usado para eliminar un objeto
     * @param id 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEstudiante(@PathParam("id") Long id){
        EstudianteEntity entity = estudiantelogic.obtenerestudiante(id);
        if(entity == null){
            throw new RuntimeException("el estudiante solicitado no existe");
        }
        estudiantelogic.eliminarEstudiante(id);
    }
}
