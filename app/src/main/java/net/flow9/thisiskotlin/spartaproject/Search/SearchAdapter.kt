package net.flow9.thisiskotlin.spartaproject.Search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.flow9.thisiskotlin.spartaproject.MainActivity
import net.flow9.thisiskotlin.spartaproject.Model.SearchModel
import net.flow9.thisiskotlin.spartaproject.UtilsApi.getDateFromTimestampWithFormat
import net.flow9.thisiskotlin.spartaproject.databinding.SearchItemBinding

class SearchAdapter(private val Context: Context): RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    var items = ArrayList<SearchModel>()

    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(Context)
            .load(currentItem.url)
            .into(holder.iv_load_image)
        //holder.iv_like.visibility = if (currentItem.isLike) View.VISIBLE else View.INVISIBLE
        if(currentItem.isLike){
            holder.iv_like.visibility = View.VISIBLE
        } else{
            holder.iv_like.visibility = View.INVISIBLE
        }
        holder.tv_title.text = currentItem.title
        holder.tv_datetime.text = getDateFromTimestampWithFormat(
            currentItem.dateTime,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

            var iv_load_image: ImageView = binding.ivLoadImage
            var iv_like: ImageView = binding.ivLike
            var tv_title: TextView = binding.tvTitle
            var tv_datetime: TextView = binding.tvDatetime
            var cl_load_item: ConstraintLayout = binding.clLoadItem

            init {
                iv_like.visibility = View.GONE
                iv_load_image.setOnClickListener(this)
                cl_load_item.setOnClickListener(this)
            }

            override fun onClick(view: View){
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
                val item = items[position]

                item.isLike = !item.isLike

                if (item.isLike){
                    (Context as MainActivity).addLikedItem(item)
                } else {
                    (Context as MainActivity).removeLikedItem(item)
                }

                notifyItemChanged(position)
            }
        }
}

