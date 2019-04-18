/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.facade;

import br.com.sgcm.dao.PerfilDAO;
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
public class PerfilDAOFacade extends AbstractFacade<PerfilDAO> {

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

    public PerfilDAOFacade() {
        super(PerfilDAO.class);
    }
    
    public PerfilDAO findByNmperfil(String parametro) throws NoResultException{
            //log.debug("Obtendo Usuario com o nome: " + nmLogin);

        getEntityManager();
        PerfilDAO objeto;
        try {
                objeto = (PerfilDAO) em.createNamedQuery("PerfilDAO.findByNmperfil")
                                .setParameter("nmperfil", parametro).getSingleResult();
        }catch (NoResultException e){
                //throw new NoResultException("Usuário "+ nmUsuario + " não encontrado");
                return null;
        }
        return objeto;
    }
}
