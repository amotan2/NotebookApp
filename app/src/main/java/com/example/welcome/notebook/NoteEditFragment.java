package com.example.welcome.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class NoteEditFragment extends Fragment {


    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate our fragment view
        View fragementLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        //grab refrences for layout
        EditText title = (EditText) fragementLayout.findViewById(R.id.editNoteTitle);
        EditText message = (EditText) fragementLayout.findViewById(R.id.editNoteMessage);
        ImageButton noteCatButton = (ImageButton) fragementLayout.findViewById(R.id.editNoteButton);

        //Creating Intent & then populate widgets with note data
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA));

        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);
        noteCatButton.setImageResource(Note.categoryToDrawable(noteCat));

        // Inflate the layout for this fragment
        return fragementLayout;


    }

}
