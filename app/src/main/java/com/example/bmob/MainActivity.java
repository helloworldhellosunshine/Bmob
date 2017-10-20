package com.example.bmob;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {
    private Button category;
    private List<Play> playList=new ArrayList<>();
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Bmob.initialize(MainActivity.this,"891626f1f13d77230f0912f0cc312102");

        initView();

        intData();
    }

    private void intData() {
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Play> bmobQuery=new BmobQuery<Play>();
                bmobQuery.findObjects(new FindListener<Play>() {
                    @Override
                    public void done(List<Play> list, BmobException e) {
                        if(e==null){
                            playList.clear();
                            playList=list;
                            if (myAdapter!=null){
                                myAdapter.notifyDataSetChanged();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                });
            }
        });

    }

    private void initView() {
        final EditText name= (EditText) findViewById(R.id.userNaMe);
        final EditText psw=(EditText)findViewById(R.id.passWord);
        Button up= (Button) findViewById(R.id.up);
        category= (Button) findViewById(R.id.look);
        ListView listView= (ListView) findViewById(R.id.lv);
        myAdapter =new MyAdapter();
        listView.setAdapter(myAdapter);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1=name.getText().toString().trim();
                String password1=psw.getText().toString().trim();
                Play play=new Play(name1,password1);
                play.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    class MyAdapter extends BaseAdapter{



        @Override
        public int getCount() {
            return playList.size();
        }

        @Override
        public Object getItem(int position) {
            return playList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                convertView=LayoutInflater.from(getApplicationContext()).inflate(R.layout.second,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.xiangming=(TextView) convertView.findViewById(R.id.name1);
                viewHolder.mima=(TextView) convertView.findViewById(R.id.pas1);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.xiangming.setText(getItem(position).toString());
            viewHolder.mima.setText(getItem(position).toString());
            return convertView;
        }

    }
    static class ViewHolder{
        public TextView xiangming;
        public TextView mima;
    }
}
