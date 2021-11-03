package com.example.bookkeepingking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
                                "l_name TEXT, address TEXT, state TEXT, city TEXT, zip TEXT, phone TEXT, ssn TEXT, pay DECIMAL(6,2),"+
                                "allowances INTEGER, p_rotation TEXT, is_married BOOL, active BOOL,"+
                                "FOREIGN KEY(bsn_id)" +
                                    "REFERENCES bsn_id(bsn_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE payroll(payroll_id INTEGER PRIMARY KEY AUTOINCREMENT, employee_id INTEGER, check_num TEXT,"+
                                "paid_on TEXT, pay_start TEXT, pay_end TEXT, hours_work DEMICAL(6,2), total_pay DECIMAL(9,2), net_pay DECIMAL(9,2),"+
                                "FOREIGN KEY(employee_id)"+
                                    "REFERENCES employee(employee_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE vendor(vendor_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, vendor_name TEXT, vendor_type TEXT, acc_num TEXT,"+
                                "FOREIGN KEY(bsn_id)"+
                                    "REFERENCES bsn_id(bsn_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE invoice(invoice_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, vendor_id INTEGER, invoice_num TEXT,"+
                                "item TEXT, description TEXT, amount DECIMAL(9,2), i_date TEXT, photo BLOB,"+
                                "FOREIGN KEY(bsn_id)"+
                                    "REFERENCES bsn_id(bsn_id),"+
                                "FOREIGN KEY(vendor_id)"+
                                    "REFERENCES vendor(vendor_id))";
        db.execSQL(createTableStatment);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

