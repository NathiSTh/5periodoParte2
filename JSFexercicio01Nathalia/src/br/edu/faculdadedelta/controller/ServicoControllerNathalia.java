package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.ServicoDaoNathalia;
import br.edu.faculdadedelta.modelo.ServicoNathalia;

@ManagedBean
@SessionScoped
public class ServicoControllerNathalia {
	
	private ServicoNathalia servico= new ServicoNathalia();
	private ServicoDaoNathalia dao = new ServicoDaoNathalia();
	
	public ServicoNathalia getServico() {
		return servico;
	}
	public void setServico(ServicoNathalia servico) {
		this.servico = servico;
	}
	
	public void limparCampos() {
		servico = new ServicoNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
	
		if(servico.getId()==null) {
			dao.incluir(servico);
			limparCampos();
			exibirMensagem("Inclusao realizada com sucesso.");
		} else {
			dao.alterar(servico);
			limparCampos();
			exibirMensagem("Alteracao realizada com sucesso.");
		}
		}catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
	}
		return "CadastroServico.xhtml";
		
	}
	
	public String editar() {
		return "CadastroServico.xhtml";
		
	}
	
	public String excluir() {
		try {
			dao.excluir(servico);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "listaServico.xhtml";
		
	}
	
	public List<ServicoNathalia> getLista(){
		List<ServicoNathalia> listaRetorno = new ArrayList<ServicoNathalia>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
		
	}
	
	
	

}
