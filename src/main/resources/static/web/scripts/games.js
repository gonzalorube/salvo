
    // Obtengo la lista ordenada de id="lista"
    // var games = document.getElementById("lista");
    // Solicito el JSON y, cuando es un éxito, lo itero y voy creando los elementos de lista
    var gamesJson = $.getJSON("/api/games").done(function recurse(data) {
        console.log("Eh, eh");
        var uName;
        var table;
        table = "<table><tr><th>Name</th><th>Total</th><th>Win</th><th>Tie</th><th>Lost</th></tr>";
        for(let k=0; k<data.length; k++){
            console.log("pip");
            for(let j; j<data[k].gamePlayers.length; j++){
                console.log("pepe");
            }
        }
        console.log("HEY");
        var leaderboard = document.getElementById("leaderboard").innerHTML = table;
        console.log( "Success" );
        /*var lista ="";
        for(let i=0; i<data.length; i++){
            lista += "<li>" + "<ul>" + "<li>" + "Game Id: " + data[i].id + "</li>" + "<li>" + "Created: " + data[i].created + "</li>" + "<li>" + "GamePlayers: " + "<ol>";
            for(let e=0; e<data[i].gamePlayers.length; e++){
                var gPlayer = data[i].gamePlayers[e];
                console.log(gPlayer);
                lista += "<li>" + "GamePlayer Id: " + data[i].gamePlayers[e].id + "</li>" + "<ul>" + "<li>" + "Player: " + "<ul>" + "<li>" + "Id: " + gPlayer.player.id + "</li>" + "<li>" + "Email: " + gPlayer.player.email + "</li>" + "</ul>" + "</li>" + "</ul>";
            }
            lista += "</ol>" + "</li>" + "</ul>" + "</li>";
        }
        // Envío la cadena guardada en "lista" al HTML de la lista ordenada accedida al principio
        games.innerHTML += lista;
        console.log("I can't stop succeeding...");*/
      })
      .fail(function() { /* Por si falla... */
        console.log( "Salió todo mal, ¡RAJEMOS!" );
      })
      .always(function() { /* Para lanzar por consola cuando se completa, pase lo que pase */
        console.log( "FUCK YEAH!");
        console.log( "Completed." );
        console.log( "I deserve a 'mate amargo'" );
      });