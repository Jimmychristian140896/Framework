package framework.jimmy.com.framework.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "history_ovo_detail")
public class HistoryOvoDetail {

    public HistoryOvoDetail(Date date, String title, String subTitle, Boolean isPlus, double pointOvo, double cashOvo) {
        this.date = date;
        this.title = title;
        this.subTitle = subTitle;
        this.isPlus = isPlus;
        this.pointOvo = pointOvo;
        this.cashOvo = cashOvo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Ignore
    public HistoryOvoDetail(String title, String subTitle, Boolean isPlus, double pointOvo, double cashOvo) {
        this.title = title;
        this.subTitle = subTitle;
        this.isPlus = isPlus;
        this.pointOvo = pointOvo;
        this.cashOvo = cashOvo;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Boolean getIsPlus() {
        return isPlus;
    }

    public void setIsPlus(Boolean isPlus) {
        isPlus = isPlus;
    }

    public double getPointOvo() {
        return pointOvo;
    }

    public void setPointOvo(double pointOvo) {
        this.pointOvo = pointOvo;
    }

    public double getCashOvo() {
        return cashOvo;
    }

    public void setCashOvo(double cashOvo) {
        this.cashOvo = cashOvo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String subTitle;
    private Boolean isPlus;
    private double pointOvo;
    private double cashOvo;
    private Date date;
}
