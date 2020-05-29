package br.com.faculdadedelta.modelo;

import java.util.Date;

public class ContratoNathalia {

	private Long id;
	private String numContrato;
	private String Contrato;
	private double valor;
	private Date dataInicio;
	private Date dataFim;
	private ProcessoNathalia processo;

	public ContratoNathalia() {
		super();
	}

	public ContratoNathalia(Long id, String numContrato, String contrato, double valor, Date dataInicio, Date dataFim,
			ProcessoNathalia processo) {
		super();
		this.id = id;
		this.numContrato = numContrato;
		Contrato = contrato;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.processo = processo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumContrato() {
		return numContrato;
	}

	public void setNumContrato(String numContrato) {
		this.numContrato = numContrato;
	}

	public String getContrato() {
		return Contrato;
	}

	public void setContrato(String contrato) {
		Contrato = contrato;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public ProcessoNathalia getProcesso() {
		return processo;
	}

	public void setProcesso(ProcessoNathalia processo) {
		this.processo = processo;
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
		ContratoNathalia other = (ContratoNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
