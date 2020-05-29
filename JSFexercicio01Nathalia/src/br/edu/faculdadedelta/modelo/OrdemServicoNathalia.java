package br.edu.faculdadedelta.modelo;

public class OrdemServicoNathalia {
	
	private Long id;
	private ClienteNathalia cliente;
	private ServicoNathalia servico;
	private double valorUnitario;
	private  int quant;
	private  double valorDesconto;
	private double valorTotal;
	
	
	public OrdemServicoNathalia() {
		super();
	}
	
	
	public OrdemServicoNathalia(Long id, ClienteNathalia cliente, ServicoNathalia servico, double valorUnitario,
			int quant, double valorDesconto, double valorTotal) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.servico = servico;
		this.valorUnitario = valorUnitario;
		this.quant = quant;
		this.valorDesconto = valorDesconto;
		this.valorTotal = valorTotal;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteNathalia getCliente() {
		return cliente;
	}

	public void setCliente(ClienteNathalia cliente) {
		this.cliente = cliente;
	}

	public ServicoNathalia getServico() {
		return servico;
	}

	public void setServico(ServicoNathalia servico) {
		this.servico = servico;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}
	
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}
	
	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(double valorTotal) {
		this.valorTotal = ((quant * valorUnitario) - valorDesconto);
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
		OrdemServicoNathalia other = (OrdemServicoNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
