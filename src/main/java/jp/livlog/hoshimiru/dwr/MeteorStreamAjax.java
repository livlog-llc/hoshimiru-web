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

import jp.linedesign.hoshimiru.helper.hoshimiru.MeteorStream;
import jp.linedesign.hoshimiru.helper.hoshimiru.MeteorStreamData;
import jp.linedesign.hoshimiru.share.AbsBaseDwr;
import jp.linedesign.utility.CurrentDateAddition;

/**
 * 流星群情報取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class MeteorStreamAjax extends AbsBaseDwr {

    /** Log. */
    private static Logger log = Logger.getLogger(TwitterAjax.class.getName());


    @Override
    protected void setClazz() {

        this.clazz = MeteorStreamAjax.class;
    }


    /**
     * 流星群情報を取得する.
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

            // 時差を計算
            // final double gmtHour = gmtSecond / Symbol.INT_3600;
            final Calendar cal = CurrentDateAddition.addSecond((gmtSecond));

            // 流星群情報を取得する。
            final StringBuffer sb = new StringBuffer();
            final List <MeteorStreamData> dataList = MeteorStream.execute(cal.getTime());
            if (dataList.size() > 0) {
                for (final MeteorStreamData meteorStreamData : dataList) {
                    // sb.append(Symbol.SEP);
                    sb.append(meteorStreamData.getFromDate() + "から");
                    sb.append(meteorStreamData.getToDate() + "までは");
                    sb.append(meteorStreamData.getName() + "が見えるよ。");
                    sb.append(meteorStreamData.getMaxDate() + "は極大日です。");
                }
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
