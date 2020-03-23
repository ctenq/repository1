/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dbProcess.ContentDb;
import dbProcess.MyMethodDb;
import entity.Content;
import entity.MyMethod;
import java.util.Arrays;
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
public class IndexBb {
    //最初に表示するタブのための初期化
    private Kind kind = Kind._ALGORITHM;
    private Kind[] kinds = Kind.values();
    
    private boolean[] disabledTab;
    {
        disabledTab = new boolean[kinds.length];
        disabledTab[0] = true;
    }
    
    @EJB
    ContentDb cdb;
    @EJB
    MyMethodDb mmdb;
    
    //--------------------------------------------------------------------------
    //クリックされたtabのrenderedを無効にする
    public void disableTabClick(int i){
        Arrays.fill(disabledTab, false);
        disabledTab[i] = true;
    }
    
    
    //--------------------------------------------------------------------------
    //index.xhtmlデータテーブル表示用
    //MyMethod 全取得
    public List<MyMethod> getAllMyMethod(){
        return mmdb.getAllFromDb();
    }
    
    //Content 全取得 (使わないかも)
    public List<Content> getAllContent(){
        return cdb.getAllFromDb();
    }
    
    //Contentフィールドのkind がthis.kind のContentを全取得
    public List<Content> getKindContent(){
        return cdb.getKindFromDb(this.kind);
    }

    
    //--------------------------------------------------------------------------
    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public boolean[] getDisabledTab() {
        return disabledTab;
    }

    public void setDisabledTab(boolean[] disabledTab) {
        this.disabledTab = disabledTab;
    }

    public Kind[] getKinds() {
        return kinds;
    }

    public void setKinds(Kind[] kinds) {
        this.kinds = kinds;
    }
}
