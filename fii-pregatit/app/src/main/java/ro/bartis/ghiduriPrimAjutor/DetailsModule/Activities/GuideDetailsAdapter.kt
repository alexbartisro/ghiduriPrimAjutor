package ro.bartis.ghiduriPrimAjutor.DetailsModule.Activities

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import ro.bartis.ghiduriPrimAjutor.DetailsModule.Entity.Guide
import ro.bartis.ghiduriPrimAjutor.DetailsModule.Entity.Step
import ro.bartis.ghiduriPrimAjutor.R
import ro.bartis.ghiduriPrimAjutor.toColor

class GuideDetailsAdapter(private val guide: Guide) : RecyclerView.Adapter<GuideDetailsAdapter.MyViewHolder>() {
    val COLOR_GRAY = "#808080".toColor()

    override fun setHasStableIds(hasStableIds: Boolean) {
        setHasStableIds(true)
    }

    class MyViewHolder(linearLayout: LinearLayout) : RecyclerView.ViewHolder(linearLayout) {
        val guideLinearLayout = linearLayout
    }

    override fun onCreateViewHolder(view: ViewGroup, index: Int): MyViewHolder {
        val linearLayout = LayoutInflater.from(view?.context).inflate(R.layout.activity_details_section_list_layout, view, false) as LinearLayout
        return MyViewHolder(linearLayout)
    }

    override fun onBindViewHolder(view: MyViewHolder, index: Int) {
        view.guideLinearLayout.removeAllViews()

        val textView = TextView(view?.guideLinearLayout.context)
        textView.setTextColor(COLOR_GRAY)

        if (index < guide.texts.size) {
            textView.text = guide.texts[index]
            textView.setTypeface(null, Typeface.ITALIC)

            view.guideLinearLayout.addView(textView)
        } else {
            val section = guide.sections[index - guide.texts.size]
            textView.text = section.title
            textView.setTypeface(null, Typeface.BOLD)
            textView.textSize = 18F

            view.guideLinearLayout.addView(textView)

            for (step: Step in section.steps) {
                val stepTextView = TextView(view?.guideLinearLayout.context)
                val stepNumber = (section.steps.indexOf(step) + 1).toString()

                stepTextView.text = stepNumber + ". " + step.title
                stepTextView.gravity = Gravity.FILL_HORIZONTAL
                stepTextView.setTextColor(COLOR_GRAY)

                view.guideLinearLayout.addView(stepTextView)
            }
        }
    }

    override fun getItemCount() = guide.texts.size + guide.sections.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}