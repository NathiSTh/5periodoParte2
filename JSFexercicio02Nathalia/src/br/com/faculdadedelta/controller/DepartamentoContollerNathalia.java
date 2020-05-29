package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.DepartamentoDaoNathalia;
import br.com.faculdadedelta.modelo.DepartamentoNathalia;

@ManagedBean
@SessionScoped
public class DepartamentoContollerNathalia {
	
	private DepartamentoNathalia departamento = new DepartamentoNathalia();
	private DepartamentoDaoNathalia dao = new DepartamentoDaoNathalia();

	public DepartamentoNathalia getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoNathalia departamento) {
		this.departamento = departamento;
	}

	public void limparCampos() {
		departamento = new DepartamentoNathalia();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {

			if (departamento.getId() == null) {
				dao.incluir(departamento);
				exibirMensagem("Inclusao realizada com sucesso.");
				limparCampos();

			} else {
				dao.alterar(departamento);
				exibirMensagem("Alteracao realizada com sucesso.");
				limparCampos();
			}

		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());

		}

		return "CadastroDepartamento.xhtml";

	}
	
	public String excluir() {
		
		try {
			dao.excluir(departamento);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListarDepartamento.xhtml";
	}
	
	public String editar() {
	  return "CadastroDepartamento.xhtml";
		
	}
	
	public List<DepartamentoNathalia> getLista(){
		List<DepartamentoNathalia> listaRetorno = new ArrayList<DepartamentoNathalia>();
		
		try {
			listaRetorno=dao.listar();
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		
		return listaRetorno;
		
	}

}
