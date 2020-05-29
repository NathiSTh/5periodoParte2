package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.MunicipioDaoNathalia;
import br.com.faculdadedelta.modelo.MunicipioNathalia;
import br.com.faculdadedelta.modelo.UfNathalia;

@ManagedBean
@SessionScoped
public class MunicipioControllerNathalia {

	private MunicipioNathalia municipio = new MunicipioNathalia();
	private MunicipioDaoNathalia dao = new MunicipioDaoNathalia();
	private UfNathalia ufSelecionado = new UfNathalia();

	public MunicipioNathalia getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipioNathalia municipio) {
		this.municipio = municipio;
	}

	public UfNathalia getUfSelecionado() {
		return ufSelecionado;
	}

	public void setUfSelecionado(UfNathalia ufSelecionado) {
		this.ufSelecionado = ufSelecionado;
	}

	public void limparCampo() {
		municipio = new MunicipioNathalia();
		ufSelecionado = new UfNathalia();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {

			if (municipio.getId() == null) {
				municipio.setUf(ufSelecionado);
				dao.incluir(municipio);
				limparCampo();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				municipio.setUf(ufSelecionado);
				dao.alterar(municipio);
				limparCampo();
				exibirMensagem("Alteracao realizada com sucesso.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}

		return "CadastroMunicipio.xhtml";

	}

	public String editar() {
		ufSelecionado = municipio.getUf();
		return "CadastroMunicipio.xhtml";
	}

	public String excluir() {

		try {
			municipio.setUf(ufSelecionado);
			dao.excluir(municipio);
			limparCampo();
			exibirMensagem("Exclusao realizada com sucesso.");		
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaMunicipio.xhtml";

	}

	public List<MunicipioNathalia> getLista() {
		List<MunicipioNathalia> listaRetorno = new ArrayList<MunicipioNathalia>();

		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}

		return listaRetorno;

	}

}
