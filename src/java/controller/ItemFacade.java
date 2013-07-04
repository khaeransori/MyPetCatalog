/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Item;

/**
 *
 * @author Khaer Ansori
 */
@Stateless
public class ItemFacade implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "MyPetCatalogPU")
    private EntityManager em;
    
    public List<Item> findAll() {
        return em.createQuery("select object(o) from Item as o").getResultList();
    }
    
    public List<Item> findRange(int maxResults, int firstResult) {
        Query q = em.createQuery("select object(o) from Item as o");
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        return q.getResultList();
    }
    
    public int getItemCount() {
        return ((Long) em.createQuery("select count(o) from Item as o").getSingleResult()).intValue();
    }
}
