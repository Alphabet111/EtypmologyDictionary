package dictionary.etymology.alphabet.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dictionary.etymology.alphabet.database.databean.WordBean
import dictionary.etymology.alphabet.etymologydictionary.R

/**
 * Created by alphabet on 2018/2/1.
 */
class DescriptionAdapter(private val context: Context) : RecyclerView.Adapter<DescriptionAdapter.DescriptionViewHolder>(){
    override fun onBindViewHolder(holder: DescriptionViewHolder?, position: Int) {
        holder?.setValue(list?.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DescriptionViewHolder
        = DescriptionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_description,parent,false))

    override fun getItemCount(): Int {
        var i = if (list == null) 0 else list!!.size
        return i
    }

    inner class DescriptionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        private val tvDescription: TextView = itemView!!.findViewById(R.id.tvDescription)
        fun setValue(wordBean: WordBean?) {
            var word = wordBean?.word
            var descritionStr = word + ": " + wordBean?.description
            if ((word!!.length - searchStr.length) <= 1){
                var s = SpannableString(descritionStr)
                s.setSpan(ForegroundColorSpan(Color.parseColor("#ff2222")),0,word.length+1,0)
                tvDescription.text = s
            }else
                tvDescription.text = descritionStr
        }
    }

    private var list: MutableList<WordBean>? = null

    private lateinit var searchStr: String

    fun setData(matchedWords: MutableList<WordBean>, wordStr: String) {
        list = matchedWords
        searchStr = wordStr
        notifyDataSetChanged()
    }

}










