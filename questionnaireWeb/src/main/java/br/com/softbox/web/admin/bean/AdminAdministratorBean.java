package br.com.softbox.web.admin.bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.softbox.ejb.entity.Administrator;
import br.com.softbox.ejb.local.AdministratorLocal;

@ViewScoped
@ManagedBean
public class AdminAdministratorBean extends AdminBaseBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2970955691699200720L;

	@EJB
	private AdministratorLocal	administratorLocal;
	

	private List<Administrator> administrators;

	
	private Administrator beanAdministrator;
	
	
	
	public List<Administrator> getAdministrators(){
		if(administrators==null){
			administrators = administratorLocal.findAll();
		}
		return administrators;
	}
	public String updateAdministrator() {
		try {
			
			
			if(beanAdministrator.getEmail()==null || beanAdministrator.getEmail().trim().equals("")){
				displayErrorMessage("Email é obrigatório");
				return null;
			}else if(beanAdministrator.getPassword()==null){
				displayErrorMessage("Senha é obrigatório");
				return null;
			}
			beanAdministrator.setPassword(encryptSHA256(beanAdministrator.getPassword()));
			beanAdministrator = administratorLocal.merge(beanAdministrator);
			
			
			resetAdministrator();
			administrators=null;
			RequestContext.getCurrentInstance().execute("PF('dlgAdministrator').hide();");
			displayInfoMessage("Atualizado com sucesso");
			return "/pages/restrict/administratorList.jsf?faces-redirect=true";

		} catch (Exception e) {
			 RequestContext.getCurrentInstance().execute("PF('dlgAdministrator').hide();");
			displayErrorMessage("Erro ao atualizar questão.");
			return null;

		}finally {
			beanAdministrator = null;
		}
	}
	
	public String createAdministrator() {
		try {
			
			
			if(beanAdministrator.getEmail()==null || beanAdministrator.getEmail().trim().equals("")){
				displayErrorMessage("Email é obrigatório");
				return null;
			}else if(beanAdministrator.getPassword()==null){
				displayErrorMessage("Senha é obrigatório");
				return null;
			}
			beanAdministrator.setPassword(encryptSHA256(beanAdministrator.getPassword()));
			administratorLocal.persist(beanAdministrator);
			
			
			resetAdministrator();
			administrators=null;
			RequestContext.getCurrentInstance().execute("PF('dlgAdministrator').hide();");
			displayInfoMessage("Atualizado com sucesso");
			return "/pages/restrict/administratorList.jsf?faces-redirect=true";

		} catch (Exception e) {
			 RequestContext.getCurrentInstance().execute("PF('dlgAdministrator').hide();");
			displayErrorMessage("Erro ao atualizar questão.");
			return null;

		}finally {
			beanAdministrator = null;
		}
	}

	
	public void resetAdministrator() {
		beanAdministrator = null;
		
	}
	public void removeAdministrator() {
		try {
			administratorLocal.remove(beanAdministrator);
			getAdministrators().remove(beanAdministrator);
			beanAdministrator = null;
			administrators = null;
			displayInfoMessage("Removido com sucesso.");
		} catch (Exception e) {
			displayErrorMessage("Ocorreu um erro ao remover.");
		}
		
	}

	public Administrator getBeanAdministrator() {
		if(beanAdministrator==null || (beanAdministrator.getId()!=null && !beanAdministrator.getId().equals(getAdministratorId()))){//TODO
			
			if(getAdministratorId()!=null && !getAdministratorId().equals(0L) ){
				beanAdministrator = administratorLocal.findById(getAdministratorId());
				if(beanAdministrator!=null){
					beanAdministrator.setPassword(null);
				}
			}else{
				beanAdministrator =  new Administrator();
			}
		}
		return beanAdministrator;
	}

	public Integer getAdministratorId() {
		String administratorId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("administratorId");
		if(administratorId!=null){
			return Integer.parseInt(administratorId);
		}else{
			return null;
		}
	}



	public void setBeanAdministrator(Administrator beanAdministrator) {
		if(beanAdministrator!=null){
			beanAdministrator.setPassword(null);
		}
		this.beanAdministrator = beanAdministrator;
	}
	
	
	public static String encryptSHA256(String password){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		
	        md.update(password.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return null;
	}

}
