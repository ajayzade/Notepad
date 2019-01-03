package aj.note.lenovo.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class NotesList extends AppCompatActivity {

    private ListView listView;
    private DBforNotes dBforNotes;
    ArrayList<NotesDataProvider> arr;
    NotesDataProvider notesDataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        getSupportActionBar().setTitle("Notes List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        listView = (ListView) findViewById(R.id.lister);
        arr = new ArrayList<>();
        dBforNotes = new DBforNotes(this);
        arr = dBforNotes.getNotesData();
        final ListAdapters listAdapters = new ListAdapters(arr,this);
        listView.setAdapter(listAdapters);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                notesDataProvider = (NotesDataProvider) listAdapters.getItem(position);
                Intent intent = new Intent(NotesList.this,ShowNotes.class);
                intent.putExtra("one",notesDataProvider.getFileName());
                intent.putExtra("two",notesDataProvider.getFileDate());
                intent.putExtra("three",notesDataProvider.getFileTime());
                intent.putExtra("four",notesDataProvider.getFileContent());
                intent.putExtra("five",String.valueOf(position));
                startActivity(intent);
            }
        });
    }
}
