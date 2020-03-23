/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author araki
 */
public class Labyrinth {

    boolean[][] seen;
    boolean[][] seen2;
    int row;
    int column;
    int st_r;
    int st_c;
    int gl_r;
    int gl_c;
    int nodes;
    boolean[][] field;
    int[][] route;
    int[][] distance;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    List<L_Edge>[][] nodeList;
    
    public Labyrinth(int row, int column, int st_r, int st_c, int gl_r, int gl_c, int nodes, boolean field[][], int[][] st_gl) {
        this.row = row;
        this.column = column;
        this.st_r = st_r;
        this.st_c = st_c;
        this.gl_r = gl_r;
        this.gl_c = gl_c;
        this.nodes = nodes;
        this.field = field;
        this.seen = new boolean[row][column];
        this.distance = new int[row][column];
        this.route = new int[row][column];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                route[i][j] = st_gl[i][j];
            }
        }
        
        nodeList = new List[row][column];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                nodeList[i][j] = new ArrayList<>();
            }
        }
    }
    
    
    //無向グラフ生成
    public void dfs(int py, int px){
        seen[py][px] = true;
        for(int i = 0; i < 4; i++){
            int y = py + dy[i];
            int x = px + dx[i];
            if(x < 0 || y < 0 || x >= column || y >= row) continue;
            if(!field[y][x]) continue;
            
            nodeList[py][px].add(new L_Edge(py, px, y, x));
            nodeList[y][x].add(new L_Edge(y, x, py, px));
            if(seen[y][x]) continue;
            dfs(y, x);
        }
        
    }
    
    public int[][] dijkstra(){
        dfs(st_r, st_c);
        
        
        //----------------------------------------------------------------------
        //スタートから各ノードまでの最短距離を求める
        //----------------------------------------------------------------------
        //各点への最短距離をMAXに設定
        for(int[] array : distance){
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        //スタートへの最短距離はゼロ
        distance[st_r][st_c] = 0;
        
        //seenを初期化
        seen = new boolean[row][column];
        //
        Deque<List<L_Edge>> que = new ArrayDeque<>();
        que.push(nodeList[st_r][st_c]);
        
        //スタートから各ノードまでの最短距離をdistance配列に記録
        while(!que.isEmpty()){
            for(L_Edge edge : que.pop()){
                seen[edge.y][edge.x] = true;
                //distanceの値を最小値に更新
                if(distance[edge.y][edge.x] + 1 < distance[edge.connecty][edge.connectx]){
                    distance[edge.connecty][edge.connectx] = distance[edge.y][edge.x] + 1;
                }
                //訪問済みならばターゲットのノードを探索対象にしない
                if(seen[edge.connecty][edge.connectx]) continue;
                if(edge.connecty == gl_r && edge.connectx == gl_c) continue;
                que.push(nodeList[edge.connecty][edge.connectx]);
            }
        }
        
        /*
        for(int[] d : distance){
            for(int f : d){
                System.out.print(f + " ");
            }System.out.println("/");
        }
        */
        
        
        
        //----------------------------------------------------------------------
        //スタートからゴールまでの最短ルートを求める
        //----------------------------------------------------------------------
        seen = new boolean[row][column];
        //ゴールノードから、隣接しているノードのうちdistance値が小さいものをたどって
        //最短経路を復元する
        
        //そのためゴールノードをプッシュ
        que.push(nodeList[gl_r][gl_c]);
        Deque<Integer[]> mindist = new ArrayDeque<>();
        //通過してきた経路を座標で保存したい
        Integer[] gl = {gl_r, gl_c};
        mindist.push(gl);
        
        while(!que.isEmpty()){
            int min = Integer.MAX_VALUE;
            int x = 0;
            int y = 0;
            for(L_Edge edge : que.pop()){
                //注目しているノードと隣接したノードのうち、distance値が小さいもの
                //を選びその座標を記録
                seen[edge.y][edge.x] = true;
                if(min > distance[edge.connecty][edge.connectx] || 
                        distance[edge.y][edge.x] > distance[edge.connecty][edge.connectx]){
                    y = edge.connecty;
                    x = edge.connectx;
                    min = distance[y][x];
                    if(edge.y == st_r && edge.x == st_c) break;
                }
            }
            if(y == st_r && x == st_c) break;
            if(seen[y][x]) continue;
            que.push(nodeList[y][x]);
            Integer [] n = {y, x};
            mindist.push(n);
        }
        
        //boolean型の二重配列で経路を表す
        for(Integer[] r : mindist){
            route[r[0]][r[1]] = 1;
        }
        
        return route;
    }
}