package com.example.anandaron.yesmam;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends android.app.Fragment {

    private String userName=null;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.login_layout, container, false);
        ProgressBar progressBar= (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        Button signUp = (Button) rootView.findViewById(R.id.signUp_btn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.Fragment frag = new FacOrStuFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,frag).commit();
            }
        });
        Button signIn = (Button) rootView.findViewById(R.id.sign_in_btn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check Credentials
                final ProgressBar progressBar= (ProgressBar) rootView.findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("CSE5").child("Credentials");
                //for(int i=1;((myRef.child("Faculty").child(""+i).child("E-mail").getKey()==((EditText) rootView.findViewById(R.id.loginEmail)).getText().toString())&&(myRef.child("Faculty").child(""+i).child("Password").getKey()==((EditText) rootView.findViewById(R.id.loginPassword)).getText().toString()))||((myRef.child("Students").child(""+(15500+i)).child("E-mail").getKey()==((EditText) rootView.findViewById(R.id.loginEmail)).getText().toString())&&(myRef.child("Students").child(""+(i+15500)).child("Password").getKey()==((EditText) rootView.findViewById(R.id.loginPassword)).getText().toString()));i++){
                final String i="1";

                Query query = myRef.child("Faculty").orderByChild("E-mail").equalTo(((EditText) rootView.findViewById(R.id.loginEmail)).getText().toString());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {


                        if (snapshot.exists()) {

                            for (DataSnapshot Faculty : snapshot.getChildren()) {
                                Toast.makeText(getActivity(),Faculty.child("Password").getValue().toString()+((EditText) rootView.findViewById(R.id.loginPassword)).getText().toString(),
                                        Toast.LENGTH_LONG).show();


                                if(((EditText) rootView.findViewById(R.id.loginPassword)).getText().toString().equals(Faculty.child("Password").getValue().toString())){

                                    userName = Faculty.child("Name").getValue().toString();
                                    Toast.makeText(getActivity(),userName,
                                            Toast.LENGTH_LONG).show();
                                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                    p.edit().putString("USER_NAME",userName).apply();
                                    p.edit().putString("ACCESS","Faculty").apply();
                                    String un= null;
                                    un=p.getString("USER_NAME",null);
                                    Toast.makeText(getActivity(),"pref"+un,
                                            Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getActivity(),HomeActivity.class);
                                    startActivity(i);
                                }


                            }
                        }


                    }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Query query2 = myRef.child("Students").orderByChild("E-mail").equalTo(((EditText) rootView.findViewById(R.id.loginEmail)).getText().toString());
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {


                        if (snapshot.exists()) {

                            for (DataSnapshot Students : snapshot.getChildren()) {

                                if(((EditText) rootView.findViewById(R.id.loginPassword)).getText().toString().equals(Students.child("Password").getValue().toString())){

                                    userName = Students.child("Name").getValue().toString();
                                    Toast.makeText(getActivity(),userName,
                                            Toast.LENGTH_LONG).show();
                                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                    p.edit().putString("USER_NAME",userName).apply();
                                    p.edit().putString("ACCESS","Student").apply();
                                    String un= null;
                                    p.getString("USER_NAME",un);
                                    Toast.makeText(getActivity(),"pref"+un,
                                            Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getActivity(),HomeActivity.class);
                                    startActivity(i);
                                }
                                else
                                    Toast.makeText(getActivity(),"Invalid Credentials",
                                            Toast.LENGTH_LONG).show();



                            }
                        }


                    }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

        return rootView;
    }

}
