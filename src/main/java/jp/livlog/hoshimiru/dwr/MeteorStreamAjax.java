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

import jp.livlog.hoshimiru.helper.hoshimiru.MeteorStream;
import jp.livlog.hoshimiru.helper.hoshimiru.MeteorStreamData;
import jp.livlog.hoshimiru.share.AbsBaseDwr;
import jp.livlog.hoshimiru.share.CurrentDateAddition;
import lombok.extern.slf4j.Slf4j;

/**
 * 流星群情報取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Slf4j
public class MeteorStreamAjax extends AbsBaseDwr {

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
            MeteorStreamAjax.log.error(e.getMessage(), e);
        }

        return ret;
    }


    @Override
    protected void setClazz() {

        this.clazz = MeteorStreamAjax.class;
    }
}
