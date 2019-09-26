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


    /*
     * (非 Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {

        return super.clone();
    }


    /**
     * @return fromDate
     */
    public String getFromDate() {

        return this.fromDate;
    }


    /**
     * @return maxDate
     */
    public String getMaxDate() {

        return this.maxDate;
    }


    /**
     * @return message
     */
    public String getMessage() {

        return this.message;
    }


    /**
     * @return name
     */
    public String getName() {

        return this.name;
    }


    /**
     * @return toDate
     */
    public String getToDate() {

        return this.toDate;
    }


    /**
     * @param pFromDate セットする fromDate
     */
    public void setFromDate(final String pFromDate) {

        this.fromDate = pFromDate;
    }


    /**
     * @param pMaxDate セットする maxDate
     */
    public void setMaxDate(final String pMaxDate) {

        this.maxDate = pMaxDate;
    }


    /**
     * @param pMessage セットする message
     */
    public void setMessage(final String pMessage) {

        this.message = pMessage;
    }


    /**
     * @param pName セットする name
     */
    public void setName(final String pName) {

        this.name = pName;
    }


    /**
     * @param pToDate セットする toDate
     */
    public void setToDate(final String pToDate) {

        this.toDate = pToDate;
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
