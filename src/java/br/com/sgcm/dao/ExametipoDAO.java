/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Antonio Augusto Teixeira
 */
@Entity
@Table(name = "exametipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exametipo.findAll", query = "SELECT e FROM ExametipoDAO e")
    , @NamedQuery(name = "Exametipo.findByIdexametipo", query = "SELECT e FROM ExametipoDAO e WHERE e.idexametipo = :idexametipo")
    , @NamedQuery(name = "Exametipo.findByNmexametipo", query = "SELECT e FROM ExametipoDAO e WHERE e.nmexametipo = :nmexametipo")
    , @NamedQuery(name = "Exametipo.findByVrexame", query = "SELECT e FROM ExametipoDAO e WHERE e.vrexame = :vrexame")})
public class ExametipoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idexametipo;

    @Size(max = 200)
    private String nmexametipo;

    private BigDecimal vrexame;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idxametipo")
    private Collection<ExamepedidoDAO> examepedidoCollection;

    public ExametipoDAO() {
    }

    /**
     * @return the idexametipo
     */
    public Integer getIdexametipo() {
        return idexametipo;
    }

    /**
     * @param idexametipo the idexametipo to set
     */
    public void setIdexametipo(Integer idexametipo) {
        this.idexametipo = idexametipo;
    }

    /**
     * @return the nmexametipo
     */
    public String getNmexametipo() {
        return nmexametipo;
    }

    /**
     * @param nmexametipo the nmexametipo to set
     */
    public void setNmexametipo(String nmexametipo) {
        this.nmexametipo = nmexametipo;
    }

    public BigDecimal getVrexame() {
        return vrexame;
    }

    public void setVrexame(BigDecimal vrexame) {
        this.vrexame = vrexame;
    }

    @XmlTransient
    public Collection<ExamepedidoDAO> getExamepedidoCollection() {
        return examepedidoCollection;
    }

    public void setExamepedidoCollection(Collection<ExamepedidoDAO> examepedidoCollection) {
        this.examepedidoCollection = examepedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexametipo != null ? idexametipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamepedidoDAO)) {
            return false;
        }
        ExametipoDAO other = (ExametipoDAO) object;
        if ((this.idexametipo == null && other.idexametipo != null) || (this.idexametipo != null && !this.idexametipo.equals(other.idexametipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.ExametipoDAO[ idexametipo=" + idexametipo + " ]";
    }

}
