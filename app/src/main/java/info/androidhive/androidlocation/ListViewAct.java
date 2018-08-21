package info.androidhive.androidlocation;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewAct extends AppCompatActivity {
    ListView mListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = findViewById(R.id.mListView);
        myDb = new DatabaseHelper(getApplicationContext());
        arrayList = new ArrayList<>();

        getData();
        displayData();

    }



    public void getData(){
        Cursor cursor = myDb.fetchData();
        if(cursor != null && cursor.moveToNext()){
            do{
                arrayList.add("Location = "+cursor.getString(1)+" Time = "+cursor.getString(2));
            }while(cursor != null && cursor.moveToNext());
        }

    }
    public void displayData(){
        adapter = new ArrayAdapter<>(ListViewAct.this, android.R.layout.simple_list_item_1, arrayList);
        mListView.setAdapter(adapter);
    }
}
