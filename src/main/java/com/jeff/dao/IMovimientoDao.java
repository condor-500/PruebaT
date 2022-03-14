package com.jeff.dao;

import org.springframework.data.repository.CrudRepository;


import com.jeff.entity.MovimientoE;

public interface IMovimientoDao extends CrudRepository<MovimientoE, Long> {

}
