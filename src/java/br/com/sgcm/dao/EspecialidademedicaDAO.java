/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "especialidademedica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspecialidademedicaDAO.findAll", query = "SELECT e FROM EspecialidademedicaDAO e")
    , @NamedQuery(name = "EspecialidademedicaDAO.findByIdespecialidademedica", query = "SELECT e FROM EspecialidademedicaDAO e WHERE e.idespecialidademedica = :idespecialidademedica")
    , @NamedQuery(name = "EspecialidademedicaDAO.findByNmespecialidademedica", query = "SELECT e FROM EspecialidademedicaDAO e WHERE e.nmespecialidademedica = :nmespecialidademedica")
    , @NamedQuery(name = "EspecialidademedicaDAO.findByDeobservacao", query = "SELECT e FROM EspecialidademedicaDAO e WHERE e.deobservacao = :deobservacao")})
public class EspecialidademedicaDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idespecialidademedica;
    @Size(max = 150)
    private String nmespecialidademedica;
    @Size(max = 1000)
    private String deobservacao;
    @OneToMany(mappedBy = "idespecialidademedica")
    private Collection<PessoaDAO> pessoaDAOCollection;

    public EspecialidademedicaDAO() {
    }

    public EspecialidademedicaDAO(Integer idespecialidademedica) {
        this.idespecialidademedica = idespecialidademedica;
    }

    public Integer getIdespecialidademedica() {
        return idespecialidademedica;
    }

    public void setIdespecialidademedica(Integer idespecialidademedica) {
        this.idespecialidademedica = idespecialidademedica;
    }

    public String getNmespecialidademedica() {
        return nmespecialidademedica;
    }

    public void setNmespecialidademedica(String nmespecialidademedica) {
        this.nmespecialidademedica = nmespecialidademedica;
    }

    public String getDeobservacao() {
        return deobservacao;
    }

    public void setDeobservacao(String deobservacao) {
        this.deobservacao = deobservacao;
    }

    @XmlTransient
    public Collection<PessoaDAO> getPessoaDAOCollection() {
        return pessoaDAOCollection;
    }

    public void setPessoaDAOCollection(Collection<PessoaDAO> pessoaDAOCollection) {
        this.pessoaDAOCollection = pessoaDAOCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idespecialidademedica != null ? idespecialidademedica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecialidademedicaDAO)) {
            return false;
        }
        EspecialidademedicaDAO other = (EspecialidademedicaDAO) object;
        if ((this.idespecialidademedica == null && other.idespecialidademedica != null) || (this.idespecialidademedica != null && !this.idespecialidademedica.equals(other.idespecialidademedica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.EspecialidademedicaDAO[ idespecialidademedica=" + idespecialidademedica + " ]";
    }
    
}
