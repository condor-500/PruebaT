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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeff.entity.ClienteE;
import com.jeff.entity.PersonaE;
import com.jeff.services.imple.IClienteService;



@RestController
@RequestMapping("/clientes")
public class ClienteRespController {
		
	@Autowired
	private IClienteService clienteService;
	
	
	 @RequestMapping("/all")
	    public ResponseEntity<?> all() {		 
		 ClienteE cliente = null ;	
		 List<ClienteE> listaCliente = null ;		 
		 Map<String, Object> resp = new HashMap<>();
		     if (clienteService.findAll().isEmpty()) {
		    	 resp.put("Mensaje", "No tiene datos");
		    	 return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		     }		     
		     listaCliente = clienteService.findAll();		     
		     return new ResponseEntity<List< ClienteE>>(listaCliente, HttpStatus.OK);		     
	    }
	 
	 @PostMapping("/add")
		public ResponseEntity<?> create( @RequestBody ClienteE cliente, BindingResult result) {			
			ClienteE clienteNew = null;
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
				clienteNew = clienteService.save(cliente);
			} catch(DataAccessException e) {
				resp.put("mensaje", "Error al realizar el insert en la base de datos");
				resp.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			resp.put("mensaje", "El cliente ha sido creado con éxito!");
			resp.put("cliente", clienteNew);
			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.CREATED);
		}
	 
	
	  @PutMapping("/{id}")
	  public ResponseEntity<?> update(@RequestBody ClienteE cliente, BindingResult result, @PathVariable Long id) {
			ClienteE clienteActual = clienteService.findById(id);
			ClienteE clienteUpdated = null;
			Map<String, Object> resp = new HashMap<>();
			if(result.hasErrors()) {
				List<String> errors = result.getFieldErrors()
						.stream()
						.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
						.collect(Collectors.toList());
				
				resp.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.BAD_REQUEST);
			}
			
			if (clienteActual == null) {
				resp.put("mensaje", "Error: no se pudo editar, el cliente ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.NOT_FOUND);
			}

			try {

				clienteActual.setEstado(cliente.getEstado());
				clienteActual.setContrasenia(cliente.getContrasenia());
				clienteUpdated = clienteService.save(clienteActual);

			} catch (DataAccessException e) {
				resp.put("mensaje", "Error al actualizar el cliente en la base de datos");
				resp.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			resp.put("mensaje", "El cliente ha sido actualizado con éxito!");
			resp.put("cliente", clienteUpdated);

			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.CREATED);
		}
	 
	 
	 
	 @DeleteMapping("/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) {			
			Map<String, Object> resp = new HashMap<>();			
			try {
			    clienteService.delete(id);
			} catch (DataAccessException e) {
				resp.put("mensaje", "Error al eliminar el cliente de la base de datos");
				resp.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
			}			
			resp.put("mensaje", "El cliente eliminado con éxito!");
			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.OK);
		}
	 
	 
	

}
