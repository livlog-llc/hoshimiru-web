/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.helper.hoshimiru;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 星空の様子Data.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class StarrySkyData implements Serializable, Cloneable {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
    * 日の出.
    */
    private String            sunriseHm        = "";

    /**
    * 日の入.
    */
    private String            sunsetHm         = "";

    /**
    * 日付.
    */
    private String            date             = "";

    /**
     * 雲量.
     */
    private String            cloudcover       = "";

    /**
     * メッセージ.
     */
    private String            message          = "";


    /*
     * (非 Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {

        return super.clone();
    }


    /**
     * @return cloudcover
     */
    public String getCloudcover() {

        return this.cloudcover;
    }


    /**
     * @return date
     */
    public String getDate() {

        return this.date;
    }


    /**
    * @return message
    */
    public String getMessage() {

        return this.message;
    }


    /**
     * @return sunriseHm
     */
    public String getSunriseHm() {

        return this.sunriseHm;
    }


    /**
     * @return sunsetHm
     */
    public String getSunsetHm() {

        return this.sunsetHm;
    }


    /**
     * @param pCloudcover セットする cloudcover
     */
    public void setCloudcover(final String pCloudcover) {

        this.cloudcover = pCloudcover;
    }


    /**
     * @param pDate セットする date
     */
    public void setDate(final String pDate) {

        this.date = pDate;
    }


    /**
    * @param pMessage セットする message
    */
    public void setMessage(final String pMessage) {

        this.message = pMessage;
    }


    /**
     * @param pSunriseHm セットする sunriseHm
     */
    public void setSunriseHm(final String pSunriseHm) {

        this.sunriseHm = pSunriseHm;
    }


    /**
     * @param pSunsetHm セットする sunsetHm
     */
    public void setSunsetHm(final String pSunsetHm) {

        this.sunsetHm = pSunsetHm;
    }


    /*
     * (非 Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return ReflectionToStringBuilder.toString(this);
    }
}
