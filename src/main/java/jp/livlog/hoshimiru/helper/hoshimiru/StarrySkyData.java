/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.hoshimiru;

import lombok.Data;

/**
 * 星空の様子Data.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Data
public class StarrySkyData {

    /**
    * 日の出.
    */
    private String sunriseHm  = "";

    /**
    * 日の入.
    */
    private String sunsetHm   = "";

    /**
    * 日付.
    */
    private String date       = "";

    /**
     * 雲量.
     */
    private String cloudcover = "";

    /**
     * メッセージ.
     */
    private String message    = "";

}
