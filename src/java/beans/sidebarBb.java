/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dbProcess.ContentDb;
import entity.Content;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author araki
 */
@Named
@RequestScoped
public class sidebarBb {
    @EJB
    ContentDb cdb;
    
    public List<Content> getRecentContents(){
        List<Content> list = cdb.getRecentContentsFromDb();
        return (list.size() < 3)? list : list.subList(0, 3);
    }
    
    public String getDateString(Content c){
        return new SimpleDateFormat("yyyy/MM/dd").format(c.getRegiTime().getTime());
    }
}
