package br.edu.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.faculdadedelta.dao.EditoraDaoNathalia;
import br.edu.faculdadedelta.modelo.EditoraNathalia;

@FacesConverter (value="EditoraConverter")
public class EditoraConverterNathalia implements Converter {
	
	private EditoraDaoNathalia dao = new EditoraDaoNathalia();

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
			return String.valueOf(((EditoraNathalia) valor).getId());
		}
		return null;
	}
	
	

}
