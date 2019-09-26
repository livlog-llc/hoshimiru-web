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
 * 太陽と月と惑星情報Data.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class PlanetData implements Serializable, Cloneable {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 星座番号.
     */
    private String            id               = "";

    /**
     * 星名（日本語）.
     */
    private String            jpName           = "";

    /**
     * 星名（英語）.
     */
    private String            enName           = "";

    /**
     * 内容.
     */
    private String            content          = "";

    /**
     * 起源.
     */
    private String            origin           = "";

    /**
     * 楕円形フラグ.
     */
    private String            eclipticalFlag   = "";

    /**
     * プトレマイオスフラグ.
     */
    private String            ptolemyFlag      = "";

    /**
     * 確認されている.
     */
    private String            confirmed        = "";

    /**
     * 星番号一覧.
     */
    private String            drowing          = "";

    /**
     * 方向.
     */
    private String            direction        = "";

    /**
     * 高度.
     */
    private String            altitude         = "";

    /**
     * 星図.
     */
    private String            starImage        = "";

    /**
     * 略説.
     */
    private String            roughly          = "";

    /**
     * メッセージ.
     */
    private String            msg              = "";

    /**
     * Twitter URL.
     */
    private String            url              = "";


    /*
     * (非 Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {

        return super.clone();
    }


    /**
     * @return altitude
     */
    public String getAltitude() {

        return this.altitude;
    }


    /**
     * @return confirmed
     */
    public String getConfirmed() {

        return this.confirmed;
    }


    /**
     * @return content
     */
    public String getContent() {

        return this.content;
    }


    /**
     * @return direction
     */
    public String getDirection() {

        return this.direction;
    }


    /**
     * @return drowing
     */
    public String getDrowing() {

        return this.drowing;
    }


    /**
     * @return eclipticalFlag
     */
    public String getEclipticalFlag() {

        return this.eclipticalFlag;
    }


    /**
     * @return enName
     */
    public String getEnName() {

        return this.enName;
    }


    /**
     * @return id
     */
    public String getId() {

        return this.id;
    }


    /**
     * @return jpName
     */
    public String getJpName() {

        return this.jpName;
    }


    /**
     * @return msg
     */
    public String getMsg() {

        return this.msg;
    }


    /**
     * @return origin
     */
    public String getOrigin() {

        return this.origin;
    }


    /**
     * @return ptolemyFlag
     */
    public String getPtolemyFlag() {

        return this.ptolemyFlag;
    }


    /**
     * @return roughly
     */
    public String getRoughly() {

        return this.roughly;
    }


    /**
     * @return starImage
     */
    public String getStarImage() {

        return this.starImage;
    }


    /**
     * @return url
     */
    public String getUrl() {

        return this.url;
    }


    /**
     * @param pAltitude セットする altitude
     */
    public void setAltitude(final String pAltitude) {

        this.altitude = pAltitude;
    }


    /**
     * @param pConfirmed セットする confirmed
     */
    public void setConfirmed(final String pConfirmed) {

        this.confirmed = pConfirmed;
    }


    /**
     * @param pContent セットする content
     */
    public void setContent(final String pContent) {

        this.content = pContent;
    }


    /**
     * @param pDirection セットする direction
     */
    public void setDirection(final String pDirection) {

        this.direction = pDirection;
    }


    /**
     * @param pDrowing セットする drowing
     */
    public void setDrowing(final String pDrowing) {

        this.drowing = pDrowing;
    }


    /**
     * @param pEclipticalFlag セットする eclipticalFlag
     */
    public void setEclipticalFlag(final String pEclipticalFlag) {

        this.eclipticalFlag = pEclipticalFlag;
    }


    /**
     * @param pEnName セットする enName
     */
    public void setEnName(final String pEnName) {

        this.enName = pEnName;
    }


    /**
     * @param pId セットする id
     */
    public void setId(final String pId) {

        this.id = pId;
    }


    /**
     * @param pJpName セットする jpName
     */
    public void setJpName(final String pJpName) {

        this.jpName = pJpName;
    }


    /**
     * @param pMsg セットする msg
     */
    public void setMsg(final String pMsg) {

        this.msg = pMsg;
    }


    /**
     * @param pOrigin セットする origin
     */
    public void setOrigin(final String pOrigin) {

        this.origin = pOrigin;
    }


    /**
     * @param pPtolemyFlag セットする ptolemyFlag
     */
    public void setPtolemyFlag(final String pPtolemyFlag) {

        this.ptolemyFlag = pPtolemyFlag;
    }


    /**
     * @param pRoughly セットする roughly
     */
    public void setRoughly(final String pRoughly) {

        this.roughly = pRoughly;
    }


    /**
     * @param pStarImage セットする starImage
     */
    public void setStarImage(final String pStarImage) {

        this.starImage = pStarImage;
    }


    /**
     * @param pUrl セットする url
     */
    public void setUrl(final String pUrl) {

        this.url = pUrl;
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
