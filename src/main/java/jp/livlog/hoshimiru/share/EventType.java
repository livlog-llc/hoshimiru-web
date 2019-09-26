/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.share;

/**
 * イベントログの列挙型です.
 *
 * @author H.Aoshima
 * @version 1.0
 *
 */
public enum EventType {

    /**
     * ログイン.
     */
    EVENT_LOGIN("login", "INFO", "ユーザーがログインしました."),
    /**
     * ログアウト.
     */
    EVENT_LOGOUT("logout", "INFO", "ユーザーがログアウトしました."),
    /**
     * ブログパーツ.
     */
    EVENT_BLOGPART("blogpart", "INFO", "ブログパーツが起動しました."),

    /**
     * 各機能.
     */
    EVENT_FUNCTION("function", "INFO", "−"),

    /**
     * システムエラー.
     */
    EVENT_SYSTEM("System", "ERROR", "システムエラーが発生しました."),
    /**
     * タイムアウト.
     */
    EVENT_TIMEOUT("TimeOut", "ERROR", "タイムアウトしました.");

    /**
     * イベントタイプ.
     */
    public String event;

    /**
     * イベントレベル.
     */
    public String level;

    /**
     * コメント.
     */
    public String remarks;


    /**
     * コンストラクタ.
     * @param pEvent String
     * @param pLevel String
     * @param pRemarks String
     */
    EventType(final String pEvent, final String pLevel, final String pRemarks) {

        this.event = pEvent;
        this.level = pLevel;
        this.remarks = pRemarks;
    }
}