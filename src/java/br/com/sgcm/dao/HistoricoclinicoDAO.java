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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Antonio Augusto Teixeira
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicoclinico.findAll", query = "SELECT h FROM Historicoclinico h")
    , @NamedQuery(name = "Historicoclinico.findByIdhistoricoclinico", query = "SELECT h FROM Historicoclinico h WHERE h.idhistoricoclinico = :idhistoricoclinico")
    , @NamedQuery(name = "Historicoclinico.findByDthistoricoclinico", query = "SELECT h FROM Historicoclinico h WHERE h.dthistoricoclinico = :dthistoricoclinico")
    , @NamedQuery(name = "Historicoclinico.findByDeobservacao", query = "SELECT h FROM Historicoclinico h WHERE h.deobservacao = :deobservacao")
    , @NamedQuery(name = "Historicoclinico.findByIcAtivo", query = "SELECT h FROM Historicoclinico h WHERE h.icAtivo = :icAtivo")})
public class HistoricoclinicoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idhistoricoclinico;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthistoricoclinico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    private String deobservacao;
    @Basic(optional = false)
    @NotNull
    private short icAtivo;
    @JoinColumn(name = "idmedico", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idmedico;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idpaciente;

    public HistoricoclinicoDAO() {
    }

    public HistoricoclinicoDAO(Integer idhistoricoclinico) {
        this.idhistoricoclinico = idhistoricoclinico;
    }

    public HistoricoclinicoDAO(Integer idhistoricoclinico, Date dthistoricoclinico, String deobservacao, short icAtivo) {
        this.idhistoricoclinico = idhistoricoclinico;
        this.dthistoricoclinico = dthistoricoclinico;
        this.deobservacao = deobservacao;
        this.icAtivo = icAtivo;
    }

    public Integer getIdhistoricoclinico() {
        return idhistoricoclinico;
    }

    public void setIdhistoricoclinico(Integer idhistoricoclinico) {
        this.idhistoricoclinico = idhistoricoclinico;
    }

    public Date getDthistoricoclinico() {
        return dthistoricoclinico;
    }

    public void setDthistoricoclinico(Date dthistoricoclinico) {
        this.dthistoricoclinico = dthistoricoclinico;
    }

    public String getDeobservacao() {
        return deobservacao;
    }

    public void setDeobservacao(String deobservacao) {
        this.deobservacao = deobservacao;
    }

    public short getIcAtivo() {
        return icAtivo;
    }

    public void setIcAtivo(short icAtivo) {
        this.icAtivo = icAtivo;
    }

    public PessoaDAO getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(PessoaDAO idmedico) {
        this.idmedico = idmedico;
    }

    public PessoaDAO getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(PessoaDAO idpaciente) {
        this.idpaciente = idpaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistoricoclinico != null ? idhistoricoclinico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoclinicoDAO)) {
            return false;
        }
        HistoricoclinicoDAO other = (HistoricoclinicoDAO) object;
        if ((this.idhistoricoclinico == null && other.idhistoricoclinico != null) || (this.idhistoricoclinico != null && !this.idhistoricoclinico.equals(other.idhistoricoclinico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.Historicoclinico[ idhistoricoclinico=" + idhistoricoclinico + " ]";
    }
    
}
