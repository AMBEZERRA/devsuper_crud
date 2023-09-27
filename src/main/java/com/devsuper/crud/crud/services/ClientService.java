package com.devsuper.crud.crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.devsuper.crud.crud.dto.ClientDTO;
import com.devsuper.crud.crud.entities.Client;
import com.devsuper.crud.crud.repositories.ClientRepository;
import com.devsuper.crud.crud.services.exceptions.DataBaseException;
import com.devsuper.crud.crud.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

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
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			return new ClientDTO(entity);
					
		}
		catch(EntityNotFoundException e) {
			throw new ResourceAccessException("ID não encontrado");
		}
	}
	
	@Transactional // padrão pra o tipo de erro esperado
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Elemento não encontrado");
		}
		try {
		repository.deleteById(id);
	} catch (DataIntegrityViolationException e){
		throw new DataBaseException("Falha de integridade referencial");
	}
		
}
	
	//Metodo auxiliar
	 private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());

	}
	
}
