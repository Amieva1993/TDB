package com.example.tdb

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import java.io.Serializable

class DropAdapter(val context: Context, val dropList:List<Drop>): BaseAdapter(), Filterable,
    Serializable {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var searchView: SearchView
    var dropFiltredList:List<Drop>

    init{
        dropFiltredList=dropList
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(position: CharSequence?): FilterResults {
                var charSearh= position.toString()
                if (charSearh.isEmpty()){
                    dropFiltredList=dropList
                }
                else{
                    var resultList = ArrayList<Drop>()

                    for (drop in dropList){
                        if (drop.name!!.lowercase().contains(charSearh.lowercase())){
                            resultList.add(drop)
                        }
                    }
                    dropFiltredList=resultList
                }
                val filterResults= FilterResults()
                filterResults.values=dropFiltredList
                return filterResults
            }

            override fun publishResults(position: CharSequence?, position1: FilterResults?) {
                dropFiltredList = position1?.values as ArrayList<Drop>
                notifyDataSetChanged()
            }

        }

    }

    override fun getCount(): Int {
        return dropFiltredList.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.row_item_drop, null)
        }
        imageView = convertView!!.findViewById(R.id.imageView)
        textView = convertView.findViewById(R.id.textView)
        Glide.with(context).load(dropFiltredList[position].imgUrl).into(imageView)
        textView.text = dropFiltredList[position].name
        return convertView
    }



}