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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author prohgy
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PessoaDAO.findAll", query = "SELECT p FROM PessoaDAO p")
    , @NamedQuery(name = "PessoaDAO.findByIdpessoa", query = "SELECT p FROM PessoaDAO p WHERE p.idpessoa = :idpessoa")
    , @NamedQuery(name = "PessoaDAO.findByNmpessoa", query = "SELECT p FROM PessoaDAO p WHERE p.nmpessoa = :nmpessoa")
    , @NamedQuery(name = "PessoaDAO.findByNucpf", query = "SELECT p FROM PessoaDAO p WHERE p.nucpf = :nucpf")
    , @NamedQuery(name = "PessoaDAO.findByNurg", query = "SELECT p FROM PessoaDAO p WHERE p.nurg = :nurg")
    , @NamedQuery(name = "PessoaDAO.findByDeendereco", query = "SELECT p FROM PessoaDAO p WHERE p.deendereco = :deendereco")
    , @NamedQuery(name = "PessoaDAO.findByNmbairro", query = "SELECT p FROM PessoaDAO p WHERE p.nmbairro = :nmbairro")
    , @NamedQuery(name = "PessoaDAO.findByNmcidade", query = "SELECT p FROM PessoaDAO p WHERE p.nmcidade = :nmcidade")
    , @NamedQuery(name = "PessoaDAO.findByDeemail", query = "SELECT p FROM PessoaDAO p WHERE p.deemail = :deemail")
    , @NamedQuery(name = "PessoaDAO.findByNutelefone", query = "SELECT p FROM PessoaDAO p WHERE p.nutelefone = :nutelefone")
    , @NamedQuery(name = "PessoaDAO.findByNucelular", query = "SELECT p FROM PessoaDAO p WHERE p.nucelular = :nucelular")
    , @NamedQuery(name = "PessoaDAO.findByNucrm", query = "SELECT p FROM PessoaDAO p WHERE p.nucrm = :nucrm")
    , @NamedQuery(name = "PessoaDAO.findByNmespecialidademedica", query = "SELECT p FROM PessoaDAO p WHERE p.nmespecialidademedica = :nmespecialidademedica")
    , @NamedQuery(name = "PessoaDAO.findByNucrt", query = "SELECT p FROM PessoaDAO p WHERE p.nucrt = :nucrt")
    , @NamedQuery(name = "PessoaDAO.findByNucoren", query = "SELECT p FROM PessoaDAO p WHERE p.nucoren = :nucoren")})
public class PessoaDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idpessoa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nmpessoa;
    @Size(max = 15)
    private String desexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    private String nucpf;
    @Size(max = 15)
    private String nurg;
    @Size(max = 100)
    private String deendereco;
    @Size(max = 100)
    private String nmbairro;
    @Size(max = 100)
    private String nmcidade;
    @Size(max = 10)
    private String nucep;
    @Size(max = 100)
    private String deemail;
    @Size(max = 15)
    private String nutelefone;
    @Size(max = 15)
    private String nucelular;
    @Size(max = 15)
    private String nucrm;
    @Size(max = 150)
    private String nmespecialidademedica;
    @Size(max = 15)
    private String nucrt;
    @Size(max = 15)
    private String nucoren;    
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne(optional = false)
    private PerfilDAO idperfil;
    @OneToMany(mappedBy = "idpessoa")
    private Collection<UsuarioDAO> usuarioDAOCollection;

    public PessoaDAO() {
    }

    public PessoaDAO(Integer idpessoa) {
        this.idpessoa = idpessoa;
    }

    public PessoaDAO(Integer idpessoa, String nmpessoa, String nucpf) {
        this.idpessoa = idpessoa;
        this.nmpessoa = nmpessoa;
        this.nucpf = nucpf;
    }

    public Integer getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(Integer idpessoa) {
        this.idpessoa = idpessoa;
    }

    public String getNmpessoa() {
        return nmpessoa;
    }

    public void setNmpessoa(String nmpessoa) {
        this.nmpessoa = nmpessoa;
    }
    public String getDesexo() {
        return desexo;
    }

    public void setDedesexo(String desexo) {
        this.desexo = desexo;
    }    
    public String getNucpf() {
        return nucpf;
    }

    public void setNucpf(String nucpf) {
        this.nucpf = nucpf;
    }

    public String getNurg() {
        return nurg;
    }

    public void setNurg(String nurg) {
        this.nurg = nurg;
    }

    public String getDeendereco() {
        return deendereco;
    }

    public void setDeendereco(String deendereco) {
        this.deendereco = deendereco;
    }

    public String getNmbairro() {
        return nmbairro;
    }

    public void setNmbairro(String nmbairro) {
        this.nmbairro = nmbairro;
    }

    public String getNmcidade() {
        return nmcidade;
    }

    public void setNmcidade(String nmcidade) {
        this.nmcidade = nmcidade;
    }

    public String getNucep() {
        return nucep;
    }

    public void setNucep(String nucep) {
        this.nucep = nucep;
    }
    
    public String getDeemail() {
        return deemail;
    }

    public void setDeemail(String deemail) {
        this.deemail = deemail;
    }

    public String getNutelefone() {
        return nutelefone;
    }

    public void setNutelefone(String nutelefone) {
        this.nutelefone = nutelefone;
    }

    public String getNucelular() {
        return nucelular;
    }

    public void setNucelular(String nucelular) {
        this.nucelular = nucelular;
    }

    public String getNucrm() {
        return nucrm;
    }

    public void setNucrm(String nucrm) {
        this.nucrm = nucrm;
    }

    public String getNmespecialidademedica() {
        return nmespecialidademedica;
    }

    public void setNmespecialidademedica(String nmespecialidademedica) {
        this.nmespecialidademedica = nmespecialidademedica;
    }

    public String getNucrt() {
        return nucrt;
    }

    public void setNucrt(String nucrt) {
        this.nucrt = nucrt;
    }

    public String getNucoren() {
        return nucoren;
    }

    public void setNucoren(String nucoren) {
        this.nucoren = nucoren;
    }

    public PerfilDAO getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(PerfilDAO idperfil) {
        this.idperfil = idperfil;
    }

    @XmlTransient
    public Collection<UsuarioDAO> getUsuarioDAOCollection() {
        return usuarioDAOCollection;
    }

    public void setUsuarioDAOCollection(Collection<UsuarioDAO> usuarioDAOCollection) {
        this.usuarioDAOCollection = usuarioDAOCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpessoa != null ? idpessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaDAO)) {
            return false;
        }
        PessoaDAO other = (PessoaDAO) object;
        if ((this.idpessoa == null && other.idpessoa != null) || (this.idpessoa != null && !this.idpessoa.equals(other.idpessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.PessoaDAO[ idpessoa=" + idpessoa + " ]";
    }
    
}
