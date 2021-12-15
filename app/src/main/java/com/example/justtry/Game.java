package com.example.justtry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

// Класс Game управляет всеми игровыми объектами, обновляет всевозможные значения
// и отрисовывает все игрове объекты на холст, заполняя пространство нашего окна

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private Context context;

    public Game(Context context) {
        super(context);

        // Эти модификации позволят нам отрисовывать события на экране и реагировать на нажатия
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        this.gameLoop = new GameLoop(this, surfaceHolder);

        setFocusable(true);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawFPS(canvas);
        drawUPS(canvas);
    }

    public void update() {
        // Обновление значений игры
    }

    // UPS - кол-во обновлений в секунду
    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.favColor);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS:" + averageUPS, 20, 40, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.favColor);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS:" + averageFPS, 20, 100, paint);
    }
}
