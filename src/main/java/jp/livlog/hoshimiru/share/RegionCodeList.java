/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.share;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import jp.livlog.hoshimiru.dto.RegionCodeDto;
import jp.livlog.hoshimiru.dto.RegionCodeDtoKey0Comparator;

/**
 * 予報地域リストクラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class RegionCodeList {

    /**  */
    private static List <RegionCodeDto>               regionCodeList = new ArrayList <>();

    /**  */
    private static Map <String, List <RegionCodeDto>> regionCodeMap  = new HashMap <>();

    static {

        // キーの取り出し
        final ResourceBundle regionCode = ResourceBundle.getBundle("region_code");
        final Enumeration <String> enu = regionCode.getKeys();

        // キーの並び替え
        final Set <String> keySet = new TreeSet <>();
        while (enu.hasMoreElements()) {
            final String text = enu.nextElement();
            keySet.add(text);
        }

        for (final String key : keySet) {
            final String line = regionCode.getString(key);
            final String[] array = line.split(",");
            final RegionCodeDto dto = new RegionCodeDto();
            dto.setForecastAreaCode(key);
            dto.setPrefectureCode(array[HoshimiruSymbol.INT_0]);
            dto.setRepresentativeFlg(array[HoshimiruSymbol.INT_1]);
            dto.setPrefectureName(array[HoshimiruSymbol.INT_2]);
            dto.setRegionName(array[HoshimiruSymbol.INT_3]);
            dto.setCityRepresentatives(array[HoshimiruSymbol.INT_4]);
            dto.setGeolat(Double.valueOf(array[HoshimiruSymbol.INT_5]));
            dto.setGeolong(Double.valueOf(array[HoshimiruSymbol.INT_6]));
            RegionCodeList.regionCodeList.add(dto);
        }

        Collections.sort(RegionCodeList.regionCodeList, new RegionCodeDtoKey0Comparator());

        String state = "";
        List <RegionCodeDto> tempGroupList = null;
        for (final RegionCodeDto dto : RegionCodeList.regionCodeList) {
            if (state.equals(dto.getPrefectureName())) {
                tempGroupList.add(dto);
                RegionCodeList.regionCodeMap.put(dto.getPrefectureName(), tempGroupList);
            } else {
                state = dto.getPrefectureName();
                tempGroupList = new ArrayList <>();
                tempGroupList.add(dto);
                RegionCodeList.regionCodeMap.put(dto.getPrefectureName(), tempGroupList);
            }
        }
    }


    /**
     * @return regionCodeList
     */
    public static List <RegionCodeDto> getRegionCodeList() {

        return RegionCodeList.regionCodeList;
    }


    /**
     * @return regionCodeMap
     */
    public static Map <String, List <RegionCodeDto>> getRegionCodeMap() {

        return RegionCodeList.regionCodeMap;
    }


    /**
     * コンストラクタ.
     */
    private RegionCodeList() {

    }

}
