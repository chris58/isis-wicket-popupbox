package com.marintek.isis.wicket.popupbox.applib;

import java.io.Serializable;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Value;


@Value(semanticsProviderClass=PopupWicketBoxSemanticsProvider.class)
public class PopupWicketBox implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String url;
    private String title;
    
    public PopupWicketBox(String url, String title, int width, int height, int top, int left) {
        this.url = url;
        this.height = height;
        this.width = width;
        this.top = top;
        this.left = left;
        this.title = title;
    }
    private int top;

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
    private int left;

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    @Programmatic
    public String getUrl() {
        return url;
    }
        private int height;

    @Programmatic
    public int getHeight() {
        return height;
    }

    private int width;

    @Programmatic
    public int getWidth() {
        return width;
    }

    public String getTitle() {
        return title;
    }


}
