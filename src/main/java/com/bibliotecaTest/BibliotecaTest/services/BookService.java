package com.bibliotecaTest.BibliotecaTest.services;

import com.bibliotecaTest.BibliotecaTest.entities.BookEntity;
import com.bibliotecaTest.BibliotecaTest.execptions.NotFoundException;
import com.bibliotecaTest.BibliotecaTest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Contiene la logica di business relativa ai libri.
 * Tutti i servizi relativi ai libri sono implementati in questa classe.
 * Utilizzando l'annotazione {@code @Service} si indica a Spring che questa classe è un componente di servizio.
 *
 * @author Drumstyle92
 */
@Service
public class BookService {

    /**
     * Utilizzando L'annotazione {@code @Autowired} diamo il compito a Spring di fare l'iniezione delle dipendenze.
     * Il questo modo semplifico il processo di collegamento tra il servizio e il repository dei libri.
     */
    @Autowired
    BookRepository bookRepository;

    /**
     * Recupera tutte i libri presenti nel database.
     *
     * @return Ritorna un oggetto ResponseEntity contenente una lista di libri, se presente,
     *         altrimenti una risposta vuota con stato HTTP 204 No Content.
     *         Utilizzando la classe ResponseEntity ottengo una risposta più dettagliata e personalizzabile
     *         infatti è stata creata per le chiamate API.
     */
    public ResponseEntity<List<BookEntity>> getAllBooks() {

        List<BookEntity> books = bookRepository.findAll();
        if (!books.isEmpty()) {

            return ResponseEntity.ok(books);
        } else {

            return ResponseEntity.noContent().build();
        }


    }

    /**
     * Recupera un libro attraverso il suo ID.
     *
     * @param id  L'ID del libro
     * @return    Ritorna un oggetto ResponseEntity contenente il libro trovata, se presente
     * @throws    NotFoundException se il libro con l'ID specificato non è presente nel database
     *                              ritorna un'eccezione personalizzata
     */
    public ResponseEntity<BookEntity> getBookById(Long id) {

        Optional<BookEntity> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {

            BookEntity book = bookOptional.get();

            return ResponseEntity.ok(book);

        } else {

            throw new NotFoundException("Book not found");

        }

    }

    /**
     * Crea un nuovo libro.
     *
     * @param book Oggetto BookEntity che contiene i dati del libro da creare
     * @return Ritorna un oggetto ResponseEntity che contiene il libro appena creato
     */
    public ResponseEntity<BookEntity> createBook(BookEntity book) {

        BookEntity createdBook = bookRepository.save(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);

    }

    /**
     * Modifica i dati di un libro selezionandolo tramite l'ID.
     *
     * @param id         L'ID del libro da aggiornare
     * @param bookEdited Oggetto BookEntity contenente i dati aggiornati del libro
     * @return           Ritorna un oggetto ResponseEntity che contiene i dati aggiornati del libro, se presente
     * @throws           NotFoundException se il libro non viene trovato nel database
     *                                     lancia un'eccezione personalizzata
     */
    public ResponseEntity<BookEntity> updateBook(Long id, BookEntity bookEdited) {

        Optional<BookEntity> bookUpdate = bookRepository.findById(id);

        if (bookUpdate.isPresent()) {

            BookEntity book = bookUpdate.get();

            book.setAuthor(bookEdited.getAuthor());
            book.setTitle(bookEdited.getTitle());
            book.setDescription(bookEdited.getDescription());
            book.setNumberOfCopiesAvailable(bookEdited.getNumberOfCopiesAvailable());

            BookEntity updatedBook = bookRepository.saveAndFlush(book);

            return ResponseEntity.ok(updatedBook);

        } else {

            throw new NotFoundException("Book not found");

        }

    }

    /**
     * Elimina un libro in base all'ID.
     *
     * @param id ID del libro da eliminare
     * @return Ritorna un oggetto ResponseEntity vuoto con uno status "OK" se il libro viene trovato ed eliminato,
     *         altrimenti restituisce uno status "Not Found"
     */
    public ResponseEntity<Void> deleteBook(Long id) {

        Optional<BookEntity> bookEntity = bookRepository.findById(id);

        if (bookEntity.isPresent()) {

            bookRepository.deleteById(id);
            return ResponseEntity.status(200).build();

        } else {

            return ResponseEntity.notFound().build();

        }

    }



    /**
     * Restituisce il numero di copie disponibili di un libro specificato tramite ID.
     *
     * @param id L'ID del libro per il quale si desidera conoscere il numero di copie disponibili
     * @return   Ritorna un oggetto ResponseEntity con status "200 OK" e un messaggio
     *           contenente il numero di copie disponibili se esistono copie disponibili,
     *           status "404 Not Found" e un messaggio di errore se il libro non esiste o non sono disponibili copie.
     */
    public ResponseEntity<String> copiesAvailableByBookId(Long id) {

            Integer copiesAvailable = bookRepository.copiesAvailableByBookId(id);

            if (copiesAvailable == null || copiesAvailable == 0) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Copies not available for book with ID: " + id);
            }

            return ResponseEntity.ok("Copies available: " + copiesAvailable);
        }

    }

