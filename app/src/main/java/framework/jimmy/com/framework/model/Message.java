package framework.jimmy.com.framework.model;

import org.parceler.Parcel;

@Parcel
public class Message {
    private String text;

    public Message() {
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
