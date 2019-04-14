package br.com.sgcm.bean;

import br.com.sgcm.dao.ConsultaDAO;
import br.com.sgcm.bean.util.JsfUtil;
import br.com.sgcm.bean.util.PaginationHelper;
import br.com.sgcm.dao.EspecialidademedicaDAO;
import br.com.sgcm.dao.MedicoagendatrabalhoDAO;
import br.com.sgcm.dao.PessoaDAO;
import br.com.sgcm.facade.ConsultaDAOFacade;
import br.com.sgcm.facade.MedicoagendatrabalhoDAOFacade;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@Named("consultaDAOController")
@SessionScoped
public class ConsultaDAOController implements Serializable {

    private ConsultaDAO current;
    private DataModel items = null;
    @EJB
    private ConsultaDAOFacade ejbFacade;
    @EJB
    private MedicoagendatrabalhoDAOFacade ejbFacadeAgendaMedico;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private String nmPessoa;
    private String nucpf;
    private String deendereco;

    private String nmMedico;

    private EspecialidademedicaDAO especialidadeMedica;

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    public ConsultaDAOController() {
    }

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();

    }

    public ConsultaDAO getSelected() {
        if (current == null) {
            current = new ConsultaDAO();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ConsultaDAOFacade getFacade() {
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
        current = (ConsultaDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ConsultaDAO();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleTemp").getString("ConsultaDAOCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleTemp").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ConsultaDAO) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleTemp").getString("ConsultaDAOUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleTemp").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ConsultaDAO) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleTemp").getString("ConsultaDAODeleted"));
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

    public ConsultaDAO getConsultaDAO(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ConsultaDAO.class)
    public static class ConsultaDAOControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConsultaDAOController controller = (ConsultaDAOController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "consultaDAOController");
            return controller.getConsultaDAO(getKey(value));
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
            if (object instanceof ConsultaDAO) {
                ConsultaDAO o = (ConsultaDAO) object;
                return getStringKey(o.getIdconsulta());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ConsultaDAO.class.getName());
            }
        }
    }

    /**
     * @return the nmPessoa
     */
    public String getNmPessoa() {
        return nmPessoa;
    }

    /**
     * @param nmPessoa the nmPessoa to set
     */
    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    /**
     * @return the nucpf
     */
    public String getNucpf() {
        return nucpf;
    }

    /**
     * @param nucpf the nucpf to set
     */
    public void setNucpf(String nucpf) {
        this.nucpf = nucpf;
    }

    /**
     * @return the deendereco
     */
    public String getDeendereco() {
        return deendereco;
    }

    /**
     * @param deendereco the deendereco to set
     */
    public void setDeendereco(String deendereco) {
        this.deendereco = deendereco;
    }

    public void onPacienteSelect(SelectEvent event) {
        PessoaDAO pessoa = (PessoaDAO) event.getObject();
        setNmPessoa(pessoa.getNmpessoa());
        setNucpf(pessoa.getNucpf());
        setDeendereco(pessoa.getDeendereco());

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }
    
    public void onPacienteKeyup() {
        setNmPessoa("");
        setNucpf("");
        setDeendereco("");

    }

    public void onMedicoSelect(SelectEvent event) {
        PessoaDAO medico = (PessoaDAO) event.getObject();
        try {
            if (medico != null) {

                //Recupera a agenda do médico, ou seja, os dias e horários em que o médico estrá em atendimento na clínica
                List<MedicoagendatrabalhoDAO> agendaMedico = ejbFacadeAgendaMedico.findByMedico(medico.getIdpessoa());

                eventModel = new DefaultScheduleModel();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //String dateString = format.format(new Date());
                Date dateMomento = format.parse("2019-01-01");
                Date dateFim = format.parse("2022-01-01");

                Calendar cal = Calendar.getInstance();

                // trava os dias do calendário para marcação de consulta onde o médico não tenha agenda criada. Não pode haver marcação de consulta
                while (dateMomento.getTime() < dateFim.getTime()) {

                    String classeAgenda = "agendamentoconsulta_day_disable";
                    for (MedicoagendatrabalhoDAO item : agendaMedico) {
                        if ((item.getDthorainicio().getYear() == dateMomento.getYear()
                                && item.getDthorainicio().getMonth() == dateMomento.getMonth()
                                && item.getDthorainicio().getDate() == dateMomento.getDate())) {
                            classeAgenda = "";
                        }
                    }

                    if (!classeAgenda.isEmpty()) {
                        DefaultScheduleEvent evento = new DefaultScheduleEvent("", dateMomento, dateMomento, classeAgenda);
                        evento.setEditable(false);
                        evento.setAllDay(true);
                        eventModel.addEvent(evento);
                    }

                    cal.setTime(dateMomento);
                    cal.add(Calendar.DATE, 1);
                    dateMomento = cal.getTime();
                }

                setNmMedico(medico.getNmpessoa());

                // Preenche as consultas já marcadas
                List<ConsultaDAO> consultaList = ejbFacade.findByMedico(medico.getIdpessoa());
                for (ConsultaDAO consulta : consultaList) {
                    eventModel.addEvent(new DefaultScheduleEvent(consulta.getIdpaciente().getNmpessoa(), consulta.getDthorainicio(), consulta.getDthorafim()));
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(ConsultaDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the especialidadeMedica
     */
    public EspecialidademedicaDAO getEspecialidadeMedica() {
        return especialidadeMedica;
    }

    /**
     * @param especialidadeMedica the especialidadeMedica to set
     */
    public void setEspecialidadeMedica(EspecialidademedicaDAO especialidadeMedica) {
        this.especialidadeMedica = especialidadeMedica;
    }

    // Abenda - Evento
    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    /*
     * Quando clica no botão Adicionar no dialog que ctria um novo agendamento no calendario
     * @return 
     */
    public String addEvent() {
        try {
            if (event.getId() == null) {
                getEventModel().addEvent(event);
            } else {
                getEventModel().updateEvent(event);
            }

            event = new DefaultScheduleEvent();

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OperacaoSucesso"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("OperacaoErro"));
            return null;
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    /**
     * Remove o agendamento da consulta
     *
     * @param event
     */
    public void onEventMove(ScheduleEntryMoveEvent event) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//            addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//            addMessage(message);
    }

    /**
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    /**
     * @return the nmMedico
     */
    public String getNmMedico() {
        return nmMedico;
    }

    /**
     * @param nmMedico the nmMedico to set
     */
    public void setNmMedico(String nmMedico) {
        this.nmMedico = nmMedico;
    }

}
