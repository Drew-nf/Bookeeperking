package LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, "bk_main.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatment = "CREATE TABLE login(username TEXT PRIMARY KEY, password TEXT, is_acc BOOL)";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE bsn_id(bsn_id INTEGER PRIMARY KEY AUTOINCREMENT, c_username TEXT, a_username TEXT,"+
                                "FOREIGN KEY(c_username)"+
                                    "REFERENCES client_login(c_username),"+
                                "FOREIGN KEY(a_username)"+
                                    "REFERENCES accountant_login(a_username))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE employee(employee_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, f_name TEXT,"+
                                "m_name TEXT, l_name TEXT, address TEXT, state TEXT, city TEXT, zip TEXT, phone TEXT, ssn TEXT, "+
                                "allowances INTEGER, p_rotation TEXT, is_married BOOL, active BOOL,"+
                                "FOREIGN KEY(bsn_id)" +
                                    "REFERENCES bsn_id(bsn_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE payroll(payroll_id INTEGER PRIMARY KEY AUTOINCREMENT, employee_id INTEGER, check_num TEXT,"+
                                "paid_on TEXT, pay_end TEXT, hours_work TEXT, total_pay TEXT, net_pay TEXT,"+
                                "FOREIGN KEY(employee_id)"+
                                    "REFERENCES employee(employee_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE vendor(vendor_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, vendor_name TEXT, vendor_type TEXT, acc_num TEXT,"+
                                "FOREIGN KEY(bsn_id)"+
                                    "REFERENCES bsn_id(bsn_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE invoice(invoice_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, gL TEXT, vendor_id TEXT,is_tax_deductible BYTE,"+
                                "invoice_num TEXT, item TEXT, amount TEXT, i_date TEXT, pay_method TEXT,"+
                                "FOREIGN KEY(bsn_id)"+
                                    "REFERENCES bsn_id(bsn_id),"+
                                "FOREIGN KEY(vendor_id)"+
                                    "REFERENCES vendor(vendor_id))";
        db.execSQL(createTableStatment);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addLogin(Login login){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", login.getUsername());
        cv.put("password", login.getPassword());
        cv.put("is_acc", login.getIs_acc());
        long insert = db.insert("login", null,cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addPayroll(Payroll payroll){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("employee_id", payroll.getEmployee_id());
        cv.put("check_num",payroll.getCheck_num());
        cv.put("paid_on",payroll.getPaid_on());
        cv.put("pay_end",payroll.getPay_end());
        cv.put("hours_work",payroll.getHours_work());
        cv.put("total_pay",payroll.getTotal_pay());
        cv.put("net_pay",payroll.getNet_pay());
        long insert = db.insert("payroll", null,cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bsn_id",employee.getBsn_id());
        cv.put("f_name", employee.getF_name());
        cv.put("m_name", employee.getM_name());
        cv.put("l_name", employee.getL_name());
        cv.put("address", employee.getAddress());
        cv.put("state", employee.getState());
        cv.put("city", employee.getCity());
        cv.put("zip", employee.getZip());
        cv.put("phone", employee.getPhone());
        cv.put("ssn", employee.getSsn());
        cv.put("allowances", employee.getAllowances());
        cv.put("p_rotation", employee.getP_rotation());
        cv.put("is_married", employee.isIs_married());
        cv.put("active", employee.isActive());
        long insert = db.insert("employee", null,cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addInvoice(Invoice invoice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bsn_id",invoice.getBsn_id());
        cv.put("vendor_id",invoice.getVendor_id());
        cv.put("gL", invoice.getgL());
        cv.put("is_tax_deductible", invoice.getIs_tax_deductible());
        cv.put("invoice_num",invoice.getInvoice_num());
        cv.put("item",invoice.getItem());
        cv.put("amount",invoice.getAmount());
        cv.put("i_date", invoice.getI_date());
        cv.put("pay_method",invoice.getPayMethod());
        long insert = db.insert("invoice", null,cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public List<Employee> getAllEmployee(){
        List<Employee> returnList= new ArrayList<>();
        String queryString = "SELECT * FROM employee";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int employee_id=cursor.getInt(0);
                int bsn_id=cursor.getInt(1);
                String f_name=cursor.getString(2);
                String m_name=cursor.getString(3);
                String l_name=cursor.getString(4);
                String address=cursor.getString(5);
                String state=cursor.getString(6);
                String city= cursor.getString(7);
                String zip= cursor.getString(8);
                String phone= cursor.getString(9);
                String ssn= cursor.getString(10);
                int allowances= cursor.getInt(11);
                String p_rotation = cursor.getString(12);
                boolean is_married=cursor.getInt(13)== 1? true:false;
                boolean active=cursor.getInt(14)==1?true:false;

                Employee newEmployee = new Employee(employee_id,bsn_id,f_name,m_name,l_name,address,state,
                        city,zip,phone,ssn,allowances,p_rotation,is_married,active);
                returnList.add(newEmployee);
            }while(cursor.moveToNext());
        }
        else{
            //failure. do not add anything to list.
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<Invoice> getAllInvoice(){
        List<Invoice> returnList= new ArrayList<>();
        String queryString = "SELECT * FROM invoice";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int invoice_id=cursor.getInt(0);
                int bsn_id=cursor.getInt(1);
                String gL=cursor.getString(2);
                String vendor_id=cursor.getString(3);
                byte is_tax_deductible=(byte) cursor.getInt(4);
                String invoice_num=cursor.getString(5);
                String item=cursor.getString(6);
                String amount= cursor.getString(7);
                String i_date= cursor.getString(8);
                String pay_method = cursor.getString(9);


                Invoice newInvoice = new Invoice(invoice_id,bsn_id,gL,vendor_id,is_tax_deductible,invoice_num,item,
                        amount,i_date,pay_method);
                returnList.add(newInvoice);
            }while(cursor.moveToNext());
        }
        else{
            //failure. do not add anything to list.
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public Employee getEmployee(int id) {
        String queryString = "SELECT * FROM employee WHERE employee_id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {int employee_id=cursor.getInt(0);
            int bsn_id=cursor.getInt(1);
            String f_name=cursor.getString(2);
            String m_name=cursor.getString(3);
            String l_name=cursor.getString(4);
            String address=cursor.getString(5);
            String state=cursor.getString(6);
            String city= cursor.getString(7);
            String zip= cursor.getString(8);
            String phone= cursor.getString(9);
            String ssn= cursor.getString(10);
            int allowances= cursor.getInt(11);
            String p_rotation = cursor.getString(12);
            boolean is_married=cursor.getInt(13)== 1? true:false;
            boolean active=cursor.getInt(14)==1?true:false;

            Employee newEmployee = new Employee(employee_id,bsn_id,f_name,m_name,l_name,address,state,
                    city,zip,phone,ssn,allowances,p_rotation,is_married,active);
            cursor.close();
            db.close();
            return newEmployee;

        }
        else{
            int employee_id = 666;
            int bsn_id=666;
            String f_name="error";
            String m_name = "error";
            String l_name="error";
            String address="error";
            String state="error";
            String city= "error";
            String zip= "error";
            String phone= "error";
            String ssn= "error";
            int allowances= 0;
            String p_rotation = "error";
            boolean is_married=false;
            boolean active=false;

            Employee newEmployee = new Employee(employee_id,bsn_id,f_name,m_name,l_name,address,state,
                    city,zip,phone,ssn,allowances,p_rotation,is_married,active);
            cursor.close();
            db.close();
            return newEmployee;
        }

    }
    public Invoice getInvoice(int id) {
        String queryString = "SELECT * FROM invoice WHERE invoice_id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {int invoice_id=cursor.getInt(0);
            int bsn_id=cursor.getInt(1);
            String gL=cursor.getString(2);
            String vendor_id=cursor.getString(3);
            byte is_tax_deductible=(byte) cursor.getInt(4);
            String invoice_num=cursor.getString(5);
            String item=cursor.getString(6);
            String amount= cursor.getString(7);
            String i_date= cursor.getString(8);
            String pay_method = cursor.getString(9);

            Invoice newInvoice = new Invoice(invoice_id,bsn_id,gL,vendor_id,is_tax_deductible,invoice_num,item,
                    amount,i_date,pay_method);
            cursor.close();
            db.close();
            return newInvoice;

        }
        else{
            int invoice_id = 666;
            int bsn_id=666;
            String gL="666";
            String vendor_id="666";
            byte is_tax_deductible = 0;
            String invoice_num="error";
            String item="error";
            String amount= "error";
            String i_date= "error";
            String pay_method = "cash";

            Invoice newInvoice = new Invoice(invoice_id,bsn_id,gL,vendor_id,is_tax_deductible,invoice_num,item,
                    amount,i_date,pay_method);
            cursor.close();
            db.close();
            return newInvoice;
        }

    }
}

