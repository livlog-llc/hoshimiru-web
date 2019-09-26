/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.exception;

/**
 * 星をみるひと例外クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class StargazersException extends Exception {

    /**
     * シリアルバージョンUID.
     */
    private static final long  serialVersionUID = 1L;

    /**  */
    public static final String E0000            = "星座情報が取得できません。";

    /**  */
    public static final String E0001            = "星情報が取得できません。";

    /**  */
    public static final String E0002            = "気象情報が取得できません。";

    /**  */
    public static final String E0003            = "日照情報が取得できません。";

    /**  */
    public static final String E0004            = "住所が取得できません。";

    /**  */
    public static final String E0005            = "メールの送信に失敗しました.";

    /**  */
    public static final String E0006            = "プッシュ通信に失敗しました.";

    /**  */
    public static final String I0000            = "取得成功。";


    // public static final String E0002 = "住所が取得できません.";
    //
    // public static final String E0003 = "チェックインが取得できません.";
    //
    // public static final String E0004 = "短縮URLが取得できません.";
    //
    // public static final String E0005 = "周辺の写真が取得できません.";
    //
    // public static final String E0006 = "メールの送信に失敗しました.";
    //
    // public static final String E0007 = "周辺施設が取得できません.";
    //
    // public static final String E0008 = "日本語形態素解析ができません.";
    //
    // public static final String E0009 = "別のユーザーで認証されています.";

    /**
     * デフォルト・コンストラクタ.
     */
    public StargazersException() {

        super();
    }


    /**
     * メッセージ付け異常を構築する.
     * @param message エラーメッセージ
     */
    public StargazersException(final String message) {

        super(message);
    }


    /**
     * メッセージ付け、異常の発生元（throwable）情報を持つ異常を構築する.
     * @param message エラーメッセージ
     * @param throwable 例外
     */
    public StargazersException(final String message, final Throwable throwable) {

        super(message, throwable);
    }


    /**
     * 異常を発生する元（throwable）情報を持つ異常を構築する.
     * @param throwable 例外
     */
    public StargazersException(final Throwable throwable) {

        super(throwable);
    }
}
