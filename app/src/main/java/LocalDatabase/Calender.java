package LocalDatabase;

public class Calender {

    private int c_id;
    private int bsn_id;
    private String name;
    private String date;
    //private Date c_date;

    public Calender(int c_id, int bsn_id, String c_name, String c_date) {
        this.c_id = c_id;
        this.bsn_id = bsn_id;
        this.name = c_name;
        this.date = c_date;
    }

    public Calender() {
    }

    public String getC_name() {
        return name;
    }

    public void setC_name(String name) {
        this.name = name;
    }

    public String getC_date() {
        return date;
    }

    public void setC_date(String date) {
        this.date = date;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getBsn_id() {
        return bsn_id;
    }

    public void setBsn_id(int bsn_id) {
        this.bsn_id = bsn_id;
    }

    @Override
    public String toString() {
        return "Calender{" +
                "c_name='" + name + '\'' +
                ", c_date='" + date + '\'' +
                '}';


    }
}
