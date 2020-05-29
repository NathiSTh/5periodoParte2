package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.BairroDaoNathalia;
import br.com.faculdadedelta.modelo.BairroNathalia;
import br.com.faculdadedelta.modelo.MunicipioNathalia;

@ManagedBean
@SessionScoped
public class BairroControllerNathalia {
	
	private BairroNathalia bairro = new BairroNathalia();
	private BairroDaoNathalia dao = new BairroDaoNathalia();
	private MunicipioNathalia municipioSelecionado = new MunicipioNathalia();

	public BairroNathalia getBairro() {
		return bairro;
	}

	public void setBairro(BairroNathalia bairro) {
		this.bairro = bairro;
	}

	public MunicipioNathalia getMunicipioSelecionado() {
		return municipioSelecionado;
	}

	public void setMunicipioSelecionado(MunicipioNathalia municipioSelecionado) {
		this.municipioSelecionado = municipioSelecionado;
	}
	
	public void limparCampo() {
		bairro = new BairroNathalia();
		municipioSelecionado = new MunicipioNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		
		try {
		if(bairro.getId()==null) {
			bairro.setMunicipio(municipioSelecionado);
				dao.incluir(bairro);
				limparCampo();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				bairro.setMunicipio(municipioSelecionado);
				dao.alterar(bairro);
				limparCampo();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroBairro.xhtml";
		
	}
	public String editar() {
		bairro.setMunicipio(municipioSelecionado);
		return "CadastroBairro.xhtml";
	}
	public String excluir() {
		try {
			bairro.setMunicipio(municipioSelecionado);
			dao.excluir(bairro);
			limparCampo();
			exibirMensagem("Exclusao realizada com sucesso.");		
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "ListaBairro.xhtml";
	}
	
	public List<BairroNathalia> getLista(){
		List<BairroNathalia> listaRetorno = new ArrayList<BairroNathalia>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
		
	}
	
	
}
