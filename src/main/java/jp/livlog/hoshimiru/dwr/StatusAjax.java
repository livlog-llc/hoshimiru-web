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

import jp.livlog.hoshimiru.helper.hoshimiru.StarrySky;
import jp.livlog.hoshimiru.helper.hoshimiru.StarrySkyData;
import jp.livlog.hoshimiru.helper.hoshimiru.StarrySkyMessage;
import jp.livlog.hoshimiru.share.AbsBaseDwr;
import jp.livlog.hoshimiru.share.CurrentDateAddition;
import jp.livlog.hoshimiru.share.HoshimiruSymbol;
import lombok.extern.slf4j.Slf4j;

/**
 * 星空の様子情報取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Slf4j
public class StatusAjax extends AbsBaseDwr {

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
            final double gmtHour = gmtSecond / HoshimiruSymbol.INT_3600;

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
            StatusAjax.log.error(e.getMessage(), e);
        }

        return data;
    }


    @Override
    protected void setClazz() {

        this.clazz = StatusAjax.class;
    }

}
