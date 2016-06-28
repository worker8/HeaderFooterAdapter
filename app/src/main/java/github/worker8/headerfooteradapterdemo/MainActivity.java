package github.worker8.headerfooteradapterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    FrameLayout mainContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mainContainer = (FrameLayout) findViewById(R.id.main_container);

        mainAdapter = new MainAdapter(true, true);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void toggleHeader(View view) {
        if (mainAdapter.hasHeader()) {
            mainAdapter.hideHeader();
        } else {
            mainAdapter.showHeader();
            recyclerView.getLayoutManager().scrollToPosition(0);
        }
    }

    public void toggleFooter(View view) {
        if (mainAdapter.hasFooter()) {
            mainAdapter.hideFooter();
        } else {
            mainAdapter.showFooter();
            recyclerView.getLayoutManager().scrollToPosition(mainAdapter.getRealItemCount() + 1);
        }
    }
}
