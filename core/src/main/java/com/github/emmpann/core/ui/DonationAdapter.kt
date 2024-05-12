package com.github.emmpann.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.emmpann.core.databinding.DonationItemBinding
import com.github.emmpann.core.domain.model.Orphanage

class DonationAdapter : RecyclerView.Adapter<DonationAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private val listOrphanage: ArrayList<Orphanage> = arrayListOf()

    interface OnItemClickCallback {
        fun onItemClicked(orphanage: Orphanage)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun submitList(listOrphanage: List<Orphanage>) {
        this.listOrphanage.addAll(listOrphanage)
        if (listOrphanage.size > 1) notifyItemRangeChanged(0, listOrphanage.lastIndex) else notifyItemInserted(0)
    }

    inner class ViewHolder(private val binding: DonationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orphanage: Orphanage) {
            with(binding) {
                tvPantiTitle.text = orphanage.name
                Glide.with(binding.root.context)
                    .load(orphanage.thumbnail)
                    .into(ivPanti)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DonationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listOrphanage.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orphanage = listOrphanage[position]
        holder.bind(orphanage)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(orphanage) }
    }
}