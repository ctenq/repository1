/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import logic.algorithm.Labyrinth;

/**
 *
 * @author araki
 */
@Named
@ViewScoped
public class LabyrinthBb implements Serializable {

    private boolean field[][];

    //-1:壁 0:道 1:start or goal
    private int st_gl[][];
    private int row = 3;
    private int column = 3;
    private Deque<Integer> stgl = new ArrayDeque<>();
    private boolean view;
    private boolean viewAns;
    private boolean calcButton;
    private int st_gl_count = 0;
    private int nodes = 0;
    private int[][] route;
    private int st_r, st_c, gl_r, gl_c;

    {
        field = new boolean[row][column];
    }

    //最短経路を求める
    public void calc() {
        findSt();
        findGl();
        Labyrinth l = new Labyrinth(row, column, st_r, st_c, gl_r, gl_c, nodes, field, st_gl);
        route = l.dijkstra();
        viewAns = true;
    }

    public void clear() {
        this.row = this.column = 3;
        this.view = this.viewAns = this.calcButton = false;
        this.st_gl_count = this.nodes = 0;
        this.stgl = new ArrayDeque<>();
        this.field = new boolean[row][column];
        this.st_gl = new int[row][column];
    }

    //計算ボタンの表示非表示用
    public void switchCalcButton() {
        calcButton = (st_gl_count >= 2);
    }

    //startの座標取得
    public void findSt() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (st_gl[i][j] == 1) {
                    st_r = i;
                    st_c = j;
                    return;
                }
            }
        }
    }

    //goalの座標取得
    public void findGl() {
        for (int i = row - 1; i >= 0; i--) {
            for (int j = column - 1; j >= 0; j--) {
                if (st_gl[i][j] == 1) {
                    gl_r = i;
                    gl_c = j;
                    return;
                }
            }
        }
    }

    //総ノード数を取得
    public void sumNodes() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (field[i][j]) {
                    nodes++;
                }
            }
        }
    }

    //迷路作成、スタート・ゴール設定切り替え
    public void switchView() {
        view = !view;
        st_gl = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                st_gl[i][j] = (field[i][j]) ? 0 : -1;
            }
        }
    }

    //スタート・ゴール設定で画像の切り替えなど
    public boolean st_gl_bool(int row, int column) {
        return st_gl[row][column] == 1;
    }

    //結果表示用
    public boolean route_bool(int row, int column) {
        return route[row][column] == 1;
    }

    //0:道 と 1:start or goalを切り替え
    public void switch_sg(int row, int column) {
        //start or goalを未設定または片方のみ設定の場合
        if (st_gl_count < 2) {
            //クリックしたのが道の場合
            if (st_gl[row][column] == 0) {
                //道→st or gl
                st_gl[row][column] = 1 - st_gl[row][column];
                stgl.push(row);
                stgl.push(column);
                st_gl_count++;
                //クリックしたのがst or gl
            } else {
                //st or gl→道
                st_gl[row][column] = 1 - st_gl[row][column];
                stgl.pop();
                stgl.pop();
                st_gl_count--;
            }
            //st or glを両方設定済みの場合
        } else {
            //st or glをクリックしたときのみそれを道に戻す
            if (st_gl[row][column] == 1) {
                st_gl[row][column] = 1 - st_gl[row][column];
                stgl.pop();
                stgl.pop();
                st_gl_count--;
            }
        }

        switchCalcButton();
    }

    //壁、道の切り替え
    public void switch_f(int row, int column) {
        field[row][column] = !field[row][column];
    }

    //値を保持してリサイズ
    public void resize(boolean increment) {
        boolean[][] newField = new boolean[row][column];
        if (increment) {
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    newField[i][j] = field[i][j];
                }
            }
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    newField[i][j] = field[i][j];
                }
            }
        }
        field = newField;
    }

    //--------------------------------------------------------------------------
    //迷路のサイズ変更
    public void addRow() {
        if (row < 12) {
            row++;
            resize(true);
        }
    }

    public void addColumn() {
        if (column < 12) {
            column++;
            resize(true);
        }
    }

    public void removeRow() {
        if (row > 3) {
            row--;
            resize(false);
        }
    }

    public void removeColumn() {
        if (column > 3) {
            column--;
            resize(false);
        }
    }

    //--------------------------------------------------------------------------
    //getter and setter
    public boolean[][] getField() {
        return field;
    }

    public void setField(boolean[][] field) {
        this.field = field;
    }

    public int[][] getSt_gl() {
        return st_gl;
    }

    public void setSt_gl(int[][] st_gl) {
        this.st_gl = st_gl;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    public boolean isCalcButton() {
        return calcButton;
    }

    public void setCalcButton(boolean calcButton) {
        this.calcButton = calcButton;
    }

    public int[][] getRoute() {
        return route;
    }

    public void setRoute(int[][] route) {
        this.route = route;
    }

    public boolean isViewAns() {
        return viewAns;
    }

    public void setViewAns(boolean viewAns) {
        this.viewAns = viewAns;
    }
}
