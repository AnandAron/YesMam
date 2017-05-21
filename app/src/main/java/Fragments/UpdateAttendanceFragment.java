package Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateAttendanceFragment extends android.app.Fragment {
    private  static  int n;
    final Map<String,String> attendance = new HashMap<String,String>();

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
        final Button submit = (Button) rootView.findViewById(R.id.att_submit);
        submit.setVisibility(View.INVISIBLE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("CSE5").child("PersDet");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                n = (int)dataSnapshot.getChildrenCount();

                final Iterator<DataSnapshot> children = dataSnapshot.getChildren().iterator();
                GridLayout layout = (GridLayout) rootView.findViewById(R.id.attendance_grid);
                int i;

                while(children.hasNext()){
                    final Button btn = new Button(getActivity());
                    btn.setBackgroundColor(Color.WHITE);
                    final String key = children.next().getKey().toString();
                    btn.setText(key);
                    btn.setTag(key);

                    attendance.put(key,"A");
                    layout.addView(btn);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (attendance.get(key).equals("P")){
                                attendance.put(key,"A");

                                btn.setBackgroundColor(Color.WHITE);
                            }

                            else if (attendance.get(key).equals("A")){
                                attendance.put(key,"P");
                                btn.setBackgroundColor(Color.BLUE);
                            }

                        }
                    });
                }
                submit.setVisibility(View.VISIBLE);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getActivity(),attendance.values().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
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
