document.addEventListener('DOMContentLoaded', function() {

   //On va chercher la div dans le HTML
    let calendarEl = document.getElementById('calendrier');

    //On instancie le calendrier
    let calendar = new FullCalendar.Calendar(calendarEl, {

        initialView: 'dayGridMonth',

        headerToolbar: {
            left: 'prev,next,today',
            center: 'title,addEventButton',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },

        locale: 'fr',

        timeZone: 'UTC',

        buttonText: {
            today:    'Aujourd\'hui',
            month:    'Mois',
            week:     'Semaine',
            day:      'Jour',
            list:     'Liste'

        },

        editable: true,

        displayEventEnd: {
           month: true
        },

        //Attribution de la vacation à l'employé
        customButtons: {
            addEventButton: {
                text: 'Attribuer vacation',
                click: function() {

                    //On affiche le formulaire d'attribution d'evenement
                    $("#event-modal").modal('show');

                    //On soumet le formulaire
                     $("#AttribuerEvenementForm").submit(function(event){

                        //On récupère les variables à fournir à l'appel Ajax
                        var idEmploye = $("#idEmploye").val();
                        var idEvenement = $("#selectEvenement").val();
                        var nomEmploye = $("#nomEmploye").text();
                        var prenomEmploye = $("#prenomEmploye").text();


                        //Appel ajax pour la persistence des données en back
                         $.ajax({
                         type: "POST",
                         url: "attribuerEvenement?idEmploye="+idEmploye,
                         cache:false,
                         async : false,
                         data: {idEvenement : idEvenement},
                         success: function(data){
                            $("#event-modal").modal('hide');

                            //On récupère les attributs de l'objet evènement
                            var idEvenement = data.id;
                            var titre = data.titre;
                            var dateDebut = data.dateDebut;
                            var dateFin = data.dateFin;
                            var adresse = data.adresse;

                            //On ajoute l'objet evènement à fullcalendar en front
                            calendar.addEvent({
                                  id: idEvenement,
                                  title: titre,
                                  start: dateDebut,
                                  end: dateFin,
                                  extendedProps: {
                                    adresse: adresse
                                   },
                                  allDay : false


                             });

                         alert("La vacation a été attribuée avec succès à l'employé "+nomEmploye+" "+prenomEmploye);

                             },
                         error: function(){
                         alert("aucune vacation de libre, elles sont toutes attribuées");
                             }
                         });

                     		return false;

                     });


                }
            }
        },

        eventResize: function(info, revertFunc) {

         if (!confirm("Voulez-vous modifier les horaires de cette vacation?")) {
           revertFunc();
         }

          modifierEvent(info.event);
        },


        eventDrop: function(info) {

          if (!confirm("Voulez-vous modifier le jour de cette vacation?")) {
            revertFunc();
          }

          modifierEvent(info.event);
        }



    });

    //On affiche le calendrier
    calendar.render();

    //On affiche les vacations de l'employé
    chargerVacationsEmploye(calendar);


});

//Méthode qui modifie la vacation pour le eventResize et le eventDrop
function modifierEvent(event) {

  //On récupère les variables à fournir à l'appel ajax
  var id = event.id;
  var dateDebut = event.start.toISOString().slice(0, 19);
  var dateFin = event.end.toISOString().slice(0, 19);

  //Appel ajax pour la persistence des données en back
  $.ajax({
    type:"POST",
    url:"updateEvenement",
    data:{"idEvenement":id,"dateDebut":dateDebut,"dateFin":dateFin},
    success:function(){
      alert('La vacation a été modifiée avec succès !');

    },
    error:function(){
      alert('Impossible de modifier la vacation !');
    }
  });

}


//Methode qui charge toutes les vacations de l'employé
function chargerVacationsEmploye(calendar) {

  //On récupère la variable à fournir à l'appel ajax
  var queryString = window.location.search;
  var urlParams = new URLSearchParams(queryString);
  var idEmploye = urlParams.get('idEmploye');

     //Appel ajax pour la persistence des données en back
     $.ajax({
         url: "listeEvenementsEmploye",
         type: 'GET',
         dataType: 'json',
         data: {idEmploye : idEmploye},
         success: function(data) {

             data.forEach((event) => {
                        calendar.addEvent({
                        id : event.id,
                        title: event.titre,
                        start: event.dateDebut,
                        end: event.dateFin,
                        extendedProps: {
                            adresse: event.adresse
                        },
                        allDay : false
                        });
             });


         }


     });

}


