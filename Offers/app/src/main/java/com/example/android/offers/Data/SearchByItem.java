package com.example.android.offers.Data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.android.offers.R;

public class SearchByItem extends AppCompatActivity {

    private EditText itemNameEditText;

    /** the category RadioButton **/
    private RadioButton category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_item);


        //For the Search activity, must be Filled ...
    }
}
