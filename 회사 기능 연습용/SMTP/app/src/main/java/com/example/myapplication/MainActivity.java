package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    EditText txtEmail,txtMessage;
    Button btn_send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.edt_Email);
        txtMessage = findViewById(R.id.edt_Message);
        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = "이곳은 이메일 보내는 사람의 메일주소 쓰는곳 (구글)";
                final String password = "이곳은 이메일 보내는 사람의 메일 비밀번호";

                String messageToSend = txtMessage.getText().toString();
                Properties props = new Properties();
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.port","587");
                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator(){
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });

                try{
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress(username));
                  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtEmail.getText().toString()));
                  message.setSubject("이부분은 메일 제목을 쓰는곳입니다.");
                  message.setText(messageToSend);
                  Transport.send(message);
                    Toast.makeText(getApplicationContext(),"이메일이 성공적으로 전송되었습니다.",Toast.LENGTH_SHORT).show();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }
}