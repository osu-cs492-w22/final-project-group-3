package com.example.banishthem

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

class ChampionMasteryAdapter  : RecyclerView.Adapter<ChampionMasteryAdapter.ViewHolder>() {
    var championMasteries = listOf<ChampionMastery>()
    public val map = hashMapOf(
        "266" to "Aatrox",
        "103" to "Ahri",
        "84" to "Akali",
        "166" to "Akshan",
        "12" to "Alistar",
        "32" to "Amumu",
        "34" to "Anivia",
        "1" to "Annie",
        "523" to "Aphelios",
        "22" to "Ashe",
        "136" to "Aurelion Sol",
        "268" to "Azir",
        "432" to "Bard",
        "53" to "Blitzcrank",
        "63" to "Brand",
        "201" to "Braum",
        "51" to "Caitlyn",
        "164" to "Camille",
        "69" to "Cassiopeia",
        "31" to "Cho'Gath",
        "42" to "Corki",
        "122" to "Darius",
        "131" to "Diana",
        "119" to "Draven",
        "36" to "Dr. Mundo",
        "245" to "Ekko",
        "60" to "Elise",
        "28" to "Evelynn",
        "81" to "Ezreal",
        "9" to "Fiddlesticks",
        "114" to "Fiora",
        "105" to "Fizz",
        "3" to "Galio",
        "41" to "Gangplank",
        "86" to "Garen",
        "150" to "Gnar",
        "79" to "Gragas",
        "104" to "Graves",
        "887" to "Gwen",
        "120" to "Hecarim",
        "74" to "Heimerdinger",
        "420" to "Illaoi",
        "39" to "Irelia",
        "427" to "Ivern",
        "40" to "Janna",
        "59" to "Jarvan IV",
        "24" to "Jax",
        "126" to "Jayce",
        "202" to "Jhin",
        "222" to "Jinx",
        "145" to "Kai'Sa",
        "429" to "Kalista",
        "43" to "Karma",
        "30" to "Karthus",
        "38" to "Kassadin",
        "55" to "Katarina",
        "10" to "Kayle",
        "141" to "Kayn",
        "85" to "Kennen",
        "121" to "Kha'Zix",
        "203" to "Kindred",
        "240" to "Kled",
        "96" to "Kog'Maw",
        "7" to "LeBlanc",
        "64" to "Lee Sin",
        "89" to "Leona",
        "876" to "Lillia",
        "127" to "Lissandra",
        "236" to "Lucian",
        "117" to "Lulu",
        "99" to "Lux",
        "54" to "Malphite",
        "90" to "Malzahar",
        "57" to "Maokai",
        "11" to "Master Yi",
        "21" to "Miss Fortune",
        "62" to "Wukong",
        "82" to "Mordekaiser",
        "25" to "Morgana",
        "267" to "Nami",
        "75" to "Nasus",
        "111" to "Nautilus",
        "518" to "Neeko",
        "76" to "Nidalee",
        "56" to "Nocturne",
        "20" to "Nunu & Willump",
        "2" to "Olaf",
        "61" to "Orianna",
        "516" to "Ornn",
        "80" to "Pantheon",
        "78" to "Poppy",
        "555" to "Pyke",
        "246" to "Qiyana",
        "133" to "Quinn",
        "497" to "Rakan",
        "33" to "Rammus",
        "421" to "Rek'Sai",
        "526" to "Rell",
        "888" to "Renata Glasc",
        "58" to "Renekton",
        "107" to "Rengar",
        "92" to "Riven",
        "68" to "Rumble",
        "13" to "Ryze",
        "360" to "Samira",
        "113" to "Sejuani",
        "235" to "Senna",
        "147" to "Seraphine",
        "875" to "Sett",
        "35" to "Shaco",
        "98" to "Shen",
        "102" to "Shyvana",
        "27" to "Singed",
        "14" to "Sion",
        "15" to "Sivir",
        "72" to "Skarner",
        "37" to "Sona",
        "16" to "Soraka",
        "50" to "Swain",
        "517" to "Sylas",
        "134" to "Syndra",
        "223" to "Tahm Kench",
        "163" to "Taliyah",
        "91" to "Talon",
        "44" to "Taric",
        "17" to "Teemo",
        "412" to "Thresh",
        "18" to "Tristana",
        "48" to "Trundle",
        "23" to "Tryndamere",
        "4" to "Twisted Fate",
        "29" to "Twitch",
        "77" to "Udyr",
        "6" to "Urgot",
        "110" to "Varus",
        "67" to "Vayne",
        "45" to "Veigar",
        "161" to "Vel'Koz",
        "711" to "Vex",
        "254" to "Vi",
        "234" to "Viego",
        "112" to "Viktor",
        "8" to "Vladimir",
        "106" to "Volibear",
        "19" to "Warwick",
        "498" to "Xayah",
        "101" to "Xerath",
        "5" to "Xin Zhao",
        "157" to "Yasuo",
        "777" to "Yone",
        "83" to "Yorick",
        "350" to "Yuumi",
        "154" to "Zac",
        "238" to "Zed",
        "221" to "Zeri",
        "115" to "Ziggs",
        "26" to "Zilean",
        "142" to "Zoe",
        "143" to "Zyra"
    )

    override fun getItemCount() = this.championMasteries.size

    fun updateChampionMastery(newChampionMasteryList: List<ChampionMastery>?) {
        this.championMasteries = newChampionMasteryList ?: listOf()
        notifyDataSetChanged()
    }
    /*
    fun deleteChampionMasteryAt(position: Int): ChampionMastery {
        val championMastery = this.championMasteries.removeAt(position)
        this.notifyItemRemoved(position)
        return championMastery
    }
    */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mastery_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.championMasteries[position])
    }
    fun getDrawableByFileName(context: Context, fileName: String): Drawable? {
        return ContextCompat.getDrawable(context, context.resources.getIdentifier(fileName, "drawable", context.packageName))
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val counterTV: TextView = view.findViewById(R.id.tv_counter)
        private val championIconIV: ImageView = view.findViewById(R.id.iv_champion_icon)
        private val championNameTV: TextView = view.findViewById(R.id.tv_champion_name)
        private val masteryIconIV: ImageView = view.findViewById(R.id.iv_mastery_icon)
        private val championPointsTV: TextView = view.findViewById(R.id.tv_mastery_points)

        fun bind(championMastery: ChampionMastery) {
            this.counterTV.text = (position + 1).toString()
            //this.championNameTV.text = map[championMastery.championId.toString()]
            this.championPointsTV.text = championMastery.championPoints.toString()
            var name: String = map[championMastery.championId.toString()]!!
            name = name.filter{ it.isLetterOrDigit() }
            name = name.lowercase()
            //Log.i("BIND", name) //used to find the champion name that is breaking the app
            this.championIconIV.background = getDrawableByFileName(this.championIconIV.context, name)
            var mastery: String = "mastery" + championMastery.championLevel.toString()
            this.masteryIconIV.background = getDrawableByFileName(this.championIconIV.context, mastery)



        }
    }
}