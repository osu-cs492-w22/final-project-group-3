package com.example.banishthem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChampionMasteryAdapter  : RecyclerView.Adapter<ChampionMasteryAdapter.ViewHolder>() {
    val championMasteries: MutableList<ChampionMastery> = mutableListOf()

    override fun getItemCount() = this.championMasteries.size

    fun addChampionMastery(championMastery: ChampionMastery, position: Int = 0) {
        this.championMasteries.add(position, championMastery)
        this.notifyItemInserted(position)
    }

    fun deleteChampionMasteryAt(position: Int): ChampionMastery {
        val championMastery = this.championMasteries.removeAt(position)
        this.notifyItemRemoved(position)
        return championMastery
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mastery_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.championMasteries[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val counterTV: TextView = view.findViewById(R.id.tv_counter)
        private val championIconIV: ImageView = view.findViewById(R.id.iv_champion_icon)
        private val championNameTV: TextView = view.findViewById(R.id.tv_champion_name)
        private val masteryIconIV: ImageView = view.findViewById(R.id.iv_mastery_icon)
        private val championPointsTV: TextView = view.findViewById(R.id.tv_mastery_points)

        fun bind(championMastery: ChampionMastery) {
            this.counterTV.text = (position + 1).toString()
            this.championNameTV.text = championMastery.championId.toString()
            this.championPointsTV.text = championMastery.championPoints.toString()
        }
    }
}