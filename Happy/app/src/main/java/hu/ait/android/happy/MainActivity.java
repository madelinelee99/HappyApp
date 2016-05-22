package hu.ait.android.happy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import hu.ait.android.happy.adapter.TodoAdapter;
import hu.ait.android.happy.adapter.TodoItemTouchHelperCallback;
import hu.ait.android.happy.data.Todo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int REQUEST_CODE_ADD_ITEM = 101;
    public static final int REQUEST_CODE_EDIT_ITEM = 102;
    public static final String KEY_EDIT = "KEY_EDIT";

    private TodoAdapter todoRecyclerAdapter;
    private Todo itemToEditHolder;
    private int itemToEditPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        todoRecyclerAdapter = new TodoAdapter(this);


        final RecyclerView recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(todoRecyclerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddItemActivity();
            }
        });

        ItemTouchHelper.Callback callback =
                new TodoItemTouchHelperCallback(todoRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void showAddItemActivity() {
        Intent intentAddItem = new Intent(MainActivity.this, AddItem.class);
        startActivityForResult(intentAddItem,
                REQUEST_CODE_ADD_ITEM);
    }

    private void showWeatherActivity() {
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void showEditTodoActivity(Todo itemToEdit, int position) {
        Intent intentEditTodo = new Intent(MainActivity.this,
                AddItem.class);
        itemToEditHolder = itemToEdit;
        itemToEditPosition = position;

        intentEditTodo.putExtra(KEY_EDIT, itemToEdit);
        startActivityForResult(intentEditTodo, REQUEST_CODE_EDIT_ITEM);
    }

    private void showAlarmActivity() {
        Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
        MainActivity.this.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQUEST_CODE_ADD_ITEM) {
                    Todo item = (Todo) data.getSerializableExtra(
                            AddItem.KEY_ITEM);

                    Toast.makeText(MainActivity.this, "item added", Toast.LENGTH_SHORT).show();

                    todoRecyclerAdapter.addTodo(item);

                } else if (requestCode == REQUEST_CODE_EDIT_ITEM) {
                    Todo itemTemp = (Todo) data.getSerializableExtra(
                            AddItem.KEY_ITEM);

                    itemToEditHolder.setName(itemTemp.getName());
                    itemToEditHolder.setDate(itemTemp.getDate());
                    itemToEditHolder.setLocation(itemTemp.getLocation());
                    itemToEditHolder.setItemType(itemTemp.getItemType());
                    itemToEditHolder.setDone(itemTemp.isDone());


                    if (itemToEditPosition != -1) {
                        todoRecyclerAdapter.updateItem(itemToEditPosition, itemToEditHolder);
                        itemToEditPosition = -1;
                    } else {
                        todoRecyclerAdapter.notifyDataSetChanged();
                    }

                }
                break;
            case RESULT_CANCELED:

                break;


        }


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

            if (id == R.id.nav_addItem) {
                //will close draw at end, so no additional action
            } else if (id == R.id.nav_alarm) {
                //go to alarm activity
                showAlarmActivity();
            } else if (id == R.id.nav_weather) {
                showWeatherActivity();
            } else if (id == R.id.nav_profile) {
                //go to profile activity
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

