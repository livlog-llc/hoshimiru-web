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
 * 流星群Data.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Data
public class MeteorStreamData {

    /**
    * 流星群名.
    */
    private String name     = "";

    /**
     * 流星出現期間カラ.
     */
    private String fromDate = "";

    /**
     * 流星出現期間マデ.
     */
    private String toDate   = "";

    /**
     * 極大.
     */
    private String maxDate  = "";

    /**
     * メッセージ.
     */
    private String message  = "";

}
