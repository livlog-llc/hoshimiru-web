/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.dwr;

import java.util.Calendar;
import java.util.List;

import jp.livlog.hoshimiru.helper.hoshimiru.AstroCalendar;
import jp.livlog.hoshimiru.helper.hoshimiru.AstroCalendarData;
import jp.livlog.hoshimiru.share.AbsBaseDwr;
import jp.livlog.hoshimiru.share.CurrentDateAddition;
import lombok.extern.slf4j.Slf4j;

/**
 * 月と暦情報取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Slf4j
public class AstroCalendarAjax extends AbsBaseDwr {

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

            final List <AstroCalendarData> astroCalendarList = AstroCalendar.execute(cal.getTime());

            final StringBuffer sb = new StringBuffer();
            if (astroCalendarList.size() > 0 && astroCalendarList.get(0) != null) {
                sb.append("今日は" + astroCalendarList.get(0).getContent() + "です。");
            } else {
                sb.append("特にないです。");
            }

            ret = sb.toString();

            this.execAfter();

        } catch (final Exception e) {
            AstroCalendarAjax.log.error(e.getMessage(), e);
        }

        return ret;
    }


    @Override
    protected void setClazz() {

        this.clazz = AstroCalendarAjax.class;
    }
}
