package com.haiying.p2papp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.haiying.p2papp.MyApplication;
import com.haiying.p2papp.activity.R;
import com.haiying.p2papp.conn.JsonVip;
import com.zcx.helper.bound.BoundViewHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 4/13/2016.
 */
public class ApplyVipAdapter extends RecyclerView.Adapter<ApplyVipAdapter.ViewHolder> {

    private int select = 0;
    private Context context;
    private List<JsonVip.Info.ListContent> list;

    MyItemClickListener listener = null;

    public ApplyVipAdapter(Context context, List<JsonVip.Info.ListContent> list) {
        this.context = context;
        this.list = list;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        @Bind(R.id.iv_01)
        SimpleDraweeView iv01;
        @Bind(R.id.iv_02)
        ImageView iv02;
        @Bind(R.id.tv_01)
        TextView tv01;
        private MyItemClickListener mListener;


    /*    public ViewHolder(View rootView,MyItemClickListener listener) {
            super(rootView);

            this.mListener = listener;

            rootView.setOnClickListener(this);

        }*/

        public ViewHolder(View view, MyItemClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.mListener = listener;
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }


    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
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
                .inflate(R.layout.gird_01_item_view, null)));
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(view, listener);

        return vh;
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link  ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int)} instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv01.setText(list.get(position).real_name);
        holder.iv01.setImageURI(Uri.parse(list.get(position).avatar));

        holder.iv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                holder.iv02.setImageResource(R.mipmap.ic_action_applyvip_check);
                holder.iv02.setSelected(true);
            }
        });

      //        holder.iv02.setSelected(select==position);
        //  holder.iv02.setSelected(false);
        /*holder.iv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select = position;
                notifyDataSetChanged();
//                holder.iv02.setImageResource(context.getResources().getDrawable(R.drawable.selector_iv_applyvip_checkbox));
                holder.iv02.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.selector_iv_applyvip_checkbox));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = position;
                notifyDataSetChanged();
//                holder.iv02.setSelected(true);
                holder.iv02.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.selector_iv_applyvip_checkbox));
            }
        });
*/


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

    public int getSelectItemPosition() {
        return select;
    }
}
