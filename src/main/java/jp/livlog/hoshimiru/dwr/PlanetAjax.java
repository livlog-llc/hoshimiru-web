/*
 * タイトル：星みるプロジェクト.
 * 説明    ：
 * 著作権  ：Copyright(c) 2016 LineDesign
 * 会社名  ：株式会社 LineDesign
 * 変更履歴：2016.01.01
 *         ：新規登録
 */
package jp.linedesign.hoshimiru.dwr;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slim3.util.BeanUtil;

import jp.linedesign.hoshimiru.helper.hoshimiru.Planet;
import jp.linedesign.hoshimiru.helper.hoshimiru.PlanetData;
import jp.linedesign.hoshimiru.helper.yahooapis.GeoCoder;
import jp.linedesign.hoshimiru.helper.yahooapis.MapData;
import jp.linedesign.hoshimiru.share.AbsBaseDwr;
import jp.linedesign.utility.HttpUtil;
import jp.linedesign.utility.Parameters;
import jp.linedesign.utility.Symbol;

/**
 * 太陽と月と惑星情報取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class PlanetAjax extends AbsBaseDwr {

    /** Log. */
    private static Logger log = Logger.getLogger(TwitterAjax.class.getName());


    @Override
    protected void setClazz() {

        this.clazz = PlanetAjax.class;
    }


    /**
     * 現在に見える太陽と月と惑星を取得する.
     * @param gmtSecond int
     * @param lat String
     * @param lng String
     * @return List <PlanetData>
     */
    public List <PlanetData> getList(final int gmtSecond, final String lat, final String lng) {

        // 返却値
        List <PlanetData> planetList = null;

        try {

            this.execBefore();

            // 時差を計算
            final double gmtHour = gmtSecond / Symbol.INT_3600;

            // 星座一覧の取得
            planetList = Planet.execute(
                    new Date(),
                    String.valueOf(gmtHour),
                    lat,
                    lng,
                    null,
                    null);

            this.execAfter();

        } catch (final Exception e) {
            log.log(Level.WARNING, e.toString());
        }

        return planetList;
    }


    /**
     * 太陽と月と惑星情報の詳細を取得.
     * @param gmtSecond int
     * @param lat String
     * @param lng String
     * @param code String
     * @return PlanetData
     */
    public PlanetData getDetail(final int gmtSecond, final String lat, final String lng, final String code) {

        // 返却値
        final PlanetData data = new PlanetData();

        try {
            this.execBefore();

            // 時差を計算
            final double gmtHour = gmtSecond / Symbol.INT_3600;

            final List <PlanetData> planetList = Planet.execute(
                    new Date(),
                    String.valueOf(gmtHour),
                    lat,
                    lng,
                    code,
                    null);
            BeanUtil.copy(planetList.get(0), data);

            // 住所の取得
            final MapData map = GeoCoder.reverse(Double.valueOf(lat), Double.valueOf(lng), "ja");
            log.log(Level.INFO, map.toString());

            // http://twitter.com/share?url=共有したいURL&text=ツイート内に含める文字&via=ツイート内に含まれるユーザー名&related=関連アカウント
            final StringBuffer sb = new StringBuffer();
            sb.append(data.getJpName() + "をみっけ!!!" + Symbol.SEP);
            sb.append("場所:" + map.getLocation() + Symbol.SEP);
            sb.append("方位:" + data.getDirection() + Symbol.SEP);
            sb.append("高さ:" + data.getAltitude() + Symbol.SEP);
            sb.append("#" + data.getJpName() + " #星をみるひと");
            data.setMsg(sb.toString());
            final Parameters parameters = new Parameters();
            parameters.addParameter("text", HttpUtil.urlEncoder(sb.toString(), Symbol.UTF_8));
            parameters.addParameter("url", "http://www.hoshi-miru.com/");
            final String url = HttpUtil.setUrlParameter("http://twitter.com/share", parameters);
            data.setUrl(url);

            this.execAfter();

        } catch (final Exception e) {
            log.log(Level.WARNING, e.toString());
        }

        return data;
    }
}
