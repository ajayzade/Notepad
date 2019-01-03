package aj.note.lenovo.notes;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateNoteActivity extends AppCompatActivity {

    String id_key,file_key,content_key;
    private EditText edit1,edit2;
    private DBforNotes dBforNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        getSupportActionBar().setTitle("Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        Intent intent = getIntent();
        id_key = intent.getStringExtra("key");
        file_key = intent.getStringExtra("key_file");
        content_key = intent.getStringExtra("key_content");

        dBforNotes = new DBforNotes(this);

        edit1 = (EditText) findViewById(R.id.enter_file);
        edit2 = (EditText) findViewById(R.id.enter_content);

        edit1.setText(file_key);
        edit2.setText(content_key);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.update_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         int ider = item.getItemId();
         if (ider == R.id.update_ss){
             upd();
         }
         return true;
    }


    private void upd() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String date = day + "/" + (month + 1) + "/" + year;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(calendar.getTime());
        boolean b = dBforNotes.updateNotesData(file_key,content_key,date,time);
        if (b == true){
            Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateNoteActivity.this,NotesList.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Note not Updated", Toast.LENGTH_SHORT).show();
        }
    }
}
