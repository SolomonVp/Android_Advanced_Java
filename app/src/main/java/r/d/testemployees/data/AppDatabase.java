package r.d.testemployees.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import r.d.testemployees.pojo.Employee;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static String DB_NAME = "employees_db";
    private static AppDatabase database;

    private static final Object LOCK = new Object();


    public static AppDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
            }
            return database;
        }
    }

    public abstract EmployeeDao employeeDao();
}
