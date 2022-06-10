package project02;

/**
 * 各地点のx座標、y座標、最小コスト、前地点の情報を保存するクラス
 *
 */
public class Point implements Comparable<Point>{
	private int x;
	private int y;
	private int cost;
	private Point beforePoint;
	
	
	/**
	 * 始点から終点までの経路を求める際に使用するコンストラクタ
	 * 
	 * @param x x座標
	 * @param y y座標
	 * @param cost 現地点の最小コスト
	 * @param beforePoint 前地点の情報
	 */
	public Point(int x, int y, int cost, Point beforePoint) {
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.beforePoint = beforePoint;
	}
	
	/**
	 * 始点から全地点までの最小コストを求める際に使用するコンストラクタ
	 * 
	 * @param x x座標
	 * @param y y座標
	 * @param cost 現地点の最小コスト
	 */
	public Point(int x, int y, int cost) {
		this(x, y, cost, null);
	}
	
	
	/**
	 * インスタンスのハッシュ値を返す
	 * 
	 * @return x座標とy座標を文字列連結したものから生成されるハッシュ値
	 */
	@Override
	public int hashCode() {
		return (x + String.valueOf(y)).hashCode();
	}

	/**
	 * 与えられたインスタンスとのx座標が同値かつy座標が同値か判定する
	 * 
	 * @param obj Objectクラスを継承しているクラスのインスタンス
	 * @return 与えられたインスタンスとのx座標が同値かつy座標が同値の場合、trueを返す
	 */
	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		return this.x == other.x && this.y == other.y;
	}

	/**
	 * 与えられたインスタンスとのフィールドのコストが小さい方を、自然順序とする
	 * 
	 * @param point Pointクラスで生成されたインスタンス
	 * @return 与えられたインスタンスのコストとの差を、数値で返す
	 */
	@Override
	public int compareTo(Point point) {
		return this.cost - point.cost;
	}

	/**
	 * x座標を取得するゲッター
	 * 
	 * @return x座標の数値
	 */
	public int getX() {
		return x;
	}

	/**
	 * y座標を取得するゲッター
	 * 
	 * @return y座標の数値
	 */
	public int getY() {
		return y;
	}

	/**
	 * costを取得するゲッター
	 * 
	 * @return 保存されているコストの数値
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * 前の地点のインスタンスを取得するゲッター
	 * 
	 * @return 前の地点のインスタンス
	 */
	public Point getBeforePoint() {
		return beforePoint;
	}
	
}
