package aj.note.lenovo.notes;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ShowNotes extends AppCompatActivity {

    private TextView t1,t2;
    private String str,str1,str2;
    private DBforNotes dBforNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        getSupportActionBar().setTitle("Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        t1 = (TextView) findViewById(R.id.show_notes_text1);
        t2 = (TextView) findViewById(R.id.show_notes_text2);

        dBforNotes = new DBforNotes(this);

        Intent i = getIntent();
        if (i != null){
            String s1 = i.getStringExtra("one");
            String s2 = i.getStringExtra("two");
            String s3 = i.getStringExtra("three");
            String s4 = i.getStringExtra("four");
            str = i.getStringExtra("five");
            t1.setText(s1);
            t2.setText(s4);
        }

        str1 = t1.getText().toString();
        str2 = t2.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.show_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.update_settings){
            update();
        }
        if (id == R.id.delete_sttings){
            del();
        }
        return true;
    }

    private void update() {
        Intent intent = new Intent(ShowNotes.this,UpdateNoteActivity.class);
        intent.putExtra("key",str);
        intent.putExtra("key_file",str1);
        intent.putExtra("key_content",str2);
        startActivity(intent);
    }

    private void del() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowNotes.this);
        View v = getLayoutInflater().inflate(R.layout.dialog,null);
        Button b1 = (Button) v.findViewById(R.id.button);
        Button b2 = (Button) v.findViewById(R.id.button2);
        builder.setView(v);
        final AlertDialog dialog = builder.create();
        dialog.show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dBforNotes.deleteNotesData(str1);
                dialog.dismiss();
                Toast.makeText(ShowNotes.this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowNotes.this,NotepadActivity.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
