package com.bibliotecaTest.BibliotecaTest.entities;

import jakarta.persistence.*;

/**
 * Entità che rappresenta un utente nel database
 *
 * @author Drumstyle92
 */
@Entity
@Table(name="user_")
public class UserEntity {

    /**
     *  ID dell'utente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     *  il nome dell'utente
     */
    @Column(name="first_name")
    private String firstName;

    /**
     * il cognome dell'utente
     */
    @Column(name="last_name")
    private String lastName;

    /**
     * l'indirizzo dell'utente
     */
    @Column
    private String address;

    /**
     * il numero di telefono dell'utente
     */
    @Column
    private long telephone;

    /**
     * Costruttore di default di UserEntity.
     */
    public UserEntity(){}

    /**
     * Crea una nuova istanza di UserEntity con i parametri specificati.
     *
     * @param userId    l'ID dell'utente
     * @param firstName il nome dell'utente
     * @param lastName  il cognome dell'utente
     * @param address   l'indirizzo dell'utente
     * @param telephone il numero di telefono dell'utente
     */
    public UserEntity(Long userId, String firstName, String lastName, String address, long telephone) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }

    /**
     * Ottieni l'ID dell'utente.
     * @return l'ID dell'utente
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * Imposti l'ID dell'utente.
     * @param userId l'ID dell'utente da impostare
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * Ottieni il nome dell'utente.
     * @return il nome dell'utente
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Imposti il nome dell'utente.
     * @param firstName il nome dell'utente da impostare
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Ottieni il cognome dell'utente.
     * @return il cognome dell'utente
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Imposti il cognome dell'utente.
     * @param lastName il cognome dell'utente da impostare
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Ottieni l'indirizzo dell'utente.
     * @return l'indirizzo dell'utente
     */
    public String getAddress() {
        return address;
    }
    /**
     * Imposti l'indirizzo dell'utente.
     * @param address l'indirizzo dell'utente da impostare
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Ottieni il numero di telefono dell'utente.
     * @return il numero di telefono dell'utente
     */
    public long getTelephone() {
        return telephone;
    }
    /**
     * Imposti il numero di telefono dell'utente.
     * @param telephone il numero di telefono dell'utente da impostare
     */
    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }
}