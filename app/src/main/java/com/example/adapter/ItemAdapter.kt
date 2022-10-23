package com.example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.beeranimation.DetailFragmentDirections
import com.example.beeranimation.ListFragmentDirections
import com.example.beeranimation.R
import com.example.model.Pub



class ItemAdapter(
    private val context: Context,
    private var dataset: ArrayList<Pub>


    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val btnDel: Button = view.findViewById(R.id.buttonDelete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.textView.text = item.tags.name
        holder.textView.setOnClickListener {
            val direction = ListFragmentDirections.actionListFragmentToDetailFragment(
                item.tags.name,
                item.lat,
                item.lon,
                item.tags.website,
                item.tags.phone,
                item.tags.opened_hours
            )
            it.findNavController().navigate(direction)
        }
        holder.btnDel.setOnClickListener {
            deleteItem(position)
        }

    }

    fun deleteItem(position: Int) {
        dataset.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }


    override fun getItemCount() = dataset.size
}