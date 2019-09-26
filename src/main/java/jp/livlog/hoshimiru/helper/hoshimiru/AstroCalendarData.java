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


    /*
     * (非 Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {

        return super.clone();
    }


    /**
     * @return content
     */
    public String getContent() {

        return this.content;
    }


    /**
     * @return date
     */
    public String getDate() {

        return this.date;
    }


    /**
     * @return detail
     */
    public String getDetail() {

        return this.detail;
    }


    /**
     * @param pContent セットする content
     */
    public void setContent(final String pContent) {

        this.content = pContent;
    }


    /**
     * @param pDate セットする date
     */
    public void setDate(final String pDate) {

        this.date = pDate;
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
}
