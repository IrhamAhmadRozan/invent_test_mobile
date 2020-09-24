package com.developers.rozan.inventtestmobile.feature.MainActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.developers.rozan.inventtestmobile.R;
import com.developers.rozan.inventtestmobile.database.AllProduct;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> implements Filterable {

    private List<AllProduct> list;
    private List<AllProduct> searchList;
    private MainAdapter.OnItemClickListener mItemClickListener;

    public MainAdapter(List<AllProduct> list) {
        this.list = list;
        this.searchList = list;
    }

    @NonNull
    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_product, parent, false);
        return new MainAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MyViewHolder holder, int position) {
        holder.tvNama.setText(list.get(position).getNamaBarang());
        holder.tvHarga.setText("Rp " + MainActivity.formatCurrencyDouble(holder.itemView.getContext(), String.valueOf(list.get(position).getHargaBarang())));
        holder.tvcabang.setText("Cabang : " + list.get(position).getCabang());
        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImage())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView tvNama, tvHarga, tvcabang;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tvNama = itemView.findViewById(R.id.txtNamaProduct);
            tvHarga = itemView.findViewById(R.id.txtPriceProduct);
            tvcabang = itemView.findViewById(R.id.txtCabangProduct);
            imageView = itemView.findViewById(R.id.imgProduct);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterResults.values = searchList;
                    filterResults.count = searchList.size();
                } else {
                    List<AllProduct> listFiltered = new ArrayList<>();
                    for (AllProduct row : searchList) {
                        if (row.getNamaBarang().toLowerCase().contains(charString.toLowerCase()) || String.valueOf(row.getHargaBarang()).toLowerCase().contains(charString.toLowerCase())) {
                            if (mItemClickListener != null)
                                mItemClickListener.onNotFoundData(charString, false);
                            listFiltered.add(row);
                        } else {
                            if (mItemClickListener != null)
                                mItemClickListener.onNotFoundData(charString, true);
                        }
                    }
                    filterResults.values = listFiltered;
                    filterResults.count = listFiltered.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (List<AllProduct>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface OnItemClickListener extends OnNotFoundDataListener {
        void onItemClicked(AllProduct allProduct);
    }

    public void setmItemClickListener(MainAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnNotFoundDataListener {
        void onNotFoundData(String key, boolean isNotFound);
    }

    public interface OnQuerySearchListener {
        void onQuerySearch(String query);
    }
}
