package project02;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * ダイクストラ法を使うクラス
 *
 */
public class Dijkstra {
    
    
    // DXとDYで縦、横、斜めの移動を表す
    // 右、下、左、上、右下、右上、左下、左上
    public static final int[] DX = { 1, 0, -1, 0, 1, 1, -1, -1 };
    public static final int[] DY = { 0, 1, 0, -1, 1, -1, 1, -1 };
    
    
    /**
     * 始点からつながっているすべての地点のコストを持つグラフを返す
     *
     * @param start 始点のインスタンス
     * @param grid 重みグラフのインスタンス
     * @return 重みグラフの高さと幅が一致した二次元配列(始点からの最小コストを格納されている)を持つGridクラスのインスタンス
     */
    public Grid dijkstraAllCost(Point start, Grid grid) {
        
        // 未確定地点を格納する
        PriorityQueue<Point> open = new PriorityQueue<>();
        // 確定地点を格納する
        Set<Point> closed = new HashSet<>();
        // 始点を未確定地点に格納する
        open.add(start);
        // 各地点の最小コストを保存するための二次元配列
        int[][] costs = new int[grid.getHeight()][grid.getWidth()];
        
        // 未確定地点があれば、未確定地点に対する処理を繰り返す
        while (!open.isEmpty()) {
            
            // 未確定地点の中で、最小コストが保存されている地点を取り出す
            Point point = open.poll();
            
            // 重みが0以下に設定されている場合は、通らない
            if (grid.getGridGraph()[point.getY()][point.getX()] <= 0) {
                // 通らない地点の最小コストは、0としておく
                costs[point.getY()][point.getX()] = 0;
                continue;
            }
            
            // 取り出した未確定地点が、確定地点に含まれていれば、処理をしない
            if (closed.contains(point)) {
                continue;
            }
            
            // 取り出した未確定地点を、確定地点に保存する
            closed.add(point);
            
            // 取り出した未確定地点のコストを、保存する
            for (int i = 0; i < grid.getHeight(); i++) {
                if (point.getY() == i) {
                    costs[point.getY()][point.getX()] = point.getCost();
                }
            }
            
            // 縦横斜めへの移動できる未確定地点を探索
            for (int i = 0; i < 8; i++) {
                // 現地点から一度の移動で行くことができる地点のx座標とy座標
                int newX = point.getX() + DX[i];
                int newY = point.getY() + DY[i];
                
                // 二次元配列gridの配列外を指定する場合は、処理をしない
                if (!(0 <= newX && newX < grid.getWidth() && 0 <= newY && newY < grid.getHeight())) {
                    continue;
                }
                // 現地点から一度の移動で行くことができる地点のコストを計算する
                int newCost = point.getCost() + grid.getGridGraph()[newY][newX];
                
                // 未確定地点を格納する
                open.add(new Point(newX, newY, newCost));
            }
        }
        
        return new Grid(grid.getHeight(), grid.getWidth(), costs);
    }
    
    /**
     * 終点までの経路を保持したPointクラスのインスタンスを返す
     * 
     * @param start 始点のインスタンス
     * @param goal 終点のインスタンス
     * @param grid 重みグラフのインスタンス
     * @return 終点のPointインスタンス、または、終点にたどりつかなかった場合はnullを返す
     */
    public Point dijkstra(Point start, Point goal, Grid grid) {
        
        // 未確定地点を格納する
        PriorityQueue<Point> open = new PriorityQueue<>();
        // 確定地点を格納する
        Set<Point> closed = new HashSet<>();
        // 始点をインスタンス化
        open.add(start);
        
        // 未確定地点があれば、未確定地点に対する処理を繰り返す
        while (!open.isEmpty()) {
            
            // 未確定地点の中で、最小コストが保存されている地点を取り出す
            Point point = open.poll();
            
            // ゴール地点にたどりつくと探索終了
            if (point.equals(goal)) {
                return point;
            }
            
            // 重みが0以下に設定されている場合は、通らない
            if (grid.getGridGraph()[point.getY()][point.getX()] <= 0) {
                continue;
            }
            
            // 取り出した未確定地点が、確定地点に含まれていれば、処理をしない
            if (closed.contains(point)) {
                continue;
            }
            
            // 取り出した未確定地点を、確定地点に保存する
            closed.add(point);
            
            // 縦横斜めへの移動できる未確定地点を探索
            for (int i = 0; i < 8; i++) {
                // 現地点から一度の移動で行くことができる地点のx座標とy座標
                int newX = point.getX() + DX[i];
                int newY = point.getY() + DY[i];
                
                // 二次元配列gridの配列外を指定する場合は、処理をしない
                if (!(0 <= newX && newX < grid.getWidth() && 0 <= newY && newY < grid.getHeight())) {
                    continue;
                }
                // 現地点から一度の移動で行くことができる地点のコストを計算する
                int newCost = point.getCost() + grid.getGridGraph()[newY][newX];
                
                // 未確定地点を格納する
                open.add(new Point(newX, newY, newCost, point));
            }
        }
        
        // ゴールにたどりつけなかった場合
        return null;
    }
    
    
    /**
     * 始点から終点までの最短経路状のコストを表す二次元配列を持つGridクラスのインスタンスを返す
     * 
     * @param point 始点から最短経路までの終点の情報を持つPointクラスのインスタンス
     * @param grid 重みグラフを表すGridクラスのインスタンス
     * @return 始点から終点までの最短経路状のコストを表す二次元配列を持つGridクラスのインスタンス
     */
    public Grid dijkstraShortestPath(Point point, Grid grid) {
        
        // 最短経路を格納する二次元配列
        int[][] shortestPath = new int[grid.getHeight()][grid.getWidth()];
        for (int i = 0; i < grid.getHeight(); i++) {
             for (int j = 0; j < grid.getWidth(); j++) {
                 shortestPath[i][j] = 0;
             }
         }
         // 最短経路が保持されていれば、始点までさかのぼる
         while (point != null) {
             // 最短経路上のコストを格納していく
             shortestPath[point.getY()][point.getX()] = point.getCost();
             // 前地点の情報を取り出す
             point = point.getBeforePoint();
         }
         
         return new Grid(grid.getHeight(), grid.getWidth(), shortestPath);
    }
}
