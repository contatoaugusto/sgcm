/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Antonio Augusto Teixeira
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examepedido.findAll", query = "SELECT e FROM Examepedido e")
    , @NamedQuery(name = "Examepedido.findByIdexamepedido", query = "SELECT e FROM Examepedido e WHERE e.idexamepedido = :idexamepedido")
    , @NamedQuery(name = "Examepedido.findByDtexamepedido", query = "SELECT e FROM Examepedido e WHERE e.dtexamepedido = :dtexamepedido")
    , @NamedQuery(name = "Examepedido.findByDeobservacoes", query = "SELECT e FROM Examepedido e WHERE e.deobservacoes = :deobservacoes")})
public class ExamepedidoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idexamepedido;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtexamepedido;
    @Size(max = 1000)
    private String deobservacoes;
    @JoinColumn(name = "idxametipo", referencedColumnName = "idexametipo")
    @ManyToOne(optional = false)
    private ExametipoDAO idxametipo;
    @JoinColumn(name = "idmedico", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idmedico;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false)
    private PessoaDAO idpaciente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idexamepedido")
    private Collection<ExameresultadoDAO> exameresultadoCollection;

    public ExamepedidoDAO() {
    }

    public ExamepedidoDAO(Integer idexamepedido) {
        this.idexamepedido = idexamepedido;
    }

    public ExamepedidoDAO(Integer idexamepedido, Date dtexamepedido) {
        this.idexamepedido = idexamepedido;
        this.dtexamepedido = dtexamepedido;
    }

    public Integer getIdexamepedido() {
        return idexamepedido;
    }

    public void setIdexamepedido(Integer idexamepedido) {
        this.idexamepedido = idexamepedido;
    }

    public Date getDtexamepedido() {
        return dtexamepedido;
    }

    public void setDtexamepedido(Date dtexamepedido) {
        this.dtexamepedido = dtexamepedido;
    }

    public String getDeobservacoes() {
        return deobservacoes;
    }

    public void setDeobservacoes(String deobservacoes) {
        this.deobservacoes = deobservacoes;
    }

    public ExametipoDAO getIdxametipo() {
        return idxametipo;
    }

    public void setIdxametipo(ExametipoDAO idxametipo) {
        this.idxametipo = idxametipo;
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

    @XmlTransient
    public Collection<ExameresultadoDAO> getExameresultadoCollection() {
        return exameresultadoCollection;
    }

    public void setExameresultadoCollection(Collection<ExameresultadoDAO> exameresultadoCollection) {
        this.exameresultadoCollection = exameresultadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexamepedido != null ? idexamepedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamepedidoDAO)) {
            return false;
        }
        ExamepedidoDAO other = (ExamepedidoDAO) object;
        if ((this.idexamepedido == null && other.idexamepedido != null) || (this.idexamepedido != null && !this.idexamepedido.equals(other.idexamepedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.Examepedido[ idexamepedido=" + idexamepedido + " ]";
    }
    
}
