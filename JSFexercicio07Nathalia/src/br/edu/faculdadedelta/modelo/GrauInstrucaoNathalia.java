package br.edu.faculdadedelta.modelo;

public class GrauInstrucaoNathalia {
	
	private Long id;
	private String Grau;
	
	public GrauInstrucaoNathalia() {
		super();
	}
	
	public GrauInstrucaoNathalia(Long id, String grau) {
		super();
		this.id = id;
		Grau = grau;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGrau() {
		return Grau;
	}
	public void setGrau(String grau) {
		Grau = grau;
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
		GrauInstrucaoNathalia other = (GrauInstrucaoNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
