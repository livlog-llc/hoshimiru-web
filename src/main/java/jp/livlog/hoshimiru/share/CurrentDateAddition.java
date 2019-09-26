/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.share;

import java.util.Calendar;

/**
 * 現在日時からの加減算クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class CurrentDateAddition {

    /**
     * コンストラクタ.
     */
    private CurrentDateAddition() {

    }


    /**
     * 現在の日付・時刻から指定の【年数】を加算・減算した結果を返します.
     * @param addYera 加算・減算する年数
     * @return    計算後の Calendar インスタンス.
     */
    public static Calendar addYera(final int addYera) {

        return add(null, addYera, 0, 0, 0, 0, 0, 0);
    }


    /**
     * 現在の日付・時刻から指定の【月数】を加算・減算した結果を返します.
     * @param addMonth 加算・減算する月数
     * @return    計算後の Calendar インスタンス.
     */
    public static Calendar addMonth(final int addMonth) {

        return add(null, 0, addMonth, 0, 0, 0, 0, 0);
    }


    /**
     * 現在の日付・時刻から指定の【日数】を加算・減算した結果を返します.
     * @param addDate 加算・減算する日数
     * @return    計算後の Calendar インスタンス.
     */
    public static Calendar addDate(final int addDate) {

        return add(null, 0, 0, addDate, 0, 0, 0, 0);
    }


    /**
     * 現在の日付・時刻から指定の【時間】を加算・減算した結果を返します.
     * @param addHour 加算・減算する時間
     * @return    計算後の Calendar インスタンス.
     */
    public static Calendar addHour(final int addHour) {

        return add(null, 0, 0, 0, addHour, 0, 0, 0);
    }


    /**
     * 現在の日付・時刻から指定の【分】を加算・減算した結果を返します.
     * @param addMinute 加算・減算する分
     * @return    計算後の Calendar インスタンス.
     */
    public static Calendar addMinute(final int addMinute) {

        return add(null, 0, 0, 0, 0, addMinute, 0, 0);
    }


    /**
     * 現在の日付・時刻から指定の【秒】を加算・減算した結果を返します.
     * @param addSecond 加算・減算する秒
     * @return    計算後の Calendar インスタンス.
     */
    public static Calendar addSecond(final int addSecond) {

        return add(null, 0, 0, 0, 0, 0, addSecond, 0);
    }


    /**
     * 現在の日付・時刻から指定の時間量を加算・減算した結果を返します.
     * 年、月、日、時間、分、秒、ミリ秒の各時間フィールドに対し、
     * 任意の時間量を設定できます.
     * たとえば、現在の日付時刻から 10 日前を計算する場合は以下となります.
     * Calendar cal = add(null,0,0,-10,0,0,0,0);
     *
     * 各時間フィールドの値がその範囲を超えた場合、次の大きい時間フィールドが
     * 増分または減分されます.
     * たとえば、以下では1時間と5分進めることになります.
     * Calendar cal = add(null,0,0,0,0,65,0,0);
     *
     * 各時間フィールドに設定する数量が0の場合は、現在の値が設定されます.
     * java.util.GregorianCalendarの内部処理では以下の分岐を行っている.
     *     if (amount == 0) {
     *         return;
     *     }
     *
     * @param pCal 日付時刻の指定があればセットする.
     *     nullの場合、現在の日付時刻で新しいCalendarインスタンスを生成する.
     * @param addYera 加算・減算する年数
     * @param addMonth 加算・減算する月数
     * @param addDate 加算・減算する日数
     * @param addHour 加算・減算する時間
     * @param addMinute 加算・減算する分
     * @param addSecond 加算・減算する秒
     * @param addMillisecond 加算・減算するミリ秒
     * @return    計算後の Calendar インスタンス.
     */
    public static Calendar add(
            final Calendar pCal,
            final int addYera,
            final int addMonth,
            final int addDate,
            final int addHour,
            final int addMinute,
            final int addSecond,
            final int addMillisecond) {

        Calendar cal = pCal;
        if (cal == null) {
            cal = Calendar.getInstance();
        }
        cal.add(Calendar.YEAR, addYera);
        cal.add(Calendar.MONTH, addMonth);
        cal.add(Calendar.DATE, addDate);
        cal.add(Calendar.HOUR_OF_DAY, addHour);
        cal.add(Calendar.MINUTE, addMinute);
        cal.add(Calendar.SECOND, addSecond);
        cal.add(Calendar.MILLISECOND, addMillisecond);
        return cal;
    }

}
