package com.oprojetodev.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oprojetodev.demo.model.Produto;
import com.oprojetodev.demo.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoRepository produtoRepository;

	public ProdutoController(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}

	@GetMapping
	public ResponseEntity<Object> lista() {
		List<Produto> lista = (List<Produto>) produtoRepository.findAll();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> carrega(@PathVariable(name = "id") String id) {

		Produto produto = null;

		Optional<Produto> op = produtoRepository.findById(id);
		if (op.isPresent())
			produto = op.get();

		return ResponseEntity.ok(produto);
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Object> adiciona(@RequestBody ProdutoForm form) {

		Produto produto = new Produto();
		produto.setId(UUID.randomUUID().toString());
		produto.setCodigo(form.getCodigo());
		produto.setNome(form.getNome());
		produto = produtoRepository.save(produto);

		return ResponseEntity.ok(produto);
	}

	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> atualiza(@PathVariable(name = "id") String id,
			@RequestBody ProdutoForm form) {

		Produto produto = null;

		Optional<Produto> op = produtoRepository.findById(id);
		if (op.isPresent()) {
			produto = op.get();
			produto.setCodigo(form.getCodigo());
			produto.setNome(form.getNome());
			produto = produtoRepository.save(produto);
		}

		return ResponseEntity.ok(produto);

	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> delete(@PathVariable(name = "id") String id) {
		produtoRepository.deleteById(id);
		return ResponseEntity.ok(id);
	}

}