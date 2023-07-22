package com.example.stickynotesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> notesList;
    private ArrayAdapter<String> notesAdapter;
    private EditText editTextNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesList = new ArrayList<>();
        notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);

        ListView listViewNotes = findViewById(R.id.listViewNotes);
        listViewNotes.setAdapter(notesAdapter);

        editTextNote = findViewById(R.id.editTextNote);

        Button buttonAddNote = findViewById(R.id.buttonAddNote);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = editTextNote.getText().toString().trim();
                if (!note.isEmpty()) {
                    notesList.add(note);
                    notesAdapter.notifyDataSetChanged();
                    editTextNote.setText("");
                }
            }
        });

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNote = notesList.get(position);
                editTextNote.setText(selectedNote);
            }
        });

        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                notesList.remove(position);
                notesAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
