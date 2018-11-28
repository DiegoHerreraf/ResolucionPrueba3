function validar(){
    var nombrearticulo, precio, expresion;
    
    nombrearticulo = document.getElementById("nombrearticulo").value;
    precio = document.getElementById("precioarticulo").value;
    
    if(nombrearticulo === ""){
        
        alert("El campo nombre esta vacio");
        return false;
    }else if(precio.length===0){
        alert("El campo precio esta vacio");
        return false;
    }else if(isNaN(precio)){
        alert("El precio debe ser numerico");
        return false;
    }else if(precio <= 0){
        alert("El precio debe ser mayor a 0");
        return false;
    }
    return true;
}
function validarAnadir(){
    var cantidad;
    cantidad = document.getElementById("cantidad").value;
    if(cantidad.length===0){
        alert("El campo cantidad esta vacio");
        return false;
    }else if(isNaN(cantidad)){
        alert("La cantidad debe ser numerica");
        return false;
    }else if(cantidad <= 0){
        alert("La cantidad debe ser mayor a 0");
        return false;
    }
    return true;
}
function validarRegistrarVenta(){
    var cliente;
    cliente =  document.getElementById("Cliente").value;
    if(cliente.length === 0){
        alert("El campo cliente no puede ser vacio");
        return false;
    }
    return true;
}


