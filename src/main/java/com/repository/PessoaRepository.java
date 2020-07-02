package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

}
