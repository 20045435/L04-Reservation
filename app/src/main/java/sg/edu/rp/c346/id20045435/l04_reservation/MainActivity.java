package sg.edu.rp.c346.id20045435.l04_reservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    TimePicker tp;
    CheckBox cbSmoke;
    TextView tvName, tvMobileNo, tvSize, tvDisplay;
    EditText etName, etMobileNo, etSize;
    Button btnReserve, btnReset;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        cbSmoke = findViewById(R.id.checkBoxSmoke);
        tvName = findViewById(R.id.textViewName);
        tvMobileNo = findViewById(R.id.textViewMobileNo);
        tvSize = findViewById(R.id.editTextSize);
        tvDisplay = findViewById(R.id.textViewDisplay);
        etName = findViewById(R.id.editTextName);
        etMobileNo = findViewById(R.id.editTextPhoneNo);
        etSize = findViewById(R.id.editTextSize);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);

        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);
        dp.updateDate(2021,5,1);

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String size = etSize.getText().toString();
                String mobileNo = etMobileNo.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(size) || TextUtils.isEmpty(mobileNo)) {
                    Toast.makeText(MainActivity.this,"Please enter all the required information.", Toast.LENGTH_LONG).show();
                }
                else {
                    String display = "Hi " + name + ", your reservation for " + size + " is confirmed. Please come at "
                            + tp.getCurrentHour() + ":" + String.format("%02d", tp.getCurrentMinute())
                            + " on " + dp.getDayOfMonth() + "/" + (dp.getMonth()+1) + "/" + dp.getYear() + ". ";
                    tvDisplay.setText(display);

                    if (cbSmoke.isChecked() == true) {
                        display += "Requested for Smoking Area.";
                    }
                    else {
                        display += "Requested for Non-Smoking Area.";
                    }
                    tvDisplay.setText(display);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etName.setText("");
                etMobileNo.setText("");
                etSize.setText("");
                tvDisplay.setText("");
                cbSmoke.setChecked(false);
                dp.updateDate(2021, 5, 1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                if ((hourOfDay >= 8 && minute >= 0) || (hourOfDay <= 20 && minute <= 59)) {
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please select a date and time after today.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}