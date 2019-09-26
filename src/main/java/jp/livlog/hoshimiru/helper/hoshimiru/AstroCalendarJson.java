/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.hoshimiru;

import java.util.ArrayList;
import java.util.List;

/**
 * Json Dto.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class AstroCalendarJson extends AbsJson {

    /**
     * シリアルバージョンUID.
     */
    private static final long        serialVersionUID = 1L;

    /**
     * 状態.
     */
    private List <AstroCalendarData> result           = new ArrayList <AstroCalendarData>();


    /**
     * @return result
     */
    public List <AstroCalendarData> getResult() {

        return this.result;
    }


    /**
     * @param pResult セットする result
     */
    public void setResult(final List <AstroCalendarData> pResult) {

        this.result = pResult;
    }

}
