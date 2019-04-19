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
    @NamedQuery(name = "Receita.findAll", query = "SELECT r FROM Receita r")
    , @NamedQuery(name = "Receita.findByIdreceita", query = "SELECT r FROM Receita r WHERE r.idreceita = :idreceita")
    , @NamedQuery(name = "Receita.findByDeobservacao", query = "SELECT r FROM Receita r WHERE r.deobservacao = :deobservacao")
    , @NamedQuery(name = "Receita.findByDtreceita", query = "SELECT r FROM Receita r WHERE r.dtreceita = :dtreceita")})
public class ReceitaDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idreceita;
    @Size(max = 1000)
    private String deobservacao;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtreceita;
    @JoinColumn(name = "idmedico", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idmedico;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idpaciente;

    public ReceitaDAO() {
    }

    public ReceitaDAO(Integer idreceita) {
        this.idreceita = idreceita;
    }

    public ReceitaDAO(Integer idreceita, Date dtreceita) {
        this.idreceita = idreceita;
        this.dtreceita = dtreceita;
    }

    public Integer getIdreceita() {
        return idreceita;
    }

    public void setIdreceita(Integer idreceita) {
        this.idreceita = idreceita;
    }

    public String getDeobservacao() {
        return deobservacao;
    }

    public void setDeobservacao(String deobservacao) {
        this.deobservacao = deobservacao;
    }

    public Date getDtreceita() {
        return dtreceita;
    }

    public void setDtreceita(Date dtreceita) {
        this.dtreceita = dtreceita;
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
        hash += (idreceita != null ? idreceita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReceitaDAO)) {
            return false;
        }
        ReceitaDAO other = (ReceitaDAO) object;
        if ((this.idreceita == null && other.idreceita != null) || (this.idreceita != null && !this.idreceita.equals(other.idreceita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.Receita[ idreceita=" + idreceita + " ]";
    }
    
}
