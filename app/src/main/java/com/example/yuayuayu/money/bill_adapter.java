package com.example.yuayuayu.money;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.yuayuayu.money.R.id.info;

/**
 * Created by yuayuayu on 2017/12/28.
 */

public class bill_adapter extends BaseAdapter implements View.OnClickListener {
    TranslateAnimation animation;
    private Context contx;
    private int id;
    private List<Map<String,Object>> d;
    private LayoutInflater inflater;
    private boolean longclick=false;
    private boolean longclick2=false;
    private MoneySQLHelper MHelper;
    private Map<String,Object> item= new HashMap<>();
    private String _name,type,remark,amount,yy,mm,dd;
    private SimpleDateFormat year=new SimpleDateFormat("yyyy");
    private SimpleDateFormat month=new SimpleDateFormat("MM");
    private SimpleDateFormat day=new SimpleDateFormat("dd");
    private InnerItemOnclickListener mListener;
    boolean panduan;
    public bill_adapter(Context context, int layoutid, List<Map<String,Object>> data){
        contx=context;
        id=layoutid;
        d=data;
        MHelper=new MoneySQLHelper(contx);
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return d.size();
    }

    @Override
    public Object getItem(int position) {
        return d.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void adddata(int position){
        item.put("click",true);
        item.put("name",d.get(position).get("name"));
        item.put("amount","");
        item.put("remark","");
        item.put("type","in");
//        item.put("ID","");
        d.add(item);

    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
         final ViewHolder vh;
        if(convertView == null) {
            convertView = inflater.inflate(id,null);
            vh  = new ViewHolder();
            vh.stereoView=(StereoView) convertView.findViewById(R.id.stereoView);
            vh.ib = (ImageButton) convertView.findViewById(R.id.button);
            vh.et = (EditText) convertView.findViewById(R.id.money);
            vh.beizhu1=(EditText) convertView.findViewById(R.id.beizhu1);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if((boolean)d.get(position).get("click")==false){
            if(d.get(position).get("type").toString().equals("in"))
            {
                vh.ib.setImageResource(R.drawable.add);
            }else{
                vh.ib.setImageResource(R.drawable.min);
            }
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(vh.ib, "translationX", -280);
            objectAnimator1.setInterpolator(new AccelerateInterpolator());
            objectAnimator1.setDuration(250);
            objectAnimator1.start();
            vh.et.postDelayed(new Runnable() {
                public void run() {
                    vh.et.setAlpha(1);
                }
            }, 400);
            AlphaAnimation a = (AlphaAnimation) AnimationUtils.loadAnimation(contx, R.anim.alpha);
            vh.et.startAnimation(a);
            vh.et.setText(d.get(position).get("amount").toString());
            vh.beizhu1.setText(d.get(position).get("remark").toString());
        }else{
            if(d.get(position).get("type").toString().equals("in"))
            {
                vh.ib.setImageResource(R.drawable.add);
            }else{
                vh.ib.setImageResource(R.drawable.min);
            }
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(vh.ib, "translationX", -280);
            objectAnimator1.setInterpolator(new AccelerateInterpolator());
            objectAnimator1.setDuration(250);
            objectAnimator1.start();
            vh.et.postDelayed(new Runnable() {
                public void run() {
                    vh.et.setAlpha(1);
                }
            }, 400);
            AlphaAnimation a = (AlphaAnimation) AnimationUtils.loadAnimation(contx, R.anim.alpha);
            vh.et.startAnimation(a);
            vh.et.setText("");
            vh.beizhu1.setText("");

        }
//        vh.ib.setOnClickListener(this);
        vh.ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((boolean)d.get(position).get("click")) {
                    ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(vh.ib, "translationX", -280);
                    objectAnimator1.setInterpolator(new AccelerateInterpolator());
                    objectAnimator1.setDuration(500);
                    objectAnimator1.start();
                    vh.et.postDelayed(new Runnable() {
                        public void run() {
                            vh.et.setAlpha(1);
                        }
                    }, 400);
                    AlphaAnimation a = (AlphaAnimation) AnimationUtils.loadAnimation(contx, R.anim.alpha);
                    vh.et.startAnimation(a);
                    vh.et.requestFocus();
                    InputMethodManager imm = (InputMethodManager) vh.et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
//
                    adddata(position);
                    Log.e("abc",String.valueOf(d.size()));
                    for(int i=0;i<d.size();i++)
                    {  Log.e("abc",d.get(i).get("click").toString());
                        Log.e("abc",d.get(i).get("name").toString());
                        Log.e("abc",d.get(i).get("amount").toString());}
//                    Log.e("abc",d.get(position).get("click").toString()+position);
//                    Log.e("abc",d.get(position-1).get("click").toString()+(position-1));
//                    Log.e("abc",d.get(position-2).get("click").toString()+(position-2));
                                 }
                if(longclick){
                    Toast.makeText(contx,"支出",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(contx,"收入",Toast.LENGTH_SHORT).show();
                }
            }
    });

        vh.ib.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(longclick2==false){
                vh.ib.setImageResource(R.drawable.min);
                longclick=true;
                longclick2=true;}
                else{
                    vh.ib.setImageResource(R.drawable.add);
                    longclick=false;
                    longclick2=false;
                }
                return true;
            }
        });
        vh.et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                vh.stereoView.toNext();
                return false;
            }
        });
//        vh.et.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                deleteData(position);
//                notifyDataSetChanged();
//                Log.e("abc","delete");
//                return true;
//            }
//        });
        vh.beizhu1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                vh.stereoView.toPre();
                _name=d.get(position).get("name").toString();
                if(longclick) {
                    type="out";
                }else {type="in";}
                amount=vh.et.getText().toString();
                remark=vh.beizhu1.getText().toString();
                yy=year.format(new Date());
                mm=month.format(new Date());
                dd=day.format(new Date());
                MHelper.insertDatabase(_name,type,amount,remark,yy,mm,dd);
                return false;
            }
        });

        return convertView;
    }

    class  ViewHolder{
       private EditText et;
        private  EditText beizhu1;
       private ImageButton ib;
        private StereoView stereoView;
   }

    interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
    public void deleteData(int position) {
        // get the id of data first
        int id = (int)d.get(position).get("amount");
        SQLiteDatabase db = MHelper.getWritableDatabase();
        // use id to find that row
        String selections = MoneyContract.MoneyTable.COLUMN_NAME_Amount + " = ?";
        // NOTE: convert id from int to string
        String[] selectionArgs = {id+""};
        // DELETE FROM MemoContract.MemoTable.TABLE_NAME WHERE selections=selectionArgs
        db.delete(MoneyContract.MoneyTable.TABLE_NAME, selections, selectionArgs);
        // remove the data in the list
        d.remove(position);
        db.close();
    }
}

