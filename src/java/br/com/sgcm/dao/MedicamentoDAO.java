/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.dao;

import java.io.Serializable;
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
@Table(name = "medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM MedicamentoDAO m")
    , @NamedQuery(name = "Medicamento.findByIdmedicamento", query = "SELECT m FROM MedicamentoDAO m WHERE m.idmedicamento = :idmedicamento")
    , @NamedQuery(name = "Medicamento.findByNuregistro", query = "SELECT m FROM MedicamentoDAO m WHERE m.nuregistro = :nuregistro")
    , @NamedQuery(name = "Medicamento.findByNmtecnico", query = "SELECT m FROM MedicamentoDAO m WHERE m.nmtecnico = :nmtecnico")
    , @NamedQuery(name = "Medicamento.findByCodclasserisco", query = "SELECT m FROM MedicamentoDAO m WHERE m.codclasserisco = :codclasserisco")
    , @NamedQuery(name = "Medicamento.findByNmcomercial", query = "SELECT m FROM MedicamentoDAO m WHERE m.nmcomercial = :nmcomercial")
    , @NamedQuery(name = "Medicamento.findByNmdetentorregistrocadastro", query = "SELECT m FROM MedicamentoDAO m WHERE m.nmdetentorregistrocadastro = :nmdetentorregistrocadastro")
    , @NamedQuery(name = "Medicamento.findByNmfabricante", query = "SELECT m FROM MedicamentoDAO m WHERE m.nmfabricante = :nmfabricante")
    , @NamedQuery(name = "Medicamento.findByNmpaisfabricante", query = "SELECT m FROM MedicamentoDAO m WHERE m.nmpaisfabricante = :nmpaisfabricante")
    , @NamedQuery(name = "Medicamento.findByDtpublicacaoregistrocadastro", query = "SELECT m FROM MedicamentoDAO m WHERE m.dtpublicacaoregistrocadastro = :dtpublicacaoregistrocadastro")
    , @NamedQuery(name = "Medicamento.findByDevalidaderegistrocadastro", query = "SELECT m FROM MedicamentoDAO m WHERE m.devalidaderegistrocadastro = :devalidaderegistrocadastro")
    , @NamedQuery(name = "Medicamento.findByDtatualizacaodado", query = "SELECT m FROM MedicamentoDAO m WHERE m.dtatualizacaodado = :dtatualizacaodado")})
public class MedicamentoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idmedicamento;
    @Size(max = 20)
    private String nuregistro;
    @Size(max = 300)
    private String nmtecnico;
    @Size(max = 10)
    private String codclasserisco;
    @Size(max = 300)
    private String nmcomercial;
    @Size(max = 300)
    private String nmdetentorregistrocadastro;
    @Size(max = 300)
    private String nmfabricante;
    @Size(max = 100)
    private String nmpaisfabricante;
    @Size(max = 10)
    private String dtpublicacaoregistrocadastro;
    @Size(max = 20)
    private String devalidaderegistrocadastro;
    @Size(max = 45)
    private String dtatualizacaodado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedicamento")
    private Collection<MedicamentoaplicacaoDAO> medicamentoaplicacaoCollection;

    public MedicamentoDAO() {
    }

    public MedicamentoDAO(Integer idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public Integer getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(Integer idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public String getNuregistro() {
        return nuregistro;
    }

    public void setNuregistro(String nuregistro) {
        this.nuregistro = nuregistro;
    }

    public String getNmtecnico() {
        return nmtecnico;
    }

    public void setNmtecnico(String nmtecnico) {
        this.nmtecnico = nmtecnico;
    }

    public String getCodclasserisco() {
        return codclasserisco;
    }

    public void setCodclasserisco(String codclasserisco) {
        this.codclasserisco = codclasserisco;
    }

    public String getNmcomercial() {
        return nmcomercial;
    }

    public void setNmcomercial(String nmcomercial) {
        this.nmcomercial = nmcomercial;
    }

    public String getNmdetentorregistrocadastro() {
        return nmdetentorregistrocadastro;
    }

    public void setNmdetentorregistrocadastro(String nmdetentorregistrocadastro) {
        this.nmdetentorregistrocadastro = nmdetentorregistrocadastro;
    }

    public String getNmfabricante() {
        return nmfabricante;
    }

    public void setNmfabricante(String nmfabricante) {
        this.nmfabricante = nmfabricante;
    }

    public String getNmpaisfabricante() {
        return nmpaisfabricante;
    }

    public void setNmpaisfabricante(String nmpaisfabricante) {
        this.nmpaisfabricante = nmpaisfabricante;
    }

    public String getDtpublicacaoregistrocadastro() {
        return dtpublicacaoregistrocadastro;
    }

    public void setDtpublicacaoregistrocadastro(String dtpublicacaoregistrocadastro) {
        this.dtpublicacaoregistrocadastro = dtpublicacaoregistrocadastro;
    }

    public String getDevalidaderegistrocadastro() {
        return devalidaderegistrocadastro;
    }

    public void setDevalidaderegistrocadastro(String devalidaderegistrocadastro) {
        this.devalidaderegistrocadastro = devalidaderegistrocadastro;
    }

    public String getDtatualizacaodado() {
        return dtatualizacaodado;
    }

    public void setDtatualizacaodado(String dtatualizacaodado) {
        this.dtatualizacaodado = dtatualizacaodado;
    }

    @XmlTransient
    public Collection<MedicamentoaplicacaoDAO> getMedicamentoaplicacaoCollection() {
        return medicamentoaplicacaoCollection;
    }

    public void setMedicamentoaplicacaoCollection(Collection<MedicamentoaplicacaoDAO> medicamentoaplicacaoCollection) {
        this.medicamentoaplicacaoCollection = medicamentoaplicacaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedicamento != null ? idmedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoDAO)) {
            return false;
        }
        MedicamentoDAO other = (MedicamentoDAO) object;
        if ((this.idmedicamento == null && other.idmedicamento != null) || (this.idmedicamento != null && !this.idmedicamento.equals(other.idmedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.MedicamentoDAO[ idmedicamento=" + idmedicamento + " ]";
    }
    
}
