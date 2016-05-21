package hu.ait.android.happy.adapter;

/**
 * Created by clarabelitz on 5/22/16.
 */
public interface HappyTouchHelperAdapter {

    void onItemDismiss(int position);

    void onItemMove(int fromPosition, int toPosition);
}
