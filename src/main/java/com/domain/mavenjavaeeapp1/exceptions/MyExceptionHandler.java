/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.mavenjavaeeapp1.exceptions;

import java.io.IOException;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 *
 * @author Daichi
 */
public class MyExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public MyExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() {
        for (Iterator<ExceptionQueuedEvent> it
                = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext() == true;) {
            ExceptionQueuedEventContext eventContext = it.next().getContext();

            // 1. ハンドリング対象のアプリケーション例外を取得
            Throwable th = getRootCause(eventContext.getException()).getCause();

            if (th instanceof RuntimeException) {

                FacesContext facesContext = eventContext.getContext();

                // メッセージを追加する
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "システム障害", "システム障害が発生しました。"));

                // 2. リダイレクトしてもFacesMessageが消えないように設定
                facesContext.getExternalContext().getFlash().setKeepMessages(true);

                try {
                    // エラー画面に画面遷移させる
                    String contextPath = facesContext.getExternalContext().getRequestContextPath();
                    facesContext.getExternalContext().redirect(contextPath + "/error.xhtml");
                } catch (IOException e) {
                    System.out.println("error.xhtmlがありません");
                } finally {
                    // 3. 未ハンドリングキューから削除する
                    it.remove();
                }
            }
        }
        wrapped.handle();
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
}
