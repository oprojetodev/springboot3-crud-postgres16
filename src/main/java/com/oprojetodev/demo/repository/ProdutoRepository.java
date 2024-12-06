package com.oprojetodev.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.oprojetodev.demo.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, String> {

}
