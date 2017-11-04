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

        double xPercent = (this.getWidth())/100.0;
        double yPercent = (this.getHeight())/100.0;

        Paint p = new Paint();
        //scan box
        p.setColor(Color.argb(100,0,0,0));
        canvas.drawRect((int) (30*xPercent),(int) (30*yPercent), (int) (52.5*xPercent),(int) (45*yPercent), p);

        //top
        p.setColor(Color.argb(200,255,0,0));
        canvas.drawRect((int) (1*xPercent),(int) (1*yPercent), (int) (99*xPercent),(int) (2*yPercent), p);

        //left
        p.setColor(Color.argb(200,0,0,255));
        canvas.drawRect((int) (1*xPercent),(int) (1*yPercent), (int) (2*xPercent),(int) (99*yPercent), p);

    }
}
