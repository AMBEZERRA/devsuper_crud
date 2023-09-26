package com.devsuper.crud.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuper.crud.crud.dto.ClientDTO;
import com.devsuper.crud.crud.services.ClientService;


@RestController 
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	/*
	@GetMapping(value = "/{id}")
	public ClientDTO findById(@PathVariable Long  id) {
		ClientDTO dto = service.findById(id);
		return dto;
	}
	*/
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
		ClientDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	/*
	@GetMapping
	public Page<ClientDTO>  findAll(Pageable pageable ) {
		return service.findAll(pageable);
	}
	*/
	@GetMapping
	public ResponseEntity<Page<ClientDTO>>  findAll(Pageable pageable ) {
		Page<ClientDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}
}
