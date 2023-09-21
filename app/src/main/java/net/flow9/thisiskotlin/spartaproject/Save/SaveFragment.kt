package net.flow9.thisiskotlin.spartaproject.Save

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            recyclerviewSave.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerviewSave.adapter = adapter

        }
        return binding?.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}