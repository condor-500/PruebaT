package com.jeff.services.imple;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jeff.dao.IClienteDao;
import com.jeff.entity.ClienteE;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteDao  clienteDao;

	@Override
	public List<ClienteE> findAll() {
		
		return (List<ClienteE>) clienteDao.findAll();
	}

	@Override
	public   ClienteE findById(Long id) {
	
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public ClienteE save(ClienteE cliente) {
	
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
		
	}
	
	
	

}
