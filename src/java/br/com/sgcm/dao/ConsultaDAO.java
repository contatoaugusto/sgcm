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
 * @author Antonio Augusto Teixeira
 */
@Entity
@Table(name = "consulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultaDAO.findAll", query = "SELECT c FROM ConsultaDAO c")
    , @NamedQuery(name = "ConsultaDAO.findByIdconsulta", query = "SELECT c FROM ConsultaDAO c WHERE c.idconsulta = :idconsulta")
    , @NamedQuery(name = "ConsultaDAO.findByIdatendente", query = "SELECT c FROM ConsultaDAO c WHERE c.idatendente = :idatendente")
    , @NamedQuery(name = "ConsultaDAO.findByDthorainicio", query = "SELECT c FROM ConsultaDAO c WHERE c.dthorainicio = :dthorainicio")
    , @NamedQuery(name = "ConsultaDAO.findByDthorafim", query = "SELECT c FROM ConsultaDAO c WHERE c.dthorafim = :dthorafim")
    , @NamedQuery(name = "ConsultaDAO.findByIdmedico", query = "SELECT c FROM ConsultaDAO c WHERE c.idmedico.idpessoa = :idmedico")
    , @NamedQuery(name = "ConsultaDAO.findByDeobservacao", query = "SELECT c FROM ConsultaDAO c WHERE c.deobservacao = :deobservacao")})
public class ConsultaDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idconsulta;
    private Integer idatendente;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthorainicio;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthorafim;
    @Size(max = 1000)
    private String deobservacao;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idpaciente;
    @JoinColumn(name = "idmedico", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idmedico;

    public ConsultaDAO() {
    }

    public ConsultaDAO(Integer idconsulta) {
        this.idconsulta = idconsulta;
    }

    public ConsultaDAO(Integer idconsulta, Date dthorainicio, Date dthorafim) {
        this.idconsulta = idconsulta;
        this.dthorainicio = dthorainicio;
        this.dthorafim = dthorafim;
    }

    public Integer getIdconsulta() {
        return idconsulta;
    }

    public void setIdconsulta(Integer idconsulta) {
        this.idconsulta = idconsulta;
    }

    public Integer getIdatendente() {
        return idatendente;
    }

    public void setIdatendente(Integer idatendente) {
        this.idatendente = idatendente;
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

    public PessoaDAO getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(PessoaDAO idpaciente) {
        this.idpaciente = idpaciente;
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
        hash += (idconsulta != null ? idconsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaDAO)) {
            return false;
        }
        ConsultaDAO other = (ConsultaDAO) object;
        if ((this.idconsulta == null && other.idconsulta != null) || (this.idconsulta != null && !this.idconsulta.equals(other.idconsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.ConsultaDAO[ idconsulta=" + idconsulta + " ]";
    }

}
