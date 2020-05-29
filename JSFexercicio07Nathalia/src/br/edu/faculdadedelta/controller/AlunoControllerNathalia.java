package br.edu.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.AlunoDaoNathalia;
import br.edu.faculdadedelta.modelo.AlunoNathalia;
import br.edu.faculdadedelta.modelo.GrauInstrucaoNathalia;

@ManagedBean
@SessionScoped
public class AlunoControllerNathalia {

	private AlunoNathalia aluno = new AlunoNathalia();
	private AlunoDaoNathalia dao = new AlunoDaoNathalia();
	private GrauInstrucaoNathalia grauSelecionado = new GrauInstrucaoNathalia();
	
	public AlunoNathalia getAluno() {
		return aluno;
	}
	public void setAluno(AlunoNathalia aluno) {
		this.aluno = aluno;
	}
	public GrauInstrucaoNathalia getGrauSelecionado() {
		return grauSelecionado;
	}
	public void setGrauSelecionado(GrauInstrucaoNathalia grauSelecionado) {
		this.grauSelecionado = grauSelecionado;
	}
	
	public void limparCampos() {
		aluno = new AlunoNathalia();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if(aluno.getId() == null) {
				aluno.setGrauInstitucional(grauSelecionado);
				dao.incluir(aluno);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				aluno.setGrauInstitucional(grauSelecionado);
				dao.alterar(aluno);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "CadastroAluno.xhtml";
	}

	public String editar() {
		aluno.setGrauInstitucional(grauSelecionado);
		return "CadastroAluno.xhtml";
	}
	
	public String excluir() {
		try {
			aluno.setGrauInstitucional(grauSelecionado);
				dao.excluir(aluno);
				limparCampos();
				exibirMensagem("Exclusao realizada com sucesso.");
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
			}
			return "ListaAluno.xhtml";
	}
	
	public List<AlunoNathalia> getListar(){
		List<AlunoNathalia> listaRetorno = new ArrayList<>();
		
		try {
		listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return listaRetorno;
	}
}
