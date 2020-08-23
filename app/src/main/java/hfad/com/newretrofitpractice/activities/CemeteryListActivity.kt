package hfad.com.newretrofitpractice.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hfad.com.newretrofitpractice.R
import hfad.com.newretrofitpractice.adapters.CemeteryListAdapter
import hfad.com.newretrofitpractice.adapters.CemeteryListener
import hfad.com.newretrofitpractice.databinding.ActivityCemeteryListBinding
import hfad.com.newretrofitpractice.viewmodels.CemeteryListActivityViewModel

class CemeteryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCemeteryListBinding
    private lateinit var viewModel: CemeteryListActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cemetery_list)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(CemeteryListActivityViewModel::class.java)

        val adapter = CemeteryListAdapter(CemeteryListener {

        })

        viewModel.allCemeteries.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.cemeteryListRecyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_cem_action ->{
                val intent = Intent(this, CreateCemeteryActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}