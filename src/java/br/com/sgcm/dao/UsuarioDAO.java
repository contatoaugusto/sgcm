/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.dao;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prohgy
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioDAO.findAll", query = "SELECT u FROM UsuarioDAO u")
    , @NamedQuery(name = "UsuarioDAO.findByIdusuario", query = "SELECT u FROM UsuarioDAO u WHERE u.idusuario = :idusuario")
    , @NamedQuery(name = "UsuarioDAO.findByNmusuario", query = "SELECT u FROM UsuarioDAO u WHERE u.nmusuario = :nmusuario")
    , @NamedQuery(name = "UsuarioDAO.findByDeSenha", query = "SELECT u FROM UsuarioDAO u WHERE u.deSenha = :deSenha")})
public class UsuarioDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nmusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String deSenha;
    @JoinColumn(name = "idpessoa", referencedColumnName = "idpessoa")
    @ManyToOne
    private PessoaDAO idpessoa;

    public UsuarioDAO() {
    }

    public UsuarioDAO(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public UsuarioDAO(Integer idusuario, String nmusuario, String deSenha) {
        this.idusuario = idusuario;
        this.nmusuario = nmusuario;
        this.deSenha = deSenha;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNmusuario() {
        return nmusuario;
    }

    public void setNmusuario(String nmusuario) {
        this.nmusuario = nmusuario;
    }

    public String getDeSenha() {
        return deSenha;
    }

    public void setDeSenha(String deSenha) {
        this.deSenha = deSenha;
    }

    public PessoaDAO getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(PessoaDAO idpessoa) {
        this.idpessoa = idpessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioDAO)) {
            return false;
        }
        UsuarioDAO other = (UsuarioDAO) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.UsuarioDAO[ idusuario=" + idusuario + " ]";
    }
    
}
