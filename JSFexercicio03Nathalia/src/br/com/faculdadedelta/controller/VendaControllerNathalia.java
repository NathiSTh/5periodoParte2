package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.VendaDaoNathalia;
import br.com.faculdadedelta.modelo.ProdutoNathalia;
import br.com.faculdadedelta.modelo.VendaNathalia;

@ManagedBean
@SessionScoped
public class VendaControllerNathalia {

	private VendaNathalia venda  = new VendaNathalia();
	private VendaDaoNathalia dao = new VendaDaoNathalia();
	private ProdutoNathalia produtoSelecionado = new ProdutoNathalia();
	

	public VendaNathalia getVenda() {
		return venda;
	}
	public void setVenda(VendaNathalia venda) {
		this.venda = venda;
	}
	public ProdutoNathalia getProdutoSelecionado() {
		return produtoSelecionado;
	}
	public void setProdutoSelecionado(ProdutoNathalia produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	public void limparCampos() {
		venda = new VendaNathalia();
		produtoSelecionado = new ProdutoNathalia();
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		
		try {
		if(venda.getId()==null) {
			venda.setProduto(produtoSelecionado);
				dao.incluir(venda);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(venda);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
				
			}
		
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroVenda.xhtml";
		
	}
	
	public String editar() {
		produtoSelecionado = venda.getProduto();
		return "CadastroVenda.xhtml";
		
	}
	
	public String excluir() {
		
		try {
			dao.excluir(venda);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
	
		return "ListaVenda.xhtml";
		
	}
	
	public List<VendaNathalia> getLista(){
	List<VendaNathalia> listaRetorno = new ArrayList<>();
	
	try {
		listaRetorno = dao.listar();
		
	} catch (Exception e) {
		e.printStackTrace();
		exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
	}
		
		return listaRetorno;
		
	}
	
	
}
