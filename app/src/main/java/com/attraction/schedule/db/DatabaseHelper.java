package com.attraction.schedule.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String  DATABASE_NAME = "schedule.db";
	private static final int DATABASE_VERSION = 1;
	// 双重加锁
	private volatile static DatabaseHelper instance;
	
	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * 单例模式
	 * @param context
	 * @return
	 */
    public static DatabaseHelper getInstance(Context context)
    {
    	// 全局性的context
    	context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                	instance = new DatabaseHelper(context);
            }
        }
        return instance;
    }
    
	@Override
	public void onCreate(SQLiteDatabase database, 
			ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Lesson.class);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database,  
            ConnectionSource connectionSource, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
		
	}

}
