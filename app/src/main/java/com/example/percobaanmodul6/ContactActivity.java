package com.example.percobaanmodul6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactDatabase contactDatabase;
    private ExecutorService executorService;

    private TextView option;
    private LinearLayout layAddContact;
    private EditText etName, etNumber, etInstagram, etGroup;
    private Button btnClear, btnSubmit;

    private ArrayList<ContactModel> contactList = new ArrayList<>();
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        executorService = Executors.newSingleThreadExecutor();
        contactDatabase = ContactDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recycle_contact);
        recyclerView.setHasFixedSize(true);

        layAddContact = findViewById(R.id.layout_add);
        option = findViewById(R.id.tv_option);
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        etInstagram = findViewById(R.id.et_instagram);
        etGroup = findViewById(R.id.et_group);
        btnClear = findViewById(R.id.btn_clear);
        btnSubmit = findViewById(R.id.btn_submit);

        option.setOnClickListener(v -> {
            if (recyclerView.getVisibility() == View.VISIBLE) {
                recyclerView.setVisibility(View.GONE);
                layAddContact.setVisibility(View.VISIBLE);
                clearData();
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                layAddContact.setVisibility(View.GONE);
            }
        });

        btnClear.setOnClickListener(v -> clearData());

        btnSubmit.setOnClickListener(v -> {
            if (etName.getText().toString().equals("") ||
                    etNumber.getText().toString().equals("") ||
                    etInstagram.getText().toString().equals("") ||
                    etGroup.getText().toString().equals("")) {
                Toast.makeText(this, "Please fill in the entire form", Toast.LENGTH_SHORT).show();
            } else {
                ContactModel contact = new ContactModel(
                    etName.getText().toString(),
                    etNumber.getText().toString(),
                    etGroup.getText().toString(),
                    etInstagram.getText().toString()
                );
                insertContact(contact);
                clearData();
            }
        });

        contactAdapter = new ContactAdapter(this, contactList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ContactActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactAdapter);

        // Load contacts from database
        loadContacts();
    }

    private void insertContact(ContactModel contact) {
        executorService.execute(() -> {
            try {
                contactDatabase.contactDao().insert(contact);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Contact added successfully", Toast.LENGTH_SHORT).show();
                    loadContacts();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error adding contact: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void loadContacts() {
        executorService.execute(() -> {
            try {
                List<ContactModel> contacts = contactDatabase.contactDao().getAllContacts();
                runOnUiThread(() -> {
                    contactList.clear();
                    contactList.addAll(contacts);
                    contactAdapter.notifyDataSetChanged();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error loading contacts: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    public void clearData() {
        etName.setText("");
        etNumber.setText("");
        etInstagram.setText("");
        etGroup.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}