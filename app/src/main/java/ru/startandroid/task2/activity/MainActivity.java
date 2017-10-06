package ru.startandroid.task2.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import ru.startandroid.task2.R;
import ru.startandroid.task2.http.HttpHandler;

public class MainActivity extends AppCompatActivity {

    final Random random = new Random();
    private AHBottomNavigation bottomNavigation;
    private static LinearLayout mainlayout;
    private static LinearLayout contacts;
    public static int post_id;
    public static int comment_id;
    public static int user_id = 5;
    public static int photo_id = 3;
    public static int todo_id;
    public static ImageView phototext;
    public static TextView post_title;
    public static TextView post_body;
    public static TextView comment_name;
    public static TextView comment_email;
    public static TextView comment_body;
    public static TextView user_text;
    public static TextView todo_text;
    public static String post_str;
    public static String photo_str;
    public static String comment_str;
    public static String user_str;
    public static String user_str_all;
    public static String todo_str;
    public static String todo_str_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setBottomNavigation();
        addPost();
        addComment();
        addUser();
        addPhoto();
        addTodo();
    }
    class PostTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            HttpHandler handler = new HttpHandler();
            post_str = handler.getDataFromUrl("https://jsonplaceholder.typicode.com/posts?id=" + post_id);
            return true;
        }
        @Override
        protected void onPostExecute(Boolean ok) {
            super.onPostExecute(ok);
            if (!ok) {
                Toast.makeText(MainActivity.this, "Отсутствует интернет соединение", Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    JSONArray jsonArray = new JSONArray(post_str);
                    post_title.setText("title:  " + jsonArray.getJSONObject(0).getString("title"));
                    post_body.setText("body:  " + jsonArray.getJSONObject(0).getString("body"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class CommentTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            HttpHandler handler = new HttpHandler();
            comment_str = handler.getDataFromUrl("https://jsonplaceholder.typicode.com/comments?id=" + comment_id);
            return true;
        }
        @Override
        protected void onPostExecute(Boolean ok) {
            super.onPostExecute(ok);
            if (!ok) {
                Toast.makeText(MainActivity.this, "Отсутствует интернет соединение", Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    JSONArray jsonArray = new JSONArray(comment_str);
                    comment_name.setText("name:  " + jsonArray.getJSONObject(0).getString("name"));
                    comment_email.setText("email:  " + jsonArray.getJSONObject(0).getString("email"));
                    comment_body.setText("body:  " + jsonArray.getJSONObject(0).getString("body"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class UserTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            HttpHandler handler = new HttpHandler();
            user_str = handler.getDataFromUrl("https://jsonplaceholder.typicode.com/users?id=" + user_id);
            return true;
        }
        @Override
        protected void onPostExecute(Boolean ok) {
            super.onPostExecute(ok);
            if (!ok) {
                Toast.makeText(MainActivity.this, "Отсутствует интернет соединение", Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    JSONObject jsonObject = new JSONArray(user_str).getJSONObject(0);
                    user_str_all = "";
                    user_str_all += "name:  " + jsonObject.getString("name") + "\n";
                    user_str_all += "username:  " + jsonObject.getString("username") + "\n";
                    user_str_all += "email:  " + jsonObject.getString("email") + "\n";
                    String help = jsonObject.getString("address");
                    JSONObject jsonObject1 = new JSONObject(help);
                    user_str_all += "address:  \n";
                    user_str_all += "   street:  " + jsonObject1.getString("street") + "\n";
                    user_str_all += "   suite:  " + jsonObject1.getString("suite") + "\n";
                    user_str_all += "   zipcode:  " + jsonObject1.getString("zipcode") + "\n";
                    user_str_all += "   geo:  \n";
                    help = jsonObject1.getString("geo");
                    jsonObject1 = new JSONObject(help);
                    user_str_all += "       lat: " + jsonObject1.getString("lat") + "\n";
                    user_str_all += "       lng: " + jsonObject1.getString("lng") + "\n";
                    user_str_all += "phone:  " + jsonObject.getString("phone") + "\n";
                    user_str_all += "website:  " + jsonObject.getString("website") + "\n";
                    user_str_all += "company:  \n";
                    help = jsonObject.getString("company");
                    jsonObject1 = new JSONObject(help);
                    user_str_all += "   name:  " + jsonObject1.getString("name") + "\n";
                    user_str_all += "   catchPhrase:  " + jsonObject1.getString("catchPhrase") + "\n";
                    user_str_all += "   bs:  " + jsonObject1.getString("bs") + "\n";
                    user_text.setText(user_str_all);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class PhotoTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            HttpHandler handler = new HttpHandler();
            photo_str = handler.getDataFromUrl("https://jsonplaceholder.typicode.com/photos?id=" + photo_id);
            return true;
        }
        @Override
        protected void onPostExecute(Boolean ok) {
            super.onPostExecute(ok);
            if (!ok) {
                Toast.makeText(MainActivity.this, "Отсутствует интернет соединение", Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    JSONArray jsonArray = new JSONArray(photo_str);
                    Glide.with(MainActivity.this).load(jsonArray.getJSONObject(0).getString("url")).into(phototext);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class TodoTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            HttpHandler handler = new HttpHandler();
            todo_str = handler.getDataFromUrl("https://jsonplaceholder.typicode.com/todos?id=" + todo_id);
            return true;
        }
        @Override
        protected void onPostExecute(Boolean ok) {
            super.onPostExecute(ok);
            if (!ok) {
                Toast.makeText(MainActivity.this, "Отсутствует интернет соединение", Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    JSONObject jsonObject = new JSONArray(todo_str).getJSONObject(0);
                    todo_str_all = "";
                    todo_str_all += "id: " + jsonObject.getInt("id") + "\n";
                    todo_str_all += "title: " + jsonObject.getString("title") + "\n";
                    todo_str_all += "completed: " + jsonObject.getBoolean("completed") + "\n";
                    todo_text.setText(todo_str_all);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setBottomNavigation() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Main",
                R.drawable.ic_view_headline_black_24dp);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Contacts",
                R.drawable.ic_view_headline_black_24dp);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mainlayout = (LinearLayout) findViewById(R.id.mainlayout);
        contacts = (LinearLayout) findViewById(R.id.contacts);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if(position == 0) {
                    contacts.setVisibility(View.GONE);
                    mainlayout.setVisibility(View.VISIBLE);
                }
                else {
                    contacts.setVisibility(View.VISIBLE);
                    mainlayout.setVisibility(View.GONE);
                }
                return true;
            }
        });
    }
    public void addPost() {
        final LayoutInflater inflater = getLayoutInflater();
        final View itemView = inflater.inflate(R.layout.post, mainlayout, false);
        final Button btnpost = (Button) itemView.findViewById(R.id.button_post);
        post_title = (TextView) itemView.findViewById(R.id.posttitle);
        post_body = (TextView) itemView.findViewById(R.id.postbody);
        mainlayout.addView(itemView);
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) itemView.findViewById(R.id.editTextpost);
                String sedt  = editText.getText().toString();
                post_id = ToIntFunction(sedt);
                if(post_id >= 1 && post_id <= 100) {
                    PostTask postTask = new PostTask();
                    postTask.execute();
                }
                else {
                    Toast.makeText(MainActivity.this, "Пост ID должно составлять от 1 до 100", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void addComment() {
        final LayoutInflater inflater = getLayoutInflater();
        final View itemView = inflater.inflate(R.layout.commment, mainlayout, false);
        final Button btnpost = (Button) itemView.findViewById(R.id.button_comment);
        comment_name = (TextView) itemView.findViewById(R.id.comment_name);
        comment_email = (TextView) itemView.findViewById(R.id.comment_email);
        comment_body = (TextView) itemView.findViewById(R.id.comment_body);
        mainlayout.addView(itemView);
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) itemView.findViewById(R.id.editTextcommment);
                String sedt  = editText.getText().toString();
                comment_id = ToIntFunction(sedt);
                if(comment_id >= 1 && comment_id <= 500) {
                    CommentTask commentTask = new CommentTask();
                    commentTask.execute();
                }
                else {
                    Toast.makeText(MainActivity.this, "комментарий ID должно составлять от 1 до 500", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void addUser() {
        final LayoutInflater inflater = getLayoutInflater();
        final View itemView = inflater.inflate(R.layout.user, mainlayout, false);
        user_text = (TextView) itemView.findViewById(R.id.user_all);
        mainlayout.addView(itemView);
        UserTask userTask = new UserTask();
        userTask.execute();
    }
    public void addTodo() {
        final LayoutInflater inflater = getLayoutInflater();
        final View itemView = inflater.inflate(R.layout.todo, mainlayout, false);
        todo_text = (TextView) itemView.findViewById(R.id.todo_all);
        mainlayout.addView(itemView);
        todo_id = random.nextInt(200) + 1;
        TodoTask todoTask = new TodoTask();
        todoTask.execute();
    }
    public void addPhoto() {
        final LayoutInflater inflater = getLayoutInflater();
        final View itemView = inflater.inflate(R.layout.photo, mainlayout, false);
        phototext = itemView.findViewById(R.id.imageView);
        mainlayout.addView(itemView);
        PhotoTask photoTask = new PhotoTask();
        photoTask.execute();
    }
    public int ToIntFunction(String str) {
        int res = 0;
        for(int i = 0; i < str.length(); i ++) {
            res = res * 10 + (str.charAt(i) - 48);
            if(res > 100000)
                return  100000;
        }
        return  res;
    }
}
