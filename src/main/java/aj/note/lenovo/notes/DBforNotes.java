package aj.note.lenovo.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBforNotes extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "notes.db";
    public static final String TABLE_NAME = "Note_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FILE_NAME";
    public static final String COL_3 = "FILE_CONTENT";
    public static final String COL_4 = "FILE_DATE";
    public static final String COL_5 = "FILE_TIME";

    public DBforNotes(Context context) {
        super(context,DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FILE_NAME TEXT,FILE_CONTENT TEXT,FILE_DATE TEXT,FILE_TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertNoteData(String fileName,String fileContent,String fileDate,String fileTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,fileName);
        contentValues.put(COL_3,fileContent);
        contentValues.put(COL_4,fileDate);
        contentValues.put(COL_5,fileTime);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<NotesDataProvider> getNotesData(){
        ArrayList<NotesDataProvider> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        if (res.moveToFirst()){
            do {
                NotesDataProvider provider = new NotesDataProvider();
                provider.setFileName(res.getString(1));
                provider.setFileContent(res.getString(2));
                provider.setFileDate(res.getString(3));
                provider.setFileTime(res.getString(4));
                arrayList.add(provider);
            }while (res.moveToNext());
        }
        return arrayList;
    }

    public void deleteNotesData(String file){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"FILE_NAME = ?",new String[]{file});
    }

    public boolean updateNotesData(String name,String content,String date,String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,content);
        contentValues.put(COL_4,date);
        contentValues.put(COL_5,time);
        int r = db.update(TABLE_NAME,contentValues,"FILE_NAME = ?",new String[]{name});
        if (r>0){
            return true;
        }else {
            return false;
        }
    }
}
