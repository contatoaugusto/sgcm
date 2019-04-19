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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Exameresultado.findAll", query = "SELECT e FROM Exameresultado e")
    , @NamedQuery(name = "Exameresultado.findByIdexameresultado", query = "SELECT e FROM Exameresultado e WHERE e.idexameresultado = :idexameresultado")
    , @NamedQuery(name = "Exameresultado.findByDtexameresultado", query = "SELECT e FROM Exameresultado e WHERE e.dtexameresultado = :dtexameresultado")
    , @NamedQuery(name = "Exameresultado.findByDeobservacao", query = "SELECT e FROM Exameresultado e WHERE e.deobservacao = :deobservacao")})
public class ExameresultadoDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idexameresultado;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtexameresultado;
    @Size(max = 1000)
    private String deobservacao;
    @Lob
    private byte[] imgexameresultado;
    @JoinColumn(name = "idexamepedido", referencedColumnName = "idexamepedido")
    @ManyToOne(optional = false)
    private ExamepedidoDAO idexamepedido;

    public ExameresultadoDAO() {
    }

    public ExameresultadoDAO(Integer idexameresultado) {
        this.idexameresultado = idexameresultado;
    }

    public ExameresultadoDAO(Integer idexameresultado, Date dtexameresultado) {
        this.idexameresultado = idexameresultado;
        this.dtexameresultado = dtexameresultado;
    }

    public Integer getIdexameresultado() {
        return idexameresultado;
    }

    public void setIdexameresultado(Integer idexameresultado) {
        this.idexameresultado = idexameresultado;
    }

    public Date getDtexameresultado() {
        return dtexameresultado;
    }

    public void setDtexameresultado(Date dtexameresultado) {
        this.dtexameresultado = dtexameresultado;
    }

    public String getDeobservacao() {
        return deobservacao;
    }

    public void setDeobservacao(String deobservacao) {
        this.deobservacao = deobservacao;
    }

    public byte[] getImgexameresultado() {
        return imgexameresultado;
    }

    public void setImgexameresultado(byte[] imgexameresultado) {
        this.imgexameresultado = imgexameresultado;
    }

    public ExamepedidoDAO getIdexamepedido() {
        return idexamepedido;
    }

    public void setIdexamepedido(ExamepedidoDAO idexamepedido) {
        this.idexamepedido = idexamepedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexameresultado != null ? idexameresultado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExameresultadoDAO)) {
            return false;
        }
        ExameresultadoDAO other = (ExameresultadoDAO) object;
        if ((this.idexameresultado == null && other.idexameresultado != null) || (this.idexameresultado != null && !this.idexameresultado.equals(other.idexameresultado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sgcm.dao.Exameresultado[ idexameresultado=" + idexameresultado + " ]";
    }
    
}
