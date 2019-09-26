/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.share;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * その他ユーティリティ.
 *
 * @author H.Aoshima
 * @version 1.0
 *
 */
@Slf4j
public class OtherUtil {


    /**
     * String型の時間を分に変換.
     *
     * @param time 変換対象となるString
     * @param pattern 変換パターン
     * @return 分
     */
    public static int convertToMinute(final String time, final String pattern) {

        // 返却値.String型のTime
        int nRtnMinute = -1;

        if (time == null) {
            return nRtnMinute;
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setLenient(false);
            final Date date = format.parse(time);

            format = new SimpleDateFormat(HoshimiruSymbol.FORMAT_TIME_HHMM);
            format.setLenient(false);
            final String timeAfter = format.format(date);

            final int nHour = Integer.parseInt(timeAfter.substring(0, 2));
            final int nMinute = Integer.parseInt(timeAfter.substring(2));

            nRtnMinute = nHour * HoshimiruSymbol.INT_60 + nMinute;

        } catch (final Exception e) {
            // エラーログ
            log.error(e.getMessage(), e);
        }

        return nRtnMinute;
    }

    /**
     * Time型の時間をString型に変換.
     *
     * @param time 変換対象となるDate
     * @param pattern 変換パターン
     * @return String型のTime
     */
    public static String convertToString(final Date time, final String pattern) {

        // 返却値.String型のTime
        String strTime = null;

        if (time == null) {
            return null;
        }
        try {
            final SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setLenient(false);
            strTime = format.format(time);
            strTime = strTime.replace("午前", "AM").replace("午後", "PM");
        } catch (final Exception e) {
            // エラーログ
            log.error(e.getMessage(), e);
        }

        return strTime;
    }


    /**
     * 指定した日付の時間を取得する.
     * （12時間表記）
     *
     * @param time Date
     * @return String
     */
    public static String getHour(final Date time) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        final String val =  StringUtils.leftPad(String.valueOf(calendar.get(Calendar.HOUR)),2,"0");

        // String val = "0" + calendar.get(Calendar.HOUR);
        // val = val.substring(val.length() - 2, val.length());
        // val = val.replace("00", "12");

        return val;
    }


    /**
     * 指定した日付の時間を取得する.
     * （24時間表記）
     *
     * @param time Date
     * @return String
     */
    public static String getHourOfDay(final Date time) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        final String val =  StringUtils.leftPad(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),2,"0");

        // String val = "0" + calendar.get(Calendar.HOUR_OF_DAY);
        // val = val.substring(val.length() - 2, val.length());
        // val = val.replace("00", "12");

        return val;
    }


    /**
     * 指定した日付の分を取得する.
     *
     * @param time Date
     * @return String
     */
    public static String getMinute(final Date time) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        final String val =  StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MINUTE)),2,"0");

        // String val = "0" + calendar.get(Calendar.MINUTE);
        // val = val.substring(val.length() - 2, val.length());

        return val;
    }


    /**
     * 指定した日付の秒を取得する.
     *
     * @param time Date
     * @return String
     */
    public static String getSecond(final Date time) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        final String val =  StringUtils.leftPad(String.valueOf(calendar.get(Calendar.SECOND)),2,"0");

        // String val = "0" + calendar.get(Calendar.SECOND);
        // val = val.substring(val.length() - 2, val.length());

        return val;
    }


    /**
     * 指定した日付の午前・午後を取得する.
     *
     * @param time Date
     * @return String
     */
    public static String getMorningAfternoon(final Date time) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        final int amorpm = calendar.get(Calendar.AM_PM);

        if (amorpm == 0) {
            return "AM";
        } else {
            return "PM";
        }
    }
}
