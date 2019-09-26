/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.share;

/**
 * 定数クラス.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public final class HoshimiruSymbol {

    /**
     * 空文字.
     */
    public static final String EMPTY                          = "";

    /***************************** 文字列定数定義 ************************************/

    /**
     * 半角スペース.
     */
    public static final String HANKAKU                        = " ";

    /**
     * 全角スペース.
     */
    public static final String ZENKAKU                        = "　";

    /**
     * アスタリスク.
     */
    public static final String ASTERISK                       = "*";

    /**
     * ハイフン.
     */
    public static final String HYPHEN                         = "-";

    /**
     * カンマ.
     */
    public static final String COMMA                          = ",";

    /**
     * カンマとスペース.
     */
    public static final String COMMA_AND_SPACE                = ", ";

    /**
     * ドット.
     */
    public static final String DOT                            = ".";

    /**
     * スラッシュ.
     */
    public static final String SLASH                          = "/";

    /**
     * コロン.
     */
    public static final String COLON                          = ":";

    /**
     * 特別な区切り.
     */
    public static final String SPECIAL_SEP                    = "<--->";

    /**
     * シングル・ダブルクォート.
     */
    public static final String QUARTO                         = "\"'";

    /**
     * 同上.
     */
    public static final String DITTO                          = "↑";

    /**
     * マイナス.
     */
    public static final String MINUS                          = "−";

    /**
     * 改行.
     */
    public static final String SEP                            = System.getProperty("line.separator");

    /**
     * タブ.
     */
    public static final String TAB                            = "\t";

    /**
     * 時間フォーマット（時分）コロンなし.
     */
    public static final String FORMAT_TIME_HHMM               = "HHmm";

    /**
     * 時間フォーマット（時:分）コロンあり.
     */
    public static final String FORMAT_TIME_HH_MM              = "HH:mm";

    /**
     * 時間フォーマット（時分秒）コロンなし.
     */
    public static final String FORMAT_TIME_HHMMSS             = "HHmmss";

    /**
     * 時間フォーマット（時:分:秒）コロンあり.
     */
    public static final String FORMAT_TIME_HH_MM_SS           = "HH:mm:ss";

    /**
     * 開始時間（時:分:秒）コロンあり.
     */
    public static final String START_TIME_HH_MM_SS            = "00:00:00";

    /**
     * 終了時間（時:分:秒）コロンあり.
     */
    public static final String END_TIME_HH_MM_SS              = "23:59:59";

    /**
     * 日付フォーマット（年月日）スラッシュなし.
     */
    public static final String FORMAT_DATE_YYYYMMDD           = "yyyyMMdd";

    /**
     * 日付フォーマット（年/月/日）スラッシュあり.
     */
    public static final String FORMAT_DATE_YYYY_MM_DD         = "yyyy/MM/dd";

    /**
     * 日付フォーマット（年月日時）スラッシュなし.
     */
    public static final String FORMAT_DATE_YYYYMMDDHH         = "yyyyMMddHH";

    /**
     * 日付フォーマット（年月日時分）スラッシュなし.
     */
    public static final String FORMAT_DATE_YYYYMMDDHHMM       = "yyyyMMddHHmm";

    /**
     * 日付フォーマット（年月日時分秒）.
     */
    public static final String FORMAT_DATETIME_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 日付（最大値）.
     */
    public static final String MAX_DATETIME_YYYYMMDDHHMMSS    = "20991231235959";

    /**
     * 日付（最小値）.
     */
    public static final String MIN_DATETIME_YYYYMMDDHHMMSS    = "19000101000000";

    /**
     * 日付フォーマット（年/月/日 時/分）.
     */
    public static final String FORMAT_DATE_YY_MM_DD_HH_MM     = "yy/MM/dd HH:mm";

    /**
     * メールフォーマット.
     */
    public static final String FORMAT_EMAIL                   = "[0-9a-zA-Z_\\-\\.]+@[0-9a-zA-Z_\\-\\.]+(\\.[0-9a-zA-Z_\\-]+){1,}";

    /**
     * URLフォーマット.
     */
    public static final String FORMAT_URL                     = "^(https?|ftp)(:\\/\\/[-_.!~*\\'()a-zA-Z0-9;\\/?:\\@&=+\\$,%#]+)$";

    /**
     * IPフォーマット.
     */
    public static final String FORMAT_IP                      = "(((\\d)|([1-9]\\d)|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d)|"
            + "([1-9]\\d)|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))";

    /**
     * 半角英数字フォーマット.
     */
    public static final String FORMAT_ALPHANUMERIC            = "[-_0-9a-zA-Z]+";

    /**
     * 半角カナフォーマット.
     */
    public static final String FORMAT_HANKAKU_KANA            = "[｡-ﾟ+]+";

    /**
     * ファイル名フォーマット.
     */
    public static final String FORMAT_FILENAME_DATETIME       = "yyyy-MM-dd_HHmmss";

    /**
     * 保存画像サイズ.
     */
    public static final int    MAX_SAVE_HW_SIZE               = 612;

    /**
     * タグ <br />.
     */
    public static final String TAG_BR                         = "<br />";

    /**
     * 星空の様子取得開始時間.
     */
    public static final String STR_GET_FROM                   = "21";

    /**
     * 星空の様子取得終了時間.
     */
    public static final String STR_GET_TO                     = "5";

    /**
     * UTF-8.
     */
    public static final String UTF_8                          = "UTF-8";

    /**
     * Shift_JIS.
     */
    public static final String SHIFT_JIS                      = "Shift_JIS";

    /***************************** 数字の定数定義 ************************************/
    /** int:0. */
    public static final int    INT_0                          = 0;

    /** int:1. */
    public static final int    INT_1                          = 1;

    /** int:2. */
    public static final int    INT_2                          = 2;

    /** int:3. */
    public static final int    INT_3                          = 3;

    /** int:4. */
    public static final int    INT_4                          = 4;

    /** int:5. */
    public static final int    INT_5                          = 5;

    /** int:6. */
    public static final int    INT_6                          = 6;

    /** int:7. */
    public static final int    INT_7                          = 7;

    /** int:8. */
    public static final int    INT_8                          = 8;

    /** int:9. */
    public static final int    INT_9                          = 9;

    /** int:10. */
    public static final int    INT_10                         = 10;

    /** int:20. */
    public static final int    INT_20                         = 20;

    /** int:30. */
    public static final int    INT_30                         = 30;

    /** int:40. */
    public static final int    INT_40                         = 40;

    /** int:50. */
    public static final int    INT_50                         = 50;

    /** int:60. */
    public static final int    INT_60                         = 60;

    /** int:70. */
    public static final int    INT_70                         = 70;

    /** int:80. */
    public static final int    INT_80                         = 80;

    /** int:90. */
    public static final int    INT_90                         = 90;

    /** int:100. */
    public static final int    INT_100                        = 100;

    /** int:200. */
    public static final int    INT_200                        = 200;

    /** int:360. */
    public static final int    INT_360                        = 360;

    /** int:3600. */
    public static final int    INT_3600                       = 3600;


    /**
     * コンストラクタ.
     */
    private HoshimiruSymbol() {

    }

}
