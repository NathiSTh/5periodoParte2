package br.com.faculdadedelta.modelo;

public class ProcessoNathalia {

	private Long id;
	private String numero;
	private String processo;
	private double valorProc;

	public ProcessoNathalia() {
		super();
	}

	public ProcessoNathalia(Long id, String numero, String processo, double valorProc) {
		super();
		this.id = id;
		this.numero = numero;
		this.processo = processo;
		this.valorProc = valorProc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public double getValorProc() {
		return valorProc;
	}

	public void setValorProc(double valorProc) {
		this.valorProc = valorProc;
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
		ProcessoNathalia other = (ProcessoNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
