package com.bibliotecaTest.BibliotecaTest.controllers;

import com.bibliotecaTest.BibliotecaTest.entities.BookEntity;
import com.bibliotecaTest.BibliotecaTest.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * La classe BookController è un controller che gestisce le richieste relative ai libri nel sistema.
 * Facciamo diventare la classe un controller con l'annotazione {@code @RestController} proprio per poter
 * creare e gestire richieste HTTP del sistema, inoltre con l'annotazione {@code @RequestMapping} diamo
 * un percorso base a tutte le varie richieste del controller.
 *
 * @author Drumstyle92
 */
@RestController
@RequestMapping("/apiBook")
public class BookController {

    /**
     * Viene iniettato automaticamente il servizio dei libri attraverso l'annotazione {@code @Autowired}
     * in modo tale da recuperare i dati dei libri dal database,
     * eseguire operazioni di business logic sui libri e fornire i
     * risultati al controller per la risposta alle richieste HTTP.
     */
    @Autowired
    BookService bookService;

    /**
     * L'annotazione {@code @GetMapping} indica che il metodo gestisce le richieste HTTP di tipo
     * GET verso la specifica route designata.
     * Il metodo attraverso la richiesta HTTP ottiene la lista dei libri dal database.
     *
     * @return Ritorna un oggetto ResponseEntity contenente la lista di tutti i libri
     */
    @GetMapping("/allBooks")
    public ResponseEntity<List<BookEntity>> getAllBook(){

        return bookService.getAllBooks();

    }

    /**
     * L'annotazione {@code @PostMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo POST verso la specifica route designata.
     * Il metodo crea un nuovo libro utilizzando l'oggetto BookEntity fornito come parametro.
     *
     * @param book l'oggetto BookEntity rappresenta il libro da creare
     * @return     Ritorna un oggetto ResponseEntity contenente il libro appena creato
     */
    @PostMapping("/createBook")
    public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity book){

        return bookService.createBook(book);

    }

    /**
     * L'annotazione {@code @GetMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo GET verso la specifica route designata.
     * Il metodo attraverso la richiesta HTTP ottiene il libro con l'ID specificato.
     *
     * @param id l'ID del libro
     * @return   Ritorna un oggetto ResponseEntity contenente il libro con l'ID specificato
     */
    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookEntity> getBook(@PathVariable Long id){

        return bookService.getBookById(id);

    }

    /**
     * L'annotazione {@code @PutMapping} indica che il metodo gestisce le richieste HTTP
     * di tipo PUT verso la specifica route designata.
     * Il metodo aggiorna un libro esistente utilizzando l'ID del libro e
     * l'oggetto BookEntity che contiene i nuovi dati del libro.
     *
     * @param id   l'ID del libro da aggiornare
     * @param book l'oggetto BookEntity contenente i nuovi dati del libro
     * @return     Ritorna un oggetto ResponseEntity contenente il libro aggiornato
     */
    @PutMapping("/putBook/{id}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody BookEntity book) {

        return bookService.updateBook(id, book);

    }

    /**
     * L'annotazione {@code @DeleteMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo DELETE verso la specifica route designata.
     * Il metodo elimina un libro esistente utilizzando l'ID del libro da eliminare.
     *
     * @param id l'ID del libro da eliminare
     * @return   Ritorna un oggetto ResponseEntity vuoto con risposta lo status HTTP
     */
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        return bookService.deleteBook(id);

    }

    /**
     * L'annotazione {@code @GetMapping} indica che il metodo gestisce
     * le richieste HTTP di tipo GET verso la specifica route designata.
     * Il metodo ottiene il numero di copie disponibili di un libro specifico.
     *
     * @param bookId l'ID del libro di cui si vuole conoscere il numero di copie disponibili
     * @return       Ritorna un oggetto ResponseEntity contenente il numero di copie disponibili come stringa
     */
    @GetMapping("/getCopies/{bookId}")
    public ResponseEntity<String> getAvailableCopies(@PathVariable Long bookId) {

        return bookService.copiesAvailableByBookId(bookId);

    }

}
