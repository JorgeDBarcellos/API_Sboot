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

import com.compasso.api.repository.CidadeRepository;
import com.compasso.api.resources.Cidade;

@RestController
@RequestMapping(path="/cidades")
public class CidadeResource{

	private CidadeRepository cidadeRepository;

	public CidadeResource(CidadeRepository cidadeRepository){
		super();
		this.cidadeRepository = cidadeRepository;
	}

	@PostMapping
	public ResponseEntity<Cidade> save(@RequestBody Cidade cidade){
		cidadeRepository.save(cidade);
		return new ResponseEntity<>(cidade, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Cidade>> getAll(){
		List<Cidade> cidades = new ArrayList<>();
		cidades = cidadeRepository.findAll();
		return new ResponseEntity<>(cidades, HttpStatus.OK);
	}

	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Cidade>> getById(@PathVariable Integer id){
		Optional<Cidade> cidade;
		try{
			cidade = cidadeRepository.findById(id);
			return new ResponseEntity<Optional<Cidade>>(cidade, HttpStatus.OK);
		}catch (NoSuchElementException nsee){
			return new ResponseEntity<Optional<Cidade>>(HttpStatus.NOT_FOUND);
		}
	}


		@DeleteMapping(path="/{id}")
		public ResponseEntity<Optional<Cidade>> deleteById(@PathVariable Integer id){
			try{
				cidadeRepository.deleteById(id);
				return new ResponseEntity<Optional<Cidade>>(HttpStatus.OK);
			}catch (NoSuchElementException nsee){
				return new ResponseEntity<Optional<Cidade>>(HttpStatus.NOT_FOUND);
			}
		}

		@PutMapping(value="/{id}")
		public ResponseEntity<Cidade> update(@PathVariable Integer id, @RequestBody Cidade novaCidade){
			return cidadeRepository.findById(id)
					.map(cidade -> {
						cidade.setNome(novaCidade.getNome());
						cidade.setEstado(novaCidade.getEstado());
						Cidade cidadeUpdated = cidadeRepository.save(cidade);
						return ResponseEntity.ok().body(cidadeUpdated);
					}).orElse(ResponseEntity.notFound().build());
		}
	}