package framework.jimmy.com.framework.model;

import java.util.ArrayList;

public class BookingResponse<T>
{
    private String is_ok;

    public String getIsOk() { return this.is_ok; }

    public void setIsOk(String is_ok) { this.is_ok = is_ok; }

    private String message;

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    private String total_row;

    public String getTotalRow() { return this.total_row; }

    public void setTotalRow(String total_row) { this.total_row = total_row; }

    private String current_page;

    public String getCurrentPage() { return this.current_page; }

    public void setCurrentPage(String current_page) { this.current_page = current_page; }

    private ArrayList<T> rows;

    public ArrayList<T> getRows() { return this.rows; }

    public void setRows(ArrayList<T> rows) { this.rows = rows; }
}
