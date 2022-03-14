package com.jeff.dao;

import org.springframework.data.repository.CrudRepository;

import com.jeff.entity.CuentasE;

public interface ICuentaDao extends CrudRepository<CuentasE, Long> {

}
