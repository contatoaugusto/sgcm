/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.facade;

import br.com.sgcm.dao.UsuarioDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;

/**
 *
 * @author Antonio Augusto Teixeira
 */
@Stateless
public class UsuarioDAOFacade extends AbstractFacade<UsuarioDAO> {

    @PersistenceContext(unitName = "sgcmPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if (em == null){
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            EntityManager em = session.getEntityManagerFactory().createEntityManager();
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("sgcmPU");
            em = factory.createEntityManager();
        }
        return em;
    }

    public UsuarioDAOFacade() {
        super(UsuarioDAO.class);
    }
    
    public UsuarioDAO findUsuarioByName(String nmUsuario) throws NoResultException{
            //log.debug("Obtendo Usuario com o nome: " + nmLogin);

        getEntityManager();
        UsuarioDAO usuario;
        try {
                usuario = (UsuarioDAO) em.createNamedQuery("UsuarioDAO.findByNmusuario")
                                .setParameter("nmusuario", nmUsuario).getSingleResult();
        }catch (NoResultException e){
                //throw new NoResultException("Usuário "+ nmUsuario + " não encontrado");
                return null;
        }
        return usuario;
    }
    
}
