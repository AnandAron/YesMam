package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.anandaron.yesmam.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateAttendanceFragment extends android.app.Fragment {
    private  static  int n;


    public UpdateAttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =inflater.inflate(R.layout.fragment_attendance, container, false);
        final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.attendanceProgressBar) ;
        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("CSE5").child("PersDet");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                n = (int)dataSnapshot.getChildrenCount();
                GridLayout layout = (GridLayout) rootView.findViewById(R.id.attendance_grid);
                int i;

                for(i=1;i<=n;i++){
                    Button btn = new Button(getActivity());
                    btn.setText(""+(15500+i));
                    layout.addView(btn);
                }
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),""+dataSnapshot.getChildrenCount(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }

}
