package com.jeff.services.imple;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeff.dao.ICuentaDao;

import com.jeff.entity.CuentasE;

@Service
@Transactional
public class CuentaServiceImpl implements ICuentaService  {

	@Autowired
	private ICuentaDao  cuentaDao ;
	
	
	@Override
	public List<CuentasE> findAll() {
		
		return  (List<CuentasE>) cuentaDao.findAll();
	}

	@Override
	public CuentasE findById(Long id) {
		
		return cuentaDao.findById(id).orElse(null);
	}

	@Override
	public CuentasE save(CuentasE cuenta) {
	
		return cuentaDao.save(cuenta);
	}

	@Override
	public void delete(Long id) {
		cuentaDao.deleteById(id);
		
	}

	
	

	
	
}
