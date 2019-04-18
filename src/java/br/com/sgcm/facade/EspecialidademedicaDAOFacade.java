/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.facade;

import br.com.sgcm.dao.EspecialidademedicaDAO;
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
public class EspecialidademedicaDAOFacade extends AbstractFacade<EspecialidademedicaDAO> {

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

    public EspecialidademedicaDAOFacade() {
        super(EspecialidademedicaDAO.class);
    }
    
    public EspecialidademedicaDAO findByNmEspecialidademedica(String parametro) throws NoResultException{
            //log.debug("Obtendo Usuario com o nome: " + nmLogin);

        getEntityManager();
        EspecialidademedicaDAO objeto;
        try {
                objeto = (EspecialidademedicaDAO) em.createNamedQuery("EspecialidademedicaDAO.findByNmespecialidademedica")
                                .setParameter("nmespecialidademedica", parametro).getSingleResult();
        }catch (NoResultException e){
                //throw new NoResultException("Usuário "+ nmUsuario + " não encontrado");
                return null;
        }
        return objeto;
    }
}
