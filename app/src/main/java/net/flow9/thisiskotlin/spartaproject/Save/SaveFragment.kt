package net.flow9.thisiskotlin.spartaproject.Save

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import net.flow9.thisiskotlin.spartaproject.MainActivity
import net.flow9.thisiskotlin.spartaproject.Model.SearchModel
import net.flow9.thisiskotlin.spartaproject.databinding.FragmentSaveBinding


class SaveFragment : Fragment() {

    private lateinit var mContext: Context

    private var binding: FragmentSaveBinding? = null
    private lateinit var adapter: SaveAdapter

    private var likedItems: List<SearchModel> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mainActivity = activity as MainActivity
        likedItems = mainActivity.likedItems

        adapter = SaveAdapter(mContext).apply {
            items = likedItems.toMutableList()
        }

        binding = FragmentSaveBinding.inflate(inflater, container, false).apply {
            recyclerviewSaveResult.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerviewSaveResult.adapter = adapter

            val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
            val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
            var isTop = true

            recyclerviewSaveResult.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerviewSaveResult.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        saveScrollUp.startAnimation(fadeOut)
                        saveScrollUp.visibility = View.GONE
                        isTop = true
                    } else {
                        if (isTop) {
                            saveScrollUp.visibility = View.VISIBLE
                            saveScrollUp.startAnimation(fadeIn)
                            isTop = false
                        }
                    }
                }
            })

            saveScrollUp.setOnClickListener {
                recyclerviewSaveResult.smoothScrollToPosition(0)
            }
        }

        return binding?.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}