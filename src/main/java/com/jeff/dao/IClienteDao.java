package com.jeff.dao;

import org.springframework.data.repository.CrudRepository;

import com.jeff.entity.ClienteE;

public interface IClienteDao extends CrudRepository<ClienteE, Long> {

}
