package com.example.android.offers;


import android.util.Log;

import java.sql.*;

/**
 * Created by aldab2 on 30/11/18.
 */

public class MySQLConnector {

    public static void main(String args[]) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql150.main-hosting.eu:3306/u572021306_ytuju", "u572021306_uxyze", "Root@2018");
            //here project is database name, root is username and password is ics324
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Company");

            while (rs.next()) {
                Log.d("LoginActivity", rs.getString(1) + "  " + rs.getString(2));
                //rs.getInt(1) is the first column and rs.getString(2) is the second column..
                //You have to take care of the mapping on your own here.


            }
            con.close();
        } catch (Exception e) {
            Log.wtf("LoginActivity", e.getMessage());
        }
    }

       public void runConnection(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://sql150.main-hosting.eu:3306/u572021306_ytuju","u572021306_uxyze","Root@2018");
            //here project is database name, root is username and password is ics324
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Company");

            while(rs.next())
            {
                Log.d("LoginActivity",rs.getString(1)+"  "+rs.getString(2));
                //rs.getInt(1) is the first column and rs.getString(2) is the second column..
                //You have to take care of the mapping on your own here.


            }
            con.close();

    }
        catch(Exception e){  Log.wtf("LoginActivity", e.getMessage()+"........");}

    }



}
