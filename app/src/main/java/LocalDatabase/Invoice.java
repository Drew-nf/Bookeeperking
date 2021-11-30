package LocalDatabase;

public class Invoice {
    private int invoice_id;
    private int bsn_id;
    private int gL;
    private int vendor_id;
    private String invoice_num;
    private String item;
    private String amount;
    private String i_date;

    public Invoice(int invoice_id,int bsn_id, int gL, int vendor_id,String invoice_num,String item,
                   String amount,String i_date){
        this.invoice_id = invoice_id;
        this.bsn_id = bsn_id;
        this.gL = gL;
        this.vendor_id = vendor_id;
        this.invoice_num = invoice_num;
        this.item = item;
        this.amount = amount;
        this.i_date = i_date;
    }

    public Invoice(){

    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", bsn_id=" + bsn_id +
                ", vendor_id=" + vendor_id +
                ", invoice_num='" + invoice_num + '\'' +
                ", item='" + item + '\'' +
                ", amount='" + amount + '\'' +
                ", i_date='" + i_date + '\'' +
                '}';
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getBsn_id() {
        return bsn_id;
    }

    public void setBsn_id(int bsn_id) {
        this.bsn_id = bsn_id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getInvoice_num() {
        return invoice_num;
    }

    public void setInvoice_num(String invoice_num) {
        this.invoice_num = invoice_num;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getgL() { return gL; }

    public void setgL(int gL) { this.gL = gL; }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getI_date() {
        return i_date;
    }

    public void setI_date(String i_date) {
        this.i_date = i_date;
    }
}
