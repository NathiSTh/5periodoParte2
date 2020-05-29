package br.com.faculdadedelta.modelo;

public class MunicipioNathalia {

	private Long id;
	private String nome;
	private String cnpj;
	private String codigo;
	private UfNathalia uf;

	public MunicipioNathalia() {
		super();
	}

	public MunicipioNathalia(Long id, String nome, String cnpj, String codigo, UfNathalia uf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.codigo = codigo;
		this.uf = uf;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public UfNathalia getUf() {
		return uf;
	}

	public void setUf(UfNathalia uf) {
		this.uf = uf;
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
		MunicipioNathalia other = (MunicipioNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
