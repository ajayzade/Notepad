package aj.note.lenovo.notes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapters extends BaseAdapter {
    ArrayList<NotesDataProvider> arrayList;
    LayoutInflater layoutInflater;
    Activity activity;

    public ListAdapters(ArrayList<NotesDataProvider> arrayList,Activity activity){
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.custom,null,false);
        }
        TextView t1 = (TextView) v.findViewById(R.id.txt1);
        TextView t2 = (TextView) v.findViewById(R.id.txt2);
        TextView t3 = (TextView) v.findViewById(R.id.txt3);
        NotesDataProvider dataProvider = (NotesDataProvider) this.getItem(position);
        t1.setText(dataProvider.fileName);
        t2.setText(dataProvider.fileTime);
        t3.setText(dataProvider.fileDate);
        return v;
    }
}
