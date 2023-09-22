package net.flow9.thisiskotlin.spartaproject.Search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import net.flow9.thisiskotlin.spartaproject.Api
//import net.flow9.thisiskotlin.spartaproject.Constants
import net.flow9.thisiskotlin.spartaproject.Model.ImgModel
import net.flow9.thisiskotlin.spartaproject.Model.SearchModel
import net.flow9.thisiskotlin.spartaproject.UtilsApi
//import kotlinx.coroutines.DefaultExecutor.enqueue
import net.flow9.thisiskotlin.spartaproject.databinding.FragmentSearchBinding
import net.flow9.thisiskotlin.spartaproject.retrofit_client.apiService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {

    private lateinit var gridmanager: StaggeredGridLayoutManager
    private lateinit var adapter: SearchAdapter
    private lateinit var mContext: Context
    private lateinit var binding: FragmentSearchBinding


    private var restItems: ArrayList<SearchModel> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupViews()
        setupListeners()


        binding.etSearch.setOnKeyListener { v, keyCode, event ->
            //if(event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER){
              if((keyCode == KEYCODE_ENTER)){
                  val keyword:String by lazy{
                      if(binding.etSearch.text.toString().isNullOrEmpty()){
                          return@lazy ""
                      } else{
                          return@lazy ""
                      }
                  }
              binding.tvSearch.requestFocus()
              binding.tvSearch.performClick()
            }
            return@setOnKeyListener false
        }  //검색창에서 엔터키치면 검색 테스트뷰로 넘어가는 기능


//        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
//            Log.d("actionId", "${actionId}, ${event.keyCode}")
//                if (actionId == EditorInfo.IME_ACTION_DONE ||
//                    event.action == KeyEvent.ACTION_DOWN ||
//                    event.keyCode == KEYCODE_ENTER) {
//                binding.tvSearch.requestFocus()
//                binding.tvSearch.performClick()
//                true
//            } else {
//                false
//            }
//        }      //검색창에서 엔터키치면 검색 테스트뷰로 넘어가는 기능


        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop = true

        binding.recyclerviewSearchResult.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerviewSearchResult.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.searchScrollUp.startAnimation(fadeOut)
                    binding.searchScrollUp.visibility = View.GONE
                    isTop = true
                } else {
                    if (isTop) {
                        binding.searchScrollUp.visibility = View.VISIBLE
                        binding.searchScrollUp.startAnimation(fadeIn)
                        isTop = false
                    }
                }
            }
        })

        binding.searchScrollUp.setOnClickListener {
            binding.recyclerviewSearchResult.smoothScrollToPosition(0)
        }

        return binding.root
    }

    private fun setupViews() {
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerviewSearchResult.layoutManager = gridmanager

        adapter = SearchAdapter(mContext)
        binding.recyclerviewSearchResult.adapter = adapter
        binding.recyclerviewSearchResult.itemAnimator = null

        val lastSearch = UtilsApi.getLastSearch(requireContext())
        binding.etSearch.setText(lastSearch)

    }


    private fun setupListeners() {
        binding.tvSearch.setOnClickListener {

            val query = binding.etSearch.text.toString()
            Log.d("searchFragment", "search click=${query}")
            if (query.isNotEmpty()) {
                UtilsApi.saveLastSearch(requireContext(), query)
                adapter.clearItem()
                fetchImageResults(query)
            } else {
                Toast.makeText(mContext, "검색어를 입력 하세요.", Toast.LENGTH_SHORT).show()
            }

            val im =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            im.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)

            binding.etSearch.requestFocus()

        }

    }

    private fun fetchImageResults(query: String) {
        apiService.image_search(Api.AUTH_HEADER, query, "recency", 1, 80)
            ?.enqueue(object : Callback<ImgModel?> {
                override fun onResponse(call: Call<ImgModel?>, response: Response<ImgModel?>) {
                    Log.d("searchFragment", "onReponse=${response.body()?.meta?.totalCount}")
                    response.body()?.meta?.let { meta ->
                        if (meta.totalCount > 0) {
                            response.body()!!.documents.forEach { document ->
                                val title = document.displaySitename
                                val datetime = document.datetime
                                val url = document.thumbnailUrl
                                restItems.add(SearchModel(title, datetime, url))
                            }
                        }
                    }
                    adapter.items = restItems
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ImgModel?>, t: Throwable) {
                    Log.e("#mwkim", "onFailure: ${t.message}")
                }
            })
    }// 검색 메소드


}