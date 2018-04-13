/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.konrad.ventalibros.resources;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *registra el inventario de todos los recursos de la aplicacion
 * @author Jeison
 **/
@ApplicationPath("api")
public class aplicationConfig extends Application{
    /**
     * hashset para almacenar el listado de recursos
     * @return 
     */
    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> resources = new HashSet<Class<?>>();
        return resources;
    }
    
    /**
     * agrega al hashset los recursos creados en la aplicacion
     * @param resources 
     */
    public void addRestResourceClasses(Set<Class<?>> resources){
        resources.add(EstudianteResource.class);
    }
}
