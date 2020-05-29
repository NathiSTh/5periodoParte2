package br.com.faculdadedelta.modelo;

public class VendaNathalia {
	private Long id;
	private String desc;
	private ProdutoNathalia produto;
	private double valor;

	public VendaNathalia() {
		super();
	}

	public VendaNathalia(Long id, String desc, ProdutoNathalia produto, double valor) {
		super();
		this.id = id;
		this.desc = desc;
		this.produto = produto;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ProdutoNathalia getProduto() {
		return produto;
	}

	public void setProduto(ProdutoNathalia produto) {
		this.produto = produto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
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
		VendaNathalia other = (VendaNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
