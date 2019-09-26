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

import jp.linedesign.hoshimiru.helper.hoshimiru.StarrySky;
import jp.linedesign.hoshimiru.helper.hoshimiru.StarrySkyData;
import jp.linedesign.hoshimiru.helper.hoshimiru.StarrySkyMessage;
import jp.linedesign.hoshimiru.share.AbsBaseDwr;
import jp.linedesign.hoshimiru.share.HoshimiruSymbol;
import jp.linedesign.utility.CurrentDateAddition;
import jp.linedesign.utility.Symbol;

/**
 * 星空の様子情報取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class StatusAjax extends AbsBaseDwr {

    /** Log. */
    private static Logger log = Logger.getLogger(StatusAjax.class.getName());


    // private final static String JAPAN = "日本";

    /**
     * 星空の様子を取得する.
     * @param gmtSecond int
     * @param lat String
     * @param lng String
     * @return StarrySkyData
     */
    public StarrySkyData getStatus(final int gmtSecond, final String lat, final String lng) {

        // 返却値
        StarrySkyData data = null;

        try {

            this.execBefore();

            // 時差を計算
            final double gmtHour = gmtSecond / Symbol.INT_3600;

            // 星空の様子を取得する
            final List <StarrySkyData> list = StarrySky.execute(
                    lat,
                    lng,
                    HoshimiruSymbol.STR_GET_FROM,
                    HoshimiruSymbol.STR_GET_TO,
                    String.valueOf(gmtHour));
            data = list.get(0);

            final Calendar cal = CurrentDateAddition.addSecond((gmtSecond));
            final StringBuffer sb = new StringBuffer();
            sb.append(StarrySkyMessage.getAstronomyMess(data.getSunriseHm(), data.getSunsetHm(), cal.getTime()));
            sb.append(StarrySkyMessage.getStarryFiguryMess(data.getCloudcover()));

            data.setMessage(sb.toString());

            this.execAfter();

        } catch (final Exception e) {
            log.log(Level.WARNING, e.toString());
        }

        return data;
    }


    @Override
    protected void setClazz() {

        this.clazz = StatusAjax.class;
    }

}
