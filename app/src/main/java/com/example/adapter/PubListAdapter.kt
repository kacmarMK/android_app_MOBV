package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beeranimation.R
import com.example.data.Pub

class PubListAdapter : ListAdapter<Pub, PubListAdapter.PubViewHolder>(PubNameComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PubViewHolder {
        return PubViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PubViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id)
    }

    class PubViewHolder(pubView: View) : RecyclerView.ViewHolder(pubView) {
        private val pubNameView: TextView = pubView.findViewById(R.id.item_title)

        fun bind(text: String?) {
            pubNameView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): PubViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
                return PubViewHolder(view)
            }
        }
    }

    class PubNameComparator : DiffUtil.ItemCallback<Pub>() {
        override fun areItemsTheSame(oldPub: Pub, newPub: Pub): Boolean {
            return oldPub === newPub
        }

        override fun areContentsTheSame(oldPub: Pub, newPub: Pub): Boolean {
            return oldPub.id == newPub.id
        }
    }
}