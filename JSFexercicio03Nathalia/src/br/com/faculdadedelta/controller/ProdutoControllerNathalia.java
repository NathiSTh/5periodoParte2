package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ProdutoDaoNathalia;
import br.com.faculdadedelta.modelo.ProdutoNathalia;
@ManagedBean
@SessionScoped
public class ProdutoControllerNathalia {

	
	private ProdutoNathalia produto = new ProdutoNathalia();
	private ProdutoDaoNathalia dao = new ProdutoDaoNathalia();
	
	public ProdutoNathalia getProduto() {
		return produto;
	}

	public void setProduto(ProdutoNathalia produto) {
		this.produto = produto;
	}

	public void limparCampos() {
		produto = new ProdutoNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
		
		if(produto.getId()==null) {
				dao.incluir(produto);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(produto);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
				
			}
		
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroProduto.xhtml";
		
	}
	
	public String editar() {
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
	
	public List<ProdutoNathalia> getLista(){
	List<ProdutoNathalia> listaRetorno= new ArrayList<>();
	try {
	
		listaRetorno= dao.listar();
	} catch (Exception e) {
		exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		e.printStackTrace();
	}
		
		return listaRetorno;
		
	}
	
	
}
