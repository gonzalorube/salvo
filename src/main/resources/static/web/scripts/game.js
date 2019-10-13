function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

var prueba = document.getElementById("prueba");


function makeUrl() {
  var gamePlayer_Id = getParameterByName('gp');
  return '/api/game_view/' + gamePlayer_Id;
}
console.log(makeUrl());

function ajaxCallURL() {
    var ajxCll = $.ajax({
    url: makeUrl(),
    type: 'GET'
    });
    return ajxCll;
}
ajaxCallURL().done(function(data) {
    var playersTag = document.getElementById("players");
    var versus = "";
    var gpViewId = getParameterByName('gp');

    console.log("listo el pollo");
    console.log(data[0].ships);
    var ships4Player = data[0].ships;
    for(e in ships4Player){
        console.log(ships4Player[e].locations);
        var shLoc4Play = ships4Player[e].locations;
        for(i in shLoc4Play){
            console.log(shLoc4Play[i]);
            var cellId = document.getElementById(shLoc4Play[i]);
            cellId.style.backgroundColor = "#660000";
        }
    }
    var gpViewId = getParameterByName('gp');
    console.log(gpViewId);
    for(k in data[0].gamePlayers){
        var gppk = data[0].gamePlayers[k];
        if(data[0].gamePlayers[k].id == gpViewId){
            versus += gppk.player.email + " (you) vs ";
            console.log("right");
        }
    }
    for(l in data[0].gamePlayers){
        var gppl = data[0].gamePlayers[l];
        if(data[0].gamePlayers[l].id != gpViewId){
            versus += gppl.player.email;
         }
        // versus += " vs ";
    }
    playersTag.innerHTML = versus;
});
/*        for(e in json){
            var versus = document.getElementById("players");
                if(json[e].id === viewGameId){
                    for(i in json[e].gamePlayers){
                        if(i.id === gpViewId){
                            versus.innerText += i.player.email + " (you)";
                            console.log("1 " + versus);
                        } else {
                            console.log("Fail");
                        }
                    }
                }
            } */

/*     if(gpInGame != null){
        versus.innerText += gpInGame[0].player.email + " (you) vs." + gpInGame[1].player.email;
     }*/

