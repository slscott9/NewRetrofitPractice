package hfad.com.newretrofitpractice.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hfad.com.newretrofitpractice.data.Cemetery
import hfad.com.newretrofitpractice.databinding.CemeteryListItemBinding
import hfad.com.newretrofitpractice.domain.CemeteryDomainModel

class CemeteryListAdapter(val clickListener: CemeteryListener): ListAdapter<CemeteryDomainModel, CemeteryListAdapter.ViewHolder>(
    CemeteryDiffUtilCallback()
){ //1.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CemeteryListItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder (val binding: CemeteryListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: CemeteryDomainModel,
            clickListener: CemeteryListener
        ){
            binding.cemetery = item
            binding.clickListener = clickListener //14.
            binding.executePendingBindings()
        }
    }
}

class CemeteryDiffUtilCallback: DiffUtil.ItemCallback<CemeteryDomainModel>(){
    override fun areItemsTheSame(oldItem: CemeteryDomainModel, newItem: CemeteryDomainModel): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: CemeteryDomainModel, newItem: CemeteryDomainModel): Boolean {
        return oldItem == newItem
    }
}

class CemeteryListener(val clickListener: (id: Int) -> Unit){
    fun onClick(cemetery: CemeteryDomainModel){
        clickListener(cemetery.id!!)
    }
}

