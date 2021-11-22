package com.example.dk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    public interface ProductListener{
        void onClick(View v,int id);
    }

    //this context we will use to inflate the layout
    private Context mCtx;
    ProductListener onClickListener;
    View.OnClickListener onClickListener2;

    //we are storing all the products in a list
    private List<kala> productList;

    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, List<kala> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.kalaview, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        kala product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.buy.setOnClickListener(v -> onClickListener.onClick(v,0));
        holder.nazarat.setOnClickListener(v -> onClickListener.onClick(v,1));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;
        protected Button buy;
        protected Button nazarat;
        public ProductViewHolder(View itemView) {
            super(itemView);
            buy = itemView.findViewById(R.id.ButtonBuy);
            nazarat = itemView.findViewById(R.id.nazarat);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            nazarat.setTag(this);
            buy.setTag(this);
//            nazarat.setOnClickListener(onClickListener);
//            buy.setOnClickListener(onClickListener);
        }
    }

    public void setOnClickListener(ProductListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}