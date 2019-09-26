/*
 * タイトル：ほしみるプロジェクト
 * 説明    ：
 * 著作権  ：Copyright(c) 2019 LivLog llc.
 * 会社名  ：リブログ合同会社
 * 変更履歴：2019.09.20
 *         ：新規登録
 */
package jp.livlog.hoshimiru.share;

import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ベース用Dwr.
 *
 * @author H.Aoshima
 * @version 1.0
 */
public abstract class AbsBaseDwr {

    /**  */
    private static Logger log   = Logger.getLogger(AbsBaseDwr.class.getName());

    /**  */
    protected Class <?>   clazz = AbsBaseDwr.class;

    /**
     * 処理時間.
     */
    protected long        msec;


    /**
     * 自クラスを設定.
     */
    protected abstract void setClazz();


    /**
     * 前処理.
     *
     * @throws Exception 例外
     */
    protected void execBefore() throws Exception {

        this.setClazz();
        this.msec = new Date().getTime();
    }


    /**
     * 後処理.
     *
     * @throws Exception 例外
     */
    @SuppressWarnings ("deprecation")
    protected void execAfter() throws Exception {

        this.msec = new Date().getTime() - this.msec;

        log.log(Level.INFO, "処理時間:" + new Time(this.msec).getSeconds() + "(秒), 機能:" + this.clazz.getName());
    }

}
