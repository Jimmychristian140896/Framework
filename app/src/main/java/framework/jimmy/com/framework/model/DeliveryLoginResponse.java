package framework.jimmy.com.framework.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeliveryLoginResponse
{
    @SerializedName("sessionid")
    private String sessionid;

    public String getSessionid() { return this.sessionid; }

    public void setSessionid(String sessionid) { this.sessionid = sessionid; }

    private String jobid;

    public String getJobid() { return this.jobid; }

    public void setJobid(String jobid) { this.jobid = jobid; }

    private String req_date;

    public String getReqDate() { return this.req_date; }

    public void setReqDate(String req_date) { this.req_date = req_date; }

    private String platno;

    public String getPlatno() { return this.platno; }

    public void setPlatno(String platno) { this.platno = platno; }

    private String drivernip;

    public String getDrivernip() { return this.drivernip; }

    public void setDrivernip(String drivernip) { this.drivernip = drivernip; }

    private String drivername;

    public String getDrivername() { return this.drivername; }

    public void setDrivername(String drivername) { this.drivername = drivername; }

    private String crew1nip;

    public String getCrew1nip() { return this.crew1nip; }

    public void setCrew1nip(String crew1nip) { this.crew1nip = crew1nip; }

    private String crew1name;

    public String getCrew1name() { return this.crew1name; }

    public void setCrew1name(String crew1name) { this.crew1name = crew1name; }

    private String crew2nip;

    public String getCrew2nip() { return this.crew2nip; }

    public void setCrew2nip(String crew2nip) { this.crew2nip = crew2nip; }

    private String crew2name;

    public String getCrew2name() { return this.crew2name; }

    public void setCrew2name(String crew2name) { this.crew2name = crew2name; }

    private String trx_assign;

    public String getTrxAssign() { return this.trx_assign; }

    public void setTrxAssign(String trx_assign) { this.trx_assign = trx_assign; }

    private String trx_finished;

    public String getTrxFinished() { return this.trx_finished; }

    public void setTrxFinished(String trx_finished) { this.trx_finished = trx_finished; }

    private int num_rows;

    public int getNumRows() { return this.num_rows; }

    public void setNumRows(int num_rows) { this.num_rows = num_rows; }

    private ArrayList<DeliveryTransaction> rows;

    public ArrayList<DeliveryTransaction> getRows() { return this.rows; }

    public void setRows(ArrayList<DeliveryTransaction> rows) { this.rows = rows; }

    public class DeliveryTransaction
    {
        private String notrans;

        public String getNotrans() { return this.notrans; }

        public void setNotrans(String notrans) { this.notrans = notrans; }

        private String site_id;

        public String getSiteId() { return this.site_id; }

        public void setSiteId(String site_id) { this.site_id = site_id; }

        private String cust_name;

        public String getCustName() { return this.cust_name; }

        public void setCustName(String cust_name) { this.cust_name = cust_name; }

        private String addr;

        public String getAddr() { return this.addr; }

        public void setAddr(String addr) { this.addr = addr; }

        private String note;

        public String getNote() { return this.note; }

        public void setNote(String note) { this.note = note; }

        private String qty_koli;

        public String getQtyKoli() { return this.qty_koli; }

        public void setQtyKoli(String qty_koli) { this.qty_koli = qty_koli; }

        private String date;

        public String getDate() { return this.date; }

        public void setDate(String date) { this.date = date; }

        private String delivery_status;

        public String getDeliveryStatus() { return this.delivery_status; }

        public void setDeliveryStatus(String delivery_status) { this.delivery_status = delivery_status; }

        private int route_status;

        public int getRouteStatus() { return this.route_status; }

        public void setRouteStatus(int route_status) { this.route_status = route_status; }
    }
}
