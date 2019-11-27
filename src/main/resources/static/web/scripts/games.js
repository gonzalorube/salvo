    // Solicito el JSON y, cuando es un éxito, lo itero y voy creando los elementos de lista
    var gamesJson = $.getJSON("/api/games").done(function recurse(data) {
        var table;
        table = "<tr><th>Name</th><th>Total</th><th>Win</th><th>Tie</th><th>Lost</th></tr>";
        var uName = [];
        for(let k=0; k<data.length; k++){  // Itero el JSON de api/games
            for(let h=0; h<data[k].gamePlayers.length; h++){  // Itero cada gamePlayer
                if(uName.indexOf(data[k].gamePlayers[h].player.email) == -1){ // Si el email no está en la lista
                    uName.push(data[k].gamePlayers[h].player.email);  // lo agrego
                    table += "<tr><td>" + data[k].gamePlayers[h].player.email + "</td><td>" + getPointsPerPlayer(data[k].gamePlayers[h].player.email) + "</td><td>" + getWins(data[k].gamePlayers[h].player.email) + "</td><td>" + getTieds(data[k].gamePlayers[h].player.email) + "</td><td>" + getDefeats(data[k].gamePlayers[h].player.email) + "</td></tr>";
                } else { console.log("Already Exists"); }
            }
        }

        var lBoard = document.getElementById("leaderboard").innerHTML = table;
        console.log( "Success" );

        function getPointsPerPlayer(player2Compare){
                  var pTotal = 0.0;
                  for(let k=0; k<data.length; k++){  // Itero el JSON de api/games
                      for(let h=0; h<data[k].gamePlayers.length; h++){  // Itero cada gamePlayer
                          var player = data[k].gamePlayers[h].player.email;
                          // console.log(player);
                          // console.log(player2Compare + " por parámetro");
                          if(player === player2Compare){
                              if(data[k].gamePlayers[h].player.scores != null){
                                  pTotal += parseFloat(data[k].gamePlayers[h].player.scores);
                                  // console.log(pTotal);
                              }
                          }
                      }
                  }
                  return pTotal;
              }

              function getWins(player2Compare){
                  var win = 0;
                  for(let k=0; k<data.length; k++){  // Itero el JSON de api7/games
                      for(let h=0; h<data[k].gamePlayers.length; h++){  // Itero cada gamePlayer
                          var player = data[k].gamePlayers[h].player.email;
                          // console.log(player);
                          // console.log(player2Compare + " por parámetro");
                          var points = data[k].gamePlayers[h].player.scores;
                          if(player === player2Compare && points != null && points == "1.0"){ // Mismo jugador? Puntos igual a 1? Sumale 1 al win
                              win++;
                              // console.log(win);
                          }
                      }
                  }
                  return win;
              }

              function getTieds(player2Compare){
                  var tied = 0;
                  for(let k=0; k<data.length; k++){  // Itero el JSON de api7/games
                      for(let h=0; h<data[k].gamePlayers.length; h++){  // Itero cada gamePlayer
                           var player = data[k].gamePlayers[h].player.email;
                           // console.log(player);
                           // console.log(player2Compare + " por parámetro");
                           var points = data[k].gamePlayers[h].player.scores;
                           if(player === player2Compare && points != null && points == "0.5"){
                               tied++;
                               // console.log(tied);
                           }
                      }
                  }
                  return tied;
               }


              function getDefeats
              (player2Compare){
                  var lost = 0;
                  for(let k=0; k<data.length; k++){  // Itero el JSON de api7/games
                      for(let h=0; h<data[k].gamePlayers.length; h++){  // Itero cada gamePlayer
                          var player = data[k].gamePlayers[h].player.email;
                          // console.log(player);
                          // console.log(player2Compare + " por parámetro");
                          var points = data[k].gamePlayers[h].player.scores;
                          if(player === player2Compare && points != null && points == "0.0"){
                              lost++;
                              // console.log(lost);
                          }
                      }
                  }
                  return lost;
              }
              // Obtengo la lista ordenada de id="lista"
              // var games = document.getElementById("lista");
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
       console.log( "Completed." );
        // console.log( "I deserve a 'mate amargo'" );
      });

      function login(evt) {
        evt.preventDefault();
        var form = evt.target.form;
        $.post("/api/login",
               { username: form["username"].value,
                 password: form["password"].value })
         .done( function() {

            //var addUserEtc = getElementById("userEtc").innerText = "ok ok";
             })
         .fail( function() { console.log("Keep calm & quit"); });
      }

      function logout(evt) {
        evt.preventDefault();
        $.post("/api/logout")
         .done( function() { // var changeBtn = getElementById("logBtn").innerText = "Sign Up";
         })
         .fail( function() { console.log("Keep calm, run away & quit  now")});
      }