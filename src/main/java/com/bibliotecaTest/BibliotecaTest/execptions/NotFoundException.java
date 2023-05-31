package com.bibliotecaTest.BibliotecaTest.execptions;

/**
 * Eccezione personalizzata per segnalare un'entità non trovata.
 * @author Drumstyle92
 */
public class NotFoundException extends RuntimeException {

    /**
     * Crea una nuova istanza di NotFoundException con il messaggio specificato.
     *
     * @param message il messaggio di errore che descrive l'entità non trovata.
     */
    public NotFoundException(String message) {

        super(message);

    }

}
