package com.cos.viewtest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.viewtest.MainActivity;
import com.cos.viewtest.R;
import com.cos.viewtest.model.Person;

import java.util.ArrayList;
import java.util.List;
//d
// 2.어댑터 만들기 (extends)
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private static final String TAG = "PersonAdapter";

    private PersonAdapter personAdapter = this;
    private MainActivity mContext;
    private int createCount=1;
    private int bindCount=1;

    public PersonAdapter(MainActivity mContext) {
        this.mContext=mContext;
    }

    //3. 컬렉션 만들기
    private List<Person> persons = new ArrayList<>();

    //4. 컬렉션 데이터 세팅
    public void addItems(List<Person> persons) {
        this.persons=persons;
        notifyDataSetChanged();//UI가 다시그려지는것 여기서는 oncreate가 종료안되서 안적어줘도댐
        //만약 그림이 다시그려져야되면 필요 그래서 데이터변경하는대는 무조건 필요
    }

    public void addItems(Person person) {
        this.persons.add(person);
        notifyDataSetChanged();
        //버튼 눌리면 그쪽으로 바로 가지게 만든것
        //1.메인액티비티에서 선언 private MainActivity mContext = this;
        //2.매개변수로 넘겨줌 personAdapter = new PersonAdapter(mContext);
        //3.생성자 만들어서 받기
        //4.메인액티비티에서 함수 하나만들기 public void mrvScroll() {
        //        rvPersons.scrollToPosition(personAdapter.getItemCount()-1);
        //    }
        //여기서 함수 사용
        mContext.mRvScroll();
    }

    //Person찾는함수
    public List<Person> getItems() {
        return persons;
    }

    public void removeItem(int index) {
        persons.remove(index);
        notifyDataSetChanged();
    }

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
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvTel;

        //앱 구동시 발동
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTel =  itemView.findViewById(R.id.tvTel);

            initListener();
        }

        private void initListener() {
            //람다는 부모의 래퍼런스를 같이 가진다 중요!
            itemView.setOnClickListener(v -> { //람다식을 쓰면 this(감싸고있는 클래스)가 자동바인딩됨
                Log.d(TAG, "initListener: "+getAdapterPosition());
                int index = getAdapterPosition();
                Log.d(TAG, "initListener: "+personAdapter.getItems().get(index).getName());
                personAdapter.removeItem(index);

            });

        }

        //앱 구동시+ 스크롤할 때
        public  void setItem(Person person) {
            tvName.setText(person.getName());
            tvTel.setText(person.getTel());
        }
    }
}
