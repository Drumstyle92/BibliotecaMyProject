package com.bibliotecaTest.BibliotecaTest.services;

import com.bibliotecaTest.BibliotecaTest.entities.UserEntity;
import com.bibliotecaTest.BibliotecaTest.execptions.NotFoundException;
import com.bibliotecaTest.BibliotecaTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Contiene la logica di business relativa agli utenti.
 * Tutti i servizi relativi agli utenti sono implementati in questa classe.
 * Utilizzando l'annotazione {@code @Service} si indica a Spring che questa classe è un componente di servizio.
 *
 * @author Drumstyle92
 */
@Service
public class UserService {

    /**
     * Utilizzando L'annotazione {@code @Autowired} diamo il compito a Spring di fare l'iniezione delle dipendenze.
     * Il questo modo semplifico il processo di collegamento tra il servizio e il repository degli utenti.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Recupera tutti gli utenti presenti nel database.
     *
     * @return Ritorna un oggetto ResponseEntity contenente una lista di utenti, se presenti,
     * altrimenti una risposta vuota con stato HTTP 204 No Content.
     * Utilizzando la classe ResponseEntity ottengo una risposta più dettagliata e personalizzabile
     * infatti è stata creata per le chiamate API.
     */
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        if (!users.isEmpty()) {

            return ResponseEntity.ok(users);
        } else {

            return ResponseEntity.noContent().build();
        }

    }

    /**
     * Recupera un utente attraverso il suo ID.
     *
     * @param id L'ID dell'utente
     * @return   Ritorna un oggetto ResponseEntity contenente l'utente trovato, se presente
     * @throws   NotFoundException se l'utente con l'ID specificato non è presente nel database
     *                             ritorna un'eccezione personalizzata
     */
    public ResponseEntity<UserEntity> getUserById(Long id) {

        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            return ResponseEntity.ok(user);
        } else {

            throw new NotFoundException("User not found");
        }

    }

    /**
     * Crea un nuovo utente.
     *
     * @param userEntity Oggetto UserEntity che contiene i dati dell'utente da creare
     * @return Ritorna un oggetto ResponseEntity che contiene l'utente appena creato
     */
    public ResponseEntity<UserEntity> createUser(UserEntity userEntity) {

        UserEntity createdUser = userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Modifica i dati di un utente selezionandolo tramite l'ID.
     *
     * @param id         L'ID dell'utente da aggiornare
     * @param userEdited Oggetto UserEntity contenente i dati aggiornati dell'utente
     * @return           Ritorna un oggetto ResponseEntity che contiene i dati aggiornati dell'utente, se presente
     * @throws           NotFoundException se l'utente non viene trovato nel database
     *                                     lancia un'eccezione personalizzata
     */
    public ResponseEntity<UserEntity> updateUser(Long id, UserEntity userEdited) {

        Optional<UserEntity> userUpdate = userRepository.findById(id);

        if (userUpdate.isPresent()) {
            UserEntity userUp = userUpdate.get();

            userUp.setFirstName(userEdited.getFirstName());
            userUp.setLastName(userEdited.getLastName());
            userUp.setAddress(userEdited.getAddress());
            userUp.setTelephone(userEdited.getTelephone());

            UserEntity updatedUser = userRepository.saveAndFlush(userUp);

            return ResponseEntity.ok(updatedUser);
        } else {

            throw new NotFoundException("User not found");
        }

    }

    /**
     * Elimina un utente in base all'ID.
     *
     * @param id ID dell'utente da eliminare
     * @return Ritorna un oggetto ResponseEntity vuoto con uno status "OK" se l'utente viene trovato ed eliminato,
     *         altrimenti restituisce uno status "Not Found"
     */
    public ResponseEntity<Void> deleteUser(Long id) {

        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            userRepository.deleteById(id);

            return ResponseEntity.status(200).build();
        } else {

            return ResponseEntity.notFound().build();
        }

    }

}






