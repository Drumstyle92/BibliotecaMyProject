package com.bibliotecaTest.BibliotecaTest.services;

import com.bibliotecaTest.BibliotecaTest.entities.BookEntity;
import com.bibliotecaTest.BibliotecaTest.entities.ReservationEntity;
import com.bibliotecaTest.BibliotecaTest.entities.UserEntity;
import com.bibliotecaTest.BibliotecaTest.execptions.NotFoundException;
import com.bibliotecaTest.BibliotecaTest.repositories.BookRepository;
import com.bibliotecaTest.BibliotecaTest.repositories.ReservationRepository;
import com.bibliotecaTest.BibliotecaTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Contiene la logica di business relativa alle prenotazioni.
 * Tutti i servizi relativi alle prenotazioni sono implementati in questa classe.
 * Utilizzando l'annotazione {@code @Service} si indica a Spring che questa classe è un componente di servizio.
 *
 * @author Drumstyle92
 */
@Service
public class ReservationService {

    /**
     * Utilizzando L'annotazione {@code @Autowired} diamo il compito a Spring di fare l'iniezione delle dipendenze.
     * Il questo modo semplifico il processo di collegamento tra il servizio e il repository delle prenotazioni.
     */
    @Autowired
    ReservationRepository reservationRepository;

    /**
     * Utilizzando L'annotazione {@code @Autowired} diamo il compito a Spring di fare l'iniezione delle dipendenze.
     * Il questo modo semplifico il processo di collegamento tra il servizio e il repository degli utenti.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Utilizzando L'annotazione {@code @Autowired} diamo il compito a Spring di fare l'iniezione delle dipendenze.
     * Il questo modo semplifico il processo di collegamento tra il servizio e il repository dei libri.
     */
    @Autowired
    BookRepository bookRepository;

    /**
     * Recupera tutte le prenotazioni presenti nel database.
     *
     * @return Ritorna un oggetto ResponseEntity contenente una lista di prenotazioni, se presente,
     *         altrimenti una risposta vuota con stato HTTP 204 No Content.
     *         Utilizzando la classe ResponseEntity ottengo una risposta più dettagliata e personalizzabile
     *         infatti è stata creata per le chiamate API.
     */
    public ResponseEntity<List<ReservationEntity>> getAllReservations() {

        List<ReservationEntity> reservations = reservationRepository.findAll();
        if (!reservations.isEmpty()) {

            return ResponseEntity.ok(reservations);
        } else {

            return ResponseEntity.noContent().build();
        }


    }

    /**
     * Recupera una prenotazione attraverso il suo ID.
     *
     * @param id  L'ID della prenotazione
     * @return    Ritorna un oggetto ResponseEntity contenente la prenotazione trovata, se presente
     * @throws    NotFoundException se la prenotazione con l'ID specificato non è presente nel database
     *                              ritorna un'eccezione personalizzata
     */
    public ResponseEntity<ReservationEntity> getReservationById(Long id) {

        Optional<ReservationEntity> reservationOptional = reservationRepository.findById(id);

        if (reservationOptional.isPresent()) {

            ReservationEntity reservation = reservationOptional.get();
            return ResponseEntity.ok(reservation);

        } else {

            throw new NotFoundException("Reservation not found");

        }

    }

