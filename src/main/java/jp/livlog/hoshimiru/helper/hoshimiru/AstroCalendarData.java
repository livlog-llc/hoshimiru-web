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
 * ほしぞらカレンダーData.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Data
public class AstroCalendarData {

    /**
     * 日付.
     */
    private String date    = "";

    /**
     * 内容.
     */
    private String content = "";

    /**
     * 詳細.
     */
    private String detail  = "";

}
