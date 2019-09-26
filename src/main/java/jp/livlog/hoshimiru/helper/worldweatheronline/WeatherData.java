/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.worldweatheronline;

import lombok.Data;

/**
 * 天気Dto.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Data
public class WeatherData  {



    /**
     * 緯度.
     */
    private Double            geolat;

    /**
     * 経度.
     */
    private Double            geolong;

    /**
     * 雲量.
     */
    private String            cloudcover;

    /**
     * 湿度.
     */
    private String            humidity;

    /**
     * 観測時間.
     */
    private String            observationTime;

    /**
     * 降水量.
     */
    private String            precipMM;

    /**
     * 気圧.
     */
    private String            pressure;

    /**
     * カ氏度（華氏度）[ﾟF].
     */
    private String            tempC;

    /**
     * セルシウス度（摂氏度）[℃].
     */
    private String            tempF;

    /**
     * 視界.
     */
    private String            visibility;

    /**
     * 気象略号.
     */
    private String            weatherCode;

    /**
     * 説明.
     */
    private String            weatherDesc;

    /**
     * アイコンURL.
     */
    private String            weatherIconUrl;

    /**
     * 風向き（方位）.
     */
    private String            winddir16Point;

    /**
     * 風向き（角度）.
     */
    private String            winddirDegree;

    /**
     * 風速（キロ）.
     */
    private String            windspeedKmph;

    /**
     * 風速（マイル）.
     */
    private String            windspeedMiles;



}
