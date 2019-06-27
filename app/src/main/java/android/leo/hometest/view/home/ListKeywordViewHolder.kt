package android.leo.hometest.view.home

import android.leo.hometest.extensions.splitKeyword
import android.leo.hometest.util.ResourceUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.keyword_item.view.*

class ListKeywordViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    fun bind(keyword : String, keywordClickListener: OnHotKeywordClickListener) {
        itemView.itemCard.setCardBackgroundColor(ResourceUtil.getRandomMaterialColor(itemView.context))
        itemView.textKeyword.text = keyword.splitKeyword()
        itemView.setOnClickListener {
            keywordClickListener.onHotKeywordClick(keyword)
        }
    }
}