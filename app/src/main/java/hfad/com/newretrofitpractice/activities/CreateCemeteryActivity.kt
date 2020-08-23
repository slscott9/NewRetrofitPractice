package hfad.com.newretrofitpractice.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hfad.com.newretrofitpractice.R
import hfad.com.newretrofitpractice.data.Cemetery
import hfad.com.newretrofitpractice.databinding.ActivityCreateCemeteryBinding
import hfad.com.newretrofitpractice.viewmodels.CemeteryListActivityViewModel

class CreateCemeteryActivity : AppCompatActivity() {

    private lateinit var viewModel: CemeteryListActivityViewModel
    private lateinit var binding: ActivityCreateCemeteryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_cemetery)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(CemeteryListActivityViewModel::class.java) //gets the existing viewmodel or creates a new one if it does not exist




        binding.addCemButton.setOnClickListener {
            if(
                binding.etCemeteryName.text.isNullOrEmpty() ||
                binding.etCemeteryLocation.text.isNullOrEmpty() ||
                binding.etCemeteryState.text.isNullOrEmpty() ||
                binding.etCemeteryCounty.text.isNullOrEmpty() ||
                binding.etCemeteryTownship.text.isNullOrEmpty() ||
                binding.etCemeteryRange.text.isNullOrEmpty() ||
                binding.etCemeterySection.text.isNullOrEmpty() ||
                binding.etCemeteryFirstYear.text.isNullOrEmpty()
            ){
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }else{
                val name = binding.etCemeteryName.text.toString()
                val location = binding.etCemeteryLocation.text.toString()
                val state = binding.etCemeteryState.text.toString()
                val county = binding.etCemeteryCounty.text.toString()
                val townShip = binding.etCemeteryTownship.text.toString()
                val range = binding.etCemeteryRange.text.toString()
                val section = binding.etCemeterySection.text.toString()
                val spot = binding.etCemeterySpot.text.toString()
                val firstYear = binding.etCemeteryFirstYear.text.toString()
                val cemetery =
                    Cemetery(
                        id = viewModel.newCemeteryIdentifier.value!!,
                        cemeteryName = name,
                        cemeteryLocation = location,
                        cemeteryState = state,
                        cemeteryCounty = county,
                        township = townShip,
                        range = range,
                        section = section,
                        spot = spot,
                        firstYear = firstYear
                    )
                Log.i("CemeteryViewModel", "In CreateCemeteryActivity before sending to network the counter is ${viewModel.newCemeteryIdentifier.value}")
                viewModel.insertCemetery(cemetery)
                viewModel.sendNewCemeteryToNetwork(cemetery)
                viewModel.incrementNewCemeteryIdentifier()
                finish()
            }
        }
    }
}