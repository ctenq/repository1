/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbProcess;

import beans.Kind;
import entity.Content;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author araki
 */
@Stateless
public class ContentDb extends CatchDb{
    public ContentDb(){
        super(Content.class);
    }
    
    //CRUD処理は継承
    
    //--------------------------------------------------------------------------
    //CRUD以外のDB処理
    //すべてのContentのリスト
    public List<Content> getAllFromDb(){
        TypedQuery<Content> q = em.createNamedQuery(Content.QAllContent, Content.class);
        return q.getResultList();
    }
    
    //KindがkindのContentのリスト
    public List<Content> getKindFromDb(Kind kind){
        TypedQuery<Content> q = em.createNamedQuery(Content.QContentKindOf, Content.class);
        q.setParameter("valueOfKind", kind);
        return q.getResultList();
    }
    
    public List<Content> getRecentContentsFromDb(){
        TypedQuery<Content> q = em.createNamedQuery(Content.QContentRegiTimeDesc, Content.class);
        return q.getResultList();
    }
}
