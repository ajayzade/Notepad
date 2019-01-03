package aj.note.lenovo.notes;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import java.util.Collections;

public class NotepadActivity extends AppCompatActivity {

    private EditText file_name,file_content;
    DBforNotes dBforNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        getSupportActionBar().setTitle("Write Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        file_name = (EditText) findViewById(R.id.enter_file_name);
        file_content = (EditText) findViewById(R.id.enter_file_content);

        dBforNotes = new DBforNotes(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.for_notes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int i = item.getItemId();
        if (i == R.id.save_button){
            saveFile();
        }
        if (i == R.id.show_files){
            Intent intent = new Intent(NotepadActivity.this,NotesList.class);
            startActivity(intent);
        }
        return true;
    }


        private void saveFile() {
        String currentFileName = file_name.getText().toString();
        String currentFileContent = file_content.getText().toString();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String date = day + "/" + (month + 1) + "/" + year;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(calendar.getTime());
        if (TextUtils.isEmpty(currentFileName) || TextUtils.isEmpty(currentFileContent)){
            Toast.makeText(NotepadActivity.this,"Please fields must not be empty",Toast.LENGTH_LONG).show();
        }else {
            boolean result = dBforNotes.insertNoteData(currentFileName,currentFileContent,date,time);
            if (result == true){
                Toast.makeText(NotepadActivity.this,"File saved successfully",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(NotepadActivity.this,"File not saved",Toast.LENGTH_LONG).show();
            }
        }
        file_name.setText("");
        file_content.setText("");
    }
}
