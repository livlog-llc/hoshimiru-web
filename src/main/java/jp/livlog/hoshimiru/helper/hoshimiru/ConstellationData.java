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
 * 星座情報Data.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Data
public class ConstellationData {

    /**
     * 星座番号.
     */
    private String id             = "";

    /**
     * 星名（日本語）.
     */
    private String jpName         = "";

    /**
     * 星名（英語）.
     */
    private String enName         = "";

    /**
     * 内容.
     */
    private String content        = "";

    /**
     * 起源.
     */
    private String origin         = "";

    /**
     * 楕円形フラグ.
     */
    private String eclipticalFlag = "";

    /**
     * プトレマイオスフラグ.
     */
    private String ptolemyFlag    = "";

    /**
     * 確認されている.
     */
    private String confirmed      = "";

    /**
     * 星番号一覧.
     */
    private String drowing        = "";

    /**
     * 方向.
     */
    private String direction      = "";

    /**
     * 高度.
     */
    private String altitude       = "";

    /**
     * 星図.
     */
    private String starImage      = "";

    /**
     * アイコン.
     */
    private String starIcon       = "";

    /**
     * 略説.
     */
    private String roughly        = "";

    /**
     * メッセージ.
     */
    private String msg            = "";

    /**
     * Twitter URL.
     */
    private String url            = "";

}
