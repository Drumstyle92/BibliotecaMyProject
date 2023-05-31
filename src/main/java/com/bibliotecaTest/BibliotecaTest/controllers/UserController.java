package com.bibliotecaTest.BibliotecaTest.controllers;


import com.bibliotecaTest.BibliotecaTest.entities.UserEntity;
import com.bibliotecaTest.BibliotecaTest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * La classe UserController è un controller che gestisce le richieste relative agli utenti nel sistema.
 * Facciamo diventare la classe un controller con l'annotazione {@code @RestController} proprio per poter
 * creare e gestire richieste HTTP del sistema, inoltre con l'annotazione {@code @RequestMapping} diamo
 * un percorso base a tutte le varie richieste del controller.
 *
 * @author Drumstyle92
 */
@RestController
@RequestMapping("/apiUser")
public class UserController {

    /**
     * Viene iniettato automaticamente il servizio degli utenti attraverso l'annotazione {@code @Autowired}
     * in modo tale da recuperare i dati degli utenti dal database,
     * eseguire operazioni di business logic sugli utenti e fornire i
     * risultati al controller per la risposta alle richieste HTTP.
     */
    @Autowired
    UserService userService;

    /**
     * L'annotazione {@code @GetMapping} indica che il metodo gestisce le richieste HTTP di tipo
     * GET verso la specifica route designata.
     * Il metodo attraverso la richiesta HTTP ottiene la lista degli utenti dal database.
     *
     * @return Ritorna un oggetto ResponseEntity contenente la lista di tutti gli utenti
     */
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserEntity>> getAllUsers() {

        return userService.getAllUsers();

    }

    /**
     * L'annotazione {@code @GetMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo GET verso la specifica route designata.
     * Il metodo attraverso la richiesta HTTP ottiene l'utente con l'ID specificato.
     *
     * @param id l'ID dell'utente
     * @return   Ritorna un oggetto ResponseEntity contenente l'utente con l'ID specificato
     */
    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {

        return userService.getUserById(id);

    }

    /**
     * L'annotazione {@code @PostMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo POST verso la specifica route designata.
     * Il metodo crea un nuovo utente utilizzando l'oggetto UserEntity fornito come parametro.
     *
     * @param user l'oggetto UserEntity rappresenta l'utente da creare
     * @return     Ritorna un oggetto ResponseEntity contenente l'utente appena creato
     */
    @PostMapping("/createUser")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {

        return userService.createUser(user);

    }

    /**
     * L'annotazione {@code @PutMapping} indica che il metodo gestisce le richieste HTTP
     * di tipo PUT verso la specifica route designata.
     * Il metodo aggiorna un utente esistente utilizzando l'ID dell'utente e
     * l'oggetto UserEntity che contiene i nuovi dati dell'utente.
     *
     * @param id   l'ID dell'utente da aggiornare
     * @param user l'oggetto UserEntity contenente i nuovi dati dell'utente
     * @return     Ritorna un oggetto ResponseEntity contenente l'utente aggiornato
     */
    @PutMapping("/putUser/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {

        return userService.updateUser(id, user);

    }

    /**
     * L'annotazione {@code @DeleteMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo DELETE verso la specifica route designata.
     * Il metodo elimina un utente esistente utilizzando l'ID dell'utente da eliminare.
     *
     * @param id l'ID dell'utente da eliminare
     * @return   Ritorna un oggetto ResponseEntity vuoto con risposta lo status HTTP
     */
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        return userService.deleteUser(id);

    }

}