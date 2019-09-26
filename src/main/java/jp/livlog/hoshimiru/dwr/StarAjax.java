/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.dwr;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import jp.livlog.hoshimiru.helper.hoshimiru.Constellation;
import jp.livlog.hoshimiru.helper.hoshimiru.ConstellationData;
import jp.livlog.hoshimiru.helper.yahooapis.GeoCoder;
import jp.livlog.hoshimiru.helper.yahooapis.MapData;
import jp.livlog.hoshimiru.share.AbsBaseDwr;
import jp.livlog.hoshimiru.share.HoshimiruSymbol;
import jp.livlog.hoshimiru.share.HttpUtil;
import jp.livlog.hoshimiru.share.Parameters;
import lombok.extern.slf4j.Slf4j;

/**
 * 星座情報取得クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
@Slf4j
public class StarAjax extends AbsBaseDwr {


    @Override
    protected void setClazz() {

        this.clazz = StarAjax.class;
    }


    /**
     * 現在に見える星座を取得する.
     * @param gmtSecond int
     * @param lat String
     * @param lng String
     * @return List <ConstellationData>
     */
    public List <ConstellationData> getList(final int gmtSecond, final String lat, final String lng) {

        // 返却値
        List <ConstellationData> constellationList = null;

        try {

            this.execBefore();

            // 時差を計算
            final double gmtHour = gmtSecond / HoshimiruSymbol.INT_3600;

            // 星座一覧の取得
            constellationList = Constellation.execute(
                    new Date(),
                    String.valueOf(gmtHour),
                    lat,
                    lng,
                    null,
                    null);

            this.execAfter();

        } catch (final Exception e) {
        	  log.error(e.getMessage(),e);
        }

        return constellationList;
    }


    /**
     * 星座情報の詳細を取得.
     * @param gmtSecond int
     * @param lat String
     * @param lng String
     * @param code String
     * @return ConstellationData
     */
    public ConstellationData getDetail(final int gmtSecond, final String lat, final String lng, final String code) {

        // 返却値
        ConstellationData data = new ConstellationData();
        final ModelMapper modelMapper = new ModelMapper();

        try {
            this.execBefore();

            // 時差を計算
            final double gmtHour = gmtSecond / HoshimiruSymbol.INT_3600;

            final List <ConstellationData> constellationList = Constellation.execute(
                    new Date(),
                    String.valueOf(gmtHour),
                    lat,
                    lng,
                    code,
                    null);
            data = modelMapper.map(constellationList.get(0), ConstellationData.class);

            // 住所の取得
            final MapData map = GeoCoder.reverse(Double.valueOf(lat), Double.valueOf(lng), "ja");
            log.info(map.toString());

            // http://twitter.com/share?url=共有したいURL&text=ツイート内に含める文字&via=ツイート内に含まれるユーザー名&related=関連アカウント
            final StringBuffer sb = new StringBuffer();
            sb.append(data.getJpName() + "をみっけ!!!" + HoshimiruSymbol.SEP);
            sb.append("場所:" + map.getLocation() + HoshimiruSymbol.SEP);
            sb.append("方位:" + data.getDirection() + HoshimiruSymbol.SEP);
            sb.append("高さ:" + data.getAltitude() + HoshimiruSymbol.SEP);
            sb.append("#" + data.getJpName() + " #星をみるひと");
            data.setMsg(sb.toString());
            final Parameters parameters = new Parameters();
            parameters.addParameter("text", HttpUtil.urlEncoder(sb.toString(), HoshimiruSymbol.UTF_8));
            parameters.addParameter("url", "http://www.hoshi-miru.com/");
            final String url = HttpUtil.setUrlParameter("http://twitter.com/share", parameters);
            data.setUrl(url);

            this.execAfter();

        } catch (final Exception e) {
            log.error(e.getMessage(),e);
        }

        return data;
    }

}
