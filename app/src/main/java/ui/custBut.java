package ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import ru.udevs.success.R;


/**
 * Created by Saper on 17.05.2016.
 */
public class custBut extends Button {

    public custBut(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/gothic.ttf"));
       // this.setBackgroundResource(R.drawable.knopka);
        this.setBackgroundResource(R.drawable.btnpress);
    }
}
