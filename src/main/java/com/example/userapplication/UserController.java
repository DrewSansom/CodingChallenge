package com.example.userapplication;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is the main controller for the application. It handles GET, POST, and DELETE requests
 *
 * @author drewsansom
 * @version 1.0
 * @since 2021-4-29
 */
@RestController
public class UserController {

    // creates connection to JPA repository
    private final UserRepository repository;

    // Assists with creating model links for HTTP responses
    private final UserModelAssembler assembler;

    /**
     * Constructor to create controller
     * @param repository the JPA repository
     * @param assembler the assembler for creating model links for users
     */
    public UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    /**
     * Shows all users in JSON formate in alphabetical order by last name
     * @return A CollectionModel of EntityModels
     */
    @GetMapping("/users")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = repository.findAll(Sort.by(Sort.Direction.ASC, "lastName"))
                .stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }


    /**
     * Shows a single user based on their ID in JSON format. Throws UserNotFoundException if the user DNE
     * @param id the id of the user to find
     * @return an EntityModel of the user
     */
    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id) {

        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }


    /**
     * Adds a new user if the first/last name pair DNE. Case sensitve so 'john doe' and "John doe" can both exist
     * @param newUser a new User object in JSON format. Only first/last name is needed since ID can be generated
     *                automatically
     * @return A ResponseEntity that represents the success or failure of the call
     */
    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {

        // Custom query to check that first and last name in the db don't exist
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.endsWith())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.endsWith());

        Example<User> example = Example.of(newUser, matcher);
        boolean exists = repository.exists(example);

        if (exists) {
             throw new UserExistsException(newUser.getFirstName(), newUser.getLastName());
        }

        // If the user doesn't exist, create one and return
        EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    /**
     * Deletes user if they exist in the database. Throws CantDeleteUserException if they do not
     * @param id The id of the user to delete
     * @return A ResponseEntity that represents the success or failure of the call
     */
    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {

        repository.findById(id).orElseThrow(() -> new CantDeleteUserException(id));
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
