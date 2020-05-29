package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.CarroDaoNathalia;
import br.edu.faculdadedelta.modelo.CarroNathalia;
import br.edu.faculdadedelta.modelo.MarcaNathalia;

@ManagedBean
@SessionScoped
public class CarroControllerNathalia {
	
	private CarroNathalia carro = new CarroNathalia();
	private CarroDaoNathalia dao = new CarroDaoNathalia();
	private MarcaNathalia marcaSelecionada = new MarcaNathalia();
	
	public CarroNathalia getCarro() {
		return carro;
	}
	public void setCarro(CarroNathalia carro) {
		this.carro = carro;
	}
	public MarcaNathalia getMarcaSelecionada() {
		return marcaSelecionada;
	}
	public void setMarcaSelecionada(MarcaNathalia marcaSelecionada) {
		this.marcaSelecionada = marcaSelecionada;
	}
	
	public void limparCampos() {
		carro = new CarroNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if(carro.getId() == null) {
				carro.setMarca(marcaSelecionada);
				dao.incluir(carro);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				carro.setMarca(marcaSelecionada);
				dao.alterar(carro);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroCarro.xhtml";
	}

	public String editar() {
		carro.setMarca(marcaSelecionada);
		return "CadastroCarro.xhtml";
	}
	
	public String excluir() {
		try {
				carro.setMarca(marcaSelecionada);
				dao.excluir(carro);
				limparCampos();
				exibirMensagem("Exclusao realizada com sucesso.");
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
			}
			return "ListaCarro.xhtml";
	}
	
	public List<CarroNathalia> getListar(){
		List<CarroNathalia> listaRetorno = new ArrayList<>();
		
		try {
		listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
	}
}
