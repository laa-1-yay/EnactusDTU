package com.app.enactusdtu;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private TextView displayTime;
    private LinearLayout pickTime;

    private int pHour;
    private int pMinute;
    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_ID = 1;


    SharedPreferences user;

    String username = null ;
    String useremailid = null ;
    String userdob = null ;
    String usermobile = null ;



    Dialog inviteoption;

    Button invitepoolfriends;

    AutoCompleteTextView projectname;
    AutoCompleteTextView membername;
    EditText duration;
    LinearLayout targetdate;
    EditText valuetargetdate;
    EditText description;

    private int pYear;
    private int pMonth;
    private int pDay;

    Button phonecontacts;
    Button emailcontacts;

    static final int DATE_DIALOG_ID = 0;

    String organizerphonetext =null;
    int totalamounttext=0;
    String poolnametext =null;

    String returnname =  null ;
    String returnduration = null;
    String returnprojectname = null;
    String returndate = null;
    String returndescription = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));




        String names[]= {"AMLAN", "DIVYAM", "SANCHIT", "AKANSHA", "ANURAG", "ISHITA V.", "JHILAKSHI", "SARTHAK", "SHASHANK", "SHIKHAR", "SIDDHIKA", "VIDUSHI", "AMBER", "AYUSH", "PIYUSH", "RICHA", "AMBESH", "HIMANSHU", "MANISH", "ASHUTOSH", "SAURABH", "GAURAV", "AMIT", "MODASSIR", "SUVEER", "APOORVA", "ISHITA", "LAAVANYE", "MAYUR", "PARIDHI", "SHREYA", "TANMAY", "ARUSHI J.", "MANASI", "NITANSHI", "SAGARI", "UTSAV", "SATYANSHU", "ADESH", "ADITYA", "SHIVAM", "ADRIJA", "GAUTAM G.", "KISHAN", "POORVA", "RISHAV", "SHIVALI G.", "TRIJUL", "ARPIT", "ISHA S.", "JIGYASA", "NAMYA", "PRAGATI", "SHUBHANGI", "AKSHIT", "GARIMA", "VIBHU"};

        invitepoolfriends = (Button) findViewById(R.id.invitefriends);

//        invitepoolfriends.setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        projectname = (AutoCompleteTextView) findViewById(R.id.poolname);
        membername = (AutoCompleteTextView) findViewById(R.id.phoneorganizer);
        duration = (EditText) findViewById(R.id.totalamount);
        valuetargetdate = (EditText) findViewById(R.id.valuedate);
        description = (EditText) findViewById(R.id.description);

        membername.setText(usermobile);

        targetdate = (LinearLayout) findViewById(R.id.targetdate);

        duration.setClickable(false);

        valuetargetdate.setClickable(false);
        valuetargetdate.setKeyListener(null);

        final Drawable originalDrawable = projectname.getBackground();

        String str[]={"Project Prakriti", "Project Chhaap", "Project Unmoolan"};


        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,str);

        projectname.setThreshold(1);
       projectname.setAdapter(adp);

        ArrayAdapter<String> adp2=new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,names);

        membername.setThreshold(1);
        membername.setAdapter(adp2);

        projectname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean arg1) {
                if (projectname.isFocused()) {
                    projectname.setBackground(originalDrawable);
                }
            }
        });

//        membername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View arg0, boolean arg1) {
//                if(membername.isFocused()) {
//                    membername.setBackground(originalDrawable);
//                }
//            }
//        });

