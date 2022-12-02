/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Kampus1;

/**
 *
 * @author Firda Amalia
 */
public class Kampus1JpaController implements Serializable {

    public Kampus1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kampus1 kampus1) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kampus1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kampus1 kampus1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kampus1 = em.merge(kampus1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kampus1.getNrp();
                if (findKampus1(id) == null) {
                    throw new NonexistentEntityException("The kampus1 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kampus1 kampus1;
            try {
                kampus1 = em.getReference(Kampus1.class, id);
                kampus1.getNrp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kampus1 with id " + id + " no longer exists.", enfe);
            }
            em.remove(kampus1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kampus1> findKampus1Entities() {
        return findKampus1Entities(true, -1, -1);
    }

    public List<Kampus1> findKampus1Entities(int maxResults, int firstResult) {
        return findKampus1Entities(false, maxResults, firstResult);
    }

    private List<Kampus1> findKampus1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kampus1.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Kampus1 findKampus1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kampus1.class, id);
        } finally {
            em.close();
        }
    }

    public int getKampus1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kampus1> rt = cq.from(Kampus1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
