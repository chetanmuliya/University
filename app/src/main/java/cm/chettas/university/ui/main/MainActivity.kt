package cm.chettas.university.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import cm.chettas.university.R
import cm.chettas.university.databinding.ActivityMainBinding
import cm.chettas.university.ui.adapter.UniversityAdapter
import cm.chettas.university.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnQueryTextListener {

    private lateinit var adapter: UniversityAdapter
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setUpUi()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel.res.observe(this, Observer {
            when(it){
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    it.data?.let { it1 -> adapter.addUniversityList(it1) }
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                }
                else-> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()
                }
            }
        })

        //setting search list
        lifecycleScope.launch {
            viewModel.filteredCategories.collectLatest {
                adapter.addUniversityList(newList = it)
            }
        }
    }

    private fun setUpUi() {
        adapter = UniversityAdapter()
        binding.rvUniversities.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        val expandListener = object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                //Log.d("TAG", "onMenuItemActionExpand: ")
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                //Log.d("TAG", "onMenuItemActionCollapse: ")
                return true
            }
        }

        val actionSearch = menu?.findItem(R.id.action_search)
        actionSearch?.setOnActionExpandListener(expandListener)
        val searchView: SearchView = actionSearch?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return true
    }

    //Search bar listener after clicking
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.queryText = newText
        return true
    }
}