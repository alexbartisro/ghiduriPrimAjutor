/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package ro.bartis.ghiduriPrimAjutor.MainModule.Activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import ro.bartis.ghiduriPrimAjutor.DetailsModule.Activities.DetailActivity
import ro.bartis.ghiduriPrimAjutor.INTENT_GUIDE_TITLE
import ro.bartis.ghiduriPrimAjutor.INTENT_GUIDE_URL
import ro.bartis.ghiduriPrimAjutor.MainModule.Entities.GuideListItem
import ro.bartis.ghiduriPrimAjutor.R

class MainActivity : AppCompatActivity() {

    private lateinit var recylerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter <*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = this.window.decorView
        view.setBackgroundColor(Color.BLACK)

        progressBar = findViewById(R.id.guide_list_progressBar)
        hideProgressBar()

        val guides = GuideListItem.getGuidesFromFile(this)

        viewManager = LinearLayoutManager(this)
        viewAdapter = GuideListAdapter(guides)

        recylerView = findViewById(R.id.guide_list_recycler_view)
        recylerView.adapter = viewAdapter
        recylerView.layoutManager = viewManager
    }

    companion object {
        private lateinit var progressBar: ProgressBar

        fun intentToDetail(context: Context, url: String, title: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(INTENT_GUIDE_URL, url)
            intent.putExtra(INTENT_GUIDE_TITLE, title)

            context.startActivity(intent)
        }

        fun hideProgressBar() {
            progressBar.visibility = View.GONE
        }

        fun showProgressBar() {
            progressBar.visibility = View.VISIBLE
        }
    }
}
