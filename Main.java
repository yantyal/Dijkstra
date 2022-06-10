package project02;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		 System.out.println("--- ダイクストラ法 ---");
		 System.out.println();
		 System.out.println("任意の高さと幅から格子状の重みグラフを作成する。(頂点の数、各頂点間の距離はランダムに生成されます。)");
		 System.out.println("作成されたグラフの中から任意の始点と終点を選び、始点から終点までの最短距離（単一始点最短経路）を求める。");
		 System.out.println("頂点に重みが存在すれば、縦、横、斜め移動が許可される。");
		 System.out.println();
		 
		 // Userクラス初期化
		 User user = new User();
		 
		 Scanner scan = new Scanner(System.in);
		 
		 // 格子状の重みグラフの高さと幅を求める際の下限
		 int girdNumberMinCondition = 2;
		 
		 System.out.println("格子状の重みグラフの高さと幅を入力してください。");
		 int height = user.inputNumber(scan, "高さ", girdNumberMinCondition);
	     
	     int width = user.inputNumber(scan, "幅", girdNumberMinCondition);
	     System.out.println();
	     
	     // Gridクラス初期化
	     Grid grid = new Grid(height, width);
	     
	     // 任意の高さと幅から作成された二次元配列(格子状の重みグラフ)を生成する
	     grid.generateGridGraph();
	     
	     System.out.println("格子状の重みグラフが生成されました。");
	     System.out.println();
	     // 作成された格子状の重みグラフを表示する
	     grid.showGridGraph();
	     System.out.println();
	     
	     // 始点と終点の入力求める際の数値の下限
	     int pointNumberMinCondition = 0;
	     
	     // 始点と終点のx座標の数値の上限
	     int pointXNumberMaxCondition = grid.getWidth() - 1;
	     String messageX = "x座標(" + pointNumberMinCondition + "以上" + pointXNumberMaxCondition + "以下)";
	     
	     // 始点と終点のy座標の数値の上限
	     int pointYNumberMaxCondition = grid.getHeight() - 1;
	     String messageY = "y座標(" + pointNumberMinCondition + "以上" + pointYNumberMaxCondition + "以下)";
	     
	     System.out.println("始点のx座標とy座標を入力してください。");
	     int startX = user.inputPointNumber(scan, messageX, pointNumberMinCondition, pointXNumberMaxCondition);
	     int startY = user.inputPointNumber(scan, messageY, pointNumberMinCondition, pointYNumberMaxCondition);
	     
	     // 始点をインスタンス化
	     Point start = new Point(startX, startY, grid.getGridGraph()[startY][startX]);
	     System.out.println();
	     
	     System.out.println("終点のx座標とy座標を入力してください。");
	     int goalX = grid.getWidth() - 1;
	     int goalY = grid.getWidth() - 1;
	     
	     // 終点は重みをもつ地点とする
	     while (true) {
	    	 goalX = user.inputPointNumber(scan, messageX, pointNumberMinCondition, pointXNumberMaxCondition);
		     goalY = user.inputPointNumber(scan, messageY, pointNumberMinCondition, pointYNumberMaxCondition);
		     if (grid.getGridGraph()[goalY][goalX] <= 0) {
		    	 System.out.println();
		    	 System.out.println("終点は重みを持つ地点を選んでください。");
		     } else {
		    	 break;
		     }
	     }
	     
	     // 終点をインスタンス化
	     Point goal = new Point(goalX, goalY, grid.getGridGraph()[goalY][goalX]);
	     System.out.println();
	     
	     // Dijkstraクラス初期化
	     Dijkstra dijkstra = new Dijkstra();
	     
	     // 始点から各地点への最小コストを格納する二次元配列
	     int[][] costs = dijkstra.dijkstraAllCost(start, grid);
	     
	     System.out.println("始点からたどり着くことができる地点の最小コストを表示する。");
	     System.out.println();
	     // 始点からたどり着くことができる地点の最小コストを表示する
	     grid.showCostsGraph(start, costs);
	     System.out.println();
	     
	     // 終点までたどり着いた場合の最短経路を保持する
	     Point point = dijkstra.dijkstra(start, goal, grid);
	     
	     // 始点から終点までの最短経路状のコストを格納する二次元配列
	     int[][] shortestPath = dijkstra.dijkstraShortestPath(point, grid);
	     
	     System.out.println("始点から終点までたどり着くことができた場合、最短経路を表示する。");
	     System.out.println("※終点までたどり着けなかった場合は、始点(S)のみ表示される。");
	     System.out.println();
	     
	     // 始点から終点までのコストの推移を表示する
	     grid.showCostsGraph(start, shortestPath);
	}
}