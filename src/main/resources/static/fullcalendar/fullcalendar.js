

document.addEventListener('DOMContentLoaded', function() {

   // On va chercher la div dans le HTML
    let calendarEl = document.getElementById('calendrier');

    // On instancie le calendrier
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

        customButtons: {
            addEventButton: {
                text: 'Attribuer vacation',
                click: function() {

                    // On affiche le formulaire d'attribution d'evenement
                    $("#event-modal").modal('show');

                    // On soumet le formulaire
                     $("#AttribuerEvenementForm").submit(function(event){

                        var idEmploye = $("#idEmploye").val();
                        var idEvenement = $("#selectEvenement").val();

                        var nomEmploye = $("#nomEmploye").text();
                        var prenomEmploye = $("#prenomEmploye").text();


                        // Appel ajax pour la persistence des données en back
                         $.ajax({
                         type: "POST",
                         url: "attribuerEvenement?idEmploye="+idEmploye,
                         cache:false,
                         async : false,
                         data: {idEvenement : idEvenement},
                         success: function(data){
                            $("#event-modal").modal('hide');

                            // On récupère l'objet evènement que l'on va ajouter à fullcalendar en front
                            var idEvenement = data.id;
                            var titre = data.titre;
                            var dateDebut = data.dateDebut;
                            var dateFin = data.dateFin;
                            var adresse = data.adresse;

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


        //events: evenements,



        eventResize: function(info, revertFunc) {

         console.log(info.event.title + " end is now " + info.event.end+"start is now :"+ info.event.start);

        /* if (!confirm("is this okay?")) {
           revertFunc();
         }*/

          modifierEvent(info.event);

        },


        eventDrop: function(info) {
          console.log(info.event.title + " was dropped on " + info.event.start.toISOString());

         /* if (!confirm("is this okay?")) {
                    revertFunc();
                  }*/

          console.log(info.event);

          modifierEvent(info.event);
        }



    });

    // On affiche le calendrier
    calendar.render();

    chargerVacationsEmploye(calendar);


});


function modifierEvent(event) {

  var id = event.id;
  var dateDebut = event.start.toISOString().slice(0, 19);
  var dateFin = event.end.toISOString().slice(0, 19);

  console.log("id = "+id+" dateDebut = "+dateDebut+" dateFin = "+dateFin);

  $.ajax({
    type:"POST",
    url:"updateEvenement",
    data:{"idEvenement":id,"dateDebut":dateDebut,"dateFin":dateFin},
    success:function(msg){
      console.log(msg);
    },
    error:function(msg){
      console.log(msg);
      alert('We are unable to process your request');
    }
  });

}



function chargerVacationsEmploye(calendar) {

            var queryString = window.location.search;
            var urlParams = new URLSearchParams(queryString);
            var idEmploye = urlParams.get('idEmploye');

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


