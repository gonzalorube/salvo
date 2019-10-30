
    // Obtengo la lista ordenada de id="lista"
    // var games = document.getElementById("lista");
    // Solicito el JSON y, cuando es un éxito, lo itero y voy creando los elementos de lista
    var gamesJson = $.getJSON("/api/games").done(function recurse(data) {
        console.log("Eh, eh");
        var table;
        table = "<div class='container'><table class='table table-bordered table-responsive'><tr><th>Name</th><th>Total</th><th>Win</th><th>Tie</th><th>Lost</th></tr>";
        var uName = [""];
        var pPoints = [];
        var pTotal = 0.0;
        for(let k=0; k<data.length; k++){
            for(let h=0; h<data[k].gamePlayers.length; h++){
                if(uName.indexOf(data[k].gamePlayers[h].player.email) == -1){
                    uName.push(data[k].gamePlayers[h].player.email);
                    pPoints.push(data[k].gamePlayers[h].player.scores);
                    pTotal += parseFloat(data[k].gamePlayers[h].player.scores);
                    table += "<tr><td>" + data[k].gamePlayers[h].player.email + "</td><td></td><td></td><td></td><td></td></tr>";
                } else { console.log("No encontrado"); }
            }
        }
        table += "</table></div>"
        console.log("HEY");
        var lBoard = document.getElementById("leaderboard").innerHTML = table;
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