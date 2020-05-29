package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.GrauInstitucionalDaoNathalia;
import br.edu.faculdadedelta.modelo.GrauInstrucaoNathalia;

@ManagedBean
@SessionScoped
public class GrauInstrucaoControllerNathalia {
	
	private GrauInstrucaoNathalia grau = new GrauInstrucaoNathalia();
	private GrauInstitucionalDaoNathalia dao = new GrauInstitucionalDaoNathalia();
	
	public GrauInstrucaoNathalia getGrau() {
		return grau;
	}
	public void setGrau(GrauInstrucaoNathalia grau) {
		this.grau = grau;
	}
	
	public void limparCampos() {
		grau = new GrauInstrucaoNathalia();
	}

	private void exibirMensagem (String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if(grau.getId() == null) {
				dao.incluir(grau);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(grau);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroGrauInstrucao.xhtml";
	}
	
	public String editar() {
		return "CadastroGrauInstrucao.xhtml";
	}
	
	public String excluir() {
		try {
				dao.excluir(grau);
				limparCampos();
				exibirMensagem("Exclusao realizada com sucesso.");		
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaGrauInstrucao.xhtml";
	}

	public List<GrauInstrucaoNathalia> getLista(){
		List<GrauInstrucaoNathalia> listaRetorno = new ArrayList<>();
		try {
		listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde!" + e.getMessage());
		}
		
		return listaRetorno;
	}

}
