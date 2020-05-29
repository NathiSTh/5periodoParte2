package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.MarcaDaoNathalia;
import br.edu.faculdadedelta.modelo.MarcaNathalia;

@ManagedBean
@SessionScoped
public class MarcaControllerNathalia {
	
	private MarcaNathalia marca = new MarcaNathalia();
	private MarcaDaoNathalia dao = new MarcaDaoNathalia();
	
	
	public MarcaNathalia getMarca() {
		return marca;
	}
	public void setMarca(MarcaNathalia marca) {
		this.marca = marca;
	}
	
	public void limparCampos() {
		marca = new MarcaNathalia();
	}

	private void exibirMensagem (String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			if(marca.getId() == null) {
				dao.incluir(marca);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(marca);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroMarca.xhtml";
	}
	
	public String editar() {
		return "CadastroMarca.xhtml";
	}
	
	public String excluir() {
		try {
				dao.excluir(marca);
				limparCampos();
				exibirMensagem("Exclusao realizada com sucesso.");		
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaMarca.xhtml";
	}

	public List<MarcaNathalia> getLista(){
		List<MarcaNathalia> listaRetorno = new ArrayList<>();
		try {
		listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
	}

}
