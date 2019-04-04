/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prohgy
 */
@Entity
@Table(name = "medicoagendatrabalho")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicoagendatrabalhoDAO.findAll", query = "SELECT m FROM MedicoagendatrabalhoDAO m")
    , @NamedQuery(name = "MedicoagendatrabalhoDAO.findByIdmedicoagendatrabalho", query = "SELECT m FROM MedicoagendatrabalhoDAO m WHERE m.idmedicoagendatrabalho = :idmedicoagendatrabalho")
    , @NamedQuery(name = "MedicoagendatrabalhoDAO.findByDthorainicio", query = "SELECT m FROM MedicoagendatrabalhoDAO m WHERE m.dthorainicio = :dthorainicio")
    , @NamedQuery(name = "MedicoagendatrabalhoDAO.findByDthorafim", query = "SELECT m FROM MedicoagendatrabalhoDAO m WHERE m.dthorafim = :dthorafim")
    , @NamedQuery(name = "MedicoagendatrabalhoDAO.findByDeobservacao", query = "SELECT m FROM MedicoagendatrabalhoDAO m WHERE m.deobservacao = :deobservacao")})
public class MedicoagendatrabalhoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idmedicoagendatrabalho;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthorainicio;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthorafim;
    @Size(max = 500)
    private String deobservacao;
    @JoinColumn(name = "idmedico", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idmedico;

    public MedicoagendatrabalhoDAO() {
    }

    public MedicoagendatrabalhoDAO(Integer idmedicoagendatrabalho) {
        this.idmedicoagendatrabalho = idmedicoagendatrabalho;
    }

    public MedicoagendatrabalhoDAO(Integer idmedicoagendatrabalho, Date dthorainicio, Date dthorafim) {
        this.idmedicoagendatrabalho = idmedicoagendatrabalho;
        this.dthorainicio = dthorainicio;
        this.dthorafim = dthorafim;
    }

    public Integer getIdmedicoagendatrabalho() {
        return idmedicoagendatrabalho;
    }

    public void setIdmedicoagendatrabalho(Integer idmedicoagendatrabalho) {
        this.idmedicoagendatrabalho = idmedicoagendatrabalho;
    }

    public Date getDthorainicio() {
        return dthorainicio;
    }

    public void setDthorainicio(Date dthorainicio) {
        this.dthorainicio = dthorainicio;
    }

    public Date getDthorafim() {
        return dthorafim;
    }

    public void setDthorafim(Date dthorafim) {
        this.dthorafim = dthorafim;
    }

    public String getDeobservacao() {
        return deobservacao;
    }

    public void setDeobservacao(String deobservacao) {
        this.deobservacao = deobservacao;
    }

    public PessoaDAO getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(PessoaDAO idmedico) {
        this.idmedico = idmedico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedicoagendatrabalho != null ? idmedicoagendatrabalho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicoagendatrabalhoDAO)) {
            return false;
        }
        MedicoagendatrabalhoDAO other = (MedicoagendatrabalhoDAO) object;
        if ((this.idmedicoagendatrabalho == null && other.idmedicoagendatrabalho != null) || (this.idmedicoagendatrabalho != null && !this.idmedicoagendatrabalho.equals(other.idmedicoagendatrabalho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.MedicoagendatrabalhoDAO[ idmedicoagendatrabalho=" + idmedicoagendatrabalho + " ]";
    }
    
}
