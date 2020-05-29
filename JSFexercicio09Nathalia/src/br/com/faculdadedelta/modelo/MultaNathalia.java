package br.com.faculdadedelta.modelo;

import java.util.Date;

public class MultaNathalia {

	private Long id;
	private InfracaoNathalia infracao;
	private VeiculoNathalia veiculo;
	private MotoristaNathalia motorista;
	private Date data;
	
	public MultaNathalia() {
		super();
	}

	public MultaNathalia(Long id, InfracaoNathalia infracao, VeiculoNathalia veiculo, MotoristaNathalia motorista,
			Date data) {
		super();
		this.id = id;
		this.infracao = infracao;
		this.veiculo = veiculo;
		this.motorista = motorista;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InfracaoNathalia getInfracao() {
		return infracao;
	}

	public void setInfracao(InfracaoNathalia infracao) {
		this.infracao = infracao;
	}

	public VeiculoNathalia getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(VeiculoNathalia veiculo) {
		this.veiculo = veiculo;
	}

	public MotoristaNathalia getMotorista() {
		return motorista;
	}

	public void setMotorista(MotoristaNathalia motorista) {
		this.motorista = motorista;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		MultaNathalia other = (MultaNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	


}