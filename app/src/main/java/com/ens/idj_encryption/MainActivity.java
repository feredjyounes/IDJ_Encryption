package com.ens.idj_encryption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText plain_Text,key,cipher_Text,phone;
    String getPlain_Text , getCipher_Text , getPhone;
    int getKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plain_Text  = findViewById(R.id.plainText);
        key         = findViewById(R.id.key);
        cipher_Text = findViewById(R.id.ciphertext);
        phone       = findViewById(R.id.phone);
    }

    public static String encryptMessage(String plainText, int key) {
        StringBuilder chipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            if (Character.isLetter(ch)) {
                char shifted = (char) (((ch - 'A') + key) % 26 + 'A');
                chipherText.append(shifted);
            } else {
                chipherText.append(ch);
            }
        }
        return chipherText.toString();
    }

    public static String decryptMessage(String chipherText, int key) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < chipherText.length(); i++) {
            char ch = chipherText.charAt(i);
            if (Character.isLetter(ch)) {
                char shifted = (char) (((ch - 'A') - key + 26) % 26 + 'A');
                plainText.append(shifted);
            } else {
                plainText.append(ch);
            }
        }
        return plainText.toString();
    }




    public void sendSMS(View view) {

        getPhone      = phone.getText().toString().trim();
        getPlain_Text = plain_Text.getText().toString().toUpperCase();
        getKey = Integer.parseInt(key.getText().toString());

        getCipher_Text = encryptMessage(getPlain_Text,getKey).toLowerCase();
        cipher_Text.setText(getCipher_Text);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(getPhone, null, getCipher_Text, null, null);
        Toast.makeText(getApplicationContext(),"Message sent successfully",Toast.LENGTH_LONG).show();
    }


}