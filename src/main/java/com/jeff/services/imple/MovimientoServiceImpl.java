package com.jeff.services.imple;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeff.dao.IMovimientoDao;
import com.jeff.entity.MovimientoE;

@Service
@Transactional
public class MovimientoServiceImpl implements IMovimientoService {

	@Autowired
	private IMovimientoDao movimientoDao ;
	
	
	@Override
	public List<MovimientoE> findAll() {
		
		return (List<MovimientoE>) movimientoDao.findAll();
	}

	@Override
	public MovimientoE findById(Long id) {
	
		return movimientoDao.findById(id).orElse(null);
	}

	@Override
	public MovimientoE save(MovimientoE movimiento) {
		
		return movimientoDao.save(movimiento);
	}

	@Override
	public void delete(Long id) {
		movimientoDao.deleteById(id);
		
	}
	
	

}
