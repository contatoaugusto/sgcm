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
    @NamedQuery(name = "Medicamentoaplicacao.findAll", query = "SELECT m FROM Medicamentoaplicacao m")
    , @NamedQuery(name = "Medicamentoaplicacao.findByIdmedicamentoaplicacao", query = "SELECT m FROM Medicamentoaplicacao m WHERE m.idmedicamentoaplicacao = :idmedicamentoaplicacao")
    , @NamedQuery(name = "Medicamentoaplicacao.findByDtmedicamentoaplicacao", query = "SELECT m FROM Medicamentoaplicacao m WHERE m.dtmedicamentoaplicacao = :dtmedicamentoaplicacao")
    , @NamedQuery(name = "Medicamentoaplicacao.findByDeobservacao", query = "SELECT m FROM Medicamentoaplicacao m WHERE m.deobservacao = :deobservacao")})
public class MedicamentoaplicacaoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idmedicamentoaplicacao;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtmedicamentoaplicacao;
    @Size(max = 500)
    private String deobservacao;
    @JoinColumn(name = "idenfermeiro", referencedColumnName = "idpessoa")
    @ManyToOne
    private PessoaDAO idenfermeiro;
    @JoinColumn(name = "idmedicamento", referencedColumnName = "idmedicamento")
    @ManyToOne(optional = false)
    private MedicamentoDAO idmedicamento;
    @JoinColumn(name = "idmedico", referencedColumnName = "idpessoa")
    @ManyToOne
    private PessoaDAO idmedico;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idpaciente;

    public MedicamentoaplicacaoDAO() {
    }

    public MedicamentoaplicacaoDAO(Integer idmedicamentoaplicacao) {
        this.idmedicamentoaplicacao = idmedicamentoaplicacao;
    }

    public MedicamentoaplicacaoDAO(Integer idmedicamentoaplicacao, Date dtmedicamentoaplicacao) {
        this.idmedicamentoaplicacao = idmedicamentoaplicacao;
        this.dtmedicamentoaplicacao = dtmedicamentoaplicacao;
    }

    public Integer getIdmedicamentoaplicacao() {
        return idmedicamentoaplicacao;
    }

    public void setIdmedicamentoaplicacao(Integer idmedicamentoaplicacao) {
        this.idmedicamentoaplicacao = idmedicamentoaplicacao;
    }

    public Date getDtmedicamentoaplicacao() {
        return dtmedicamentoaplicacao;
    }

    public void setDtmedicamentoaplicacao(Date dtmedicamentoaplicacao) {
        this.dtmedicamentoaplicacao = dtmedicamentoaplicacao;
    }

    public String getDeobservacao() {
        return deobservacao;
    }

    public void setDeobservacao(String deobservacao) {
        this.deobservacao = deobservacao;
    }

    public PessoaDAO getIdenfermeiro() {
        return idenfermeiro;
    }

    public void setIdenfermeiro(PessoaDAO idenfermeiro) {
        this.idenfermeiro = idenfermeiro;
    }

    public MedicamentoDAO getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(MedicamentoDAO idmedicamento) {
        this.idmedicamento = idmedicamento;
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
        hash += (idmedicamentoaplicacao != null ? idmedicamentoaplicacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoaplicacaoDAO)) {
            return false;
        }
        MedicamentoaplicacaoDAO other = (MedicamentoaplicacaoDAO) object;
        if ((this.idmedicamentoaplicacao == null && other.idmedicamentoaplicacao != null) || (this.idmedicamentoaplicacao != null && !this.idmedicamentoaplicacao.equals(other.idmedicamentoaplicacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.Medicamentoaplicacao[ idmedicamentoaplicacao=" + idmedicamentoaplicacao + " ]";
    }
    
}
