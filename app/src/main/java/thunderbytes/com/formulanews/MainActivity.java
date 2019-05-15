package thunderbytes.com.formulanews;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import thunderbytes.com.formulanews.Fragments.ListFragment;


public class MainActivity extends AppCompatActivity {
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();

                switch (item.getItemId()) {
                    case R.id.action_recents:
                        bundle.putString("title", "Calendario");
                        fragment = new ListFragment();
                        fragment.setArguments(bundle);
                        break;

                    case R.id.action_favorites:
                        bundle.putString("title", "Classifica piloti");
                        fragment = new ListFragment();
                        fragment.setArguments(bundle);
                        break;

                    case R.id.action_nearby:
                        bundle.putString("title", "Classifica costruttori");
                        fragment = new ListFragment();
                        fragment.setArguments(bundle);
                        break;
                }
                fragmentTransaction.replace(R.id.dynamicFragmentFrameLayout, fragment).commit();
                return true;
            }

        });

        //HttpGetTask httpTask = (HttpGetTask) new HttpGetTask().execute();
        //String response = httpTask.getResponse();
    }
}
