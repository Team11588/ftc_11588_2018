package org.firstinspires.ftc.teamcode.Autonomous;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Color;

/**
 * Created by piscullin18641 on 10/28/2017.
 */

public class JewelView extends View {
    public JewelView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        double xPercent = (1280)/100.0;
        double yPercent = (960)/100.0;

        Paint p = new Paint();
        p.setColor(Color.BLACK);
        canvas.drawRect((int) (20*xPercent),(int) (60*yPercent), (int) (30*xPercent),(int) (80*yPercent), p);



    }
}
