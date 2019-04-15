/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.facade;

import br.com.sgcm.dao.PessoaDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Antonio Augusto Teixeira
 */
@Stateless
public class PessoaDAOFacade extends AbstractFacade<PessoaDAO> {

    @PersistenceContext(unitName = "sgcmPU")
    private EntityManager em;

    public PessoaDAOFacade() {
        super(PessoaDAO.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("sgcmPU");
            em = factory.createEntityManager();
        }
        return em;
    }

    public List<PessoaDAO> findByPerfil(int idperfil) throws NoResultException {

        getEntityManager();
        try {
            return em.createNamedQuery("PessoaDAO.findByPerfil").setParameter("idperfil", idperfil).getResultList();
        } catch (NoResultException e) {
            throw new NoResultException("Perfil " + idperfil + " não encontrado");
        }
    }

    public PessoaDAO findByCPF(String nuCPF) throws NoResultException {

        getEntityManager();
        try {
            return (PessoaDAO) em.createNamedQuery("PessoaDAO.findByNucpf").setParameter("nucpf", nuCPF).getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("CPF " + nuCPF + " não encontrado");
        }
    }
}
