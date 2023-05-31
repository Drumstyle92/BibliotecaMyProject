package com.bibliotecaTest.BibliotecaTest.controllers;

import com.bibliotecaTest.BibliotecaTest.entities.ReservationEntity;
import com.bibliotecaTest.BibliotecaTest.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

/**
 * La classe ReservationController è un controller che gestisce le richieste relative alle prenotazioni nel sistema.
 * Facciamo diventare la classe un controller con l'annotazione {@code @RestController} proprio per poter
 * creare e gestire richieste HTTP del sistema, inoltre con l'annotazione {@code @RequestMapping} diamo
 * un percorso base a tutte le varie richieste del controller.
 *
 * @author Drumstyle92
 */
@RestController
@RequestMapping("/apiReservation")
public class ReservationController {

    /**
     * Viene iniettato automaticamente il servizio delle prenotazioni attraverso l'annotazione {@code @Autowired}
     * in modo tale da recuperare i dati delle prenotazioni dal database,
     * eseguire operazioni di business logic sulle prenotazioni e fornire i
     * risultati al controller per la risposta alle richieste HTTP.
     */
    @Autowired
    ReservationService reservationService;


    /**
     * L'annotazione {@code @GetMapping} indica che il metodo gestisce le richieste HTTP di tipo
     * GET verso la specifica route designata.
     * Il metodo attraverso la richiesta HTTP ottiene la lista delle prenotazioni dal database.
     *
     * @return Ritorna un oggetto ResponseEntity contenente la lista di tutte le prenotazioni
     */
    @GetMapping("/allReservations")
    public ResponseEntity<List<ReservationEntity>> getAllReservations() {

        return reservationService.getAllReservations();

    }

    /**
     * L'annotazione {@code @GetMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo GET verso la specifica route designata.
     * Il metodo attraverso la richiesta HTTP ottiene una prenotazione con l'ID specificato.
     *
     * @param id l'ID della prenotazione
     * @return   Ritorna un oggetto ResponseEntity contenente la prenotazione con l'ID specificato
     */
    @GetMapping("/getReservation/{id}")
    public ResponseEntity<ReservationEntity> getReservationById(@PathVariable Long id) {

        return reservationService.getReservationById(id);

    }

    /**
     * L'annotazione {@code @PutMapping} indica che il metodo gestisce le richieste HTTP
     * di tipo PUT verso la specifica route designata.
     * Metodo che aggiorna una prenotazione esistente.
     *
     * @param reservationId l'ID della prenotazione da aggiornare
     * @param date          la data di prenotazione aggiornata
     * @param userId        l'ID dell'utente associato alla prenotazione
     * @param bookId        l'ID del libro associato alla prenotazione
     * @return              Ritorna un oggetto ResponseEntity contenente la prenotazione aggiornata
     */
    @PutMapping("/putReservation/{reservationId}")
    public ResponseEntity<ReservationEntity> updateReservation(@PathVariable Long reservationId,
                                                               @RequestParam LocalDate date,
                                                               @RequestParam Long userId,
                                                               @RequestParam Long bookId) {

        return reservationService.updateReservation(reservationId,date,userId,bookId);

    }

    /**
     * L'annotazione {@code @DeleteMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo DELETE verso la specifica route designata.
     * Il metodo elimina una prenotazione esistente utilizzando l'ID della prenotazione da eliminare.
     *
     * @param id l'ID della prenotazione da eliminare
     * @return   Ritorna un oggetto ResponseEntity vuoto con risposta lo status HTTP
     */
    @DeleteMapping("/deleteReservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {

        return reservationService.deleteReservation(id);

    }

    /**
     * L'annotazione {@code @PostMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo POST verso la specifica route designata.
     * Metodo che crea una nuova prenotazione.
     *
     * @param userId l'ID dell'utente per cui creare la prenotazione
     * @param bookId l'ID del libro da prenotare
     * @return       Ritorna un oggetto ResponseEntity contenente una stringa di conferma
     */
    @PostMapping("/createReservation")
    public ResponseEntity<String> createReservation(@RequestParam Long userId, @RequestParam Long bookId){

        return reservationService.createReservation(userId,bookId);

    }

}
