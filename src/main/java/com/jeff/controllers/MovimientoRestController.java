package com.jeff.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aspectj.asm.IModelFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeff.entity.CuentasE;
import com.jeff.entity.MovimientoE;
import com.jeff.services.imple.ICuentaService;
import com.jeff.services.imple.IMovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoRestController {
	
	
	@Autowired
	 private IMovimientoService  movimientoService ;
	 
	 @RequestMapping("/all")
	    public ResponseEntity<?> all() {		 
		 MovimientoE movimiento = null ;	
		 List<MovimientoE> listaMovimiento = null ;		 
		 Map<String, Object> resp = new HashMap<>();
		     if (movimientoService.findAll().isEmpty()) {
		    	 resp.put("Mensaje", "No tiene datos");
		    	 return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		     }		     
		     listaMovimiento = movimientoService.findAll();		     
		     return new ResponseEntity<List< MovimientoE>>(listaMovimiento, HttpStatus.OK);		     
	    }
	 
	 
	 @PostMapping("/add")
		public ResponseEntity<?> add( @RequestBody MovimientoE movimiento, BindingResult result) {			
			MovimientoE movimientoNew = null;
			Map<String, Object> resp = new HashMap<>();			
			if(result.hasErrors()) {
				List<String> errors = result.getFieldErrors()
						.stream()
						.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
						.collect(Collectors.toList());
				
				resp.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.BAD_REQUEST);
			}
			
			try {
				movimientoNew = movimientoService.save(movimiento);
			} catch(DataAccessException e) {
				resp.put("mensaje", "Error al realizar el insert en la base de datos");
				resp.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			resp.put("mensaje", "La cuenta ha sido creado con éxito!");
			resp.put("cuenta", movimientoNew);
			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.CREATED);
		}
	 
	 @PutMapping("/{id}")
	  public ResponseEntity<?> update(@RequestBody MovimientoE movimiento, BindingResult result, @PathVariable Long id) {
			MovimientoE movimientoActual = movimientoService.findById(id);
			MovimientoE movimientoUpdated = null;
			Map<String, Object> resp = new HashMap<>();
			if(result.hasErrors()) {
				List<String> errors = result.getFieldErrors()
						.stream()
						.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
						.collect(Collectors.toList());
				
				resp.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.BAD_REQUEST);
			}
			
			if (movimientoActual == null) {
				resp.put("mensaje", "Error: no se pudo editar, el movimiento ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.NOT_FOUND);
			}
			
			if(movimientoActual.getValor() == 0  ) {
				resp.put("mensaje", "Saldo no disponible" );
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.NOT_FOUND);				
			}
			
			if(movimientoActual.getValor() > 1000  ) {
				resp.put("mensaje", "limite diario de retiro $1000" );
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.NOT_FOUND);				
			}
			

			try {

				movimientoActual.setFecha( movimiento.getFecha());   
				movimientoActual.setTipoMovimiento(movimiento.getTipoMovimiento());
				movimientoActual.setTotal(movimiento.getTotal() );
				movimientoActual.setValor(movimiento.getValor() );		
				
				movimientoUpdated = movimientoService.save(movimientoActual);

			} catch (DataAccessException e) {
				resp.put("mensaje", "Error al actualizar el movimiento en la base de datos");
				resp.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			resp.put("mensaje", "El movimiento ha sido actualizado con éxito!");
			resp.put("cuenta", movimientoUpdated);

			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.CREATED);
		}
	 
	 
	 
	 @DeleteMapping("/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) {			
			Map<String, Object> resp = new HashMap<>();			
			try {
			    movimientoService.delete(id);
			} catch (DataAccessException e) {
				resp.put("mensaje", "Error al eliminar la movimiento de la base de datos");
				resp.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}			
			resp.put("mensaje", "La cuenta ha sido eliminado con éxito!");
			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.OK);
		}
	 
	 

}
