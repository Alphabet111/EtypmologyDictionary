package dictionary.etymology.alphabet.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dictionary.etymology.alphabet.database.databean.WordBean
import dictionary.etymology.alphabet.etymologydictionary.R

/**
 * Created by alphabet on 2018/1/31.
 */

class SearchAdapter( val context: Context) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
        private var list: MutableList<WordBean>? = null
    override fun onBindViewHolder(holder: SearchViewHolder?, position: Int) {
        holder?.setValue(list?.get(position)?.word)
        holder?.itemView?.setOnClickListener{
            listener?.onItemClick(list!![position].word)
        }
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search,parent,false))
    }


    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvContent: TextView = view.findViewById(R.id.tvContent)

        fun setValue(word: String?) {
            tvContent.text = word
        }


    }

    fun setData(matchedWords: MutableList<WordBean>?) {
        list = matchedWords
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener{
        fun onItemClick(position: String)
    }

}
