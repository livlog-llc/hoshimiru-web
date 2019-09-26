/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.share;

import java.util.Locale;

/**
 * 情報種類の列挙型.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public enum NotificationType {

    /**
     * Success.
     */
    SUCCESS("success", "成功", "Success"),

    /**
     * Error.
     */
    ERROR("error", "異常", "Error"),

    /**
     * Warning.
     */
    WARNING("warning", "警告", "Warning"),

    /**
     * Information.
     */
    INFORMATION("info", "情報", "Information");

    /**
     * @return String[]
     */
    public static String[] getCode() {

        final NotificationType[] types = NotificationType.values();
        final String[] codes = new String[types.length];
        for (int i = 0; i < codes.length; i++) {
            codes[i] = types[i].cd;
        }
        return codes;
    }


    /**
     * @param cd String
     * @param lang String
     * @return String
     */
    public static String getName(final String cd, final String lang) {

        final NotificationType[] types = NotificationType.values();
        for (final NotificationType type : types) {
            if (Locale.ENGLISH.getLanguage().equals(lang)) {
                if ((type.cd).equals(cd)) {
                    return type.en;
                }
            } else {
                if ((type.cd).equals(cd)) {
                    return type.ja;
                }
            }
        }
        return null;
    }

    /** コード. */
    public String cd;

    /** 日本語. */
    public String ja;

    /** 英語. */
    public String en;


    /**
     * コンストラクタ.
     * @param pCd コード
     * @param pJa 日本語
     * @param pEn 英語
     */
    NotificationType(final String pCd, final String pJa, final String pEn) {

        this.cd = pCd;
        this.ja = pJa;
        this.en = pEn;
    }

}