    /**
     * Crea una prenotazione prendendo l'utente e il libro scelto attraverso i loro rispettivi ID, inoltre
     * in automatico nella variabile data verrò inserita la data di quando effettivamente si è creata la prenotazione
     *
     * @param userId L'ID dell'utente per il quale creare la prenotazione
     * @param bookId L'ID del libro scelto per la prenotazione
     * @return       Ritorna un aggetto ResponseEntity che indica lo stato dell'operazione di prenotazione:
     *               - ResponseEntity con status "200 OK" e un messaggio di successo se
     *                 la prenotazione è stata effettuata con successo.
     *               - ResponseEntity con status "400 Bad Request" e un messaggio di errore se il libro
     *                 non è al momento disponibile per la prenotazione.
     *               - ResponseEntity con status "404 Not Found" se l'utente o il libro non esistono nel database.
     */
    public ResponseEntity<String> createReservation(Long userId, Long bookId ) {

        Optional<UserEntity> userOp = userRepository.findById(userId);
        Optional<BookEntity> bookOp = bookRepository.findById(bookId);

        if (userOp.isPresent() && bookOp.isPresent()) {

            UserEntity user = userOp.get();
            BookEntity book = bookOp.get();

            if (book.getNumberOfCopiesAvailable() > 0) {

                ReservationEntity reservation = new ReservationEntity();
                reservation.setUserReservation(user);
                reservation.setBookReservation(book);
                reservation.setDateReservation(LocalDate.now());

                reservationRepository.saveAndFlush(reservation);

                book.setNumberOfCopiesAvailable((book.getNumberOfCopiesAvailable() - 1));

                bookRepository.saveAndFlush(book);

                return ResponseEntity.status(200).body("Book booked successfully!");
            } else {

                return ResponseEntity.badRequest().body("The book is not currently available for pre-order.");
            }

        } else {

            return ResponseEntity.notFound().build();
        }

    }



    /**
     * Aggiorna una prenotazione specificata tramite l'ID della prenotazione,
     * modificando la data, l'utente e il libro associato, inoltre aggiunge una copia disponibile
     * al libro precedentemente prenotato e ne toglie una a quello nuovo.
     *
     * @param reservationId L'ID della prenotazione da aggiornare
     * @param date          La nuova data di prenotazione da assegnare
     * @param userId        L'ID del nuovo utente associato alla prenotazione
     * @param bookId        L'ID del nuovo libro associato alla prenotazione
     * @return              Ritorna un oggetto ResponseEntity con status "200 OK" e
     *                      l'oggetto ReservationEntity aggiornato
     *                      se l'aggiornamento è avvenuto con successo.
     * @throws              NotFoundException Se la prenotazione, l'utente o il libro non esistono nel database viene
     *                                        lanciata un'eccezione personalizzata
     */
    public ResponseEntity<ReservationEntity> updateReservation(Long reservationId, LocalDate date, Long userId, Long bookId) {

        Optional<ReservationEntity> reservationUpdate = reservationRepository.findById(reservationId);
        Optional<UserEntity> userUpdate = userRepository.findById(userId);
        Optional<BookEntity> bookUpdate = bookRepository.findById(bookId);
        if (reservationUpdate.isPresent() && userUpdate.isPresent() && bookUpdate.isPresent()) {

            ReservationEntity reservation = reservationUpdate.get();
            reservation.getBookReservation().setNumberOfCopiesAvailable(
                    (reservation.getBookReservation().getNumberOfCopiesAvailable() + 1));

            UserEntity userEntity = userUpdate.get();
            BookEntity bookEntity = bookUpdate.get();
            bookEntity.setNumberOfCopiesAvailable((bookEntity.getNumberOfCopiesAvailable() - 1));


            reservation.setDateReservation(date);
            reservation.setUserReservation(userEntity);
            reservation.setBookReservation(bookEntity);


            ReservationEntity updatedReservation = reservationRepository.saveAndFlush(reservation);

            return ResponseEntity.ok(updatedReservation);

        } else {

            throw new NotFoundException("Reservation not found");

        }
    }

    /**
     * Elimina una prenotazione in base all'ID.
     *
     * @param id ID della prenotazione da eliminare
     * @return Ritorna un oggetto ResponseEntity vuoto con uno status "OK"
     *         se la prenotazione viene trovata ed eliminata,
     *         altrimenti restituisce uno status "Not Found"
     */
    public ResponseEntity<Void> deleteReservation(Long id) {

        Optional<ReservationEntity> reservationEntity = reservationRepository.findById(id);

        if(reservationEntity.isPresent()) {

            ReservationEntity reservation =  reservationEntity.get();
            reservation.getBookReservation().setNumberOfCopiesAvailable(
                    (reservation.getBookReservation().getNumberOfCopiesAvailable() + 1));

            reservationRepository.saveAndFlush(reservation);
            reservationRepository.deleteById(id);

            return ResponseEntity.status(200).build();

        }else {

            return ResponseEntity.noContent().build();

        }

    }

}
