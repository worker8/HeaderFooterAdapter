package github.worker8.headerfooteradapterdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private Item item;

    public Item getItem() {
        return item;
    }

    public ItemViewHolder(View view) {
        super(view);
    }

    public static ItemViewHolder create(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(itemView);
    }

    public void bind(final Item item, final View.OnClickListener onClickListener) {
        this.item = item;

        if (item.isSelected()) {
            itemView.setBackgroundColor(itemView.getResources().getColor(android.R.color.holo_orange_light));
        } else {
            itemView.setBackgroundColor(itemView.getResources().getColor(android.R.color.white));
        }

        itemView.setOnClickListener(onClickListener);
    }
}


