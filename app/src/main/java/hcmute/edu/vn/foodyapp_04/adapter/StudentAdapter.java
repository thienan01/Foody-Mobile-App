package hcmute.edu.vn.foodyapp_04.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.foodyapp_04.R;
import hcmute.edu.vn.foodyapp_04.models.Food;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{

    private  List<Food> foodList;
    public StudentAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_container,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.setStudentData(foodList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class StudentViewHolder extends  RecyclerView.ViewHolder{
        TextView txtName;
        TextView mssv;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvFoodName);
            mssv = itemView.findViewById(R.id.tvFoodPrice);
        }
        void setStudentData(Food food){
            txtName.setText(food.name);
            mssv.setText(food.price);
        }
    }

}
