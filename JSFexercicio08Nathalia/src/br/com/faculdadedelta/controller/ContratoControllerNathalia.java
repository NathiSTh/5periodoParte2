package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ContratoDaoNathalia;
import br.com.faculdadedelta.modelo.ContratoNathalia;
import br.com.faculdadedelta.modelo.ProcessoNathalia;

@ManagedBean
@SessionScoped
public class ContratoControllerNathalia {
	
	private ContratoNathalia contrato = new ContratoNathalia();
	private ContratoDaoNathalia dao = new ContratoDaoNathalia();
	private ProcessoNathalia processoSelecionado = new ProcessoNathalia();

	public ContratoNathalia getContrato() {
		return contrato;
	}

	public void setContrato(ContratoNathalia contrato) {
		this.contrato = contrato;
	}

	public ProcessoNathalia getProcessoSelecionado() {
		return processoSelecionado;
	}

	public void setProcessoSelecionado(ProcessoNathalia processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public void limparCampos() {
		contrato = new ContratoNathalia();
		processoSelecionado= new  ProcessoNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			if(contrato.getId() == null) {
				contrato.setProcesso(processoSelecionado);
				dao.incluir(contrato);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				contrato.setProcesso(processoSelecionado);
				dao.alterar(contrato);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroContrato.xhtml";
		
	}
	
	public String editar() {
		contrato.setProcesso(processoSelecionado);
		return "CadastroContrato.xhtml";
	}
	
	public String excluir() {
		
		try {
			contrato.setProcesso(processoSelecionado);
			dao.excluir(contrato);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaContrato.xhtml";
	}
	
	public List<ContratoNathalia> getLista(){
		List<ContratoNathalia>listaRetorno = new ArrayList<ContratoNathalia>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
		
	}
	
	

}
