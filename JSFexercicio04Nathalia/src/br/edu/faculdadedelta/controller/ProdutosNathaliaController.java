package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.ProdutosDAONathalia;
import br.edu.faculdadedelta.modelo.FornecedorNathalia;
import br.edu.faculdadedelta.modelo.ProdutosNathalia;

@ManagedBean
@SessionScoped
public class ProdutosNathaliaController {
	
	private ProdutosNathalia produto = new ProdutosNathalia();
	private ProdutosDAONathalia dao = new ProdutosDAONathalia();
	private FornecedorNathalia fornecedorSelecionado = new FornecedorNathalia();
	
	public ProdutosNathalia getProduto() {
		return produto;
	}
	
	public void setProduto(ProdutosNathalia produto) {
		this.produto = produto;
	}
	
	public FornecedorNathalia getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(FornecedorNathalia fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

	public void limparCampos() {
		produto = new ProdutosNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			if(produto.getIdProd() == null) {
				produto.setFornecedor(fornecedorSelecionado);
				dao.incluir(produto);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				produto.setFornecedor(fornecedorSelecionado);
				dao.alterar(produto);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroProduto.xhtml";
	}
	
	public String editar() {
		fornecedorSelecionado = produto.getFornecedor();
		return "CadastroProduto.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(produto);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaProduto.xhtml";
	}
	
	public List<ProdutosNathalia> getListar(){
		List<ProdutosNathalia> listaRetorno = new ArrayList<>();
		
		try {
		listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
	}
	

}
