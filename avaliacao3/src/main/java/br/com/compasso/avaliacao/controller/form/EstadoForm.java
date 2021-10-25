package br.com.compasso.avaliacao.controller.form;

import javax.validation.constraints.Pattern;

import br.com.compasso.avaliacao.modelo.Estado;
import br.com.compasso.avaliacao.repository.EstadoRepository;

public class EstadoForm {
	
	private String nome;
	@Pattern(regexp = "Norte|Nordeste|Sul|Sudeste|Centro-Oeste" )
	private String regiao;
	private int populacao;
	private String capital;
	private double area;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public int getPopulacao() {
		return populacao;
	}
	public void setPopulacao(int populacao) {
		this.populacao = populacao;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public Estado converter() {
		return new Estado(nome, regiao, populacao, capital, area);
	}
	public Estado atualizar(Long id, EstadoRepository estadoRepository) {
        Estado estado = estadoRepository.getOne(id);
        estado.setArea(this.area);
        estado.setNome(this.nome);
        estado.setRegiao(this.regiao);
        estado.setCapital(this.capital);
        estado.setPopulacao(this.populacao);
        return estado;
	}
}
