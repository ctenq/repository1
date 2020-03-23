/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbProcess;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author araki
 */
//継承用CRUD処理
public abstract class SuperDb<E> {
    protected Class<E> entityClass;
    @PersistenceContext
    protected EntityManager em;
    
    //--------------------------------------------------------------------------
    //constructor
    public SuperDb(Class<E> entityClass){
        this.entityClass = entityClass;
    }
    
    //--------------------------------------------------------------------------
    //CRUD
    public void create(E e) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException{
        em.persist(e);
    }
     
    public E read(Object key) throws IllegalArgumentException{
        return em.find(entityClass, key);
    }
        
    public void update(E e) throws IllegalArgumentException, TransactionRequiredException{
        em.merge(e);
    }
   
    public void delete(E e) throws IllegalArgumentException, TransactionRequiredException{
        em.remove(em.merge(e));
    }
}
