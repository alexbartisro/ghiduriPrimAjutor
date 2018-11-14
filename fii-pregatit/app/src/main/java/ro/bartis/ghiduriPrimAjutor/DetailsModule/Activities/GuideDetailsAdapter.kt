package ro.bartis.ghiduriPrimAjutor.DetailsModule.Activities

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import ro.bartis.ghiduriPrimAjutor.DetailsModule.Entity.Guide
import ro.bartis.ghiduriPrimAjutor.DetailsModule.Entity.Step
import ro.bartis.ghiduriPrimAjutor.R
import ro.bartis.ghiduriPrimAjutor.toColor

interface GuideDetailsAdapterDelegate {
    fun playVideo(urlString: String)
}

class GuideDetailsAdapter(private val guide: Guide, private val delegate: GuideDetailsAdapterDelegate) : RecyclerView.Adapter<GuideDetailsAdapter.MyViewHolder>() {
    private val grayColor = "#808080".toColor()

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
        textView.setTextColor(grayColor)

        if (index < guide.texts.size) {
            textView.text = guide.texts[index]
            textView.setTypeface(null, Typeface.ITALIC)

            view.guideLinearLayout.addView(textView)
        } else if (index >= guide.texts.size && index < guide.sections.size + guide.texts.size) {
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
                stepTextView.setTextColor(grayColor)

                view.guideLinearLayout.addView(stepTextView)
            }
        } else {
            val button = Button(view?.guideLinearLayout.context)
            button.run {
                button.setText(R.string.action_play)
                button.setTextColor(ContextCompat.getColor(view?.guideLinearLayout.context,R.color.white))
                button.layoutParams = LinearLayout.LayoutParams(
                        500,
                        130
                )
                setBackgroundResource(R.drawable.rouded_button)
                setOnClickListener {
                    delegate.playVideo(guide.video)
                }
            }

            view?.guideLinearLayout.gravity = Gravity.CENTER
            view?.guideLinearLayout.addView(button)
        }
    }

    override fun getItemCount() = guide.texts.size + guide.sections.size + 1

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}