//        duration.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View arg0, boolean arg1) {
//                if(duration.isFocused()) {
//                    duration.setBackground(originalDrawable);
//                }
//            }
//        });


        targetdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                valuetargetdate.setBackground(originalDrawable);

            }
        });

        /** Get the current date */
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        /** Display the current date in the TextView */
//        updateDisplay();


        invitepoolfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                returnprojectname = projectname.getText().toString();
                returndate = valuetargetdate.getText().toString();
                returnname = membername.getText().toString();
                returndescription = description.getText().toString();
                returnduration = duration.getText().toString();


                if (returnname.length() == 0 ||  returndate.length() == 0 ||   returndescription.length() == 0 ||
                        returnduration.length() == 0 ||
                        returnprojectname.length() == 0) {
//                    Toast.makeText(getApplicationContext(), "Please fill in all the details before you proceed !", Toast.LENGTH_SHORT).show();

                    if (returnname.length() == 0) {
                        membername.setBackgroundResource(R.drawable.redtextfield);
                        Toast.makeText(getApplicationContext(), "Enter your name", Toast.LENGTH_SHORT).show();
                    }
//                    if ( returnname.length() == 0 || (returnname.length() > 0 && returnname.length() < 10)) {
//                        membername.setBackgroundResource(R.drawable.redtextfield);
//                        Toast.makeText(getApplicationContext(), "Invalid number : Should be of 10 digits", Toast.LENGTH_SHORT).show();
//                    }
//                    if ( (returnname.charAt(0) == '6') || (returnname.charAt(0) == '5') || (returnname.charAt(0) == '4') || (returnname.charAt(0) == '3') || (returnname.charAt(0) == '2') || (returnname.charAt(0) == '1')) {
//                        membername.setBackgroundResource(R.drawable.redtextfield);
//                        Toast.makeText(getApplicationContext(), "Invalid number : Number should start with 9 or 8 or 7 ", Toast.LENGTH_SHORT).show();
//                    }

//                    if (returnduration.length()==0) {
//                        duration.setBackgroundResource(R.drawable.redtextfield);
//                        Toast.makeText(getApplicationContext(), "Minimum amount should be Rs 100", Toast.LENGTH_SHORT).show();
//                    }
//                    if (returntotalamounttextvalue < 100) {
//                        duration.setBackgroundResource(R.drawable.redtextfield);
//                        Toast.makeText(getApplicationContext(), "Minimum amount should be Rs 100", Toast.LENGTH_SHORT).show();
//                    }
                    if (returnprojectname.length() == 0) {
                        projectname.setBackgroundResource(R.drawable.redtextfield);
                        Toast.makeText(getApplicationContext(), "Enter a project name", Toast.LENGTH_SHORT).show();
                    }
                    if (returndate.length() == 0) {
                        valuetargetdate.setBackgroundResource(R.drawable.redtextfield);
                        Toast.makeText(getApplicationContext(), "Enter a valid date", Toast.LENGTH_SHORT).show();
                    }

                    if (returndescription.length() == 0) {
                        description.setBackgroundResource(R.drawable.redtextfield);
                        Toast.makeText(getApplicationContext(), "Enter a description", Toast.LENGTH_SHORT).show();
                    }

                    if (returnduration.length() == 0) {
                        duration.setBackgroundResource(R.drawable.redtextfield);
                        Toast.makeText(getApplicationContext(), "Enter the duration", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(getApplicationContext(), "Data added !", Toast.LENGTH_LONG).show();
                    

                }
            }
        });




        /** Capture our View elements */
        pickTime = (LinearLayout) findViewById(R.id.picktime);

        /** Listener for click event of the button */
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

//        /** Get the current time */
//        final Calendar cal2 = Calendar.getInstance();
//        pHour = cal2.get(Calendar.HOUR_OF_DAY);
//        pMinute = cal2.get(Calendar.MINUTE);
//
//        /** Display the current time in the TextView */
//        updateDisplay2();
    }

    /** Create a new dialog for time picker */





    /**
     * Create a new dialog for date picker
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog d = new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);

                return d;

            case TIME_DIALOG_ID:
                TimePickerDialog timePickerDialog =
                 new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute, true);
                timePickerDialog.setTitle("Duration of the activity in HH:MM");

                return timePickerDialog;
        }
        return null;
    }


    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplay2();
                    displayToast();
                }
            };

    /** Updates the time in the TextView */
    private void updateDisplay2() {
        duration.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(" hours ")
                        .append(pad(pMinute)).append(" min")
        );
    }

    /** Displays a notification when the time is updated */
    private void displayToast() {
        Toast.makeText(getApplicationContext(), new StringBuilder().append("Duration is ").append(duration.getText()),   Toast.LENGTH_SHORT).show();

    }

    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();
                    //  displayToast();
                }
            };

    /**
     * Updates the date in the TextView
     */
    private void updateDisplay() {
        valuetargetdate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(String.format("%02d", (pYear))).append("-")
                        .append(String.format("%02d", (pMonth + 1))).append("-")
                        .append(String.format("%02d", pDay)).append("")
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
