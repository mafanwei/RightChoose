package com.mafanwei.catelcall.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mafanwei.catelcall.R;
import com.mafanwei.catelcall.Setting.SettingThree;
import com.mafanwei.catelcall.Tools;

/**
 * Created by Mafanwei on 2018/7/29.
 */

public class NumberView extends View implements View.OnTouchListener{
    private int hight,width,left,right,top,bottom;
    private float span;
    private Paint paint;
    private static OnClickListener clickListener;
    private Context context;
    private Toast mToast;
    private String number[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};
    public NumberView(Context context) {
        super(context);
        init(context);
    }
    public NumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public interface OnClickListener{
        void onchoose(String number);
    }
    public static void setOnClickListener(OnClickListener whichchoose){
        clickListener=whichchoose;
    }
    private void init(Context context) {
        this.context=context;
        paint=new Paint();
        paint.setTextSize(24);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        setOnTouchListener(this);
    }

    public void setContext(Context context)
    {
        this.context=context;
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        hight=getHeight();
        width=getWidth();
        this.left=getLeft();
        this.right=getRight();
        this.top=getTop();
        this.bottom=getBottom();
        span= (float) (getHeight()/27.0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<number.length;i++)
        {
            float y=top+span*i;
            canvas.drawText(number[i],0,y,paint);
        }
    }

    public void setTextSize(float size)
    {
        paint.setTextSize(size);
        invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE)
        {
            float needy=event.getY();
            for(int i=0;i<number.length;i++)
            {
                float y=top+span*i;
                if(needy<=y+span/2&&needy>=y-span/2)
                {
                    toastSome(number[i]);
                    if(clickListener!=null)
                    clickListener.onchoose(number[i]);
                    break;
                }
            }
        }
        return true;
    }

    private void toastSome(String s) {
        if(context==null)
            return;
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = new Toast(context);
        View toastView = View.inflate(context, R.layout.toast_view_layout, null);
        mToast.setGravity(Gravity.CENTER, 0, Tools.dp2px(context,-80));
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(toastView);
        TextView tv_toast = (TextView) mToast.getView().findViewById(R.id.txt1);
        tv_toast.setText(s);
        mToast.show();
    }

    public void setNumber(String[] strings)
    {
        number=strings;
        invalidate();
    }
}
