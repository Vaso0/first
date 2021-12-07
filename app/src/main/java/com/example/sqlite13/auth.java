package com.example.sqlite13;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class auth extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin,btnSign;
    EditText Login,Pass;
    DBhelper dbHelper;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnSign = (Button) findViewById(R.id.btnSign);
        btnSign.setOnClickListener(this);
        Login = (EditText) findViewById(R.id.Login);
        Pass = (EditText) findViewById(R.id.Pass);
        dbHelper = new DBhelper(this);
        database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.KEY_Name,"admin");
        contentValues.put(DBhelper.KEY_Password,"admin");
        database.insert(DBhelper.TABLE_USERS,null,contentValues);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLogin:
                Cursor findUser = database.query(DBhelper.TABLE_USERS,null,null,null,null,null,null);

                boolean check = false;

                if(findUser.moveToNext())
                {
                    int userlogInd = findUser.getColumnIndex(DBhelper.KEY_Name);
                    int userPass = findUser.getColumnIndex(DBhelper.KEY_Password);
                    do
                    {
                        if(Login.getText().toString().equals(findUser.getString(userlogInd)) && Pass.getText().toString().equals(findUser.getString(userPass)))
                        {
                            if(Login.getText().toString().equals("admin") &&  Pass.getText().toString().equals("admin"))
                            {

                                startActivity(new Intent(this, store.class));
                                check = true;
                                break;
                            }
                            else {
                                startActivity(new Intent(this, MainActivity.class));
                                check = true;
                                break;
                            }
                        }
                    }while (findUser.moveToNext());

                }
                findUser.close();
                if(!check)
                    Toast.makeText(this,"Данного пользователя нет в системе",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnSign:
                Cursor find = database.query(DBhelper.TABLE_USERS,null,null,null,null,null,null);

                boolean logged = false;

                if(find.moveToNext()) {
                    int userlogInd = find.getColumnIndex(DBhelper.KEY_Name);
                    int userPass = find.getColumnIndex(DBhelper.KEY_Password);
                    do {
                        if (Login.getText().toString().equals(find.getString(userlogInd)) && Pass.getText().toString().equals(find.getString(userPass))) {
                            Toast.makeText(this, "Данный пользователь уже есть в системе", Toast.LENGTH_LONG).show();
                            logged = true;
                            break;
                        }
                    } while (find.moveToNext());

                }
                if(!logged)
                {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DBhelper.KEY_Name,Login.getText().toString());
                    contentValues.put(DBhelper.KEY_Password,Pass.getText().toString());
                    database.insert(DBhelper.TABLE_USERS,null,contentValues);
                    Toast.makeText(this, "Успешная регистрация", Toast.LENGTH_LONG).show();
                }
                find.close();
                break;

        }
    }
}