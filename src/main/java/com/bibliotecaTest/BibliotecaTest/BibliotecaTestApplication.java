package com.bibliotecaTest.BibliotecaTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Classe principale di Spring Boot per l'applicazione BibliotecaTest.
 * <p>
 * L'annotazione {@code @SpringBootApplication} è l'entry point dell'applicazione ed è utilizzata
 * per semplificare la configurazione iniziale e fornire una base di configurazione usufruibile.
 * </p>
 * <p>
 * L'annotazione {@code @EntityScan} è utilizzata per aiutare Hibernate a costruire automaticamente
 * le tabelle nel database, indicando il package in cui si trovano le entità.
 * </p>
 *
 * @author Drumstyle92
 */
@SpringBootApplication
@EntityScan(basePackages = "com.bibliotecaTest.BibliotecaTest.entities")
public class BibliotecaTestApplication {

    /**
     * Metodo principale per far partire il programma.
     *
     * @param args Il parametro principale dove passano i dati che elabora il metodo.
     */
    public static void main(String[] args) {
		SpringApplication.run(BibliotecaTestApplication.class, args);
	}

}
