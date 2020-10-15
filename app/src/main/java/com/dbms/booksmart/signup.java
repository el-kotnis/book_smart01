package com.dbms.booksmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText entpass=findViewById (R.id.entpass) ;
        final EditText entphone =findViewById (R.id.entphone);
        final EditText entname=findViewById(R.id.entname);
        Button btnsignup =findViewById(R.id.btnsignup);
        FirebaseDatabase database = FirebaseDatabase.getInstance() ;
        final DatabaseReference table_user = database.getReference ("User");
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(entphone.getText().toString()).getValue(User.class)!=null) {
                            User user = new User(entname.getText().toString(),entpass.getText().toString());
                            table_user.child(entphone.getText().toString()).setValue(user);
                            Toast.makeText(signup.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(signup.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}