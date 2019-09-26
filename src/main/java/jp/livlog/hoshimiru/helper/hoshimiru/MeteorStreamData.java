/*
 * タイトル：流星群Data
 * 説明    ：流星群を保持する.
 * 著作権  ：Copyright(c) 2013-2014 LineDesign
 * 会社名  ：株式会社 LineDesign
 * 変更履歴：2011.02.07
 *         ：新規登録
 */
package jp.linedesign.hoshimiru.helper.hoshimiru;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 流星群Data.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class MeteorStreamData implements Serializable, Cloneable {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
    * 流星群名.
    */
    private String            name             = "";

    /**
     * 流星出現期間カラ.
     */
    private String            fromDate         = "";

    /**
     * 流星出現期間マデ.
     */
    private String            toDate           = "";

    /**
     * 極大.
     */
    private String            maxDate          = "";

    /**
     * メッセージ.
     */
    private String            message          = "";


    /**
     * @return name
     */
    public String getName() {

        return this.name;
    }


    /**
     * @param pName セットする name
     */
    public void setName(final String pName) {

        this.name = pName;
    }


    /**
     * @return fromDate
     */
    public String getFromDate() {

        return this.fromDate;
    }


    /**
     * @param pFromDate セットする fromDate
     */
    public void setFromDate(final String pFromDate) {

        this.fromDate = pFromDate;
    }


    /**
     * @return toDate
     */
    public String getToDate() {

        return this.toDate;
    }


    /**
     * @param pToDate セットする toDate
     */
    public void setToDate(final String pToDate) {

        this.toDate = pToDate;
    }


    /**
     * @return maxDate
     */
    public String getMaxDate() {

        return this.maxDate;
    }


    /**
     * @param pMaxDate セットする maxDate
     */
    public void setMaxDate(final String pMaxDate) {

        this.maxDate = pMaxDate;
    }


    /**
     * @return message
     */
    public String getMessage() {

        return this.message;
    }


    /**
     * @param pMessage セットする message
     */
    public void setMessage(final String pMessage) {

        this.message = pMessage;
    }


    /*
     * (非 Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return ReflectionToStringBuilder.toString(this);
    }


    /*
     * (非 Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
}
