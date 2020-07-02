package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Pessoa;
import com.repository.PessoaRepository;

@RestController
public class PessoaController {

	@Autowired
	private PessoaRepository repository;

	public PessoaController(PessoaRepository repo) {
		this.repository = repo;
	}

	@PostMapping
	public ResponseEntity<Pessoa> cadastrar(@RequestBody Pessoa pessoa) {
		Pessoa p = repository.save(pessoa);
		if (p != null) {
			return new ResponseEntity<Pessoa>(p, HttpStatus.OK);
		} else {
			return new ResponseEntity<Pessoa>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> p = repository.findAll();
		if (p != null) {
			return new ResponseEntity<List<Pessoa>>(p, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Pessoa>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/buscaum/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);
		if (pessoa != null) {
			return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/buscadois/{id}")
	public ResponseEntity<Optional<Pessoa>> buscarPorIdd(@PathVariable Long id) {
		Optional<Pessoa> p = repository.findById(id);
		if (p.isPresent()) {
			return new ResponseEntity<Optional<Pessoa>>(p, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<Pessoa>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletarPorId(@PathVariable Long id){
		repository.deleteById(id);	
	}
}
