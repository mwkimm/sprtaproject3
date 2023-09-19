package net.flow9.thisiskotlin.spartaproject

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

//class RecyclerViewAdapter(private val items: List<RecyclerModel>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHodler> {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

//    var itemClick : ItemClick? = null
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHodler {
//        val binding = RecyclerViewItemBinding.inflate(layoutInflater.from(parent.context), parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerViewAdapter, position: Int) {
//        var item = items[position]
//
//        Glide.with(holder.itemView.context)
//            .load(item.thumbnail)
//            .transition(DrawableTranstionOptions.with(withCrossFade()))
//            .into(holder.imageView)
//
//        holder.siteNameTextView.text = item.sitename
//        holder.dateTextView.text = item.datetime
//
//        holder.itemView.setOnClickListener {
//            itemClick?.onClick(it, position)
//        }
//
//        if(position % 2 == 0){
//            holder.itemView.setPadding(
//                holder.itemView.paddingLeft,
//                holder.itemView.paddingTop,
//                0,
//                holder.itemView.paddingBottom
//            )
//        }
//    }
//
//
//    override fun getItemCount(): Int{
//        return items.size
//    }
//
//    inner class MyViewHolder(private val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
//        val imageView = binding.itemImage
//        val siteNameTextView = binding.itemName
//        val dateTextView = binding.itemDate
//        init {
//            itemView.setOnClickListener{
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION){
//                    itemClick?.onClick(it, position)
//                }
//            }
//        }
//    }
//}

class ItemSpacingDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration(){
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = spacing
        outRect.right = spacing
        outRect.top = spacing
    }
}