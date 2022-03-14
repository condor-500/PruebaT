package com.jeff.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.jeff.entity.ClienteE;
import com.jeff.entity.CuentasE;
import com.jeff.services.imple.ICuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaRespController {
	
	
	 @Autowired
	 private ICuentaService  cuentaService ;
	 
	 @RequestMapping("/all")
	    public ResponseEntity<?> all() {		 
		 CuentasE cuenta = null ;	
		 List<CuentasE> listaCuentas = null ;		 
		 Map<String, Object> resp = new HashMap<>();
		     if (cuentaService.findAll().isEmpty()) {
		    	 resp.put("Mensaje", "No tiene datos");
		    	 return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		     }		     
		     listaCuentas = cuentaService.findAll();		     
		     return new ResponseEntity<List< CuentasE>>(listaCuentas, HttpStatus.OK);		     
	    }
	 
	 
	 @PostMapping("/add")
		public ResponseEntity<?> add( @RequestBody CuentasE cuenta, BindingResult result) {			
			CuentasE cuentaNew = null;
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
				cuentaNew = cuentaService.save(cuenta);
			} catch(DataAccessException e) {
				resp.put("mensaje", "Error al realizar el insert en la base de datos");
				resp.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			resp.put("mensaje", "La cuenta ha sido creado con éxito!");
			resp.put("cuenta", cuentaNew);
			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.CREATED);
		}
	 
	 @PutMapping("/{id}")
	  public ResponseEntity<?> update(@RequestBody CuentasE cuenta, BindingResult result, @PathVariable Long id) {
			CuentasE cuentaActual = cuentaService.findById(id);
			CuentasE cuentaUpdated = null;
			Map<String, Object> resp = new HashMap<>();
			if(result.hasErrors()) {
				List<String> errors = result.getFieldErrors()
						.stream()
						.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
						.collect(Collectors.toList());
				
				resp.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.BAD_REQUEST);
			}
			
			if (cuentaActual == null) {
				resp.put("mensaje", "Error: no se pudo editar, el cuenta ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.NOT_FOUND);
			}

			try {

				cuentaActual.setNumCuenta(cuenta.getNumCuenta() ); 
				cuentaActual.setEstado(cuenta.isEstado() );
				cuentaActual.setSaldoinicial(cuenta.getSaldoinicial());
				cuentaActual.setTipocuenta(cuenta.getTipocuenta());				
				
				cuentaUpdated = cuentaService.save(cuentaActual);

			} catch (DataAccessException e) {
				resp.put("mensaje", "Error al actualizar el cuenta en la base de datos");
				resp.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			resp.put("mensaje", "El cuenta ha sido actualizado con éxito!");
			resp.put("cuenta", cuentaUpdated);

			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.CREATED);
		}
	 
	 
	 
	 @DeleteMapping("/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) {			
			Map<String, Object> resp = new HashMap<>();			
			try {
			    cuentaService.delete(id);
			} catch (DataAccessException e) {
				resp.put("mensaje", "Error al eliminar la cuenta de la base de datos");
				resp.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}			
			resp.put("mensaje", "La cuenta ha sido eliminado con éxito!");
			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.OK);
		}
	 
	 
	 
}
