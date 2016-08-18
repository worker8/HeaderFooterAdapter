package github.worker8.headerfooteradapterdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import github.worker8.headerfooteradapter.HeaderFooterAdapter;

public class MainAdapter extends HeaderFooterAdapter {

    private List<Item> items = new ArrayList<>();

    {
        for (int i = 0; i < 10; i++) {
            items.add(new Item());
        }
    }

    public MainAdapter(boolean hasHeader, boolean hasFooter) {
        super(hasHeader, hasFooter);
    }

    @Override
    public int getRealItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRealItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateRealViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindRealViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Item item = items.get(position);
        ((ItemViewHolder) holder).bind(item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = "ITEM #" + position + "!";
                Toast.makeText(holder.itemView.getContext(), temp, Toast.LENGTH_SHORT).show();
                Log.d("HeaderFooterAdapter", temp);

                item.toggleSelection();

                notifyRealItemChanged(position);
            }
        });
    }

    /************
     * Header
     ************/
    private static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View view) {
            super(view);
        }

        public static HeaderViewHolder create(ViewGroup parent) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            return new HeaderViewHolder(headerView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return HeaderViewHolder.create(parent);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Header!", Toast.LENGTH_SHORT).show();
                Log.d("HeaderFooterAdapter", "Header IS CLICKED! ");
            }
        });
    }

    /************
     * Footer
     ************/
    private static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

        public static FooterViewHolder create(ViewGroup parent) {
            View footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            return new FooterViewHolder(footerView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        return FooterViewHolder.create(parent);
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Footer!", Toast.LENGTH_SHORT).show();
                Log.d("HeaderFooterAdapter", "Footer IS CLICKED! ");
            }
        });
    }

}
