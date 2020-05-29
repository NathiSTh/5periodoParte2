package br.edu.faculdadedelta.modelo;

public class FornecedorNathalia {

	private Long idForn;
	private String descForn;

	public FornecedorNathalia() {
	}

	public FornecedorNathalia(Long idForn, String descForn) {
		super();
		this.idForn = idForn;
		this.descForn = descForn;
	}

	public Long getIdForn() {
		return idForn;
	}

	public void setIdForn(Long idForn) {
		this.idForn = idForn;
	}

	public String getDescForn() {
		return descForn;
	}

	public void setDescForn(String descForn) {
		this.descForn = descForn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idForn == null) ? 0 : idForn.hashCode());
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
		FornecedorNathalia other = (FornecedorNathalia) obj;
		if (idForn == null) {
			if (other.idForn != null)
				return false;
		} else if (!idForn.equals(other.idForn))
			return false;
		return true;
	}

}
