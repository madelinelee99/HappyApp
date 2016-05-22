package hu.ait.android.happy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import hu.ait.android.happy.R;
import hu.ait.android.happy.data.Item;

/**
 * Created by clarabelitz on 5/22/16.
 */
public class HappyAdapter  extends RecyclerView.Adapter<HappyAdapter.ViewHolder> implements HappyTouchHelperAdapter {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;
        private TextView tvItem;
        private Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvItem = (TextView) itemView.findViewById(R.id.tvItem);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }

    private Context context;
    private List<Item> items;

    public HappyAdapter(List<Item> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.row_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tvItem.setText(items.get(position).getItemToDo());

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });

    }

    @Override
    public int getItemCount() { return items.size(); }

    public void addItem(Item item) {
        items.add(0, item);
        item.save();
        notifyItemInserted(0);
    }

    public void removeItem(int position) {
        items.get(position).delete();
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void deleteAll() {
        items.clear();
        notifyDataSetChanged();
    }

    public void updateCity(int index, Item item) {
        items.set(index, item);
        item.save();
        notifyItemChanged(index);
    }

    public void updateCount(int index, Item item) {
        items.set(index, item);
        item.save();
        notifyItemChanged(index);
    }

    @Override
    public void onItemDismiss(int position) { removeItem(position); }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(items, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(items, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }








}
