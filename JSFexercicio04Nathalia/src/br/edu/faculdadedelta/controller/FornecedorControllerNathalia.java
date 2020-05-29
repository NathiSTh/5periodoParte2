package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.FornecedorDAONathalia;
import br.edu.faculdadedelta.modelo.FornecedorNathalia;

@ManagedBean
@SessionScoped
public class FornecedorControllerNathalia {

	private FornecedorNathalia fornecedor = new FornecedorNathalia();
	private FornecedorDAONathalia dao = new FornecedorDAONathalia();
	
	public FornecedorNathalia getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(FornecedorNathalia fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void limparCampos() {
		fornecedor = new FornecedorNathalia();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			if(fornecedor.getIdForn() == null) {
				dao.incluir(fornecedor);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(fornecedor);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroFornecedor.xhtml";
	}
	
	public String editar() {
		return "CadastroFornecedor.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(fornecedor);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaFornecedor.xhtml";
	}
	
	public List<FornecedorNathalia> getLista(){
		List<FornecedorNathalia> listaRetorno = new ArrayList<>();
		try {
		listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
	}
	
}
