/*
 * タイトル：星みるプロジェクト.
 * 説明    ：
 * 著作権  ：Copyright(c) 2016 LineDesign
 * 会社名  ：株式会社 LineDesign
 * 変更履歴：2016.01.01
 *         ：新規登録
 */
package jp.linedesign.hoshimiru.dwr;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.linedesign.hoshimiru.helper.hoshimiru.AstroCalendar;
import jp.linedesign.hoshimiru.helper.hoshimiru.AstroCalendarData;
import jp.linedesign.hoshimiru.share.AbsBaseDwr;
import jp.linedesign.utility.CurrentDateAddition;

/**
 * 月と暦情報取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class AstroCalendarAjax extends AbsBaseDwr {

    /** Log. */
    private static Logger log = Logger.getLogger(TwitterAjax.class.getName());


    @Override
    protected void setClazz() {

        this.clazz = AstroCalendarAjax.class;
    }


    /**
     * 月と暦情報を取得する.
     * @param gmtSecond int
     * @param lat String
     * @param lng String
     * @return String
     */
    public String getStatus(final int gmtSecond, final String lat, final String lng) {

        // 返却値
        String ret = null;

        try {

            this.execBefore();

            final Calendar cal = CurrentDateAddition.addSecond((gmtSecond));

            List <AstroCalendarData> astroCalendarList = AstroCalendar.execute(cal.getTime());

            final StringBuffer sb = new StringBuffer();
            if (astroCalendarList.size() > 0 && astroCalendarList.get(0) != null) {
                sb.append("今日は" + astroCalendarList.get(0).getContent() + "です。");
            } else {
                sb.append("特にないです。");
            }

            ret = sb.toString();

            this.execAfter();

        } catch (final Exception e) {
            log.log(Level.WARNING, e.toString());
        }

        return ret;
    }
}
