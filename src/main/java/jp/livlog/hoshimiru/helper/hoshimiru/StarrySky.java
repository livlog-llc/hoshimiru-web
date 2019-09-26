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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.livlog.hoshimiru.share.HoshimiruSymbol;
import jp.livlog.hoshimiru.share.HttpUtil;
import jp.livlog.hoshimiru.share.Parameters;
import net.arnx.jsonic.JSON;

/**
 * 星空の様子クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class StarrySky {

    /** Log. */
    private static Logger       log          = Logger.getLogger(StarrySky.class.getName());

    /**
     * URL.
     * http://hoshi-miru.appspot.com/starrySky?lat=35&lng=136&gmt=9
     */
    private static final String SUGGETS_URL1 = "https://livlog.xyz/hoshimiru/starrySky";

    /** エラー. */
    private static final int    ERROR        = -1;


    /**
     * コンストラクタ.
     */
    private StarrySky() {

    }


    /**
     * 実行処理.
     * @param lat String
     * @param lng String
     * @param from String
     * @param to String
     * @param gmt String
     * @return List <StarrySkyData>
     * @throws Exception 例外
     */
    public static List <StarrySkyData> execute(
            final String lat,
            final String lng,
            final String from,
            final String to,
            final String gmt) throws Exception {

        final Parameters parameters = new Parameters();
        parameters.addParameter("lat", lat);
        parameters.addParameter("lng", lng);
        parameters.addParameter("from", from);
        parameters.addParameter("to", to);

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

        final StarrySkyJson dto = JSON.decode(r, StarrySkyJson.class);

        r.close();
        is.close();

        if (ERROR == dto.getStatus()) {
            throw new Exception();
        }

        return dto.getResult();
    }

}
