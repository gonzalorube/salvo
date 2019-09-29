    var games = document.getElementById("lista");
    var gamesJson = $.getJSON( "/api/games").done(function recurse(data) {
        console.log( "Success" );
        var lista;
        for (let i=0;i<data.length;i++){
            lista += "<li>" + "<ul>" + "<li>" + "Game Id: " + data[i].id + "</li>" + "<li>" + "Created: " + data[i].created + "</li>" + "<li>" + "GamePlayers: " + "<ol>";
            for(let e=0; e<data[i].gamePlayers.length; e++){
                var gPlayer = data[i].gamePlayers[e];
                console.log(gPlayer);
                lista += "<li>" + "GamePlayer Id: " + data[i].gamePlayers[e].id + "</li>" + "<ul>" + "<li>" + "Player: " + "<ul>" + "<li>" + "Id: " + gPlayer.player.id + "</li>" + "<li>" + "Email: " + gPlayer.player.email + "</li>" + "</ul>" + "</li>" + "</ul>";
            }
            lista += "</ol>" + "</li>" + "</ul>" + "</li>";
        }
        games.innerHTML += lista;
        console.log("I can't stop succeeding...")
      })
      .fail(function() {
        console.log( "Salió todo mal, ¡RAJEMOS!" );
      })
      .always(function() {
        console.log( "FUCK YEAH!");
        console.log( "Completed." );
        console.log( "I deserve a 'mate amargo'" );
      });