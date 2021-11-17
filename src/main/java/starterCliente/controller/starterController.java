package starterCliente.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import starter.configuracion.Estado;

@RestController
public class starterController {

	@Autowired
	private Estado status;

	private RestTemplate template = new RestTemplate();
	private String url = "http://localhost:8080/";

	@GetMapping(path = "/status")
	public List<String> nextStatus() {
	
			String urlTemp = url + "actuator/estados";
			List<String> respuesta = new ArrayList<>();
			ResponseEntity<String[]> responseEntity = template.getForEntity(urlTemp, String[].class);
			List<String> listaEstados = Arrays.asList(responseEntity.getBody());
			System.out.println(listaEstados.get(1));
			for (int i = 0; i < listaEstados.size(); i++) {
				 respuesta = status.nextStatus(listaEstados.get(i));
			}
			
	
		return respuesta;

	}
}
