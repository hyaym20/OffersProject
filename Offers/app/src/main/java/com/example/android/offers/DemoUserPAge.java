package com.example.android.offers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import com.squareup.picasso.Picasso;

import com.example.android.offers.MySQLConnector;
import com.example.android.offers.OffersInfoAdapter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DemoUserPAge extends AppCompatActivity {

    private static ArrayList<OffersInfoAdapter> foodItemArrayList;  //List items Array
    private static ArrayList<OffersInfoAdapter> electronicItemArrayList;
    private MyAppAdapter myAppAdapter; //Array Adapter
    private static ListView listView; // Listview
    private boolean success = false; // boolean
    private SwipeRefreshLayout swipeLayout;
    private MySQLConnector connectionClass; //Connection Class Variable
    ViewPager viewPager;
    TabLayout tabLayout;
    UserFragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_user_page);

        listView = (ListView) findViewById(android.R.id.list); //Listview Declaration
        connectionClass = new MySQLConnector(); // Connection Class Initialization
        foodItemArrayList = new ArrayList<OffersInfoAdapter>(); // Arraylist Initialization
        electronicItemArrayList = new ArrayList<OffersInfoAdapter>();
        viewPager = findViewById(R.id.view_pager);
       /* swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });*/
       /* swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);*/


        fragmentAdapter = new UserFragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(fragmentAdapter);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        // Calling Async Task
         SyncData orderData = new SyncData();
        orderData.execute("");


    }

    // Async Task has three overrided methods,
    private class SyncData extends AsyncTask<String, String, String> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(DemoUserPAge.this, "Synchronising",
                    "Listview Loading! Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            try {
                //Connection conn = connectionClass.CONN(); //Connection Object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://sql150.main-hosting.eu:3306/u572021306_ytuju", "u572021306_uxyze", "Root@2018");
                if (conn == null) {
                    success = false;
                } else {
                    // Change below query according to your own database.
                    String electronicQuery = "SELECT e.DeviceName,o.discountPrice,c.name from Electronics e join Offer o JOIN Product p JOIN Company c on o.ProductID = p.proID and  e.proID = p.proID and c.compID=p.compID";
                    String foodQuery = "SELECT f.name,o2.discountPrice,c2.name from Food f JOIN Offer o2 JOIN Product p2 JOIN Company c2 on o2.ProductID = p2.proID and  f.proID = p2.proID and c2.compID=p2.compID;";
                    Statement stmt1 = conn.createStatement();
                    Statement stmt2 = conn.createStatement();
                    ResultSet foodRs = stmt1.executeQuery(foodQuery);
                    ResultSet electronicRs = stmt2.executeQuery(electronicQuery);
                    if (foodRs != null && electronicRs != null) // if resultset not null, I add items to itemArraylist using class created
                    {
                        while (foodRs.next() || electronicRs.next()) {
                            try {
                                if (foodRs.next())
                                    foodItemArrayList.add(new OffersInfoAdapter(foodRs.getString(1), foodRs.getString(2), foodRs.getString(3)));
                                if (electronicRs.next())
                                    electronicItemArrayList.add(new OffersInfoAdapter(electronicRs.getString(1), electronicRs.getString(2), electronicRs.getString(3)));

                                // itemArrayList.add(new OffersInfoAdapter(rs.getString("Fname"),rs.getString("Phone"),rs.getString("Mail")));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        msg = "Found";
                        success = true;
                    } else {
                        msg = "No Data found!";
                        success = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) // disimissing progress dialoge, showing error and setting up my listview
        {
            progress.dismiss();
            Toast.makeText(DemoUserPAge.this, msg + "....Loading Data", Toast.LENGTH_LONG).show();
            if (success == false) {
            } else {
                try {
                  /*  myAppAdapter = new MyAppAdapter(itemArrayList, DemoUserPAge.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);*/
                } catch (Exception ex) {

                }

            }

        }
    }

    public class MyAppAdapter extends BaseAdapter         //has a class viewholder which holds
    {
        public class ViewHolder {
            TextView textName;
            //  ImageView imageView;
        }

        public List<OffersInfoAdapter> parkingList;

        public Context context;
        ArrayList<OffersInfoAdapter> arraylist;

        private MyAppAdapter(List<OffersInfoAdapter> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<OffersInfoAdapter>();
            //arraylist.addAll(parkingList);
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) // inflating the layout and initializing widgets
        {

            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.demo_list_conetent, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView) rowView.findViewById(R.id.textName);
                //0 viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // here setting up names and images
            // viewHolder.textName.setText(parkingList.get(position).getName()+"\t"+parkingList.get(position).getMobileNumber()+"\t"+parkingList.get(position).getPlaceLocation());
            // Picasso.with(context).load("http://"+parkingList.get(position).getImg()).into(viewHolder.imageView);

            return rowView;
        }
    }

    public static class DemoFoodFragment extends Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



            /*
            LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.demo_list_conetent, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView) rowView.findViewById(R.id.textName);
               //0 viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
                rowView.setTag(viewHolder);

             */
            inflater = getLayoutInflater();
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);
            final ArrayList<OffersInfoAdapter> OffersInfoAdapters = new ArrayList<OffersInfoAdapter>();

            //  OffersInfoAdapters.add(new OffersInfoAdapter(getString(R.string.type), getString(R.string.price), getString(R.string.location)));
          /*  for(int i=0;i<foodItemArrayList.size();i++){
                OffersInfoAdapters.add(foodItemArrayList.get(i));
            }*/
            OffersAdapter adapter = new OffersAdapter(getActivity(), foodItemArrayList, R.color.blue);

            listView = rootView.findViewById(android.R.id.list);
            listView.setAdapter(adapter);

            return rootView;
        }
    }


    public static class DemoElectronicFragment extends Fragment {



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




           /* LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.demo_list_conetent, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView) rowView.findViewById(R.id.textName);
               //0 viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
                rowView.setTag(viewHolder);*/


            inflater = getLayoutInflater();
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);
            final ArrayList<OffersInfoAdapter> OffersInfoAdapters = new ArrayList<OffersInfoAdapter>();

            //  OffersInfoAdapters.add(new OffersInfoAdapter(getString(R.string.type), getString(R.string.price), getString(R.string.location)));
          /*  for(int i=0;i<electronicItemArrayList.size();i++){
                OffersInfoAdapters.add(electronicItemArrayList.get(i));
            }*/
           OffersAdapter adapter = new OffersAdapter(getActivity(), electronicItemArrayList, R.color.blue);
            listView = rootView.findViewById(android.R.id.list);
            listView.setAdapter(adapter);

            return rootView;
        }

    }
}
