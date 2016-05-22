package hu.ait.android.happy.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by clarabelitz on 5/22/16.
 */
public class HappyTouchHelperCallback extends ItemTouchHelper.Callback {

    private final HappyTouchHelperAdapter mAdapter;

    public HappyTouchHelperCallback(HappyTouchHelperAdapter adapter) {mAdapter = adapter;}

    @Override
    public boolean isLongPressDragEnabled() { return true; }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //bitwise or allows dragging up or dragging down
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    //this is called with move gesture. tells handler what to do
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    //called when there is a swipe gesture. gives you a whole row, not specific location, so we have
    //to get the location
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
