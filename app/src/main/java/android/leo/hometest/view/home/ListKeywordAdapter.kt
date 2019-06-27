package android.leo.hometest.view.home

import android.content.Context
import android.leo.hometest.R
import android.leo.hometest.model.KeywordModel
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListKeywordAdapter(private val context: Context, private val listKeyWord : MutableList<KeywordModel>) : RecyclerView.Adapter<ListKeywordViewHolder>() {

    private val keywordClickListener : OnHotKeywordClickListener = context as OnHotKeywordClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKeywordViewHolder {
        val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.keyword_item, parent, false)
        return ListKeywordViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ListKeywordViewHolder, position: Int) {
        val keyword : KeywordModel = listKeyWord[position]
        viewHolder.bind(keyword.keyword, keywordClickListener)
    }

    override fun getItemCount(): Int  = listKeyWord.size
}