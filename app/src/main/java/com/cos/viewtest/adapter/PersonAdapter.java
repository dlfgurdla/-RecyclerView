package com.cos.viewtest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.viewtest.R;
import com.cos.viewtest.model.Person;

import java.util.ArrayList;
import java.util.List;
//d
// 2.어댑터 만들기 (extends)
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private static final String TAG = "PersonAdapter";
    private int createCount=1;
    private int bindCount=1;


    //3. 컬렉션 만들기
    private List<Person> persons = new ArrayList<>();

    //4. 컬렉션 데이터 세팅
    public void addItems(List<Person> persons) {
        this.persons=persons;
        notifyDataSetChanged();//UI가 다시그려지는것 여기서는 oncreate가 종료안되서 안적어줘도댐
        //만약 그림이 다시그려져야되면 필요 그래서 데이터변경하는대는 무조건 필요
    }

//    public void removeItem(int index) {
//        this.persons.remove(index);
//        notifyDataSetChanged();//UI가 다시그려지는것
//    }
//


    // ViewHolder 객체 만드는 친구
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: 그림 만들어짐"+createCount);
        createCount++;
        LayoutInflater layoutInflater =
                (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.person_item,parent, false);
        return new MyViewHolder(view);
    }

    // ViewHolder 데이터를 갈아끼우는 친구
    @Override
    public void onBindViewHolder(PersonAdapter.MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: 데이터 바인딩"+bindCount);
        bindCount++;
        Person person = persons.get(position);
        holder.setItem(person);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    //순서 잘지켜서 만들어야됨
    // 1.View 홀더만들기
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvTel;

        //앱 구동시 발동
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTel =  itemView.findViewById(R.id.tvTel);
        }

        //앱 구동시+ 스크롤할 때
        public  void setItem(Person person) {
            tvName.setText(person.getName());
            tvTel.setText(person.getTel());
        }
    }
}
