package net.flow9.thisiskotlin.spartaproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(private val context: Context, private val items: List<GridItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)

        val item = items[position]

        val imageView: ImageView = view.findViewById(R.id.gridItemImage)
        imageView.setImageResource(item.imageResource)

        val textView: TextView = view.findViewById(R.id.gridItemText)
        val textView2: TextView = view.findViewById(R.id.gridItemText2)
        textView.text = item.text
        textView2.text = item.date


        return view
    }
}
