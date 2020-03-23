/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import beans.Kind;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author araki
 */
@NamedQueries({
    @NamedQuery(name=Content.QAllContent, query="SELECT c FROM Content c"),
    @NamedQuery(name=Content.QContentKindOf, query="SELECT c FROM Content c WHERE c.kind=:valueOfKind")
})
@Entity
@IdClass(ContentCompKey.class)
public class Content implements Serializable{
    public static final String QAllContent = "QAllContent";
    public static final String QContentKindOf = "QContentKindOf";
    @Id
    private String name;
    @Id
    private String link;
    private String jName;
    @Enumerated(EnumType.STRING)
    private Kind kind;
    
    
    //--------------------------------------------------------------------------
    //constructor
    public Content(){}
    public Content(String name, String link, String jName, Kind kind){
        this.name = name;
        this.link = link;
        this.jName = jName;
        this.kind = kind;
    }
    
    
    //--------------------------------------------------------------------------
    //getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public String getjName() {
        return jName;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }
}
