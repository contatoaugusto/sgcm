/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgcm.facade;

import br.com.sgcm.dao.EspecialidademedicaDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
        return em;
    }

    public EspecialidademedicaDAOFacade() {
        super(EspecialidademedicaDAO.class);
    }
    
}
