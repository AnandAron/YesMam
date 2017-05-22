package Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.anandaron.yesmam.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class _DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    protected int day,month,year;
    public _DatePickerFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
         year = c.get(Calendar.YEAR);
         month = c.get(Calendar.MONTH);
         day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {

        View inflatedView = getLayoutInflater(new Bundle()).inflate(R.layout.fragment_attendance,null);
        Button btn_date= (Button) inflatedView.findViewById(R.id.btn_date);
        btn_date.setText("Date:"+day+"/"+month+"/"+year);
    }
}
