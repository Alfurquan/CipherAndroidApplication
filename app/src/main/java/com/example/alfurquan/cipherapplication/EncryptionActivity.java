package com.example.alfurquan.cipherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EncryptionActivity extends AppCompatActivity {

    EditText text;
    Button btn;
    TextView res;
    String myText,myRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        text = (EditText)findViewById(R.id.text);
        btn = (Button)findViewById(R.id.encrypt);
        res = (TextView)findViewById(R.id.result);
        res.setMovementMethod(new ScrollingMovementMethod());
        getSupportActionBar().setTitle("Encryption");
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  //Disabling the button when text gets empty and making label empty on edit of text field.
                   String ss = text.getText().toString();
                   res.setText("");
                   btn.setEnabled(!ss.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager)EncryptionActivity.this.getSystemService(DecryptionActivity.INPUT_METHOD_SERVICE);
                view = EncryptionActivity.this.getCurrentFocus();
                if(view == null){
                    view = new View(EncryptionActivity.this);
                }
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
                myText = text.getText().toString();
                myRes = encrypt(myText);
                res.setText(myRes);
            }
        });
    }
    //function to encrypt the text
    public String encrypt(String str){
        int i,j,c;
        char ch;
        String st1="";
        for(i=0;i<str.length();i++){
            ch = str.charAt(i);
            j=i;
            c=1;
            //Case for just One character in the text
            if(i==str.length()-1)
            {
                st1 = st1+ch+String.valueOf(c);
                break;
            }
            while(j<str.length()-1 && (str.charAt(j)==str.charAt(j+1)))
           {
                c++;
                j++;
                i++;
            }
            st1 = st1+ch+String.valueOf(c);
        }
    return  st1;
    }
}
