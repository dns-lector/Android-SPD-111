package step.learning.android_spd_111;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalcActivity extends AppCompatActivity {

    private TextView tvHistory;
    private TextView tvResult;

    @SuppressLint("DiscouragedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvHistory = findViewById( R.id.calc_tv_history );
        tvResult  = findViewById( R.id.calc_tv_result  );
        if( savedInstanceState == null ) {  // немає збереженого стану -- перший запуск
            tvResult.setText("0");
        }
        /* Задача: циклом перебрати ресурсні кнопки calc_btn_{i} і для
           кожної з них поставити один обробник onDigitButtonClick */
        for (int i = 0; i < 10; i++) {
            findViewById(   // На заміну R.id.calc_btn_0 приходить наступний вираз
                    getResources().getIdentifier(        // R
                                    "calc_btn_" + i,     //     .calc_btn_0
                                    "id",                //  .id
                                    getPackageName()
                            )
            ).setOnClickListener( this::onDigitButtonClick );
        }

    }

    /*
    При зміні конфігурації пристрою (поворотах, змінах налаштувань, тощо) відбувається
    перезапуск активності. При цьому подаються події життєвого циклу
    onSaveInstanceState - при виході з активності перед перезапуском
    onRestoreInstanceState - при відновленні активності після перезапуску
    До обробників передається Bundle, що є сховищем, яке дозволяє зберегти та відновити дані
    Також збережений Bundle передається до onCreate, що дозволяє визначити
     чи це перший запуск, чи перезапуск через зміну конфігурації
     */

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);  // потрібно
        outState.putCharSequence( "tvResult", tvResult.getText() );
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvResult.setText( savedInstanceState.getCharSequence( "tvResult" ) );
    }

    private void onDigitButtonClick(View view) {
        String result = tvResult.getText().toString();
        if(result.length() >= 10) {
            Toast.makeText(this, R.string.calc_limit_exceeded, Toast.LENGTH_SHORT).show();
            return;
        }
        if( result.equals("0") ) {
            result = "";
        }
        result += ((Button) view).getText();
        tvResult.setText( result );
    }
}
/*
Д.З. Завершити розмітку активності для калькулятора
Підібрати необхідні Юнікод-символи
Забезпечити вирівнювання та відступи, підібрати заокруглення
 */