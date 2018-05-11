(function ($){

    $(document).ready(function(event){
       $.ajax({
           url:'/VentaLibrosKonrad/api/estudiantes',
           method:'GET',
           contentType: 'application/json',
           dataType: 'json' 
       }).done(function(data){
            for (var i = 0; i < data.length; i++) {
                var tableRow = $('<tr>');
                //diviciones de las filas
                var nombreEstudiante = $('<td>');
                nombreEstudiante.text(data[i].nombreEstudiante);
                var apellidoEstudiante = $('<td>');
                apellidoEstudiante.text(data[i].apellidoEstudiante);
                var correoEstudiante = $('<td>');
                correoEstudiante.text(data[i].correoEstudiante);
                var codigoEstudiante = $('<td>');
                codigoEstudiante.text(data[i].codigoEstudiante);
                var acciones = $('<td>');
                
                var botonEliminar = $('<Button>').addClass('btn btn-danger');
                botonEliminar.text('eliminar');
                var botonActualizar = $('<a>').addClass('btn btn-success');
                botonActualizar.text('actualizar');
                acciones.append(botonEliminar);
                acciones.append(botonActualizar);
                
                tableRow.append(nombreEstudiante);
                tableRow.append(apellidoEstudiante);
                tableRow.append(correoEstudiante);
                tableRow.append(codigoEstudiante);
                tableRow.append(acciones);
                
                
                $('#tablaEstudiantes table tbody').append(tableRow);  
                console.log(data);
            }
            
       }).fail(function(xhr,status,error){
            console.log(error);  
       });
    });
    
})(jQuery);

