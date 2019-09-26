/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.yahooapis;

/**
 * Yahoo関係の定数クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class YahooapisSymbol {

    /**
     * アプリケーションID.
     */
    public static final String   APP_ID     = "dj0zaiZpPXB3VjBhWnBTQ3JrYiZzPWNvbnN1bWVyc2VjcmV0Jng9YWQ-";

    /***************************** 文字列定数定義 ************************************/

    /**
     * シークレット.
     */
    public static final String   APP_SECRET = "44ac192b5db2cc81945e890cd54308c7e4f17672";

    /**
     * xml.
     */
    public static final String   XML        = "xml";

    /**
     * json.
     */
    public static final String   JSON       = "json";

    /**
     * 検索対象カテゴリ.
     * address - 住所
     * world - 世界
     * landmark - ランドマーク
     */
    public static final String[] CATEGORY   = new String[] { "address", "world", "landmark" };

    /**
     * 文字列:0.
     */
    public static final String   STR_ZERO   = "0";


    /**
     * コンストラクタ.
     */
    private YahooapisSymbol() {

    }

}
