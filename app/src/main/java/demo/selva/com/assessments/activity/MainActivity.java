package demo.selva.com.assessments.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import demo.selva.com.assessments.R;
import demo.selva.com.assessments.fragment.Task1Fragment;
import demo.selva.com.assessments.fragment.Task2Fragment;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/13/2018
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button task1 = findViewById(R.id.task1);
        Button task2 = findViewById(R.id.task2);

        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(Task1Fragment.getInstance());
            }
        });

        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(Task2Fragment.getInstance());
            }
        });
    }

    /**
     * This method is used to attach the fragment to the activity
     *
     * @param fragment the Fragment
     */
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }
}
