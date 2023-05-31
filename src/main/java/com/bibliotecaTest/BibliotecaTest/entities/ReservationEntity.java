package com.bibliotecaTest.BibliotecaTest.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entità che rappresenta una prenotazione nel database.
 *
 * @author Drumstyle92
 */
@Entity
@Table(name="reservation")
public class ReservationEntity {

    /**
     * ID della prenotazione.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    /**
     * Data della prenotazione.
     */
    @Column(name="date_reservation")
    private LocalDate dateReservation;

    /**
     * Libro associato alla prenotazione.
     * Annotazione {@code @ManyToOne} per far capire e gestire a Spring la relazione
     * tra libro e prenotazione (molti /libri/ a uno /prenotazione/).
     * Annotazione {@code @JoinColumn} che indica la colonna di join utilizzata per la relazione.
     */
    @ManyToOne
    @JoinColumn(name="book_reservation")
    BookEntity bookReservation;

    /**
     * Utente associato alla prenotazione.
     * Annotazione {@code @ManyToOne} per far capire e gestire a Spring la relazione
     * tra utente e prenotazione (molti /utenti/ a uno /prenotazione/).
     * Annotazione {@code @JoinColumn} che indica la colonna di join utilizzata per la relazione.
     */
    @ManyToOne
    @JoinColumn(name="user_reservation")
    UserEntity userReservation;

    /**
     * Costruttore di default di ReservationEntity.
     */
    public ReservationEntity(){}

    /**
     * Crea una nuova istanza di ReservationEntity con i parametri specificati.
     * @param reservationId   l'ID della prenotazione
     * @param dateReservation la data della prenotazione
     * @param bookReservation il libro associato alla prenotazione
     * @param userReservation l'utente associato alla prenotazione
     */
    public ReservationEntity(Long reservationId, LocalDate dateReservation, BookEntity bookReservation, UserEntity userReservation) {
        this.reservationId = reservationId;
        this.dateReservation = dateReservation;
        this.bookReservation = bookReservation;
        this.userReservation = userReservation;
    }
    /**
     * Ottieni l'ID della prenotazione.
     * @return l'ID della prenotazione
     */
    public Long getReservationId() {
        return reservationId;
    }
    /**
     * Imposti l'ID della prenotazione.
     * @param reservationId l'ID della prenotazione da impostare
     */
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
    /**
     * Ottieni la data della prenotazione.
     * @return la data della prenotazione
     */
    public LocalDate getDateReservation() {
        return dateReservation;
    }
    /**
     * Imposti la data della prenotazione.
     * @param dateReservation la data della prenotazione da impostare
     */
    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }
    /**
     * Ottieni il libro associato alla prenotazione.
     * @return il libro associato alla prenotazione
     */
    public BookEntity getBookReservation() {
        return bookReservation;
    }
    /**
     * Imposti il libro associato alla prenotazione.
     * @param bookReservation il libro associato alla prenotazione da impostare
     */
    public void setBookReservation(BookEntity bookReservation) {
        this.bookReservation = bookReservation;
    }
    /**
     * Ottieni l'utente associato alla prenotazione.
     * @return l'utente associato alla prenotazione
     */
    public UserEntity getUserReservation() {
        return userReservation;
    }
    /**
     * Imposti l'utente associato alla prenotazione.
     * @param userReservation l'utente associato alla prenotazione da impostare
     */
    public void setUserReservation(UserEntity userReservation) {
        this.userReservation = userReservation;
    }

}
