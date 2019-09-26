/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.yahooapis;

import lombok.Data;

/**
 * 地図情報Dto.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Data
public class MapData  {



    /**
     * 記号.
     */
    private String            sign;

    /**
     * ID.
     */
    private String            id;

    /**
     * 名前.
     */
    private String            name;

    /**
     * 電話.
     */
    private String            phone;

    /**
     * 郵便番号.
     */
    private String            zip;

    /**
     * 国.
     */
    private String            country;

    /**
     * 県.
     */
    private String            state;

    /**
     * 市.
     */
    private String            city;

    /**
     * 住所.
     */
    private String            address;

    /**
     * 1行であらわす住所.
     */
    private String            fullAddress;

    /**
     * 緯度.
     */
    private Double            geolat;

    /**
     * 経度.
     */
    private Double            geolong;

    /**
     * API種類.
     */
    private String            acquisition;

    /**
     * その他.
     */
    private String            etc;




    /**
     * @return 住所
     */
    public String getLocation() {

        final StringBuffer sb = new StringBuffer();
        if (this.country != null && !"null".equals(this.country)) {
            sb.append(this.country);
        }
        if (this.state != null && !"null".equals(this.state)) {
            sb.append(this.state);
        }
        if (this.city != null && !"null".equals(this.city)) {
            sb.append(this.city);
        }

        return sb.toString();
    }
}
