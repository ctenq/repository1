/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.math;

/**
 *
 * @author araki
 */
public class Mstl {

    int size;
    Double[][] list;
    Double[] result;
    String message;
    int stopLoop = 0;
    int judge;

    public Mstl(int size, Double[][] list, Double[] result) {
        this.size = size;
        this.list = list;
        this.result = result;
    }

    public void swap2dArray(int i, int j, int k, int l) {
        double temp = list[i][j];
        list[i][j] = list[k][l];
        list[k][l] = temp;
    }

    //拡大行列と、係数行列それぞれの階数を求める
    public int[] rank() {
        int[] rank = {0, 0};
        boolean isZero = true;

        //係数行列のi行目について、要素がすべて0かそうでないかを調べる
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (list[i][j] != 0) {
                    isZero = false;
                    break;
                }
            }

            //0でない要素があれば係数行列の階数をインクリメント
            if (!isZero) {
                rank[0]++;
                isZero = true;
            }
        }

        //同様に拡大行列について
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size; j++) {
                if (list[i][j] != 0) {
                    isZero = false;
                    break;
                }
            }

            if (!isZero) {
                rank[1]++;
                isZero = true;
            }
        }

        return rank;
    }

    //拡大行列と係数行列の階数による解の場合分け
    public int solJudge() {
        int[] rank = rank();
        if (rank[0] == rank[1]) {
            return 1;
        } else if (rank[0] < rank[1]) {
            return 0;
        } else if (rank[0] == rank[1] && rank[1] < size) {
            return -1;
        } else {
            return -2;
        }
    }

    public Mstl_ans gauss() {
        //前進消去を行う
        //i行目について
        for (int i = 0; i < size; i++) {

            //拡大行列の右端から操作を行う
            for (int j = size; j >= i; j--) {

                /*最終的には係数行列の対角要素をすべて1にしたいので、対角要素が0でなければ
				 i行の要素をi行i列目の値で割っていく*/
                if (list[i][i] != 0) {
                    list[i][j] = list[i][j] / list[i][i];

                    /*そうでなければ、i行目の対角要素が0とならないように、他の行とi行目を交換
					 割る数をなるべく大きいものにして浮動小数点の丸め誤差による誤差を減らす*/
                } else {
                    int rowNum = 0;
                    int columNum = 0;

                    for (int m = i; m < size - 1; m++) {
                        if (Math.abs(list[m][i]) < Math.abs(list[m + 1][i])) {
                            rowNum = m + 1;
                        }
                    }

                    for (columNum = 0; columNum < size + 1; columNum++) {
                        swap2dArray(i, columNum, rowNum, columNum);
                    }
                    j++;
                }

                stopLoop++;
                if (stopLoop > 10000000) {
                    judge = -2;
                    break;
                }
            }

            /*最終的には下三角行列を作りたいのでi行目の操作が終わった後、i行目より下の行に
			 以下の計算を行うことで、i列目の対角要素より下の要素を0にする*/
            for (int h = i + 1; h < size; h++) {
                for (int n = size; n >= i; n--) {
                    list[h][n] = list[h][n] - list[i][n] * list[h][i];
                }
            }

            if (judge != -2) {
                judge = solJudge();
                if (judge != 1) {
                    break;
                }
            }
        }

        //後退代入を行う
        if (judge == 1) {
            double numih;

            for (int i = size - 1; i >= 0; i--) {
                for (int h = i - 1; h >= 0; h--) {
                    numih = list[h][i];
                    for (int j = 0; j <= size; j++) {
                        list[h][j] = list[h][j] - list[i][j] * numih;
                    }
                }

                result[i] = list[i][size];
            }
        }

        Mstl_ans ans = new Mstl_ans(null, null, false);
        switch (judge) {
            case 1:
                message = "解の組み合わせはただ一つ存在";
                ans.setResult(result);
                ans.setMessage(message);
                ans.setB(true);
                break;
            case 0:
                message = "解は存在しない";
                ans.setMessage(message);
                break;
            case -1:
                message = "解は複数存在";
                ans.setMessage(message);
                break;
            default:
                message = "エラー";
                ans.setMessage(message);
                break;
        }

        return ans;
    }
}
