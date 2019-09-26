/*
 * タイトル：現在見える星座取得クラス
 * 説明    ：現在見える星座取得を提供をする.
 * 著作権  ：Copyright(c) 2013-2014 LineDesign
 * 会社名  ：株式会社 LineDesign
 * 変更履歴：2012.03.03
 *         ：新規登録
 */
package jp.linedesign.hoshimiru.helper.hoshimiru;

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

import jp.linedesign.utility.DateUtil;
import jp.linedesign.utility.HttpUtil;
import jp.linedesign.utility.Parameters;
import jp.linedesign.utility.Symbol;
import jp.linedesign.utility.TimeUtil;
import net.arnx.jsonic.JSON;

/**
 * 太陽と月と惑星取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class Planet {

    /** Log. */
    private static Logger       log          = Logger.getLogger(Planet.class.getName());

    /**
     * URL.
     * http://hoshi-miru.appspot.com/planet?lat=35&lng=136&date=2013-10-31&hour=0&min=0
     */
    private static final String SUGGETS_URL1 = "https://livlog.xyz/hoshimiru/planet";

    /** エラー. */
    private static final int    ERROR        = -1;


    /**
     * コンストラクタ.
     */
    private Planet() {

    }


    /**
     * 実行処理.
     * @param pDatetime Date
     * @param gmt String
     * @param lat String
     * @param lng String
     * @param id String
     * @param disp String
     * @return List <PlanetData>
     * @throws Exception 例外
     */
    public static List <PlanetData> execute(
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
        parameters.addParameter("date", DateUtil.convertToString(datetime, "yyyy-MM-dd"));
        parameters.addParameter("hour", TimeUtil.getHourOfDay(new Time(datetime.getTime())));
        parameters.addParameter("min", TimeUtil.getMinute(new Time(datetime.getTime())));
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
        final Reader r = new InputStreamReader(is, Symbol.UTF_8);

        final PlanetJson dto = JSON.decode(r, PlanetJson.class);

        r.close();
        is.close();

        if (ERROR == dto.getStatus()) {
            throw new Exception();
        }

        return dto.getResult();
    }

}
