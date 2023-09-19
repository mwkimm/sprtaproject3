package net.flow9.thisiskotlin.spartaproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import net.flow9.thisiskotlin.spartaproject.databinding.ActivityMainBinding
import net.flow9.thisiskotlin.spartaproject.databinding.GridViewLayoutBinding
import net.flow9.thisiskotlin.spartaproject.databinding.ViewPagerLayoutBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewPager2: ViewPager2 by lazy { binding.includedViewpager.viewPager }
    private val gridView: GridView by lazy { binding.includedGridview.gridview}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val fragmentList = listOf(SearchFragment(), SaveFragment())
        val pagerAdapter = MyPagerAdapter(this@MainActivity, fragmentList)
        viewPager2.adapter = pagerAdapter


        val adapter = MyAdapter(this, ItemList)
        gridView.adapter = adapter


        binding.fragmentBtn1.setOnClickListener {
            viewPager2.currentItem = 0
        }

        binding.fragmentBtn2.setOnClickListener {
            viewPager2.currentItem = 1
        }


    }
}
