package br.edu.faculdadedelta.modelo;

public class CarroNathalia {
	
	private Long id;
	private String desc;
	private MarcaNathalia marca;
	private Double valor;
	public CarroNathalia() {
		super();
	}
	public CarroNathalia(Long id, String desc, MarcaNathalia marca, Double valor) {
		super();
		this.id = id;
		this.desc = desc;
		this.marca = marca;
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
	public MarcaNathalia getMarca() {
		return marca;
	}
	public void setMarca(MarcaNathalia marca) {
		this.marca = marca;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
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
		CarroNathalia other = (CarroNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
