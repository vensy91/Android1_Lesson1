package vensy.android1_lesson1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vensy on 26.07.2016.
 */
public class SecondActivity extends AppCompatActivity {

    TextView text_task;
    TextView text_delete;
    RelativeLayout rel_lay;
    Button btn_clear;
    Button btn_color;
    TextSwitcher txt_switcher;
    Animation fade_out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        text_task = (TextView) findViewById(R.id.text_task);
        text_task.setMovementMethod(new ScrollingMovementMethod());

        fade_out = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        fade_out.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                text_task.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        btn_clear = (Button) findViewById(R.id.button2);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text_task.getVisibility() == View.VISIBLE) {
                    text_task.startAnimation(fade_out);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Текст удален", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        btn_color = (Button) findViewById(R.id.button3);
        btn_color.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        rel_lay = (RelativeLayout) findViewById(R.id.relativeLayout);
                        rel_lay.setBackgroundColor(getResources().getColor(R.color.color_red));
                        break;
                    case MotionEvent.ACTION_UP:
                        rel_lay = (RelativeLayout) findViewById(R.id.relativeLayout);
                        rel_lay.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        break;
                }
                return true;
            }
        });
    }

    public void onBtnClickText(View view) {
        String text = getResources().getText(R.string.text_task) +"\n"+ new SimpleDateFormat("dd.MM.yyyy hh:mm").format(new Date().getTime())+"\n";
        text_task.setText(text);
        text_task.setVisibility(View.VISIBLE);
    }

}
