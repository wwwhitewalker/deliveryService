package com.example.menu.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.menu.R;
import com.example.menu.activities.AnimeActivity;
import com.example.menu.ui.HistoryList;
import com.example.menu.ui.ProductList;

import java.util.List;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.MyViewHolder>  {
    private Context mContext ;
    private List<ProductList> mData ;
    RequestOptions option;

    public ProductViewAdapter(Context mContext, List<ProductList> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public ProductViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.order_details,parent,false) ;
//        Button btnOrderDetails = (Button) parent.findViewById(R.id.btnOrderDetails);
        final ProductViewAdapter.MyViewHolder viewHolder = new ProductViewAdapter.MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, AnimeActivity.class);
                i.putExtra("product_name",mData.get(viewHolder.getAdapterPosition()).getProductName());
                i.putExtra("order_product_id",mData.get(viewHolder.getAdapterPosition()).getProduct_id());
//                i.putExtra("telephone",mData.get(viewHolder.getAdapterPosition()).getTelephone());
//                i.putExtra("email",mData.get(viewHolder.getAdapterPosition()).getEmail());
//                i.putExtra("total",mData.get(viewHolder.getAdapterPosition()).getTotal());
//                i.putExtra("address",mData.get(viewHolder.getAdapterPosition()).getAddress());
//                i.putExtra("delivery_date",mData.get(viewHolder.getAdapterPosition()).getDdate());
//                i.putExtra("status",mData.get(viewHolder.getAdapterPosition()).getStatus());
//                i.putExtra("company",mData.get(viewHolder.getAdapterPosition()).getCompanyName());

                //                i.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getRating());
//                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(i);

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewAdapter.MyViewHolder holder, int position) {
        Log.d("holder", String.valueOf(holder));
//        holder.tv_name.setText(mData.get(position).getName());
//        holder.tv_order_id.setText(""+mData.get(position).getOrderId());
//        holder.tv_total.setText(""+mData.get(position).getTotal());
//        holder.tv_telephone.setText(""+mData.get(position).getTelephone());
//        holder.tv_email.setText(mData.get(position).getEmail());
//        holder.tv_companyName.setText(mData.get(position).getCompanyName());
//        holder.tv_address.setText(mData.get(position).getAddress());
////        holder.tv_status.setText(mData.get(position).getStatus());
//        holder.tv_ddate.setText(mData.get(position).getDdate());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name ;
        TextView tv_email ;
        TextView tv_order_id ;
        TextView tv_total ;
        TextView tv_telephone;
        TextView tv_companyName;
        TextView tv_address;
        TextView tv_status;
        TextView tv_ddate;
        //        ImageView img_thumbnail;
        LinearLayout view_container;





        public MyViewHolder(View itemView) {
            super(itemView);
//
//            view_container = itemView.findViewById(R.id.container);
//            tv_name =   itemView.findViewById(R.id.anime_name);
//            tv_email = itemView.findViewById(R.id.email);
//            tv_order_id = itemView.findViewById(R.id.order_id);
//            tv_total = itemView.findViewById(R.id.total);
//            tv_telephone =  itemView.findViewById(R.id.telephone);
//            tv_address = itemView.findViewById(R.id.address);
//            tv_status = itemView.findViewById(R.id.status);
//            tv_companyName = itemView.findViewById(R.id.ocompanyName);
//            tv_ddate =  itemView.findViewById(R.id.odeliveryDate);
//            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }

    }
}
