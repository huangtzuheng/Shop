package com.example.user.project.Utils.Cart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.project.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder> {
    private String[] mCartData;
    private final CartAdapter.CartAdapterOnClickHandler mClickHandler;

    public interface CartAdapterOnClickHandler {
        void onClick(String thisGood);
    }

    public CartAdapter(String[] mData, CartAdapter.CartAdapterOnClickHandler clickHandler) {
        mCartData = mData;
        mClickHandler = clickHandler;
    }

    public class CartAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mGoodTextView;
        public RelativeLayout mRelativeLayout;

        public CartAdapterViewHolder(View view) {
            super(view);

            mGoodTextView = (TextView) view.findViewById(R.id.tv_cartList);
            mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_cartList);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String thisGood = mCartData[adapterPosition];
            mClickHandler.onClick(thisGood);
        }
    }

    @Override
    public CartAdapter.CartAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.cart_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new CartAdapter.CartAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartAdapter.CartAdapterViewHolder cartAdapterViewHolder, int position) {
        String cartItem = mCartData[position];
        cartAdapterViewHolder.mGoodTextView.setText(cartItem);
    }

    @Override
    public int getItemCount() {
        if (mCartData == null) return 0;
        return mCartData.length;
    }

    public void setCartData(String[] cartData) {
        mCartData = cartData;
        notifyDataSetChanged();
    }
}
