package com.example.ucltopscorers

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ucltopscorers.databinding.ItemRowScoreBinding

class ListTopScoreAdapter(private val listTopScore: ArrayList<TopScore>) :
    RecyclerView.Adapter<ListTopScoreAdapter.ListViewHolder>() {
    companion object {
        const val KEY_TS = "key_top_score"
    }

    class ListViewHolder(var binding: ItemRowScoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listTopScore.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (img, name, goal, desc) = listTopScore[position]
        holder.binding.imgItem.setImageResource(img)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemGoals.text = "$goal Goals"
        holder.binding.tvItemDescription.text = desc

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(KEY_TS, listTopScore[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}
