package com.bibliotecaTest.BibliotecaTest.entities;

import jakarta.persistence.*;

/**
 * Entità che rappresenta un libro nel database.
 *
 * @author Drumstyle92
 */
@Entity
@Table(name = "book")
public class BookEntity {

    /**
     * ID del libro
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    /**
     * la descrizione del libro
     */
    @Column
    private String description;

    /**
     * Il titolo del libro
     */
    @Column
    private String title;

    /**
     * l'autore del libro
     */
    @Column
    private String author;

    /**
     *  Numero di copie disponibili del libro
     */
    @Column(name = "copies_available")
    private int numberOfCopiesAvailable;

    /**
     * Costruttore di default di BookEntity.
     */
    public BookEntity() {}

    /**
     * Istanza di un nuovo oggetto BookEntity con i parametri specificati.
     *
     * @param bookId                  l'ID del libro
     * @param description             la descrizione del libro
     * @param title                   il titolo del libro
     * @param author                  l'autore del libro
     * @param numberOfCopiesAvailable il numero di copie disponibili del libro
     */
    public BookEntity(Long bookId, String description, String title, String author, int numberOfCopiesAvailable) {
        this.bookId = bookId;
        this.description = description;
        this.title = title;
        this.author = author;
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
    }

    /**
     * Ottieni l'ID del libro.
     * @return l'ID del libro
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * Imposti l'ID del libro.
     * @param bookId l'ID del libro da impostare
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * Ottieni la descrizione del libro.
     * @return la descrizione del libro
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposti la descrizione del libro.
     * @param description la descrizione del libro da impostare
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Ottieni il titolo del libro.
     * @return il titolo del libro
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposti il titolo del libro.
     * @param title il titolo del libro da impostare
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Ottieni l'autore del libro.
     * @return l'autore del libro
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Imposti l'autore del libro.
     * @param author l'autore del libro da impostare
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Ottieni il numero di copie disponibili del libro.
     * @return il numero di copie disponibili del libro
     */
    public int getNumberOfCopiesAvailable() {
        return numberOfCopiesAvailable;
    }

    /**
     * Imposti il numero di copie disponibili del libro.
     * @param numberOfCopiesAvailable il numero di copie disponibili del libro da impostare
     */
    public void setNumberOfCopiesAvailable(int numberOfCopiesAvailable) {
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
    }

}
