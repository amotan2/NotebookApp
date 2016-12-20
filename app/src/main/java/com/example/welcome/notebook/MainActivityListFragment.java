package com.example.welcome.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.zip.Inflater;


/*
 * A simple {@link Fragment} subclass.
 */

public class MainActivityListFragment extends ListFragment {

    ArrayList<Note> notes;
    ArrayAdapter<Note> noteAdapter;

    @Override   ////called callback method
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    /*
        String[] values = new String[]{"OS","iPhone", "Android", "Whatever", "blah", "hello there", "Mac", "Surface Pro", "Some other dumb shesh", "More dumb shesh", "Windoes NT?", "XP"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
    */

        notes = new ArrayList<Note>();

        notes.add(new Note("1 - This is a new note", "A - This is the body of our note", Note.Category.PERSONAL));
        notes.add(new Note("2 - This is a new note", "B - This is the body of our note", Note.Category.FINANCE));
        notes.add(new Note("3 - This is a new note", "C - This is the body of our note", Note.Category.TECHNICAL));
        notes.add(new Note("4 - This is a new note", "D - This is the body of our note", Note.Category.TECHNICAL));
        notes.add(new Note("5 - This is a new note", "E - This is the body of our note", Note.Category.QUOTE));
        notes.add(new Note("6 - This is a new note", "F - This is the body of our note", Note.Category.PERSONAL));
        notes.add(new Note("7 - This is a new note", "G - This is the body of our note", Note.Category.TECHNICAL));
        notes.add(new Note("8 - This is a new note", "H - This is the body of our note", Note.Category.QUOTE));
        notes.add(new Note("9 - This is a new note", "I - This is the body of our note", Note.Category.PERSONAL));
        notes.add(new Note("10 - This is a new note", "J - This is the body of our note", Note.Category.PERSONAL));
        notes.add(new Note("11 - This is a new note", "K - This is the body of our note", Note.Category.PERSONAL));
        notes.add(new Note("12 - This is a new note", "L - This is the body of our note", Note.Category.PERSONAL));

        noteAdapter = new NoteAdapter(getActivity(), notes);
        setListAdapter(noteAdapter);

        registerForContextMenu(getListView());

        //to get deviding lines of a specific color, we can uncomment the code below
        //getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.black));  //not working...
        //getListView().setDividerHeight(1);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        launchNoteDetailActivity(MainActivity.FragmentToLaunch.VIEW, position); //defined below
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        //Getting the position of whatever note we long pressed on
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        //returns to us the ID of whatever menu item we selected
        switch (item.getItemId()){
            //if we press edit
            case R.id.edit:
                //do something here.
                launchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT, rowPosition);
                Log.d("Menu Clicked", "We pressed edit");
                return true;    //when handling a click, return true

        }

        return super.onContextItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        //This function allows us to build a context menu for list fragment
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    private void launchNoteDetailActivity(MainActivity.FragmentToLaunch ftl, int position){

        //grab the note info associated with what ever note item we clicked on
        Note note = (Note) getListAdapter().getItem(position);

        //Create a new intent that launches our note detail activity
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);

        //Pass along the info of the note we clicked on to the Note detail activity
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());      //to do this, we need to create some unique keys. We'll do that in the main activity
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getNoteId());

        switch (ftl){
            case VIEW:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLaunch.EDIT);
                break;
        }

        //start the activity with that intent
        startActivity(intent);


    }

}
