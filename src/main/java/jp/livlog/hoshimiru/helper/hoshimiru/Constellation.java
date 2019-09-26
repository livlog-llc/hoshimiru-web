/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.hoshimiru;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.livlog.hoshimiru.share.HoshimiruSymbol;
import jp.livlog.hoshimiru.share.HttpUtil;
import jp.livlog.hoshimiru.share.OtherUtil;
import jp.livlog.hoshimiru.share.Parameters;
import net.arnx.jsonic.JSON;

/**
 * 現在見える星座取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class Constellation {

    /** Log. */
    private static Logger       log          = Logger.getLogger(Constellation.class.getName());

    /**
     * URL.
     * http://hoshi-miru.appspot.com/consetellation?lat=35&lng=136&date=2013-10-31&hour=0&min=0
     */
    private static final String SUGGETS_URL1 = "https://livlog.xyz/hoshimiru/constellation";

    /** エラー. */
    private static final int    ERROR        = -1;


    /**
     * コンストラクタ.
     */
    private Constellation() {

    }


    /**
     * 実行処理.
     * @param pDatetime Date
     * @param gmt String
     * @param lat String
     * @param lng String
     * @param id String
     * @param disp String
     * @return List <ConstellationData>
     * @throws Exception 例外
     */
    public static List <ConstellationData> execute(
            final Date pDatetime,
            final String gmt,
            final String lat,
            final String lng,
            final String id,
            final String disp) throws Exception {

        Date datetime = pDatetime;

        if (pDatetime == null) {
            datetime = new Date();
        }

        final Parameters parameters = new Parameters();
        parameters.addParameter("lat", lat);
        parameters.addParameter("lng", lng);
        parameters.addParameter("date", OtherUtil.convertToString(datetime, "yyyy-MM-dd"));
        parameters.addParameter("hour", OtherUtil.getHourOfDay(new Time(datetime.getTime())));
        parameters.addParameter("min", OtherUtil.getMinute(new Time(datetime.getTime())));
        if (id != null) {
            parameters.addParameter("id", id);
        }
        if (disp != null) {
            parameters.addParameter("disp", disp);
        }
        if (gmt != null) {
            parameters.addParameter("gmt", gmt);
        }

        final URL url = new URL(HttpUtil.setUrlParameter(SUGGETS_URL1, parameters));
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(0);

        log.log(Level.INFO, url.toString());

        // 値の取得
        final InputStream is = connection.getInputStream();
        final Reader r = new InputStreamReader(is, HoshimiruSymbol.UTF_8);

        final ConstellationJson dto = JSON.decode(r, ConstellationJson.class);

        r.close();
        is.close();

        if (ERROR == dto.getStatus()) {
            throw new Exception();
        }

        return dto.getResult();
    }

}
