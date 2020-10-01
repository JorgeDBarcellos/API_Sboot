package com.compasso.api.models;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.api.repository.ClienteRepository;
import com.compasso.api.resources.Cliente;

@RestController
@RequestMapping(path="/clientes")
public class ClienteResource{
	
	private ClienteRepository clienteRepository;

	public ClienteResource(ClienteRepository clienteRepository){
		super();
		this.clienteRepository = clienteRepository;
	}

	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
		clienteRepository.save(cliente);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> getAll(){
		List<Cliente> clientes = new ArrayList<>();
		clientes = clienteRepository.findAll();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Cliente>> getById(@PathVariable Integer id){
		Optional<Cliente> cliente;
		try{
			cliente = clienteRepository.findById(id);
			return new ResponseEntity<Optional<Cliente>>(cliente, HttpStatus.OK);
		}catch (NoSuchElementException nsee){
			return new ResponseEntity<Optional<Cliente>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Cliente>> deleteById(@PathVariable Integer id){
	try{
		clienteRepository.deleteById(id);
		return new ResponseEntity<Optional<Cliente>>(HttpStatus.OK);
	}catch (NoSuchElementException nsee){
		return new ResponseEntity<Optional<Cliente>>(HttpStatus.NOT_FOUND);
	}
}

@PutMapping(value="/{id}")
public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente novoCliente){
	return clienteRepository.findById(id)
			.map(cliente -> {
				cliente.setNome(novoCliente.getNome());
				Cliente clienteUpdated = clienteRepository.save(cliente);
				return ResponseEntity.ok().body(clienteUpdated);
			}).orElse(ResponseEntity.notFound().build());
}
}