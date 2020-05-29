package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.PaisDaoNathalia;
import br.com.faculdadedelta.modelo.PaisNathalia;

@ManagedBean
@SessionScoped
public class PaisControllerNathalia {
	
	private PaisNathalia pais = new PaisNathalia();
	private PaisDaoNathalia dao = new PaisDaoNathalia();

	public PaisNathalia getPais() {
		return pais;
	}

	public void setPais(PaisNathalia pais) {
		this.pais = pais;
	}
	
	public void limparCampo() {
		pais = new PaisNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		if(pais.getId()==null) {
				dao.incluir(pais);
				limparCampo();
				exibirMensagem("Inclusao realizada com sucesso.");
			}else {
				dao.alterar(pais);
				limparCampo();
				exibirMensagem("Alteracao realizada com sucesso.");
				
			}
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroPais.xhtml";
		
	}
	
	public String editar() {
		return "CadastroPais.xhtml";
		
	}
	
	public String excluir() {
		try {
			dao.excluir(pais);
			limparCampo();
			exibirMensagem("Exclusao realizada com sucesso.");		
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "ListaPais.xhtml";
		
	}
	
	public List<PaisNathalia> getLista(){
		List<PaisNathalia>listaRetorno = new ArrayList<PaisNathalia>();
		
		try {
			listaRetorno= dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
		
	}
	
	

}
