/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.hoshimiru;

import java.sql.Time;
import java.util.Date;

import jp.livlog.hoshimiru.share.HoshimiruSymbol;
import jp.livlog.hoshimiru.share.OtherUtil;

/**
 * 定数クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class StarrySkyMessage {

    /***************************** 返却メッセージ **:::**********************************
     * 以下の定義は画面で使用するメッセージのキー値になります.
     *********************************************************************************/
    /**  */
    public static final String M0001 = "日中なので、まだ星は見えないですよ。";

    /**  */
    public static final String M0002 = "もうじき朝日がでます。もう見えないですよ。";

    /**  */
    public static final String M0003 = "まだまだ明るいので、星は見えないですよ。";

    /**  */
    public static final String S0110 = "今夜はよくわからないですね。";

    /**  */
    public static final String S0100 = "今夜はとってもきれいな星空が、空いっぱいにひろがるよ！";

    /**  */
    public static final String S0090 = "今夜は空いっぱいに星空がひろがりそうだよ。楽しみだね！";

    /**  */
    public static final String S0080 = "今夜は天体観測が楽しめそう！！どの星座がみられるかな？";

    /**  */
    public static final String S0070 = "今夜は星座を見るにはにはまずまずの条件。いくつ見つけられるかな？";

    /**  */
    public static final String S0060 = "今夜は空を見上げてみて！きれいな星がみえるはずだよ。";

    /**  */
    public static final String S0050 = "今夜はきれいな星空が見られるチャンスあり！どんな星空が見えるかな？";

    /**  */
    public static final String S0040 = "今夜は星が見られるチャンスはあるよ！よーく空を探してみてね。";

    /**  */
    public static final String S0030 = "今夜はじっくり待ってみて。きっと星がみられるよ！";

    /**  */
    public static final String S0020 = "今夜は星空が見られる時間はわずかかも・・・見られるといいね！";

    /**  */
    public static final String S0010 = "今夜は残念だけど、星空は期待できなさそうだよ。";

    /**  */
    public static final String S0000 = "今夜は星空はまったく見られなさそう・・・つぎは見られるといいね！";


    /**
     * 日の出、日の入りからメッセージを返す.
     * @param sunriseHm 日の出
     * @param sunsetHm 日の入り
     * @param date 日時
     * @return メッセージ
     */
    public static String getAstronomyMess(final String sunriseHm, final String sunsetHm, final Date date) {

        final int sunrise = OtherUtil.convertToMinute(sunriseHm, HoshimiruSymbol.FORMAT_TIME_HH_MM);
        final int sunset = OtherUtil.convertToMinute(sunsetHm, HoshimiruSymbol.FORMAT_TIME_HH_MM);

        final String stTime = OtherUtil.convertToString(new Time(date.getTime()), HoshimiruSymbol.FORMAT_TIME_HH_MM);
        final int time = OtherUtil.convertToMinute(stTime, HoshimiruSymbol.FORMAT_TIME_HH_MM);

        if (sunrise < time && time < sunset) {
            return StarrySkyMessage.M0001;
        } else if ((sunrise - HoshimiruSymbol.INT_30) < time && time < sunrise) {
            return StarrySkyMessage.M0002;
        } else if (sunset < time && time < (sunset + HoshimiruSymbol.INT_30)) {
            return StarrySkyMessage.M0003;
        }

        return HoshimiruSymbol.EMPTY;
    }


    /**
     * 雲の量からメッセージを返す.
     * @param cloudcover String
     * @return String
     */
    public static String getStarryFiguryMess(final String cloudcover) {

        final int figure = Integer.parseInt(cloudcover);

        if (figure == 0) {
            return StarrySkyMessage.S0100;
        } else if (figure < HoshimiruSymbol.INT_10) {
            return StarrySkyMessage.S0090;
        } else if (figure < HoshimiruSymbol.INT_20) {
            return StarrySkyMessage.S0080;
        } else if (figure < HoshimiruSymbol.INT_30) {
            return StarrySkyMessage.S0070;
        } else if (figure < HoshimiruSymbol.INT_40) {
            return StarrySkyMessage.S0060;
        } else if (figure < HoshimiruSymbol.INT_50) {
            return StarrySkyMessage.S0050;
        } else if (figure < HoshimiruSymbol.INT_60) {
            return StarrySkyMessage.S0040;
        } else if (figure < HoshimiruSymbol.INT_70) {
            return StarrySkyMessage.S0030;
        } else if (figure < HoshimiruSymbol.INT_80) {
            return StarrySkyMessage.S0020;
        } else if (figure < HoshimiruSymbol.INT_90) {
            return StarrySkyMessage.S0010;
        } else {
            return StarrySkyMessage.S0000;
        }
    }


    /**
     * コンストラクタ.
     */
    private StarrySkyMessage() {

    }

}
