package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.ClienteDaoNathalia;
import br.edu.faculdadedelta.modelo.ClienteNathalia;

@ManagedBean
@SessionScoped
public class ClienteContollerNathalia {

	private ClienteNathalia cliente = new ClienteNathalia();
	private ClienteDaoNathalia dao = new ClienteDaoNathalia();

	
	
	public ClienteNathalia getCliente() {
		return cliente;
	}

	public void setCliente(ClienteNathalia cliente) {
		this.cliente = cliente;
	}

	public void limparCampos() {
		cliente = new ClienteNathalia();
		
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
	
		if(cliente.getId()==null) {
				dao.incluir(cliente);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			}else {
				dao.alterar(cliente);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroCliente.xhtml";
	}
	
	public String editar() {
		return "CadastroCliente.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(cliente);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "ListaCliente.xhtml";
		
	}
	
	public List<ClienteNathalia> getLista(){
		List<ClienteNathalia>listaRetorno = new ArrayList<>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		
		return listaRetorno;
		
	}
	
	

}
