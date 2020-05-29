package br.com.faculdadedelta.modelo;

public class DepartamentoNathalia {
	
	private Long id;
	private String departemanto;
	public DepartamentoNathalia() {
		super();
	}
	public DepartamentoNathalia(Long id, String departemanto) {
		super();
		this.id = id;
		this.departemanto = departemanto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepartemanto() {
		return departemanto;
	}
	public void setDepartemanto(String departemanto) {
		this.departemanto = departemanto;
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
		DepartamentoNathalia other = (DepartamentoNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}