package ro.bartis.ghiduriPrimAjutor.DetailsModule.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ro.bartis.ghiduriPrimAjutor.DetailsModule.Entity.Guide
import ro.bartis.ghiduriPrimAjutor.INTENT_GUIDE_TITLE
import ro.bartis.ghiduriPrimAjutor.INTENT_GUIDE_URL
import ro.bartis.ghiduriPrimAjutor.MainModule.Activities.MainActivity
import ro.bartis.ghiduriPrimAjutor.R
import ro.bartis.ghiduriPrimAjutor.loadJsonFromAsset

class DetailActivity : AppCompatActivity() {
    private lateinit var recylerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter <*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)

        setupUI()
        MainActivity.hideProgressBar()
    }

    private fun setupUI() {
        val intent = getIntent()
        val guideURL = intent.getStringExtra(INTENT_GUIDE_URL)
        val guideTitle = intent.getStringExtra(INTENT_GUIDE_TITLE)
        val jsonString = loadJsonFromAsset(guideURL, this).toString()

        val guide = Guide.fromJson(jsonString)
        setTitle(guideTitle)

        viewManager = LinearLayoutManager(this)
        viewAdapter = GuideDetailsAdapter(guide!!)

        recylerView = findViewById(R.id.guide_details_recycler_view)
        recylerView.adapter = viewAdapter
        recylerView.layoutManager = viewManager
    }
}