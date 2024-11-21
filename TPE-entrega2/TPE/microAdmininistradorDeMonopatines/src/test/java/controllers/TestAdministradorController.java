package controllers;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.example.microAdministrador.dto.AdministradorDTO;
import com.example.microAdministrador.dto.MonopatinRequestDTO;
import com.example.microAdministrador.model.Tarifa;
import com.example.microAdministrador.model.Monopatin;
import com.example.microAdministrador.service.AdministradorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class TestAdministradorController {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdministradorService adminSer;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@DisplayName("test de obtener los datos de administrador, por controller")
	@Test
	public void testGetAdministrador() throws Exception {
		List<Monopatin> monopatines=new ArrayList<>();
		monopatines.add(any(Monopatin.class));
		monopatines.add(any(Monopatin.class));
		monopatines.add(any(Monopatin.class));
		monopatines.add(any(Monopatin.class));
		monopatines.add(any(Monopatin.class));
		
		List<Double[]> paradas=new ArrayList<>();
		Double[] parada1= {54.3,76.3}, parada2= {52.1,25.3}, parada3= {22.3,76.1};
		paradas.add(parada1);
		paradas.add(parada3);
		paradas.add(parada2);
		
		List<Tarifa> tarifas=new ArrayList<>();
		tarifas.add(any(Tarifa.class));
		tarifas.add(any(Tarifa.class));
		tarifas.add(any(Tarifa.class));
		tarifas.add(any(Tarifa.class));
		
		AdministradorDTO dto=new AdministradorDTO();
		dto.setId(1);
		dto.setMonopatines(monopatines);
		dto.setParadas(paradas);
		dto.setTarifa(null);
		given(adminSer.getAdministrador())
			.willReturn(dto);
		
		ResultActions response=mockMvc.perform(get("/"))
				.andExpect(status().isOk());
		
		response.andDo(print()).andExpect(jsonPath("$.getId()",is(dto.getId())));
		
		
	}
	
	@DisplayName("test para añadir un monopatin")
	@Test
	public void testAddMonopatin() throws Exception {
		MonopatinRequestDTO dto=new MonopatinRequestDTO();
		dto.setEstado("200");
		dto.setLatitud(54.3);
		dto.setLongitud(76.3);
		
		given(adminSer.añadirMonopatin(any(MonopatinRequestDTO.class)))
			.willAnswer((invocation) -> invocation.getArgument(0));
		
		ResultActions response=mockMvc.perform(post("/monopatin/añadir")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)));
		
		response.andDo(print()).andExpect(status().isAccepted())
			.andExpect(jsonPath("$.latitud",is(dto.getLatitud())))
			.andExpect(jsonPath("$.longitud",is(dto.getLongitud())))
			.andExpect(jsonPath("$.estado",is(dto.getEstado())));
	}
	
	@DisplayName("test para borrar un monopatin")
	@Test
	public void testDeleteMonopatin() throws Exception{
		long id=3L;
		willDoNothing().given(adminSer).borrarMonopatin(id);
		
		ResultActions response=mockMvc.perform(delete("/monopatin/{id}"));
		
		response.andExpect(status().isNoContent())
			.andDo(print());
	}
	
}
