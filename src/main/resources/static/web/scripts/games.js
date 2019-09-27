function mostrarData(data){
    console.log(data);
}

$.get("localhost:8080/api/games").done(function(data){
mostrarData(data);
})
