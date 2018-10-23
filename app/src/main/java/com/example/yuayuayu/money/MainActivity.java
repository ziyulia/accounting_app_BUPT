package com.example.yuayuayu.money;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements bill_adapter.InnerItemOnclickListener,
        AdapterView.OnItemClickListener {
    ListView lv;
    bill_adapter ba;
    String user;
    Button month;
    TextView Day_view;
    TextView Week_day;
    DatePicker datePicker;
    private int year;
    private int mth;
    private int day;

    private String year1;
    private String mmm;
    private String day1;
    boolean clickclander;
    private MoneySQLHelper sqlHelper;
    private SimpleDateFormat yy=new SimpleDateFormat("yyyy");
    private SimpleDateFormat mm=new SimpleDateFormat("MM");
    private SimpleDateFormat dd=new SimpleDateFormat("dd");
    private SimpleDateFormat weekday=new SimpleDateFormat("EEEE");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Day_view=(TextView)findViewById(R.id.day);
        Week_day=(TextView) findViewById(R.id.week);
//        lv=(ListView)findViewById(R.id.list) ;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user = bundle.getString("user");
        Log.d("abc",user);

        month=(Button) findViewById(R.id.month);
        year1=yy.format(new Date());
        mmm=mm.format(new Date());
        day1=dd.format(new Date());
        Day_view.setText(day1);
        String dateDay = String.format("%ta", new Date()); // 星期
        Week_day.setText(dateDay);
        String mmonth=String.format("%tb",new Date());
        month.setText(mmonth);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        mth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);



        sqlHelper = new MoneySQLHelper(this);
        ba=new bill_adapter(this,R.layout.listitem2,getlistmap());
        ba.setOnInnerItemOnClickListener(this);
        ba.panduan=true;
        lv.setAdapter(ba);

        lv.setOnItemClickListener(this);

        Log.d("abc",yy.format(new Date())+mm.format(new Date())+dd.format(new Date()));
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        clickclander=true;
        ViewGroup rootView = (ViewGroup) datePicker.getChildAt(0);
        if (rootView == null) {
            return;
        }
        View headerView = rootView.getChildAt(0);
        if (headerView == null) {
            return;
        }
        headerView.setVisibility(View.GONE);

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   datePicker.setVisibility(View.VISIBLE);
                   ObjectAnimator animator=ObjectAnimator.ofFloat(datePicker,"alpha",1);
                   animator.setDuration(800);
                   animator.start();
                   datePicker.init(year, mth, day, new DatePicker.OnDateChangedListener() {
                       @Override
                       public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                           int month2;
                           month2 = monthOfYear + 1;
                           Toast.makeText(MainActivity.this, year + "年" + month2 + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                           year1=String.valueOf(year);
                           if(month2<10){
                               mmm="0"+String.valueOf(month2);}else{
                               mmm=String.valueOf(month2);
                           }
                           if(dayOfMonth<10){
                               day1="0"+String.valueOf(dayOfMonth);
                           }else {
                               day1=String.valueOf(dayOfMonth);
                           }
//                           Toast.makeText(MainActivity.this, year1 + "年" + mmm + "月" + day1 + "日", Toast.LENGTH_SHORT).show();
                           ObjectAnimator animator=ObjectAnimator.ofFloat(datePicker,"alpha",0);
                           animator.setDuration(800);
                           animator.start();
                           datePicker.setVisibility(View.GONE);
                           Day_view.setText(day1);
                           Week_day.setText(week(year,monthOfYear,dayOfMonth));
                           month.setText(yuefen(month2));
                           ba=new bill_adapter(MainActivity.this,R.layout.listitem2,getlistmap());
                           lv.setAdapter(ba);
                       }
                   });

//
            }
        });

    }
    private void initView() {
        lv = (ListView) findViewById(R.id.list);
    }
    @Override
    public void itemClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Log.e("abc",  "dianle");
