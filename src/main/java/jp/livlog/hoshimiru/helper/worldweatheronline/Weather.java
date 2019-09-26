/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.worldweatheronline;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.livlog.hoshimiru.share.HoshimiruSymbol;
import jp.livlog.hoshimiru.share.HttpUtil;
import jp.livlog.hoshimiru.share.Parameters;
import net.arnx.jsonic.JSON;

/**
 * 気象情報取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class Weather {

    /** Log. */
    private static Logger       log         = Logger.getLogger(Weather.class.getName());

    /**
     * URL.
     * http://free.worldweatheronline.com/feed/weather.ashx?q=35.00,136.00&format=json&num_of_days=2&key=e54174fdb2225024123008
     */
    private static final String SUGGETS_URL = "http://api.worldweatheronline.com/free/v1/weather.ashx";


    /**
     * コンストラクタ.
     */
    private Weather() {

    }


    /**
     * 現在の天気を取得する.
     *
     * @param lat double
     * @param lng double
     * @param lang String
     * @return Weather2Dto
     * @throws Exception 例外
     */
    @SuppressWarnings ("rawtypes")
    public static WeatherData getWeather(final double lat, final double lng, final String lang) throws Exception {

        // 返却値
        final WeatherData weatherDto = new WeatherData();
        weatherDto.setGeolat(lat);
        weatherDto.setGeolong(lng);

        final Parameters parameters = new Parameters();
        parameters.addParameter("q", convLatlog(lat) + "," + convLatlog(lng));
        parameters.addParameter("format", "json");
        parameters.addParameter("num_of_days", "1");
        parameters.addParameter("key", WeatherSymbol.WEATHER_API_KEY);

        final URL url = new URL(HttpUtil.setUrlParameter(SUGGETS_URL, parameters));
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(0);

        log.log(Level.INFO, url.toString());

        // 値の取得
        final InputStream is = connection.getInputStream();
        final Reader r = new InputStreamReader(is, HoshimiruSymbol.UTF_8);
        final Map resultMap = JSON.decode(r);
        final Map dataMap = (Map) resultMap.get("data");
        final List currentConditionList = (List) dataMap.get("current_condition");
        final Map currentConditionMap = (Map) currentConditionList.get(0);

        // LOG.log(Level.INFO, currentConditionMap.toString());

        final String cloudcover = objectToString(currentConditionMap.get("cloudcover"));
        final String humidity = objectToString(currentConditionMap.get("humidity"));
        final String observationTime = objectToString(currentConditionMap.get("observation_time"));
        final String precipMM = objectToString(currentConditionMap.get("precipMM"));
        final String pressure = objectToString(currentConditionMap.get("pressure"));
        final String tempC = objectToString(currentConditionMap.get("temp_C"));
        final String tempF = objectToString(currentConditionMap.get("temp_F"));
        final String visibility = objectToString(currentConditionMap.get("visibility"));
        final String weatherCode = objectToString(currentConditionMap.get("weatherCode"));
        final List weatherDescList = (List) currentConditionMap.get("weatherDesc");
        final Map weatherDescMap = (Map) weatherDescList.get(0);
        final String weatherDesc = objectToString(weatherDescMap.get("value"));
        final List weatherIconUrlList = (List) currentConditionMap.get("weatherIconUrl");
        final Map weatherIconUrlMap = (Map) weatherIconUrlList.get(0);
        final String weatherIconUrl = objectToString(weatherIconUrlMap.get("value"));
        final String winddir16Point = objectToString(currentConditionMap.get("winddir16Point"));
        final String winddirDegree = objectToString(currentConditionMap.get("winddirDegree"));
        final String windspeedKmph = objectToString(currentConditionMap.get("windspeedKmph"));
        final String windspeedMiles = objectToString(currentConditionMap.get("windspeedMiles"));

        weatherDto.setCloudcover(cloudcover);
        weatherDto.setHumidity(humidity);
        weatherDto.setObservationTime(observationTime);
        weatherDto.setPrecipMM(precipMM);
        weatherDto.setPressure(pressure);
        weatherDto.setTempC(tempC);
        weatherDto.setTempF(tempF);
        weatherDto.setVisibility(visibility);
        weatherDto.setWeatherCode(weatherCode);
        weatherDto.setWeatherDesc(weatherDesc);
        weatherDto.setWeatherIconUrl(weatherIconUrl);
        weatherDto.setWinddir16Point(winddir16Point);
        weatherDto.setWinddirDegree(winddirDegree);
        weatherDto.setWindspeedKmph(windspeedKmph);
        weatherDto.setWindspeedMiles(windspeedMiles);

        r.close();
        is.close();

        if (weatherDto.getWeatherCode() == null) {
            throw new NullPointerException();
        }

        log.log(Level.INFO, weatherDto.toString());

        return weatherDto;
    }


    /**
     * @param latlng double
     * @return String
     */
    private static String convLatlog(final double latlng) {

        double tmpLatlng = latlng;

        if (tmpLatlng < 0) {
            tmpLatlng += HoshimiruSymbol.INT_360;
        }

        return String.valueOf(tmpLatlng);
    }

    /**
     * ObjectをStringに変換する.
     *
     * @param value Object
     * @return String
     */
    public static String objectToString(final Object value) {

        if (value == null) {
            return null;
        } else {
            return value.toString();
        }
    }

}
