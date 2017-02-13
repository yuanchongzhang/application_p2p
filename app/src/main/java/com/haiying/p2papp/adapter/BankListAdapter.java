package com.haiying.p2papp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.haiying.p2papp.MyApplication;
import com.haiying.p2papp.activity.ChoseBankActivity;
import com.haiying.p2papp.activity.R;
import com.haiying.p2papp.conn.JsonBanklist;
import com.zcx.helper.bound.BoundViewHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 3/7/2016.
 */
public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.ViewHolder> implements View.OnClickListener {

    private OnRecyclerViewItemClickListener myOnItemClickListener = null;
    private Context context;
    private int flag = 0;
    private List<JsonBanklist.Info.ListContent> list;
    OnItemClickListener onItemClickListener;

    public BankListAdapter(Context context, List<JsonBanklist.Info.ListContent> list, int flag) {
        this.context = context;
        this.list = list;
        this.flag = flag;
    }

    public void setOnItemCliclListener(OnRecyclerViewItemClickListener listener) {
        this.myOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (myOnItemClickListener != null) {
            //将点击事件转移给外面的调用者
            myOnItemClickListener.onItemClick(v);
        }
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @Bind(R.id.iv_logo)
        ImageView ivLogo;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_id)
        TextView tvId;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = BoundViewHelper.boundView(this, MyApplication.scaleScreenHelper.loadView((ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_17_item_view, null)));
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p/>
     * Note that unlike {@link  ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p/>
     * Override {@link #onBindViewHolder(ViewHolder, int)} instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvName.setText(list.get(position).bank_name);
        Log.d(list.get(position).bank_name, "-----------------------------");

        if (!TextUtils.isEmpty(list.get(position).bank_num)) {
            holder.tvId.setText(list.get(position).bank_num);
        }
        holder.ivLogo.setImageURI(Uri.parse(list.get(position).bank_ico));
        if (flag == 1) {
            holder.tvId.setVisibility(View.GONE);
        }
        holder.tvId.setVisibility(View.GONE);
        holder.itemView.setTag(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", list.get(position).id);
                intent.putExtra("name", list.get(position).bank_name);
                Log.d(list.get(0).bank_name, "66666666666666666666666");

                intent.putExtra("ico", list.get(position).bank_ico);
                if (!TextUtils.isEmpty(list.get(position).bank_num)) {
                    intent.putExtra("num", list.get(position).bank_num);
                }
                intent.setClass(context, ChoseBankActivity.class);
                ((Activity) context).setResult(Activity.RESULT_OK, intent);

                //context.startActivity(intent);

                ((Activity) context).finish();
            }
        });

    }


    public interface OnItemClickListener {
        void onClick(int position);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return list.size();
    }


    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
    }


}
