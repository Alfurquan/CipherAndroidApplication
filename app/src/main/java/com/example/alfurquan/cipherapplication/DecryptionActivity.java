package com.example.alfurquan.cipherapplication;

import android.support.design.widget.Snackbar;
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

public class DecryptionActivity extends AppCompatActivity {
    EditText text;
    Button btn;
    TextView res;
    String myText,myRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);
        text = (EditText)findViewById(R.id.text);
        btn = (Button)findViewById(R.id.encrypt);
        res = (TextView)findViewById(R.id.result);
        res.setMovementMethod(new ScrollingMovementMethod());
        getSupportActionBar().setTitle("Decryption");
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
                InputMethodManager inputMethodManager = (InputMethodManager)DecryptionActivity.this.getSystemService(DecryptionActivity.INPUT_METHOD_SERVICE);
                view = DecryptionActivity.this.getCurrentFocus();
                if(view == null){
                    view = new View(DecryptionActivity.this);
                }
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
                myText = text.getText().toString();
                myRes = decrypt(myText,view);
                res.setText(myRes);
            }
        });
    }
    //function to Decrypt the text
    public String decrypt(String str, final View view){
        int i,c,c1,f=0;
        char ch;
        c1=0;
        String st2 = "";
        int flag=0;
        //Checking if String has just numbers
        for(i=0;i<str.length();i++)
        {
            if(Character.isDigit(str.charAt(i))){
                f++;
            }
        }
        if(f==str.length())
        {
            Snackbar.make(view,"Check Message",Snackbar.LENGTH_LONG).show();
            return "";
        }
        //Checking if last letter of text is a character and no number follows it
        if((Character.isLetter(str.charAt(str.length()-1)))){
            Snackbar.make(view,"Check Message",Snackbar.LENGTH_LONG).show();
            return "";
        }
        //Logic to check the correct format of message
        for(i=0;i<str.length()-1;i++)
        {
            if(!Character.isDigit(str.charAt(i)))
            {
                // System.out.println(se.charAt(i));
                if(!Character.isDigit(str.charAt(i+1)))
                {
                    flag=1;
                    break;
                }
            }
        }
        if(Character.isDigit(str.charAt(0)) && !Character.isDigit(str.charAt(str.length()-1)))
            flag=1;

        if(flag!=0)
        {
            Snackbar.make(view,"Check Message",Snackbar.LENGTH_LONG).show();
            return "";
        }
        //Decryption logic
        String number="";
        try
        {
            for(i=0;i<str.length();i++)
            {
                ch = str.charAt(i);
                while(i<str.length()-1 && Character.isDigit(str.charAt(i+1)))
                {
                    number=number+str.charAt(i+1);
                    i++;
                }
                c = Integer.parseInt(number);
                number="";
                //  System.out.println(c);
                while(c!=0)
                {
                    st2= st2+ch;
                    c--;
                }
            }
        }
        catch(Exception e){
            System.out.println("Caught");
        }
        return st2;
    }
}
