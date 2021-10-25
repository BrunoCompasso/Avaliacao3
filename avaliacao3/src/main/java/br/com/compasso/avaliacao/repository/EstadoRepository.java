package br.com.compasso.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.compasso.avaliacao.modelo.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

	@Query("SELECT e FROM Estado e WHERE e.regiao = :regiao")
	List<Estado> carregarPorRegiao(@Param ("regiao") String regiao);
	
	@Query("SELECT e FROM Estado e ORDER BY populacao DESC")
	List<Estado> carregarPorPopulacao();

	@Query("SELECT e FROM Estado e ORDER BY area DESC")
	List<Estado> carregarPorArea();
}
