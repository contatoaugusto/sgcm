package br.com.sgcm.bean;

import br.com.sgcm.dao.MedicoagendatrabalhoDAO;
import br.com.sgcm.bean.util.JsfUtil;
import br.com.sgcm.bean.util.PaginationHelper;
import br.com.sgcm.facade.MedicoagendatrabalhoDAOFacade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
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

@Named("medicoagendatrabalhoDAOController")
@SessionScoped
public class MedicoagendatrabalhoDAOController implements Serializable {

    private MedicoagendatrabalhoDAO current;
    private DataModel items = null;
    @EJB
    private br.com.sgcm.facade.MedicoagendatrabalhoDAOFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private Date dtinicio;
    private Date dtfim;
    private Date hrinicio;
    private Date hrfim;

    public MedicoagendatrabalhoDAOController() {
    }

    public MedicoagendatrabalhoDAO getSelected() {
        if (current == null) {
            current = new MedicoagendatrabalhoDAO();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MedicoagendatrabalhoDAOFacade getFacade() {
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
        current = (MedicoagendatrabalhoDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MedicoagendatrabalhoDAO();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            Calendar calendar = Calendar.getInstance();

            LocalDateTime localDateTime = LocalDateTime.parse(dtinicio.toString()); 
            
            //SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dtinicio.toString());
            //Time time = new Time(date.getTime());
      
            LocalDate datePart = LocalDate.parse(dtinicio.toString());
            LocalTime timePart = LocalTime.parse(dtfim.getHours() + ":" + dtfim.getMinutes());
            LocalDateTime dt = LocalDateTime.of(datePart, timePart);

//            calendar.setTime(dthorainicio);
//            calendar.add(Calendar.DATE, 1);
//            current.setDthorainicio(calendar.getTime());
            getFacade().create(current);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OperacaoSucesso"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("OperacaoErro"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (MedicoagendatrabalhoDAO) getItems().getRowData();
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
            return null;
        }
    }

    public String destroy() {
        current = (MedicoagendatrabalhoDAO) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleTemp").getString("MedicoagendatrabalhoDAODeleted"));
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

    public MedicoagendatrabalhoDAO getMedicoagendatrabalhoDAO(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MedicoagendatrabalhoDAO.class)
    public static class MedicoagendatrabalhoDAOControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MedicoagendatrabalhoDAOController controller = (MedicoagendatrabalhoDAOController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "medicoagendatrabalhoDAOController");
            return controller.getMedicoagendatrabalhoDAO(getKey(value));
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
            if (object instanceof MedicoagendatrabalhoDAO) {
                MedicoagendatrabalhoDAO o = (MedicoagendatrabalhoDAO) object;
                return getStringKey(o.getIdmedicoagendatrabalho());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MedicoagendatrabalhoDAO.class.getName());
            }
        }

    }

    /**
     * @return the dtinicio
     */
    public Date getDtinicio() {
        return dtinicio;
    }

    /**
     * @param dtinicio the dtinicio to set
     */
    public void setDtinicio(Date dtinicio) {
        this.dtinicio = dtinicio;
    }

    /**
     * @return the dtfim
     */
    public Date getDtfim() {
        return dtfim;
    }

    /**
     * @param dtfim the dtfim to set
     */
    public void setDtfim(Date dtfim) {
        this.dtfim = dtfim;
    }

    /**
     * @return the hrinicio
     */
    public Date getHrinicio() {
        return hrinicio;
    }

    /**
     * @param hrinicio the hrinicio to set
     */
    public void setHrinicio(Date hrinicio) {
        this.hrinicio = hrinicio;
    }

    /**
     * @return the hrfim
     */
    public Date getHrfim() {
        return hrfim;
    }

    /**
     * @param hrfim the hrfim to set
     */
    public void setHrfim(Date hrfim) {
        this.hrfim = hrfim;
    }
}
