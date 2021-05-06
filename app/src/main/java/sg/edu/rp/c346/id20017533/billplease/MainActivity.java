package sg.edu.rp.c346.id20017533.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup RG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editTextNumberAmt);
        numPax = findViewById(R.id.editTextnopax);
        totalBill = findViewById(R.id.textViewTBill);
        eachPays = findViewById(R.id.textViewEPay);
        svs = findViewById(R.id.toggleButtonSVS);
        gst = findViewById(R.id.toggleButtonGST);
        split = findViewById(R.id.splitbutt);
        reset = findViewById(R.id.resetbutt);
        discount = findViewById(R.id.editTextdiscount);
        RG = findViewById(R.id.rgMode);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().trim().length() != 0 && numPax.getText().toString().trim().length() != 0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
                    //Discount
                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }
                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(numPax.getText().toString());
                    if (numPerson != 1)
                        eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                    else
                        eachPays.setText("Each Pays: $" + newAmt);
                }

            }
        });

    }
}