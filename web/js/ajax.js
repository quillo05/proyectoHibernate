function funcionAjax (opcion) {
    
    var xmlhttp;

    if (document.getElementById('nif').value=='' && opcion != "Listar Todos") {
        alert('Escribe un NIF para realizar la operacion pulsada');
        return;
    }

    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    var servlet="";
    
    switch (opcion) {
        case "Insertar":
            servlet="servletInsertar?nif="+document.getElementById('nif').value+"&nombre="+document.getElementById('nombre').value+"&fecha="+document.getElementById('fechaNac').value+"&calle="+document.getElementById('calle').value+"&poblacion="+document.getElementById('poblacion').value+"&provincia="+document.getElementById('provincia').value+"&codigo="+document.getElementById('codPostal').value;
            break;
        case "Listar":
            servlet="servletListar?nif="+document.getElementById('nif').value;
            break;
        case "Modificar":
            servlet="servletModificar?nif="+document.getElementById('nif').value+"&nombre="+document.getElementById('nombre').value+"&fecha="+document.getElementById('fechaNac').value+"&calle="+document.getElementById('calle').value+"&poblacion="+document.getElementById('poblacion').value+"&provincia="+document.getElementById('provincia').value+"&codigo="+document.getElementById('codPostal').value;
            break;
        case "Listar Todos":
            servlet="servletMostrar";
            break;
        case "Borrar":
            servlet="servletBorrar?nif="+document.getElementById('nif').value;
            break;
    }
    
    xmlhttp.open("GET",servlet,true);
    xmlhttp.send();
    
    xmlhttp.onreadystatechange = function() {
        
        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
            var respuesta=xmlhttp.responseText;
            if (respuesta.startsWith("Error")) {
                alert(respuesta);
            }
            else {
                var elementos = document.getElementById('pagina').getElementsByClassName('campos');
                for (var i=0; i<elementos.length; i++) {
                    elementos[i].value = "";
                }
                document.getElementById('resultado').innerHTML = "<br/><br/>"+respuesta;
            }
        }
        
    }
    
}