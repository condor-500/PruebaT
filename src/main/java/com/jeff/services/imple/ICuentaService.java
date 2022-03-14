package com.jeff.services.imple;

import java.util.List;


import com.jeff.entity.CuentasE;

public interface ICuentaService {
	
	 List<CuentasE> findAll();
		
	 CuentasE findById(Long id);
	
	 CuentasE save(CuentasE cuenta);
	
	  void delete(Long id);

}