//                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(v, "translationX", -280);
//                    objectAnimator1.setInterpolator(new AccelerateInterpolator());
//                    objectAnimator1.setDuration(500);
//                    objectAnimator1.start();
                break;
            default:
                break;
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        ListView.AdapterContextMenuInfo info =
//                (ListView.AdapterContextMenuInfo) item.getMenuInfo();
//        switch (item.getItemId()) {
//            case R.id.listview_delete:
//                // delete data via API provided by Adapter
//                ba.deleteData(info.position);
//                ba.notifyDataSetChanged();
//                break;
//        }
//        return true;
//    }

    public String yuefen(int i){
        String yue=null;
        switch (i){
            case 1:
                yue = "Jan";
                break;
            case 2:
                yue = "Feb";
                break;
            case 3:
                yue = "Mar";
                break;
            case 4:
                yue = "Apr";
                break;
            case 5:
                yue = "May";
                break;
            case 6:
                yue = "Jun";
                break;
            case 7:
                yue = "Jul";
                break;
            case 8:
                yue = "Aug";
                break;
            case 9:
                yue = "Sept";
                break;
            case 10:
                yue = "Oct";
                break;
            case 11:
                yue = "Nov";
                break;
            case 12:
                yue = "Dec";
                break;
            default:
                break;

        }
        return yue;
    }
    public String week(int nian,int yue,int ri) {
        int y = nian - 1;
        int m = yue;
        int c = 20;
        int d = ri + 12;
        int w = (y + (y / 4) + (c / 4) - 2 * c + (26 * (m + 1) / 10) + d - 1) % 7;
        String myWeek = null;
        switch (w) {
            case 0:
                myWeek = "Sun";
                break;
            case 1:
                myWeek = "Mon";
                break;
            case 2:
                myWeek = "Tue";
                break;
            case 3:
                myWeek = "Wed";
                break;
            case 4:
                myWeek = "Thu";
                break;
            case 5:
                myWeek = "Fri";
                break;
            case 6:
                myWeek = "Sat";
                break;
            default:
                break;

        }
        return myWeek;
    }
    public List<Map<String,Object>> getlistmap(){
        List<Map<String, Object>> ret = new ArrayList<>();
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        // the column we need
        String[] projection = {
                MoneyContract.MoneyTable.COLUMN_NAME_User,
                MoneyContract.MoneyTable.COLUMN_NAME_Type,
                MoneyContract.MoneyTable.COLUMN_NAME_Amount,
                MoneyContract.MoneyTable.COLUMN_NAME_Remark,
                MoneyContract.MoneyTable.COLUMN_NAME_Year,
                MoneyContract.MoneyTable.COLUMN_NAME_Month,
                MoneyContract.MoneyTable.COLUMN_NAME_Day
        };
        Cursor c = db.query(
                MoneyContract.MoneyTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        String a,b,d,w;
        while(c.moveToNext()) {
            a=c.getString(c.getColumnIndex(MoneyContract.MoneyTable.COLUMN_NAME_User));
            b=c.getString(c.getColumnIndex(MoneyContract.MoneyTable.COLUMN_NAME_Year));
            d=c.getString(c.getColumnIndex(MoneyContract.MoneyTable.COLUMN_NAME_Month));
            w=c.getString(c.getColumnIndex(MoneyContract.MoneyTable.COLUMN_NAME_Day));
            Log.e("abc",a+b+d+w);
            if(a.equals(user))
            {
                if(b.equals(year1)){
                    if(d.equals(mmm)){
                        if(w.equals(day1)){
                            Map<String, Object> tempData = new HashMap<>();
//                            tempData.put("ID",
//                                    c.getString(c.getColumnIndex(MoneyContract.MoneyTable._ID)));
                            tempData.put("name",
                                    c.getString(c.getColumnIndex(MoneyContract.MoneyTable.COLUMN_NAME_User)));
                            tempData.put("type",
                                    c.getString(c.getColumnIndex(MoneyContract.MoneyTable.COLUMN_NAME_Type)));
                            tempData.put("amount",
                                    c.getString(c.getColumnIndex(MoneyContract.MoneyTable.COLUMN_NAME_Amount)));
                            tempData.put("remark",
                                    c.getString(c.getColumnIndex(MoneyContract.MoneyTable.COLUMN_NAME_Remark)));
                            tempData.put("click",false);
                            ret.add(tempData);

                        }
                    }
                }

            }


        }
        c.close();
        db.close();
//        return ret;
//        Map<String,Object> item1= new HashMap<>();
//        item1.put("name",user);item1.put("type","out");item1.put("amount","555");item1.put("remark","test");item1.put("click",false);
//        ret.add(item1);
            Map<String,Object> item2= new HashMap<>();
//        item2.put("ID","");
            item2.put("click",true);
            item2.put("name",user);
        item2.put("amount","");
        item2.put("remark","");
        item2.put("type","in");
            ret.add(item2);

        return ret;
    }


}
