package github.worker8.headerfooteradapterdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val mainAdapter = MainAdapter(true, true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = mainAdapter
        toggleHeaderButton.setOnClickListener {
            toggleHeader(it)
        }
        toggleFooterButton.setOnClickListener {
            toggleFooter(it)
        }
    }

    fun toggleHeader(view: View) {
        if (mainAdapter.hasHeader()) {
            mainAdapter.hideHeader()
        } else {
            mainAdapter.showHeader()
            recyclerView.layoutManager.scrollToPosition(0)
        }
    }

    fun toggleFooter(view: View) {
        if (mainAdapter.hasFooter()) {
            mainAdapter.hideFooter()
        } else {
            mainAdapter.showFooter()
            recyclerView.layoutManager.scrollToPosition(mainAdapter.itemCount - 1)
        }
    }
}
