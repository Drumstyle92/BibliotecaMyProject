package com.bibliotecaTest.BibliotecaTest.repositories;

import com.bibliotecaTest.BibliotecaTest.entities.BookEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Mi dà la possibilità di facilitarmi la gestione delle entità libri all'interno del database.
 * Usando l'annotazione {@code @Repository} indico a Spring che questa classe si occuperà della gestione
 * di accesso dei dati dei libri e la loro persistenza all'interno del database.
 * Estendendo la classe JpaRepository posso usufruire di metodi predefiniti per eseguire operazioni
 * di base di creazione, lettura, aggiornamento e cancellazione (CRUD) su entità libri persistenti nel database.
 *
 * @author Drumstyle92
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    /**
     * Selezionando l'ID del libro possiamo vedere quante copie ha ancora disponibili.
     * Metodo personalizzato usando l'annotazione {@code @Query} che mi dà la possibilità
     * di fare query personalizzate, inoltre ho usato il linguaggio JPQL in modo tale da poter interrogare
     * adattandosi al database relazionale che si sta gestendo.
     * Annotazione {@code @Nullable} che mi dà la possibilità che ritorni come risposta null.
     *
     * @param bookId l'ID del libro selezionato
     * @return Ritorna il numero di copie che ha il libro selezionato.
     */
    @Query("SELECT b.numberOfCopiesAvailable FROM BookEntity b WHERE b.bookId = :bookId")
    @Nullable
    Integer copiesAvailableByBookId(@Param("bookId") Long bookId);

}

