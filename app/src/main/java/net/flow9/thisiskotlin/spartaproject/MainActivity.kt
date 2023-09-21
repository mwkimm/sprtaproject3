package net.flow9.thisiskotlin.spartaproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import net.flow9.thisiskotlin.spartaproject.Model.SearchModel
import net.flow9.thisiskotlin.spartaproject.Save.SaveFragment
import net.flow9.thisiskotlin.spartaproject.Search.SearchFragment
import net.flow9.thisiskotlin.spartaproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    var likedItems: ArrayList<SearchModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnSearchFragment.setOnClickListener {
                setFragment(SearchFragment())
            }
            btnSaveFragment.setOnClickListener {
                setFragment(SaveFragment())
            }
        }

        //초기 프래그먼트로
        setFragment(SearchFragment())


    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frameLayout, frag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    fun addLikedItem(item: SearchModel){
        if(!likedItems.contains(item)){
            likedItems.add(item)
        }
    }

    fun removeLikedItem(item: SearchModel){
        likedItems.remove(item)
    }


}





