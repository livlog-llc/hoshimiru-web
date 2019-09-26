/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.share;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL送信のユーティリティです.
 *
 * @author H.Aoshima
 * @version 1.0
 *
 */
public final class HttpUtil {

    /** TAG_P_START. */
    public static final String TAG_P_START = "<p>";

    /** TAG_P_END. */
    public static final String TAG_P_END   = "</p>";

    /** TAG_BR. */
    public static final String TAG_BR      = "<br />";


    /**
     * URLに送信するパラメータを生成する.
     *
     * @param parameters Parameters
     * @return String
     */
    public static String setParameter(final Parameters parameters) {

        return parameters.toString();
    }


    /**
     * URLにパラメータを付加する.
     *
     * @param url String
     * @param parameters Parameters
     * @return String
     */
    public static String setUrlParameter(final String url, final Parameters parameters) {

        return url + "?" + parameters.toString();
    }


    /**
     * 文字列のURLデコードを行う.
     *
     * @param text String
     * @param charsetName String
     * @return String
     * @throws UnsupportedEncodingException 例外
     */
    public static String urlDecoder(final String text, final String charsetName) throws UnsupportedEncodingException {

        return URLDecoder.decode(text, charsetName);
    }


    /**
     * 文字列のURLエンコードを行う.
     *
     * @param text String
     * @param charsetName String
     * @return String
     * @throws UnsupportedEncodingException 例外
     */
    public static String urlEncoder(final String text, final String charsetName) throws UnsupportedEncodingException {

        return URLEncoder.encode(text, charsetName);
    }


    /**
     * コンストラクタ.
     */
    private HttpUtil() {

    }

}
