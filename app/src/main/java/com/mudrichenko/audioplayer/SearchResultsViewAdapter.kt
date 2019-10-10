package com.mudrichenko.audioplayer

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mudrichenko.GlideApp
import com.mudrichenko.audioplayer.data.AudioInfo

import java.util.ArrayList
import androidx.constraintlayout.widget.ConstraintLayout

class SearchResultsViewAdapter(private val mContext: Context, private val mItemList: ArrayList<AudioInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal var mOnItemClickListener: OnItemClickListener? = null

    lateinit var mImageView: ImageView

    lateinit var mTextViewName: TextView

    lateinit var mTextViewArtist: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            mImageView = holder.imageView
            GlideApp.with(mImageView)
                    .load(mItemList[position].imageUrl)
                    .placeholder(mContext.resources.getDrawable(R.drawable.image_default))
                    .centerCrop()
                    .into(mImageView)
            mTextViewName = holder.textViewName
            mTextViewName.setText(mItemList[position].songName)
            mTextViewArtist = holder.textViewArtist
            mTextViewArtist.setText(mItemList[position].artistName)
        }
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

        internal var mainLayout: ConstraintLayout = itemView.findViewById(R.id.main_layout)

        internal var imageView: ImageView = itemView.findViewById(R.id.imageView)

        internal var textViewName: TextView = itemView.findViewById(R.id.textViewName)

        internal var textViewArtist: TextView = itemView.findViewById(R.id.textViewArtist)

        init {
            mainLayout.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener!!.onItemClick(layoutPosition)
            }
        }

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            return true
        }

        override fun onLongClick(v: View?): Boolean {
            return true
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {

        fun onItemClick(position: Int)

    }

}
