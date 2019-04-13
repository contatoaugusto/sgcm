/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.facade;

import br.com.sgcm.dao.MedicoagendatrabalhoDAO;
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
public class MedicoagendatrabalhoDAOFacade extends AbstractFacade<MedicoagendatrabalhoDAO> {

    @PersistenceContext(unitName = "sgcmPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if (em == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("sgcmPU");
            em = factory.createEntityManager();
        }
        return em;
    }

    public MedicoagendatrabalhoDAOFacade() {
        super(MedicoagendatrabalhoDAO.class);
    }
    
    public List<MedicoagendatrabalhoDAO> findByMedico(int idMedico) throws NoResultException {

        getEntityManager();
        try {
            return em.createNamedQuery("MedicoagendatrabalhoDAO.findByMedico").setParameter("idmedico", idMedico).getResultList();
        } catch (NoResultException e) {
            throw new NoResultException("Médico " + idMedico + " não encontrado");
        }
    }
}
