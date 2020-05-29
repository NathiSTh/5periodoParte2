package br.com.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.BensDaoNathalia;
import br.com.faculdadedelta.modelo.BensNathalia;
import br.com.faculdadedelta.modelo.DepartamentoNathalia;

@ManagedBean
@SessionScoped
public class BensControllerNathalia {
	
	private BensNathalia bens = new BensNathalia();
	private BensDaoNathalia dao = new BensDaoNathalia();
	private DepartamentoNathalia departamentoselecionado = new DepartamentoNathalia();
	
	public BensNathalia getBens() {
		return bens;
	}
	public void setBens(BensNathalia bens) {
		this.bens = bens;
	}

	public DepartamentoNathalia getDepartamentoselecionado() {
		return departamentoselecionado;
	}
	public void setDepartamentoselecionado(DepartamentoNathalia departamentoselecionado) {
		this.departamentoselecionado = departamentoselecionado;
	}
	public void limparCampos() {
		bens = new BensNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);	
	}
	
	public String salvar() {
		
		try {
				if(bens.getId() == null){
					
					bens.setDepartamento(departamentoselecionado);
					dao.incluir(bens);
					limparCampos();
					exibirMensagem("Inclusão realizado com sucesso.");
					} else {
						bens.setDepartamento(departamentoselecionado);
						dao.alterar(bens);
						limparCampos();
						exibirMensagem("Alteração realizada com sucesso.");
					}
			
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro!! Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		
		return "CadastroBens.xhtml";
	}
	
	public String excluir() {
		try {
			bens.setDepartamento(departamentoselecionado);
			dao.exluir(bens);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro. Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		
		return "ListaBens.xhtml" ;
	}
	
	public String editar() {
		return "CadastroBens.xhtml";
	}
	
	public List<BensNathalia> getListar(){
		
		List<BensNathalia> listaRetorno = new ArrayList<>();
		
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operacao. Tente novamente mais tarde!" + e.getMessage());
			e.printStackTrace();
		}
		
		return listaRetorno;
	}

}
