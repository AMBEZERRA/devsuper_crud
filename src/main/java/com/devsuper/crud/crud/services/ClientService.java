package com.devsuper.crud.crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuper.crud.crud.dto.ClientDTO;
import com.devsuper.crud.crud.entities.Client;
import com.devsuper.crud.crud.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;	
	
	
	public ClientDTO findById(Long id) {
		Optional<Client> result = repository.findById(id);
		Client client = result.get();
		ClientDTO dto = new ClientDTO(client);
		return dto;
	}
	
	public Page<ClientDTO>  findAll(Pageable pageable ) {
		Page<Client> result = repository.findAll(pageable);
		return result.map(x -> new ClientDTO(x));
	}
	
}
