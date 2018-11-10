package ro.bartis.ghiduriPrimAjutor.MainModule.Activities

import android.os.Handler
import android.os.Looper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ro.bartis.ghiduriPrimAjutor.MainModule.Entities.GuideListItem
import ro.bartis.ghiduriPrimAjutor.R

class GuideListAdapter(private val guideListItems: ArrayList<GuideListItem>) : RecyclerView.Adapter<GuideListAdapter.MyViewHolder>() {
    class MyViewHolder(textView: View) : RecyclerView.ViewHolder(textView) {
        val guideName = itemView.findViewById(R.id.guide_title_text_view) as TextView
    }

    override fun onCreateViewHolder(view: ViewGroup, index: Int): MyViewHolder {
        val view = LayoutInflater.from(view?.context).inflate(R.layout.list_layout, view, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(view: MyViewHolder, index: Int) {
        view?.guideName.text = guideListItems[index].title
        view?.itemView.setOnClickListener {

            Handler(Looper.getMainLooper()).post {
                MainActivity.showProgressBar()
            }

            MainActivity.intentToDetail(view?.itemView.context, guideListItems[index].url, guideListItems[index].title)
        }
    }

    override fun getItemCount() = guideListItems.size
}