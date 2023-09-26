package com.devsuper.crud.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuper.crud.crud.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
