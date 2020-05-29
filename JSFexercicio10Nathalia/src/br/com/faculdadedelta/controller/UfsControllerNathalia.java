package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.UfDaoNathalia;
import br.com.faculdadedelta.modelo.PaisNathalia;
import br.com.faculdadedelta.modelo.UfNathalia;

@ManagedBean
@SessionScoped
public class UfsControllerNathalia {

	private UfNathalia uf = new UfNathalia();
	private UfDaoNathalia dao= new UfDaoNathalia();
	private PaisNathalia paisSelecionado = new PaisNathalia();

	public UfNathalia getUf() {
		return uf;
	}

	public void setUf(UfNathalia uf) {
		this.uf = uf;
	}

	public PaisNathalia getPaisSelecionado() {
		return paisSelecionado;
	}

	public void setPaisSelecionado(PaisNathalia paisSelecionado) {
		this.paisSelecionado = paisSelecionado;
	}
	
	public void limparCampo() {
		uf = new UfNathalia();
		paisSelecionado = new PaisNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		
		if(uf.getId()==null) {
			uf.setPais(paisSelecionado);
				dao.incluir(uf);
				limparCampo();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				uf.setPais(paisSelecionado);
				dao.alterar(uf);
				limparCampo();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroUf.xhtml";
		
	}
	
	public String editar() {
		paisSelecionado = uf.getPais();
		return "CadastroUf.xhtml";	
	}
	public String excluir() {
		try {
			uf.setPais(paisSelecionado);
			dao.excluir(uf);
			limparCampo();
			exibirMensagem("Exclusao realizada com sucesso.");		
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaUf.xhtml";
	}
	
	public List<UfNathalia> getLista(){
		List<UfNathalia> listaRetorno = new ArrayList<UfNathalia>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
		
	}
	
	
}
