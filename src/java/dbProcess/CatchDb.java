/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbProcess;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author araki
 */
public class CatchDb<V> extends SuperDb {

    public CatchDb(Class entityClass) {
        super(entityClass);
    }

    @Inject
    transient Logger logger;

    public void c(V v) {
        String message, errorM;
        try {
            create(v);
        } catch (EntityExistsException e) {
            message = String.format("■THIS ENTITY ALREADY EXISTS■ : {0}", v.toString());
            errorM = e.getMessage();
            logger.log(Level.SEVERE, message);
            logger.log(Level.SEVERE, errorM);
        } catch (IllegalArgumentException e) {
            message = String.format("■INSTANS IS NOT ENTITY■ : {0}", v.toString());
            errorM = e.getMessage();
            logger.log(Level.SEVERE, message);
            logger.log(Level.SEVERE, errorM);
        } catch (TransactionRequiredException e) {
            message = String.format("■THERE IS NO TRANSACTION■ : {0}", v.toString());
            errorM = e.getMessage();
            logger.log(Level.SEVERE, message);
            logger.log(Level.SEVERE, errorM);
        }
    }
    
    public void r(Object o){
        String message, errorM;
        try {
            read(o);
        } catch (IllegalArgumentException e) {
            message = String.format("■ARGUMENT DOES NOT MATCH ENTITY'S PRIMARY KEY■ : {0}", o.toString());
            errorM = e.getMessage();
            logger.log(Level.SEVERE, message);
            logger.log(Level.SEVERE, errorM);
        }
    }
    
    public void u(V v) {
        String message, errorM;
        try {
            update(v);
        } catch (IllegalArgumentException e) {
            message = String.format("■INSTANS IS NOT AN ENTITY OR IS A REMOVED ENTITY■ : {0}", v.toString());
            errorM = e.getMessage();
            logger.log(Level.SEVERE, message);
            logger.log(Level.SEVERE, errorM);
        } catch (TransactionRequiredException e) {
            message = String.format("■THERE IS NO TRANSACTION■ : {0}", v.toString());
            errorM = e.getMessage();
            logger.log(Level.SEVERE, message);
            logger.log(Level.SEVERE, errorM);
        }
    }
    
    public void d(V v) {
        String message, errorM;
        try {
            delete(v);
        } catch (IllegalArgumentException e) {
            message = String.format("■INSTANS IS NOT AN ENTITY OR IS A DETACHED ENTITY■ : {0}", v.toString());
            errorM = e.getMessage();
            logger.log(Level.SEVERE, message);
            logger.log(Level.SEVERE, errorM);
        } catch (TransactionRequiredException e) {
            message = String.format("■THERE IS NO TRANSACTION■ : {0}", v.toString());
            errorM = e.getMessage();
            logger.log(Level.SEVERE, message);
            logger.log(Level.SEVERE, errorM);
        }
    }
}
