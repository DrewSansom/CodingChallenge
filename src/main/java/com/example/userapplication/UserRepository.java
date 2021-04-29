package com.example.userapplication;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Creates a Repository for Users based on JPA
 */
interface UserRepository extends JpaRepository<User, Long> {
}
