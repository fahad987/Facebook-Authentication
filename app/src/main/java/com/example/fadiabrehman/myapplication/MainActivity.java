package com.example.fadiabrehman.myapplication;


                import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;


                import com.facebook.AccessToken;
                import com.facebook.CallbackManager;
                import com.facebook.FacebookCallback;
                import com.facebook.FacebookException;
                import com.facebook.GraphRequest;
                import com.facebook.GraphResponse;
                import com.facebook.login.LoginResult;
                import com.facebook.login.widget.LoginButton;
                import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

                import org.json.JSONObject;
                import org.w3c.dom.Text;

                import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";

    private EditText username, password;
    private Button signin;
    private TextView signup, forget;
    //String user,pass;
    // FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback <LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                AccessToken accessToken=loginResult.getAccessToken();
                GraphRequest graphRequest=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                    Display_data(object);
                    }
                });
                Bundle bundle=new Bundle();
                bundle.putString("fields","email,id");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();
            }



            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code

            }


        });


    }


    private void Display_data(JSONObject object){
        TextView textView=(TextView)findViewById(R.id.textview);
        textView.setText(object.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}








        /*username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.sigin);
        signup = (TextView) findViewById(R.id.signup);
        */// forget = (TextView)findViewById(R.id.forget);

        //firebaseAuth=FirebaseAuth.getInstance();

        //Now Going To see Email Verification using Firebase
        //Now Going to see whether email has verified or not
/*
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), sign_up.class);
                startActivity(i);
            }
        });*/

/*        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Forgot_Password.class));
            }
        });*/
    /*    signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=username.getText().toString();
                pass=password.getText().toString();
                if(valid())
                {

                    firebaseAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                               *//*finish();
                                Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),Welcome.class);
                                startActivity(i);*//*
                                checkEmail();
                            }
                            else
                            {
                                firebaseAuth.signOut();
                                Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });





    }


    private Boolean valid()
    {
        Boolean result=false;

        if(user.equals("")||pass.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Fill All The Fields",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }


        return  result;
    }




    private void checkEmail()
    {

        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();

        Boolean emailverify=firebaseUser.isEmailVerified(); //it return either true or false

        if(emailverify)
        {
            finish();
            Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),welcome.class));
        }
        else {
            firebaseAuth.signOut();
            Toast.makeText(getApplicationContext(),"Verify your Email",Toast.LENGTH_SHORT).show();
        }


    }





}*/
