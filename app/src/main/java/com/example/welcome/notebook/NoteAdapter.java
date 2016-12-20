package com.example.welcome.notebook;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Welcome on 8/15/2016.
 */
public class NoteAdapter extends ArrayAdapter<Note>{

    public static class ViewHolder{
        TextView title;
        TextView note;
        ImageView noteIcon;
    }

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        //To try to optimize this NoteAdapter, we try to minimize the 'findViewById()' function, as it is process intensive.
        //To do that we create the following class below


        //Get the data item for this position
        Note note = getItem(position);

        //create a view holder
        ViewHolder viewHolder;

        //check if an exsisting item is being used. If not, inflate a new view from custom row layout
        if (convertView == null){
            //If we do not already have a view, and need to create a new one, we're going to create a new view holder to save our view refrences to.
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            //Grab reference to the views so we can populate them with specific note row data
            viewHolder.title = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
            viewHolder.note = (TextView) convertView.findViewById(R.id.listItemNoteBody);
            viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);

            //We use set tag to remmeber our View Holder which is holding our references to our widgets
            convertView.setTag(viewHolder);
        } else{
            //we already have the view, so go to the view Holder and grab the widgets from it
            viewHolder = (ViewHolder) convertView.getTag();

        }

        //Now fill in each of the newly referenced stuff in view with data associated with the note it's referencing
        viewHolder.title.setText(note.getTitle());
        viewHolder.note.setText(note.getMessage());
        viewHolder.noteIcon.setImageResource(note.getAssociatedDrawable());

        //Once we've populated the view with the respective data, return the view, so it can be displayed.
        return convertView;

    }
}
