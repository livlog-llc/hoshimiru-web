/*
 * タイトル：Json Dto
 * 説明    ：Jsonを保持する.
 * 著作権  ：Copyright(c) 2013-2014 LineDesign
 * 会社名  ：株式会社 LineDesign
 * 変更履歴：2011.02.07
 *         ：新規登録
 */
package jp.linedesign.hoshimiru.helper.hoshimiru;

import java.util.ArrayList;
import java.util.List;

/**
 * Json Dto.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class MeteorStreamJson extends AbsJson {

    /**
     * シリアルバージョンUID.
     */
    private static final long       serialVersionUID = 1L;

    /**
     * 状態.
     */
    private List <MeteorStreamData> result           = new ArrayList <MeteorStreamData>();


    /**
     * @return result
     */
    public List <MeteorStreamData> getResult() {

        return this.result;
    }


    /**
     * @param pResult セットする result
     */
    public void setResult(final List <MeteorStreamData> pResult) {

        this.result = pResult;
    }

}
