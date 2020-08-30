package com.example.coruscate_task;
// import libraries
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class Spacecraft {

        /* INSTANCE FIELDS */
        //id
        @SerializedName("id")
        private int id;
        //author name
        @SerializedName("author")
        private String author;
        //url
        @SerializedName("url")
        private String url;
        //url
        @SerializedName("download_url")
        private String download_url;
        //height
        @SerializedName("height")
        private int height;
        //width
        @SerializedName("width")
        private int width;

        //generate getter setter methods
        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        @Override
        public String toString() {
            return author;
        }

        public Spacecraft(int id, String author, String url, String download_url,int height,int width) {
            this.id = id;
            this.author = author;
            this.url = url;
            this.download_url = download_url;
            this.height=height;
            this.width=width;
        }
    }

    //create interface
    interface MyAPIService {
        //get url
        @GET("/v2/list?page=2&limit=10")
        Call<List<Spacecraft>> getSpacecrafts();
    }

    //class
    static class RetrofitClientInstance {

        private static Retrofit retrofit;
        //base url
        private static final String BASE_URL = "https://picsum.photos/v2";

        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

    //display data to grid view
    //extend  BaseApter

    class GridViewAdapter extends BaseAdapter {
        private List<Spacecraft> spacecrafts;
        private Context context;

        public GridViewAdapter(Context context, List<Spacecraft> spacecrafts) {
            this.context = context;
            this.spacecrafts = spacecrafts;
        }

        @Override
        public int getCount() {
            return spacecrafts.size();
        }

        @Override
        public Object getItem(int pos) {
            return spacecrafts.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view=LayoutInflater.from(context).inflate(R.layout.model,viewGroup,false);
            }

            TextView nameTxt = view.findViewById(R.id.nameTextView);
            ImageView spacecraftImageView = view.findViewById(R.id.spacecraftImageView);
            final Spacecraft thisSpacecraft= spacecrafts.get(position);
            nameTxt.setText(thisSpacecraft.getAuthor());



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, thisSpacecraft.getAuthor(), Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }
}