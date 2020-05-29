package br.edu.faculdadedelta.modelo;

public class ProdutosNathalia {
	
	private Long idProd;
	private String descProd;
	private Double valorProd;
	private FornecedorNathalia fornecedor;
	
	public ProdutosNathalia() {
		super();
	}
	public ProdutosNathalia(Long idProd, String descProd, Double valorProd, FornecedorNathalia fornecedor) {
		super();
		this.idProd = idProd;
		this.descProd = descProd;
		this.valorProd = valorProd;
		this.fornecedor = fornecedor;
	}
	public Long getIdProd() {
		return idProd;
	}
	public void setIdProd(Long idProd) {
		this.idProd = idProd;
	}
	public String getDescProd() {
		return descProd;
	}
	public void setDescProd(String descProd) {
		this.descProd = descProd;
	}
	public Double getValorProd() {
		return valorProd;
	}
	public void setValorProd(Double valorProd) {
		this.valorProd = valorProd;
	}
	public FornecedorNathalia getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(FornecedorNathalia fornecedor) {
		this.fornecedor = fornecedor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProd == null) ? 0 : idProd.hashCode());
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
		ProdutosNathalia other = (ProdutosNathalia) obj;
		if (idProd == null) {
			if (other.idProd != null)
				return false;
		} else if (!idProd.equals(other.idProd))
			return false;
		return true;
	}
	
	
	

}
