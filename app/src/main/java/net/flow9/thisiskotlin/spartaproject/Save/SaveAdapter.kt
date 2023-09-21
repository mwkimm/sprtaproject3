package net.flow9.thisiskotlin.spartaproject.Save

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
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class SaveAdapter(var Context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<SearchModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(Context)
            .load(items[position].url)
            .into((holder as ItemViewHolder).iv_load_image)

        holder.iv_like.visibility = View.GONE
        holder.tv_title.text = items[position].title
        holder.tv_datetime.text = getDateFromTimestampWithFormat(
            items[position].dateTime,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var iv_load_image: ImageView = binding.ivLoadImage
        var iv_like: ImageView = binding.ivLike
        var tv_title: TextView = binding.tvTitle
        var tv_datetime: TextView = binding.tvDatetime
        var cl_item: ConstraintLayout = binding.clLoadItem

        init {
            iv_like.visibility = View.GONE

            cl_item.setOnClickListener {
                val position = adapterPosition
                (Context as MainActivity).removeLikedItem(items[position])

                if (position != RecyclerView.NO_POSITION) {
                    items.removeAt(position)
                    notifyItemRemoved(position)

                }
            }
        }
    }
}
