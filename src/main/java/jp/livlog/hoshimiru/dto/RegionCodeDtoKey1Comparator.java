/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.dto;

import java.io.Serializable;
import java.util.Comparator;

/**
 * RegionCodeKey1Comparatorクラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class RegionCodeDtoKey1Comparator implements Comparator <RegionCodeDto>, Serializable {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;


    @Override
    public int compare(final RegionCodeDto arg0, final RegionCodeDto arg1) {

        return (arg0.getDistance()).compareTo(arg1.getDistance()); // * -1;
    }

}