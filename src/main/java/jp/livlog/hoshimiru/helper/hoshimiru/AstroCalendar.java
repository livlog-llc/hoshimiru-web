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
 * ほしぞらカレンダー取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class AstroCalendar {

    /** Log. */
    private static Logger       log          = Logger.getLogger(AstroCalendar.class.getName());

    /**
     * URL.
     * http://hoshi-miru.appspot.com/astroCalendar?date=2013-10-31
     */
    private static final String SUGGETS_URL1 = "https://livlog.xyz/hoshimiru/astroCalendar";

    /** エラー. */
    private static final int    ERROR        = -1;


    /**
     * コンストラクタ.
     */
    private AstroCalendar() {

    }


    /**
     * 実行処理.
     * @param pDatetime Date
     * @return List <AstroCalendarData>
     * @throws Exception 例外
     */
    public static List <AstroCalendarData> execute(
            final Date pDatetime) throws Exception {

        Date datetime = pDatetime;

        if (pDatetime == null) {
            datetime = new Date();
        }

        final Parameters parameters = new Parameters();

        parameters.addParameter("date", OtherUtil.convertToString(datetime, "yyyy-MM-dd"));
        final URL url = new URL(HttpUtil.setUrlParameter(SUGGETS_URL1, parameters));
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(0);

        log.log(Level.INFO, url.toString());

        // 値の取得
        final InputStream is = connection.getInputStream();
        final Reader r = new InputStreamReader(is, HoshimiruSymbol.UTF_8);

        final AstroCalendarJson dto = JSON.decode(r, AstroCalendarJson.class);

        r.close();
        is.close();

        if (ERROR == dto.getStatus()) {
            throw new Exception();
        }

        return dto.getResult();
    }
}
