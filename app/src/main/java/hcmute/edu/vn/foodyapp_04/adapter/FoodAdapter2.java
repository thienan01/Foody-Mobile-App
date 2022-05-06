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

public class FoodAdapter2 extends  RecyclerView.Adapter<FoodAdapter2.FoodViewHolder> {

    private List<Food> foodList;

    public FoodAdapter2(List<Food> studentList) {
        this.foodList = studentList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_test,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.setFoodData(foodList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class FoodViewHolder extends  RecyclerView.ViewHolder{
        TextView txtName;
        TextView mssv;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvName);
            mssv = itemView.findViewById(R.id.tvPrice);
        }
        void setFoodData(Food food){
            txtName.setText("Jaja");
            mssv.setText(food.price);
        }
    }}
