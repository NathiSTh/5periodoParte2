package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class AlunoNathalia {
	
	private Long id;
	private String nome;
	private int idade;
	private Date nascimento;
	private GrauInstrucaoNathalia grauInstitucional;
	public AlunoNathalia() {
		super();
	}
	public AlunoNathalia(Long id, String nome, int idade, Date nascimento, GrauInstrucaoNathalia grauInstitucional) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.nascimento = nascimento;
		this.grauInstitucional = grauInstitucional;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public GrauInstrucaoNathalia getGrauInstitucional() {
		return grauInstitucional;
	}
	public void setGrauInstitucional(GrauInstrucaoNathalia grauInstitucional) {
		this.grauInstitucional = grauInstitucional;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlunoNathalia other = (AlunoNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
