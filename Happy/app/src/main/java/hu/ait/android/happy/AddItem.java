package hu.ait.android.happy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import hu.ait.android.happy.data.Todo;

/**
 * Created by miadeng on 5/22/16.
 */
public class AddItem  extends AppCompatActivity  {

    public static final String KEY_ITEM = "KEY_ITEM";

    private Spinner spinnerItemType;
    private EditText etItemName;
    private EditText etItemLocation;
    private EditText etItemDate;
    private Button btnSave;
    private CheckBox cbItemDone;
    private Todo itemToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        etItemLocation = (EditText) findViewById(R.id.etLocation);
        etItemName = (EditText) findViewById(R.id.etName);
        etItemDate = (EditText) findViewById(R.id.etDate);
        cbItemDone = (CheckBox) findViewById(R.id.cbItemDone);


        spinnerItemType = (Spinner) findViewById(R.id.spinnerItemType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.itemtypes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemType.setAdapter(adapter);



        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItem();
            }
        });


        if (getIntent().getSerializableExtra(MainActivity.KEY_EDIT) != null) {
            itemToEdit = (Todo) getIntent().getSerializableExtra(MainActivity.KEY_EDIT);


            if (itemToEdit != null){
                etItemName.setText(itemToEdit.getName());
                etItemDate.setText(itemToEdit.getDate());
                etItemLocation.setText(itemToEdit.getLocation());
                cbItemDone.setChecked(itemToEdit.isDone());

                spinnerItemType.setSelection(itemToEdit.getItemType().getValue());

            }
        }

    }


    private void saveItem() {
        if ("".equals(etItemName.getText().toString())) {
            etItemName.setError(getString(R.string.txt_error_empty));
        }

        else {
            Intent intentResult = new Intent();
            Todo itemResult = null;
            if (itemToEdit != null) {
                itemResult = itemToEdit;
            } else {
                itemResult = new Todo();
            }

            itemResult.setName(etItemName.getText().toString());
            itemResult.setItemType(Todo.ItemType.fromInt(spinnerItemType.getSelectedItemPosition()));
            itemResult.setDate(etItemDate.getText().toString());
            itemResult.setLocation(etItemLocation.getText().toString());
            itemResult.setDone(cbItemDone.isChecked());



            intentResult.putExtra(KEY_ITEM, itemResult);
            setResult(RESULT_OK, intentResult);
            finish();
        }
    }





}
