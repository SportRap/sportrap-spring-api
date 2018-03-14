package br.com.sportrap.api.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sportrap.api.model.BaseModelo;

public interface BaseDao <T extends BaseModelo> extends JpaRepository<T, Serializable> {

}
