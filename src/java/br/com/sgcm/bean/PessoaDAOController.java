package br.com.sgcm.bean;

import br.com.sgcm.dao.PessoaDAO;
import br.com.sgcm.bean.util.JsfUtil;
import br.com.sgcm.bean.util.PaginationHelper;
import br.com.sgcm.dao.PerfilDAO;
import br.com.sgcm.dao.UsuarioDAO;
import br.com.sgcm.facade.PessoaDAOFacade;
import br.com.sgcm.facade.UsuarioDAOFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;

@Named("pessoaDAOController")
@SessionScoped
public class PessoaDAOController implements Serializable {

       
    private PessoaDAO current;
    private DataModel items = null;
    @EJB
    private br.com.sgcm.facade.PessoaDAOFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private String nmUsuario = "";
    private String deSenha = "";
    
    @EJB
    private UsuarioDAOFacade usuarioDAOFacade;
    
    private List<PessoaDAO> pessoaList;
    // Apenas pra usarmos o hidden que força o carregamento de selected
    private Integer idPessoa;
    
    public PessoaDAOController() {
    }
    
    public PessoaDAO getSelected() {
        
        setPessoaList(ejbFacade.findAll());
        
        if (current == null) {
            current = new PessoaDAO();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    private PessoaDAOFacade getFacade() {
        return ejbFacade;
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }
                
                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }
    
    public String prepareList() {
        recreateModel();
        return "List";
    }
    
    public String prepareView() {
        current = (PessoaDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    public String prepareCreate() {
        current = new PessoaDAO();
        selectedItemIndex = -1;
        return "Create";
    }
    
    public String create() {
        try {
            
            getFacade().create(current);

            // Cria o usuario
            UsuarioDAO currentUsuario = new UsuarioDAO();
            currentUsuario = new UsuarioDAO();
            currentUsuario.setNmusuario(getNmUsuario());
            currentUsuario.setDeSenha(getDeSenha());
            currentUsuario.setIdpessoa(current);
            usuarioDAOFacade.create(currentUsuario);
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OperacaoSucesso"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("OperacaoErro"));
            return null;
        }
    }
    
    public String prepareEdit() {
        current = (PessoaDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleTemp").getString("PessoaDAOUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleTemp").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String destroy() {
        current = (PessoaDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }
    
    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }
    
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleTemp").getString("PessoaDAODeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleTemp").getString("PersistenceErrorOccured"));
        }
    }
    
    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }
    
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    private void recreateModel() {
        items = null;
    }
    
    private void recreatePagination() {
        pagination = null;
    }
    
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }
    
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }
    
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
    
    public PessoaDAO getPessoaDAO(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    /**
     * @return the nmUsuario
     */
    public String getNmUsuario() {
        return nmUsuario;
    }

    /**
     * @param nmUsuario the nmUsuario to set
     */
    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    /**
     * @return the deSenha
     */
    public String getDeSenha() {
        return deSenha;
    }

    /**
     * @param deSenha the deSenha to set
     */
    public void setDeSenha(String deSenha) {
        this.deSenha = deSenha;
    }    
    
    @FacesConverter(forClass = PessoaDAO.class, value = "pessoaConverter")
    public static class PessoaDAOControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PessoaDAOController controller = (PessoaDAOController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pessoaDAOController");
            return controller.getPessoaDAO(getKey(value));
        }
        
        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }
        
        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }
        
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PessoaDAO) {
                PessoaDAO o = (PessoaDAO) object;
                return getStringKey(o.getIdpessoa());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PessoaDAO.class.getName());
            }
        }
        
    }
    
    /**
     * @return the pessoaList
     */
    public List<PessoaDAO> getPessoaList() {
        return pessoaList;
    }

    /**
     * @param pessoaList the pessoaList to set
     */
    public void setPessoaList(List<PessoaDAO> pessoaList) {
        this.pessoaList = pessoaList;
    }

    /**
     * @return the idPessoa
     */
    public Integer getIdPessoa() {
        return idPessoa;
    }

    /**
     * @param idPessoa the idPessoa to set
     */
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }
    
   
    public List<PessoaDAO> completePessoa(String query) {
        
        List<PessoaDAO> pessoas = new ArrayList<PessoaDAO>();
        for (PessoaDAO pessoa : pessoaList) {
            if (pessoa.getNmpessoa().toLowerCase().contains(query.toLowerCase())) {
                pessoas.add(pessoa);
            }
        }
        
        return pessoas;
    }
}
