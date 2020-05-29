package br.edu.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.faculdadedelta.dao.GrauInstitucionalDaoNathalia;
import br.edu.faculdadedelta.modelo.GrauInstrucaoNathalia;

@FacesConverter (value="GrauConverter")
public class GrauInstrucaoConverter implements Converter{
	
	private GrauInstitucionalDaoNathalia dao = new GrauInstitucionalDaoNathalia();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if(valor != null) {
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((GrauInstrucaoNathalia) valor).getId());
		}
		return null;
	}

}
