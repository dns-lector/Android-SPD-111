package step.learning.android_spd_111;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // додаємо аналізатор (слухач) свайпів на всю активність (R.id.main)
        findViewById(R.id.main).setOnTouchListener(new OnSwipeListener(this) {
            @Override
            public void onSwipeBottom() {
                Toast.makeText(GameActivity.this, "Bottom", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(GameActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeRight() {
                Toast.makeText(GameActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeTop() {
                Toast.makeText(GameActivity.this, "Top", Toast.LENGTH_SHORT).show();
            }
        });
    }
}