package hcmute.edu.vn.foodyapp_04.database;

import android.content.Context;
import android.database.Cursor;

import hcmute.edu.vn.foodyapp_04.models.Food;
import hcmute.edu.vn.foodyapp_04.models.User;
import hcmute.edu.vn.foodyapp_04.utilities.Constants;

public  class DAO {

    public  static Cursor selectAll(Database database, String tableName){
        Cursor data =  database.GetData("SELECT * FROM "+ tableName);
        return data;
    }
    public  static  void insertUser(Database database, User user){
        database.QueryData("INSERT INTO "+ Constants.KEY_USERS+" VALUES(null,null,'"+user.getName()+"', '"+user.getPhoneNumber()+"', '"+user.getPassword()+"', " +
                "'"+user.getType()+"', '"+user.getAddress()+"')");
    }
    public  static  void insertFood(Database database, Food food){
        database.QueryData("CREATE TABLE IF NOT EXISTS "+Constants.KEY_FOOD+"(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+ Constants.KEY_FOOD_IMAGE +" TEXT," +
                " "+ Constants.KEY_FOOD_NAME+" VARCHAR(200), " +
                Constants.KEY_FOOD_PRICE+ " VARCHAR(20), " +
                        Constants.KEY_FOOD_DESCRIPTION+ " TEXT, " +
                        Constants.KEY_FOOD_RESTAURANT_ID+" INTEGER)");

        database.QueryData("INSERT INTO "+ Constants.KEY_FOOD+" VALUES(null, '"+food.getImage()+"','"+food.getName()+"'," +
                "'"+food.getPrice()+"','"+food.getDescription()+"', '"+food.getRestaurantId()+"')");
    }

    public  static User getRestaurantById(Database database, String id){
        Cursor data = database.GetData("SELECT * FROM "+Constants.KEY_RESTAURANT+" WHERE id = "+id);
        User user =null;
        while (data.moveToNext()){
            user = new User(data.getInt(0),data.getString(1),
                    data.getString(2),
                    data.getString(3),
                    data.getString(4),
                    data.getString(5),
                    data.getString(6));
        }
        return user;
    }

}
