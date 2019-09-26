/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.dto;

import lombok.Data;

/**
 * 予報地域Dto.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Data
public class RegionCodeDto {

    /**
     * 予報地域コード.
     */
    private String forecastAreaCode;

    /**
     * 都道府県コード.
     */
    private String prefectureCode;

    /**
     * 都道府県代表地.
     */
    private String representativeFlg;

    /**
     * 予報都道府県名.
     */
    private String prefectureName;

    /**
     * 予報地域名.
     */
    private String regionName;

    /**
     * 代表都市名.
     */
    private String cityRepresentatives;

    /**
     * 緯度.
     */
    private Double geolat;

    /**
     * 経度.
     */
    private Double geolong;

    /**
     * 距離.
     */
    private Double distance;
}
