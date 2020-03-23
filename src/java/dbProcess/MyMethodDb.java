/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbProcess;

import entity.MyMethod;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author araki
 */

@Stateless
public class MyMethodDb extends CatchDb{
    public MyMethodDb(){
        super(MyMethod.class);
    }
    
    //CRUDは継承
    
    //--------------------------------------------------------------------------
    //CRUD以外のDB処理
    public List<MyMethod> getAllFromDb(){
        TypedQuery<MyMethod> q = em.createNamedQuery(MyMethod.QAllMyMethod, MyMethod.class);
        return q.getResultList();
    }
    
    //cascadeしてあるのでContentも消す
    public void deleteAllMyMethod(){
        TypedQuery<MyMethod> q = em.createNamedQuery(MyMethod.DAllMyMethod, MyMethod.class);
        q.executeUpdate();
    }
}
