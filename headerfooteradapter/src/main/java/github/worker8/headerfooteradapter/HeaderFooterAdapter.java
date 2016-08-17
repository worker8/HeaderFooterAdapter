package github.worker8.headerfooteradapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

abstract public class HeaderFooterAdapter extends RecyclerView.Adapter {

    boolean hasHeader, hasFooter;

    public HeaderFooterAdapter(boolean hasHeader, boolean hasFooter) {
        this.hasHeader = hasHeader;
        this.hasFooter = hasFooter;
    }

    public void showHeader() {
        hasHeader = true;
        notifyItemInserted(0);
    }

    public void hideHeader() {
        hasHeader = false;
        notifyItemRemoved(0);
    }

    public void showFooter() {
        hasFooter = true;
        notifyItemInserted(getItemCount());
    }

    public void hideFooter() {
        hasFooter = false;
        notifyItemRemoved(getItemCount());
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public boolean hasFooter() {
        return hasFooter;
    }

    @Override
    final public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (hasHeader && hasFooter) {
            if (viewType == ViewType.HEADER) {
                RecyclerView.ViewHolder header = onCreateHeaderViewHolder(parent);
                if (header == null) {
                    throw new IllegalStateException("onCreateHeaderViewHolder() is returning null, it must be overridden and cannot return null");
                } else {
                    return header;
                }
            } else if (viewType == ViewType.FOOTER) {
                RecyclerView.ViewHolder footer = onCreateFooterViewHolder(parent);
                if (footer == null) {
                    throw new IllegalStateException("onCreateFooterViewHolder() is returning null, it must be overridden and cannot return null");
                } else {
                    return footer;
                }
            } else {
                return onCreateRealViewHolder(parent, viewType);
            }
        } else if (hasHeader && !hasFooter) {
            RecyclerView.ViewHolder header = onCreateHeaderViewHolder(parent);
            if (viewType == ViewType.HEADER) {
                if (header == null) {
                    throw new IllegalStateException("onCreateHeaderViewHolder() is returning null, it must be overridden and cannot return null");
                } else {
                    return header;
                }
            } else {
                return onCreateRealViewHolder(parent, viewType);
            }
        } else if (!hasHeader && hasFooter) {
            RecyclerView.ViewHolder footer = onCreateFooterViewHolder(parent);
            if (viewType == ViewType.FOOTER) {
                if (footer == null) {
                    throw new IllegalStateException("onCreateFooterViewHolder() is returning null, it must be overridden and cannot return null");
                } else {
                    return footer;
                }
            } else {
                return onCreateRealViewHolder(parent, viewType);
            }
        } else {
            return onCreateRealViewHolder(parent, viewType);
        }
    }

    @Override
    final public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (hasHeader && hasFooter) {

            if (position == 0) {
                onBindHeaderViewHolder(holder);
            } else if (position == (getItemCount() - 1)) { // footer
                onBindFooterViewHolder(holder);
            } else {
                onBindRealViewHolder(holder, position - 1);
            }
        } else if (hasHeader && !hasFooter) {
            if (position == 0) {
                onBindHeaderViewHolder(holder);
            } else {
                onBindRealViewHolder(holder, position - 1);
            }
        } else if (!hasHeader && hasFooter) {
            if (position == (getItemCount() - 1)) { // footer
                onBindFooterViewHolder(holder);
            } else {
                onBindRealViewHolder(holder, position);
            }
        } else {
            onBindRealViewHolder(holder, position);
        }
    }

    /*********
     * ViewType
     ********/
    private static class ViewType {

        private static final int HEADER = 999991;

        private static final int FOOTER = 999992;

    }

    abstract public int getRealItemViewType(int position);

    @Override
    final public int getItemViewType(int position) {
        if (hasHeader && hasFooter) {
            if (position == 0) {
                return ViewType.HEADER;
            } else if (position == (getItemCount() - 1)) { // footer
                return ViewType.FOOTER;
            } else {
                return getRealItemViewType(position - 1); // shift by header
            }

        } else if (hasHeader && !hasFooter) {
            if (position == 0) {
                return ViewType.HEADER;
            } else {
                return getRealItemViewType(position - 1); // shift by header
            }
        } else if (!hasHeader && hasFooter) {
            if (position == (getItemCount() - 1)) { // footer
                return ViewType.FOOTER;
            } else {
                return getRealItemViewType(position);
            }
        } else {
            return getRealItemViewType(position);
        }
    }

    @Override
    final public int getItemCount() {
        if (hasHeader && hasFooter) {
            return 2 + getRealItemCount();
        } else if (hasHeader || hasFooter) {
            return 1 + getRealItemCount();
        } else {
            return getRealItemCount();
        }
    }

    public void notifyRealItemRangeInserted(int positionStart, int itemCount) {
        if (hasHeader) {
            notifyItemRangeInserted(positionStart + 1, itemCount);
        } else {
            notifyItemRangeInserted(positionStart, itemCount);
        }
    }

    public void notifyRealItemChanged(int position) {
        int realPosition = position + (hasHeader ? 1 : 0);
        notifyItemChanged(realPosition);
    }

    /****************
     * Real children
     ****************/
    abstract public int getRealItemCount();

    abstract public RecyclerView.ViewHolder onCreateRealViewHolder(ViewGroup parent, int viewType);

    abstract public void onBindRealViewHolder(RecyclerView.ViewHolder holder, int position);

    /**********
     * Header
     **********/
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {

    }

    /**********
     * Footer
     **********/
    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        return null;
    }

    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {

    }
}