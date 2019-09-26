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
 * ベースJson Dto.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public abstract class AbsJson implements Serializable, Cloneable {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 状態.
     */
    private int               status           = 0;


    /**
     * @return status
     */
    public int getStatus() {

        return this.status;
    }


    /**
     * @param pStatus セットする status
     */
    public void setStatus(final int pStatus) {

        this.status = pStatus;
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
