package hu.ait.android.happy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.ait.android.happy.MainActivity;
import hu.ait.android.happy.R;
import hu.ait.android.happy.data.Todo;

/**
 * Created by miadeng on 5/21/16.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>
        implements TodoTouchHelperAdapter {

    private Context context;
    private List<Todo> todos = new ArrayList<Todo>();
    private int lastPosition = -1;

    public Todo getItem(int i) {
        return todos.get(i);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvDate;
        private TextView tvLocation;
        private CheckBox Status;
        private Button btnDeleteItem;
        private Button btnEdit;
        private ImageView icon;


        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvLocation= (TextView) itemView.findViewById(R.id.tvLocation);
            icon = (ImageView) itemView.findViewById(R.id.ivIcon);
            Status = (CheckBox) itemView.findViewById(R.id.cbDone);
            btnDeleteItem = (Button) itemView.findViewById(R.id.btnDeleteItem);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);

        }
    }

    public TodoAdapter(Context context) {
        this.context = context;
        todos = Todo.listAll(Todo.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.happy_list_row, parent, false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvName.setText(todos.get(position).getName());
        holder.tvLocation.setText(todos.get(position).getLocation());
        holder.tvDate.setText(todos.get(position).getDate());
        holder.Status.setChecked(todos.get(position).isDone());
        holder.icon.setImageResource(
                todos.get(position).getItemType().getIconId());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showEditTodoActivity(todos.get(position), position);
            }
        });


        holder.Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo todo = todos.get(position);
                todo.setDone(holder.Status.isChecked());

                // we will save/update todo object in the DataBase here

//                Toast.makeText(context,
//                        todo.getName() + ": " +
//                                todo.isDone(),
//                        Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void addTodo(Todo todo) {
        todos.add(0, todo);

        // ths refreshes the whole list
        notifyDataSetChanged();

        // this refreshes only the first item, more optimal!
        //notifyItemInserted(0);
    }


    public void updateItem(int index, Todo item) {
        todos.set(index, item);
        item.save();
        notifyItemChanged(index);
    }

    public void removeTodo(int position) {
        todos.remove(position).delete();
        todos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemDismiss(int position) {
        removeTodo(position);
    }




    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todos, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todos, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }


    public void refreshListfromDb (){
        todos = Todo.listAll(Todo.class);
        notifyDataSetChanged();
    }


}
