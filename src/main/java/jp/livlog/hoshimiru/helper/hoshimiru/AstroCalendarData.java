/*
 * タイトル：ほしぞらカレンダーData
 * 説明    ：ほしぞらカレンダーを保持する.
 * 著作権  ：Copyright(c) 2013-2014 LineDesign
 * 会社名  ：株式会社 LineDesign
 * 変更履歴：2011.02.07
 *         ：新規登録
 */
package jp.linedesign.hoshimiru.helper.hoshimiru;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * ほしぞらカレンダーData.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public class AstroCalendarData implements Serializable, Cloneable {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 日付.
     */
    private String            date             = "";

    /**
     * 内容.
     */
    private String            content          = "";

    /**
     * 詳細.
     */
    private String            detail           = "";


    /**
     * @return date
     */
    public String getDate() {

        return this.date;
    }


    /**
     * @param pDate セットする date
     */
    public void setDate(final String pDate) {

        this.date = pDate;
    }


    /**
     * @return content
     */
    public String getContent() {

        return this.content;
    }


    /**
     * @param pContent セットする content
     */
    public void setContent(final String pContent) {

        this.content = pContent;
    }


    /**
     * @return detail
     */
    public String getDetail() {

        return this.detail;
    }


    /**
     * @param pDetail セットする detail
     */
    public void setDetail(final String pDetail) {

        this.detail = pDetail;
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
