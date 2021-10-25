package br.com.compasso.avaliacao.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.avaliacao.controller.dto.EstadoDto;
import br.com.compasso.avaliacao.controller.form.EstadoForm;
import br.com.compasso.avaliacao.modelo.Estado;
import br.com.compasso.avaliacao.repository.EstadoRepository;

@RestController
@RequestMapping("/api/states")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping
	public List<EstadoDto> lista(String regiao,int populacao, int area) {
		if (regiao != null) {
			List<Estado> estados = estadoRepository.carregarPorRegiao(regiao);
			return EstadoDto.converter(estados);
		}
		if (populacao != 0){
			List<Estado> estados = estadoRepository.carregarPorPopulacao();
			return EstadoDto.converter(estados);
		}
		if (area != 0){
			List<Estado> estados = estadoRepository.carregarPorArea();
			return EstadoDto.converter(estados);
		}
		else{
			List<Estado> estados = estadoRepository.findAll();
			return EstadoDto.converter(estados);
		}
	}

	@PostMapping
	public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm form, UriComponentsBuilder uriBuilder) {
		Estado estado = form.converter();
		estadoRepository.save(estado);
		
		URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri(); 
		return ResponseEntity.created(uri).body(new EstadoDto(estado));		
	}
	
	@GetMapping("/{id}")
	public EstadoDto detalhar(@PathVariable Long id){
		Estado estados = estadoRepository.getOne(id);
		return new EstadoDto(estados);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id,@RequestBody EstadoForm form){
		Estado estados = form.atualizar(id, estadoRepository);
        return ResponseEntity.ok(new EstadoDto(estados));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		estadoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
