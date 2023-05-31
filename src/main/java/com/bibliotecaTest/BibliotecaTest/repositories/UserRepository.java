package com.bibliotecaTest.BibliotecaTest.repositories;

import com.bibliotecaTest.BibliotecaTest.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Mi dà la possibilità di facilitarmi la gestione delle entità utenti all'interno del database.
 * Usando l'annotazione {@code @Repository} indico a Spring che questa classe si occuperà della gestione
 * di accesso dei dati degli utenti e la loro persistenza all'interno del database.
 * Estendendo la classe JpaRepository posso usufruire di metodi predefiniti per eseguire operazioni
 * di base di creazione, lettura, aggiornamento e cancellazione (CRUD) su entità utenti persistenti nel database.
 *
 * @author Drumstyle92
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
