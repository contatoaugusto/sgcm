package br.com.sgcm.bean;

import br.com.sgcm.dao.ReceitaDAO;
import br.com.sgcm.bean.util.JsfUtil;
import br.com.sgcm.bean.util.PaginationHelper;
import br.com.sgcm.dao.PessoaDAO;
import br.com.sgcm.facade.ReceitaDAOFacade;

import java.io.Serializable;
import java.util.Date;
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

@Named("receitaDAOController")
@SessionScoped
public class ReceitaDAOController implements Serializable {

    private ReceitaDAO current;
    private DataModel items = null;
    @EJB
    private br.com.sgcm.facade.ReceitaDAOFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private String nmPessoaMedico;
    private String nuCRM;
    
    public ReceitaDAOController() {
    }

    public ReceitaDAO getSelected() {
        if (current == null) {
            current = new ReceitaDAO();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ReceitaDAOFacade getFacade() {
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
        current = (ReceitaDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ReceitaDAO();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.setDtreceita(new Date());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OperacaoSucesso"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("OperacaoErro"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ReceitaDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OperacaoSucesso"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("OperacaoErro"));
            return prepareList();
        }
    }

    public String destroy() {
        current = (ReceitaDAO) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleTemp").getString("ReceitaDAODeleted"));
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

    public ReceitaDAO getReceitaDAO(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ReceitaDAO.class)
    public static class ReceitaDAOControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReceitaDAOController controller = (ReceitaDAOController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "receitaDAOController");
            return controller.getReceitaDAO(getKey(value));
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
            if (object instanceof ReceitaDAO) {
                ReceitaDAO o = (ReceitaDAO) object;
                return getStringKey(o.getIdreceita());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ReceitaDAO.class.getName());
            }
        }

    }
        /**
     * @return the nmPessoaMedico
     */
    public String getNmPessoaMedico() {
        return nmPessoaMedico;
    }

    /**
     * @param nmPessoaMedico the nmPessoaMedico to set
     */
    public void setNmPessoaMedico(String nmPessoaMedico) {
        this.nmPessoaMedico = nmPessoaMedico;
    }

    /**
     * @return the nuCRM
     */
    public String getNuCRM() {
        return nuCRM;
    }

    /**
     * @param nuCRM the nuCRM to set
     */
    public void setNuCRM(String nuCRM) {
        this.nuCRM = nuCRM;
    }
    
    /**
     * chamado pelo evento ajax da página ao selecionar um médico
     *
     * @param event
     */
    public void onMedicoSelect(SelectEvent event) {
        PessoaDAO medico = (PessoaDAO) event.getObject();
        setNmPessoaMedico(medico.getNmpessoa());
        setNuCRM(medico.getNucrm());
    }

}
