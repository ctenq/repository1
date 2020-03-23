/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import beans.Kind;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author araki
 */
@NamedQueries({
    @NamedQuery(name=MyMethod.QAllMyMethod, query="SELECT c FROM MyMethod c"),
    @NamedQuery(name=MyMethod.DAllMyMethod, query="DELETE FROM MyMethod c")
})
@Entity
public class MyMethod implements Serializable{
    public static final String QAllMyMethod = "QAllMyMethod";
    public static final String DAllMyMethod = "DAllMyMethod";
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Kind kind;
    private String kindName;
    //1方向OneToMany(MyMethodがContentのListを持つ)
    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
    private List<Content> contentList = new ArrayList<>();
    
    
    //--------------------------------------------------------------------------
    //constructor
    public MyMethod(){}

    public MyMethod(Kind kind, String kindName, List<Content> contentList){
        this.kind = kind;
        this.kindName = kindName;
        this.contentList = contentList;
    }
    
    
    //--------------------------------------------------------------------------
    //getter and setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }
}
