package framework.jimmy.com.framework.model;

import java.util.Calendar;

public class NotificationOvo {
    private Calendar date;
    private String message;
    private Boolean isRead;

    public NotificationOvo(Calendar date, String message, Boolean isRead, String reference) {
        this.date = date;
        this.message = message;
        this.isRead = isRead;
        this.reference = reference;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    private String reference;
}
