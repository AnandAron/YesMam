package com.example.anandaron.yesmam;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewEntryActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_entry_layout);

    }

    public void update(View view){
        if(!isNetworkConnected()){
            Toast.makeText(getBaseContext(), "Check your Connection and try again",
                    Toast.LENGTH_LONG).show();
        }else{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("CSE5").child("PersDet").child(((EditText) findViewById(R.id.etRollNo)).getText().toString());
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.getValue() != null) {

                        // 1. Instantiate an AlertDialog.Builder with its constructor
                        // Create custom dialog object
                        final Dialog dialog = new Dialog(NewEntryActivity.this);
                        // Include dialog.xml file
                        dialog.setContentView(R.layout.alert_box);
                        // Set dialog title
                        dialog.setTitle("Custom Dialog");
                        dialog.show();
                        Button okButton = (Button) dialog.findViewById(R.id.alertOk);
                        Button cancelButton = (Button) dialog.findViewById(R.id.alertCancel);
                        // if decline button is clicked, close the custom dialog
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                updateValues();
                                dialog.dismiss();
                            }
                        });
                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });



                    } else {
                        updateValues();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getBaseContext(), "Unknown Error",
                            Toast.LENGTH_LONG).show();
                }

            });

        }


    }

    protected void updateValues(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("CSE5").child("PersDet").child(((EditText) findViewById(R.id.etRollNo)).getText().toString());
        myRef.child("Name").setValue(((EditText) findViewById(R.id.etName)).getText().toString());
        myRef.child("Gender").setValue(((EditText) findViewById(R.id.etGender)).getText().toString());
        myRef.child("Phone").setValue(((EditText) findViewById(R.id.etPhone)).getText().toString());
        myRef.child("E-mail").setValue(((EditText) findViewById(R.id.etEmail)).getText().toString());
        ((EditText) findViewById(R.id.etRollNo)).setText("");
        ((EditText) findViewById(R.id.etName)).setText("");
        ((EditText) findViewById(R.id.etGender)).setText("");
        ((EditText) findViewById(R.id.etPhone)).setText("");
        ((EditText) findViewById(R.id.etEmail)).setText("");
        Toast.makeText(getBaseContext(), "Values Updated Successfully",
                Toast.LENGTH_LONG).show();
    }
    public void retrieve(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("CSE5").child("PersDet");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                TextView tv1 = (TextView) findViewById(R.id.textView);
                String value = dataSnapshot.getValue(String.class);
                tv1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                TextView tv1 = (TextView) findViewById(R.id.textView);
                tv1.setText("Failed");;
            }
        });
    }
    public void cancelUpdate(){

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


}
