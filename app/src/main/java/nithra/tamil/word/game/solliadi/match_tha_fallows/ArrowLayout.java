package nithra.tamil.word.game.solliadi.match_tha_fallows;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

public class ArrowLayout extends RelativeLayout {

    public static final String PROPERTY_X = "PROPERTY_X";
    public static final String PROPERTY_Y = "PROPERTY_Y";

    private final static double ARROW_ANGLE = Math.PI / 6;
    private final static double ARROW_SIZE = 30;

    private Paint mPaint;
    int duration=0;

    private boolean mDrawArrow = false, ans_check;
    private Point mPointFrom = new Point();   // current (during animation) arrow start point
    private Point mPointTo = new Point();

    // current (during animation)  arrow end point

    public ArrowLayout(Context context) {
        super(context);
        init();
    }

    public ArrowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArrowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public ArrowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        if (duration==0)
        {
            mPaint.setColor(Color.parseColor("#BD3939"));
            if (ans_check) {
                mPaint.setColor(Color.parseColor("#3DAD0A"));
            }
        }else {
            mPaint.setColor(Color.parseColor("#FF9800"));
        }

        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        if (mDrawArrow) {
            drawArrowLines(mPointFrom, mPointTo, canvas);
        } else {
            canvas.drawLine(0, 0, 0, 0, mPaint);
        }
        canvas.restore();
    }

/*    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        if (mDrawArrow) {
            drawArrowLines(mPointFrom, mPointTo, canvas);
        }
        canvas.restore();
    }*/

    private Point calcPointFromleft(Rect fromViewBounds, Rect toViewBounds) {
        Point pointFrom = new Point();

        pointFrom.x = fromViewBounds.right;
        pointFrom.y = fromViewBounds.top + (fromViewBounds.bottom - fromViewBounds.top) / 2;

        return pointFrom;
    }


    private Point calcPointToleft(Rect fromViewBounds, Rect toViewBounds) {
        Point pointTo = new Point();

        pointTo.x = toViewBounds.left;
        pointTo.y = toViewBounds.top + (toViewBounds.bottom - toViewBounds.top) / 2;

        return pointTo;
    }

    private Point calcPointFromright(Rect fromViewBounds, Rect toViewBounds) {
        Point pointFrom = new Point();

        pointFrom.x = fromViewBounds.left;
        pointFrom.y = fromViewBounds.top + (fromViewBounds.bottom - fromViewBounds.top) / 2;

        return pointFrom;
    }

    private Point calcPointToright(Rect fromViewBounds, Rect toViewBounds) {
        Point pointTo = new Point();

        pointTo.x = toViewBounds.right;
        pointTo.y = toViewBounds.top + (toViewBounds.bottom - toViewBounds.top) / 2;

        return pointTo;
    }


    private void drawArrowLines(Point pointFrom, Point pointTo, Canvas canvas) {
        canvas.drawLine(pointFrom.x, pointFrom.y, pointTo.x, pointTo.y, mPaint);

        double angle = Math.atan2(pointTo.y - pointFrom.y, pointTo.x - pointFrom.x);

        int arrowX, arrowY;

        arrowX = (int) (pointTo.x - ARROW_SIZE * Math.cos(angle + ARROW_ANGLE));
        arrowY = (int) (pointTo.y - ARROW_SIZE * Math.sin(angle + ARROW_ANGLE));
        canvas.drawLine(pointTo.x, pointTo.y, arrowX, arrowY, mPaint);

        arrowX = (int) (pointTo.x - ARROW_SIZE * Math.cos(angle - ARROW_ANGLE));
        arrowY = (int) (pointTo.y - ARROW_SIZE * Math.sin(angle - ARROW_ANGLE));
        canvas.drawLine(pointTo.x, pointTo.y, arrowX, arrowY, mPaint);
    }

    public void animateArrows(int duration1, View viewA, View viewB, boolean mDrawArrow1, boolean ans_check1, boolean arrow_move) {

        mDrawArrow = mDrawArrow1;
        ans_check = ans_check1;

        duration=duration1;

        View from_view = null, to_view = null;
/*
        if (arrow_move)
        {
            from_view = viewB;
            to_view = viewA;
        }else {*/
        from_view = viewA;
        to_view = viewB;
        // }

        init();

        // find from and to views bounds
        Rect fromViewBounds = new Rect();
        from_view.getDrawingRect(fromViewBounds);
        offsetDescendantRectToMyCoords(from_view, fromViewBounds);

        Rect toViewBounds = new Rect();
        to_view.getDrawingRect(toViewBounds);
        offsetDescendantRectToMyCoords(to_view, toViewBounds);

        // calculate arrow sbegin and end points

        Point pointFrom = null;
        Point pointTo = null;

        if (arrow_move) {
            pointFrom = calcPointFromright(toViewBounds, fromViewBounds);
            pointTo = calcPointToright(toViewBounds, fromViewBounds);
        } else {
            pointFrom = calcPointFromleft(fromViewBounds, toViewBounds);
            pointTo = calcPointToleft(fromViewBounds, toViewBounds);
        }


        ValueAnimator arrowAnimator = createArrowAnimator(pointFrom, pointTo, duration);
        arrowAnimator.start();
    }

    private ValueAnimator createArrowAnimator(Point pointFrom, Point pointTo, int duration) {

        final double angle = Math.atan2(pointTo.y - pointFrom.y, pointTo.x - pointFrom.x);

        mPointFrom.x = pointFrom.x;
        mPointFrom.y = pointFrom.y;

        int firstX = (int) (pointFrom.x + ARROW_SIZE * Math.cos(angle));
        int firstY = (int) (pointFrom.y + ARROW_SIZE * Math.sin(angle));

        PropertyValuesHolder propertyX = PropertyValuesHolder.ofInt(PROPERTY_X, firstX, pointTo.x);
        PropertyValuesHolder propertyY = PropertyValuesHolder.ofInt(PROPERTY_Y, firstY, pointTo.y);

        ValueAnimator animator = new ValueAnimator();
        animator.setValues(propertyX, propertyY);
        animator.setDuration(duration);
        // set other interpolator (if needed) here:
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mPointTo.x = (int) valueAnimator.getAnimatedValue(PROPERTY_X);
                mPointTo.y = (int) valueAnimator.getAnimatedValue(PROPERTY_Y);

                invalidate();
            }
        });

        return animator;
    }


}