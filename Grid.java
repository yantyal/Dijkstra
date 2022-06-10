package project02;

import java.util.Random;


/**
 * 格子状の重みグラフを作成、表示するクラス
 *
 */
public class Grid {
	
	private int height;
	private int width;
	private int[][] gridGraph;
	
	
	/**
	 * @param height
	 * @param width
	 * @param gridGraph
	 */
	public Grid(int height, int width, int[][] gridGraph) {
		this.height = height;
		this.width = width;
		this.gridGraph = gridGraph;
	}
	/**
	 * 重みグラフのコンストラクタ
	 * 
	 * @param height 重みグラフの高さ
	 * @param width 重みグラフの幅
	 */
	public Grid(int height, int width) {
		this(height, width, null);
	}
	
	/**
	 * 重みグラフの高さを取得するゲッター
	 * 
	 * @return 重みグラフの高さ
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 重みグラフの幅を取得するゲッター
	 * 
	 * @return 重みグラフの幅
	 */
	public int getWidth() {
		return width;
	}

	
	/**
	 * 重みグラフを表す二次元配列を取得するゲッター
	 * 
	 * @return 重みグラフを表す二次元配列
	 */
	public int[][] getGridGraph() {
		return gridGraph;
	}



	/**
	 * 任意の高さと幅から格子状の重みグラフ(二次元配列)を作成する
	 * 
	 */
	public void generateGridGraph() {
		
		int[][] gridGraph = new int[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// -5以上10以下の乱数を重みとする
				gridGraph[i][j] = new Random().nextInt(16) - 5;
			}
		}
		this.gridGraph = gridGraph;
	}
	
	/**
	 * 作成された格子状の重みグラフの内容を表示する
	 * 
	 */
	public void showGridGraph() {
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// 重みがある(1以上)の地点のコストを表示する
				if (gridGraph[i][j] > 0) {
					System.out.printf("%3d", gridGraph[i][j]);
				} else {
					// 到達不可能な地点をMで表す
					System.out.printf("%3s", "M");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * 始点からすべての地点への最小コストを表示する
	 * 
	 * @param start 始点のインスタンス
	 * @param costs 始点からすべての地点への最小コストが格納された二次元配列
	 */
	public void showCostsGraph(Point start, int[][] costs) {
		
		for (int i = 0; i < height; i++) {
	         for (int j = 0; j < width; j++) {
	        	 if (i == start.getY() && j == start.getX()) {
	        		 System.out.printf("%3s", "S");
	        	 } else if (costs[i][j] == 0) {
	        		 System.out.printf("%3s", "M");
	        	 } else {
	        		 System.out.printf("%3d", costs[i][j]);
	        	 }
	         }
	         System.out.println();
	     }
	}

}
