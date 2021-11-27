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

        String createTableStatment = "CREATE TABLE client_login(c_username TEXT PRIMARY KEY, c_password TEXT)";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE accountant_login(a_username TEXT PRIMARY KEY, a_password TEXT)";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE bsn_id(bsn_id INTEGER PRIMARY KEY AUTOINCREMENT, c_username TEXT, a_username TEXT,"+
                                "FOREIGN KEY(c_username)"+
                                    "REFERENCES client_login(c_username),"+
                                "FOREIGN KEY(a_username)"+
                                    "REFERENCES accountant_login(a_username))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE employee(employee_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, f_name TEXT,"+
                                "l_name TEXT, address TEXT, state TEXT, city TEXT, zip TEXT, phone TEXT, ssn TEXT, "+
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

        createTableStatment = "CREATE TABLE invoice(invoice_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, vendor_id INTEGER, invoice_num TEXT,"+
                                "item TEXT, description TEXT, amount TEXT, i_date TEXT,"+
                                "FOREIGN KEY(bsn_id)"+
                                    "REFERENCES bsn_id(bsn_id),"+
                                "FOREIGN KEY(vendor_id)"+
                                    "REFERENCES vendor(vendor_id))";
        db.execSQL(createTableStatment);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

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
        cv.put("l_name", employee.getL_name());
        cv.put("address", employee.getAddress());
        cv.put("state", employee.getState());
        cv.put("city", employee.getCity());
        cv.put("zip", employee.getZip());
        cv.put("phone", employee.getPhone());
        cv.put("ssn", employee.getSsn());
        cv.put("allowances", employee.getAllowances());
        cv.put("p_rotation", employee.getAllowances());
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
        cv.put("invoice_num",invoice.getInvoice_num());
        cv.put("item",invoice.getItem());
        cv.put("description",invoice.getDescription());
        cv.put("amount",invoice.getAmount());
        cv.put("i_date", invoice.getI_date());
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
                String l_name=cursor.getString(3);
                String address=cursor.getString(4);
                String state=cursor.getString(5);
                String city= cursor.getString(6);
                String zip= cursor.getString(7);
                String phone= cursor.getString(8);
                String ssn= cursor.getString(9);
                int allowances= cursor.getInt(10);
                String p_rotation = cursor.getString(11);
                boolean is_married=cursor.getInt(12)== 1? true:false;
                boolean active=cursor.getInt(13)==1?true:false;

                Employee newEmployee = new Employee(employee_id,bsn_id,f_name,l_name,address,state,
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
}

