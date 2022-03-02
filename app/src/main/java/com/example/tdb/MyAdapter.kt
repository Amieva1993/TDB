package com.example.tdb

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.Serializable

class MyAdapter(val context: Context, val beastList:List<BeastItem>):BaseAdapter(),Filterable,
    Serializable {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var typeViewCategory: TextView
    private lateinit var searchView: SearchView
    var beastFiltredList:List<BeastItem>

            init{
                beastFiltredList=beastList
            }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(position: CharSequence?): FilterResults {
                var charSearh= position.toString()
                if (charSearh.isEmpty()){
                    beastFiltredList=beastList
                }
                else{
                    var resultList = ArrayList<BeastItem>()

                    for (beast in beastList){
                        if (beast.name.lowercase().contains(charSearh.lowercase())){
                            resultList.add(beast)
                        }
                    }
                    beastFiltredList=resultList
                }
                val filterResults= FilterResults()
                filterResults.values=beastFiltredList
                return filterResults
            }

            override fun publishResults(position: CharSequence?, position1: FilterResults?) {
                beastFiltredList = position1?.values as ArrayList<BeastItem>
                notifyDataSetChanged()
            }


        }

    }

    override fun getFilter2(): Filter {
        return object :Filter(){
            override fun performFiltering(position: CharSequence?): FilterResults {
                var charSearh= position.toString()
                if (charSearh.isEmpty()){
                    beastFiltredList=beastList
                }
                else{
                    var resultList = ArrayList<BeastItem>()

                    for (beast in beastList){
                        if (beast.name.lowercase().contains(charSearh.lowercase())){
                            resultList.add(beast)
                        }
                    }
                    beastFiltredList=resultList
                }
                val filterResults= FilterResults()
                filterResults.values=beastFiltredList
                return filterResults
            }

            override fun publishResults(position: CharSequence?, position1: FilterResults?) {
                beastFiltredList = position1?.values as ArrayList<BeastItem>
                notifyDataSetChanged()
            }


        }

    }

    override fun getCount(): Int {
        return beastFiltredList.size
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
            convertView = layoutInflater!!.inflate(R.layout.row_item, null)
        }
        imageView = convertView!!.findViewById(R.id.imageView)
        textView = convertView.findViewById(R.id.textView)
        typeViewCategory = convertView.findViewById(R.id.typeViewCategory)
        Glide.with(context).load(beastFiltredList[position].imgUrl).into(imageView)
        textView.text = beastFiltredList[position].name
        typeViewCategory.text = beastFiltredList[position].type
        convertView.setOnClickListener {
            context.startActivity(Intent(context, BeastPage::class.java).putExtra("beast",beastFiltredList[position]).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
        return convertView
    }



}