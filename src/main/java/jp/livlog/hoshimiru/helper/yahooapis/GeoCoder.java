/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.yahooapis;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import jp.livlog.hoshimiru.share.HoshimiruSymbol;
import jp.livlog.hoshimiru.share.HttpUtil;
import jp.livlog.hoshimiru.share.Parameters;
import net.arnx.jsonic.JSON;

/**
 * ジオコードクラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class GeoCoder {

    /**  */
    private static Logger       log          = Logger.getLogger(GeoCoder.class.getName());

    /**
     * URI 1.
     */
    private static final String SUGGETS_URL1 = "http://reverse.search.olp.yahooapis.jp/OpenLocalPlatform/V1/reverseGeoCoder";

    /**
     * URI 2.
     */
    private static final String SUGGETS_URL2 = "http://geo.search.olp.yahooapis.jp/OpenLocalPlatform/V1/geoCoder";


    /**
     * 文字配列を連結して返す.
     *
     * @param array String[]
     * @param separator String
     * @return String
     */
    public static String arrayToStringLine(final String[] array, final String separator) {

        final StringBuffer sb = new StringBuffer();

        for (final String str : array) {
            if (StringUtils.isBlank(str)) {
                sb.append(str + separator);
            }
        }

        final String value = sb.toString().trim();

        return StringUtils.removeEnd(value, separator.trim());
    }


    /**
     * ジオコード.
     * @param query String
     * @return String
     * @throws Exception 例外
     */
    @SuppressWarnings ("rawtypes")
    public static String geo(final String query) throws Exception {

        String coordinates = null;

        try {

            // URLの生成
            final Parameters parameters = new Parameters();
            parameters.addParameter("query", HttpUtil.urlEncoder(query, HoshimiruSymbol.UTF_8));
            parameters.addParameter("detail", "string");
            parameters.addParameter("datum", "wgs");
            parameters.addParameter("output", "json");
            parameters.addParameter("appid", YahooapisSymbol.APP_ID);

            final URL url = new URL(HttpUtil.setUrlParameter(GeoCoder.SUGGETS_URL2, parameters));
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            GeoCoder.log.log(Level.INFO, url.toString());

            // 値の取得
            final InputStream is = connection.getInputStream();
            final Reader r = new InputStreamReader(is, HoshimiruSymbol.UTF_8);
            final Map resultMap = JSON.decode(r);

            GeoCoder.log.log(Level.INFO, resultMap.toString());

            r.close();
            is.close();

            final Map resultInfoMap = (Map) resultMap.get("ResultInfo");
            final BigDecimal status = (BigDecimal) resultInfoMap.get("Status");

            if (status.intValue() == HoshimiruSymbol.INT_200) {

                final List featureList = (List) resultMap.get("Feature");
                final Map featureMap = (Map) featureList.get(0);
                final Map geometry = (Map) featureMap.get("Geometry");
                coordinates = (String) geometry.get("Coordinates");

            } else {
                throw new Exception();
            }

        } catch (final ClassCastException e) {
            throw new Exception(e);
        } catch (final NullPointerException e) {
            throw new Exception(e);
        } catch (final MalformedURLException e) {
            throw new Exception(e);
        } catch (final Exception e) {
            throw new Exception(e);
        }

        return coordinates;
    }


    /**
     * リバースジオコード.
     *
     * @param lat double
     * @param lng double
     * @param lang String
     * @return MapDto
     * @throws Exception 例外
     */
    @SuppressWarnings ("rawtypes")
    public static MapData reverse(final double lat, final double lng, final String lang) throws Exception {

        final MapData dto = new MapData();

        try {

            // URLの生成
            final Parameters parameters = new Parameters();
            parameters.addParameter("lat", String.valueOf(lat));
            parameters.addParameter("lon", String.valueOf(lng));
            parameters.addParameter("datum", "wgs");
            parameters.addParameter("output", "json");
            parameters.addParameter("appid", YahooapisSymbol.APP_ID);

            final URL url = new URL(HttpUtil.setUrlParameter(GeoCoder.SUGGETS_URL1, parameters));
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            GeoCoder.log.log(Level.INFO, url.toString());

            // 値の取得
            final InputStream is = connection.getInputStream();
            final Reader r = new InputStreamReader(is, HoshimiruSymbol.UTF_8);
            final Map resultMap = JSON.decode(r);

            r.close();
            is.close();

            final List featureList = (List) resultMap.get("Feature");
            final Map featureMap = (Map) featureList.get(0);
            final Map propertyMap = (Map) featureMap.get("Property");

            GeoCoder.log.log(Level.INFO, propertyMap.toString());

            final Map countryMap = (Map) propertyMap.get("Country");
            dto.setCountry((String) countryMap.get("Name"));

            final Object addressElementObj = propertyMap.get("AddressElement");

            Map stateMap = null;
            Map cityMap = null;
            Map addressMap1 = null;
            Map addressMap2 = null;
            Map addressMap3 = null;

            if (addressElementObj instanceof List) {

                final List addressElementList = (List) addressElementObj;

                final int size = addressElementList.size();

                switch (size) {
                    case HoshimiruSymbol.INT_1:
                        stateMap = (Map) addressElementList.get(HoshimiruSymbol.INT_0);
                        dto.setState((String) stateMap.get("Name"));
                        break;
                    case HoshimiruSymbol.INT_2:
                        stateMap = (Map) addressElementList.get(HoshimiruSymbol.INT_0);
                        dto.setState((String) stateMap.get("Name"));
                        cityMap = (Map) addressElementList.get(HoshimiruSymbol.INT_1);
                        dto.setCity((String) cityMap.get("Name"));
                        break;
                    case HoshimiruSymbol.INT_3:
                        stateMap = (Map) addressElementList.get(HoshimiruSymbol.INT_0);
                        dto.setState((String) stateMap.get("Name"));
                        cityMap = (Map) addressElementList.get(HoshimiruSymbol.INT_1);
                        dto.setCity((String) cityMap.get("Name"));
                        addressMap1 = (Map) addressElementList.get(HoshimiruSymbol.INT_2);
                        dto.setAddress((String) addressMap1.get("Name"));
                        break;
                    case HoshimiruSymbol.INT_4:
                        stateMap = (Map) addressElementList.get(HoshimiruSymbol.INT_0);
                        dto.setState((String) stateMap.get("Name"));
                        cityMap = (Map) addressElementList.get(HoshimiruSymbol.INT_1);
                        dto.setCity((String) cityMap.get("Name"));
                        addressMap1 = (Map) addressElementList.get(HoshimiruSymbol.INT_2);
                        addressMap2 = (Map) addressElementList.get(HoshimiruSymbol.INT_3);
                        dto.setAddress((String) addressMap1.get("Name") + (String) addressMap2.get("Name"));
                        break;
                    case HoshimiruSymbol.INT_5:
                        stateMap = (Map) addressElementList.get(HoshimiruSymbol.INT_0);
                        dto.setState((String) stateMap.get("Name"));
                        cityMap = (Map) addressElementList.get(HoshimiruSymbol.INT_1);
                        dto.setCity((String) cityMap.get("Name"));
                        addressMap1 = (Map) addressElementList.get(HoshimiruSymbol.INT_2);
                        addressMap2 = (Map) addressElementList.get(HoshimiruSymbol.INT_3);
                        addressMap3 = (Map) addressElementList.get(HoshimiruSymbol.INT_4);
                        dto.setAddress((String) addressMap1.get("Name")
                                + (String) addressMap2.get("Name")
                                + (String) addressMap3.get("Name"));
                        break;
                    default:
                        break;
                }
            } else if (addressElementObj instanceof Map) {
                stateMap = (Map) addressElementObj;
                dto.setState((String) stateMap.get("Name"));
            }

            dto.setFullAddress(GeoCoder.arrayToStringLine(new String[] { dto.getCountry(), dto.getState(), dto.getCity(), dto.getAddress() },
                    HoshimiruSymbol.COMMA_AND_SPACE));

        } catch (final ClassCastException e) {
            throw new Exception(e);
        } catch (final NullPointerException e) {
            throw new Exception(e);
        } catch (final MalformedURLException e) {
            throw new Exception(e);
        } catch (final Exception e) {
            throw new Exception(e);
        }

        return dto;
    }


    /**
     * コンストラクタ.
     */
    private GeoCoder() {

    }
}
