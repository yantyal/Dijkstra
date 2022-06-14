package project02;

import java.util.Scanner;

/**
 * ユーザに操作を求めるクラス
 *
 */
public class User {
    
    /**
     * 入力された文字列が、数値の条件(numberMinCondition)以上の整数の場合、その値を数値に変換して返す。
     * 
     * @param scan 入力を求めるためのスキャナー
     * @param message 入力を求める内容を表す
     * @param numberMinCondition 求める数値の下限
     * @return 求める数値の下限(numberCondition)以上の整数
     * @exception NumberFormatException 入力された文字列を数値に変換できない場合に発生する。
     */
    public int inputNumber(Scanner scan, String message, int numberMinCondition) {
        int number = 0;
        while (true) {
            System.out.print(message + " >>");
            String strNumber = scan.nextLine();
            try {
                number = Integer.parseInt(strNumber);
            } catch (NumberFormatException e) {
                System.out.println(numberMinCondition + "以上の整数で入力してください。");
                continue;
            }

            if (number >= numberMinCondition) {
                break;
            }
            System.out.println(numberMinCondition + "以上の整数で入力してください。");
        }
        return number;
    }
    
    /**
     * 数値の下限(numberMinCondition)以上、求める数値の上限(numberMaxCondition)以下の整数を返す
     * 
     * @param scan 入力を求めるためのスキャナー
     * @param message 入力を求める内容を表す
     * @param numberMinCondition 求める数値の下限
     * @param numberMaxCondition 求める数値の上限
     * @return 求める数値の下限(numberMinCondition)以上、求める数値の上限(numberMaxCondition)以下の整数
     */
    public int inputPointNumber(Scanner scan, String message, int numberMinCondition, int numberMaxCondition) {
        int number = 0;
        while (true) {
            number = inputNumber(scan, message, numberMinCondition);

            if (number <= numberMaxCondition) {
                break;
            } else {
                System.out.println(numberMaxCondition + "以下の整数を入力してください。");
            }
        }
        return number;
    }

}
