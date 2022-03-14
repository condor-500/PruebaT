package com.jeff.services.imple;

import java.util.List;


import com.jeff.entity.MovimientoE;

public interface IMovimientoService {
	
	 List<MovimientoE> findAll();
		
	 MovimientoE findById(Long id);
	
	 MovimientoE save(MovimientoE movimiento);
	
	  void delete(Long id);

}
