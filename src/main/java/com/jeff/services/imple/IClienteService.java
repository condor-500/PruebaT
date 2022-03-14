package com.jeff.services.imple;

import java.util.List;

import com.jeff.entity.ClienteE;


public interface IClienteService {
	
     List<ClienteE> findAll();
	
	 ClienteE findById(Long id);
	
	 ClienteE save(ClienteE cliente);
	
	  void delete(Long id);

}
