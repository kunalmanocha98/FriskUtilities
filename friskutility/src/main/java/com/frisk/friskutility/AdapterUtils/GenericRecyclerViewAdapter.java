package com.frisk.friskutility.AdapterUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public abstract class GenericRecyclerViewAdapter<T> extends RecyclerView.Adapter<GenericRecyclerViewAdapter.ViewHolder> {
    public List<T> itemsList;
    private Context context;
    private FoopClickListener foopClickListener;
    private final int NO_ID = -1;
    Filterable filterable;


    public GenericRecyclerViewAdapter(Context context) {
        this(context, null);
    }

    GenericRecyclerViewAdapter(Context context, FoopClickListener<T> listener) {

        this.context = context;
        this.foopClickListener = listener;
        itemsList = new ArrayList<>();
    }

    GenericRecyclerViewAdapter(List<T> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }


    public Context getContext() {
        return context;
    }

    public List<T> getItemsList() {
        return itemsList;
    }


    protected abstract View onCreateView(Context context, ViewGroup viewGroup, int viewType);

    protected abstract void onBindView(T item, ViewHolder viewHolder);

    public void setItemsList(List<T> itemsList) {
        this.itemsList = itemsList;
    }

    public void setFoopClickListener(FoopClickListener foopClickListener) {
        this.foopClickListener = foopClickListener;
    }

    public FoopClickListener getFoopClickListener() {
        return foopClickListener;
    }


    public void addNull() {

    }

    public void removeNull() {

    }

    public void addAll(List<T> list) {
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
        int position = itemsList.size();
        itemsList.addAll(list);
        notifyItemRangeInserted(position, list.size());
    }

    public void addAllNew(List<T> list) {
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
        int position = itemsList.size();
        this.itemsList = list;
        notifyItemRangeInserted(position, itemsList.size());
    }

    public void removeItem(int position) {

        if (itemsList != null) {
            itemsList.remove(position);
            notifyItemRangeRemoved(position, itemsList.size());
        }

    }

    public void removeAll() {
        itemsList = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void addBusinessApps(List<T> list) {
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
        int position = itemsList.size();
        itemsList.addAll(0, list);
        notifyItemRangeInserted(position, list.size());
    }

    public void addAllWithProgress(List<T> list) {
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
        if (itemsList.size() > 0) {
            itemsList.remove(itemsList.size() - 1);
            notifyItemRemoved(itemsList.size());
            int position = itemsList.size();
            itemsList.addAll(list);
            notifyItemRangeInserted(position, list.size());
        } else {
            int position = itemsList.size();
            itemsList.addAll(list);
            notifyItemRangeInserted(position, list.size());
        }
    }

    public void hideprogress() {
        if (itemsList != null) {
            if (itemsList.size() > 0) {
                itemsList.remove(itemsList.size() - 1);
                notifyItemRemoved(itemsList.size() - 1);
            }
        }

    }


    public List<T> getList() {
        return itemsList;
    }

    public void replaceall(List<T> list) {
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
        itemsList.clear();
        if (list != null) {
            itemsList.addAll(list);
        }
        notifyDataSetChanged();
    }


    public void add(T item) {
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
        int position = itemsList.size();
        itemsList.add(item);
        notifyItemInserted(position);
    }

    public void add(T item, int position) {
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
        itemsList.add(position, item);
        notifyItemInserted(position);
    }

    public void reset() {
        if (itemsList != null) {
            itemsList.clear();
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(onCreateView(context, viewGroup, viewType), foopClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        //noinspection unchecked
        onBindView(getItem(i), viewHolder);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void remove(int i) {
        itemsList.remove(i);
        notifyItemRemoved(i);
    }

    public void remove(T item) {
        int position = itemsList.indexOf(item);
        itemsList.remove(position);
        notifyItemRemoved(position);
    }

    public T getItem(int index) {
        return ((itemsList != null && index < itemsList.size()) ? itemsList.get(index) : null);
    }

    @Override
    public int getItemCount() {
        return itemsList != null ? itemsList.size() : 0;
    }


    public interface FoopClickListener<T> {
        void onClick(View view, int position, T item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private HashMap<Integer, View> views;

        ViewHolder(View itemView, FoopClickListener foopClickListener) {
            super(itemView);
            views = new HashMap<>();
            views.put(0, itemView);
            if (foopClickListener != null)
                itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (foopClickListener != null)
                //noinspection unchecked
                foopClickListener.onClick(itemView, getAdapterPosition(), getItem(getAdapterPosition()));
        }

        T getItem(int index) {
            return ((itemsList != null && index < itemsList.size()) ? itemsList.get(index) : null);
        }

        public void initViewList(int[] idList) {
            for (int id : idList)
                initViewById(id);
        }

        void initViewById(int id) {
            View view = (getView() != null ? getView().findViewById(id) : null);

            if (view != null)
                views.put(id, view);
        }

        View getView() {
            return views.get(0);
        }

        private View getView(int id) {
            if (views.containsKey(id))
                return views.get(id);
            else
                initViewById(id);

            return views.get(id);
        }

        public <T extends View> T findViewId(int id) {
            if (id == NO_ID) {
                return null;
            }
            if (views.containsKey(id)) {
                //noinspection unchecked
                return ((T) views.get(id));
            } else {

                initViewById(id);
            }
            //noinspection unchecked
            return ((T) views.get(id));
        }

        public int getItemPosition() {
            return getAdapterPosition();
        }
    }


}
