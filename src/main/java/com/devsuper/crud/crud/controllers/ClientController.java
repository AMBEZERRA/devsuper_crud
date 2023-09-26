package com.devsuper.crud.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuper.crud.crud.dto.ClientDTO;
import com.devsuper.crud.crud.services.ClientService;


@RestController
@RequestMapping(value = "/client")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping(value = "/{id}")
	public ClientDTO findById(@PathVariable Long  id) {
		ClientDTO dto = service.findById(id);
		return dto;
	}
}
