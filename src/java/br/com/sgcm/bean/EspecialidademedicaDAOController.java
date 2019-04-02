package br.com.sgcm.bean;

import br.com.sgcm.dao.EspecialidademedicaDAO;
import br.com.sgcm.bean.util.JsfUtil;
import br.com.sgcm.bean.util.PaginationHelper;
import br.com.sgcm.dao.PessoaDAO;
import br.com.sgcm.facade.EspecialidademedicaDAOFacade;

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

@Named("especialidademedicaDAOController")
@SessionScoped
public class EspecialidademedicaDAOController implements Serializable {

    private EspecialidademedicaDAO current;
    private DataModel items = null;
    @EJB
    private br.com.sgcm.facade.EspecialidademedicaDAOFacade ejbFacade;
    @EJB
    private br.com.sgcm.facade.PessoaDAOFacade ejbFacadePessoa;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private List<EspecialidademedicaDAO> especialidademedicaList;
    private Integer idEspecialidademedica;
    
    private List<PessoaDAO> medicoEspecialidadeList;
    
    public EspecialidademedicaDAOController() {
    }

    public EspecialidademedicaDAO getSelected() {
        
        setEspecialidademedicaList(ejbFacade.findAll());
        
        if (current == null) {
            current = new EspecialidademedicaDAO();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EspecialidademedicaDAOFacade getFacade() {
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
        current = new EspecialidademedicaDAO();
        return "manterEspecialidadeMedica";
    }

    public String prepareView() {
        current = (EspecialidademedicaDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new EspecialidademedicaDAO();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OperacaoSucesso"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("OperacaoErro"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (EspecialidademedicaDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleTemp").getString("EspecialidademedicaDAOUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleTemp").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (EspecialidademedicaDAO) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleTemp").getString("EspecialidademedicaDAODeleted"));
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

    public EspecialidademedicaDAO getEspecialidademedicaDAO(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = EspecialidademedicaDAO.class, value = "especialidadeMedicaConverter")
    public static class EspecialidademedicaDAOControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EspecialidademedicaDAOController controller = (EspecialidademedicaDAOController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "especialidademedicaDAOController");
            return controller.getEspecialidademedicaDAO(getKey(value));
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
            if (object instanceof EspecialidademedicaDAO) {
                EspecialidademedicaDAO o = (EspecialidademedicaDAO) object;
                return getStringKey(o.getIdespecialidademedica());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EspecialidademedicaDAO.class.getName());
            }
        }

    }

    /**
     * @return the especialidademedicaList
     */
    public List<EspecialidademedicaDAO> getEspecialidademedicaList() {
        return especialidademedicaList;
    }

    /**
     * @param especialidademedicaList the especialidademedicaList to set
     */
    public void setEspecialidademedicaList(List<EspecialidademedicaDAO> especialidademedicaList) {
        this.especialidademedicaList = especialidademedicaList;
    }

    /**
     * @return the idEspecialidademedica
     */
    public Integer getIdEspecialidademedica() {
        return idEspecialidademedica;
    }

    /**
     * @param idEspecialidademedica the idEspecialidademedica to set
     */
    public void setIdEspecialidademedica(Integer idEspecialidademedica) {
        this.idEspecialidademedica = idEspecialidademedica;
    }

    public List<EspecialidademedicaDAO> completeEspecialidade(String query) {
        
        List<EspecialidademedicaDAO> especialidades = new ArrayList<EspecialidademedicaDAO>();
        for (EspecialidademedicaDAO especialidade : especialidademedicaList) {
            if (especialidade.getNmespecialidademedica().toLowerCase().contains(query.toLowerCase())) {
                especialidades.add(especialidade);
            }
        }
        
        return especialidades;
    }
    
    /***
     * Quando seleciona uma especialidade deve mostrar todos os médico esppecialista nela
     * @param event 
     */
    
    public void onEspecialidadeSelect(SelectEvent event) {
        medicoEspecialidadeList = new ArrayList<PessoaDAO>();
        EspecialidademedicaDAO especialidade = (EspecialidademedicaDAO) event.getObject();
        List<PessoaDAO> medicosEspecialidade = ejbFacadePessoa.findAll();
        for (PessoaDAO item : medicosEspecialidade) {
            if (item.getIdespecialidademedica() != null && item.getIdespecialidademedica().getIdespecialidademedica() == especialidade.getIdespecialidademedica())
                medicoEspecialidadeList.add(item);
        }
    }

    /**
     * @return the medicoEspecialidadeList
     */
    public List<PessoaDAO> getMedicoEspecialidadeList() {
        return medicoEspecialidadeList;
    }

    /**
     * @param medicoEspecialidadeList the medicoEspecialidadeList to set
     */
    public void setMedicoEspecialidadeList(List<PessoaDAO> medicoEspecialidadeList) {
        this.medicoEspecialidadeList = medicoEspecialidadeList;
    }
}
