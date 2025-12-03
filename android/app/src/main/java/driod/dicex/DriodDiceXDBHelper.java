package driod.dicex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * データベース接続
 * @author yasupong
 */
public class DriodDiceXDBHelper extends SQLiteOpenHelper {

	public static String DB_NAME = "DiceX";
	public static int DB_VERSON = 1;
	public static String DB_TABLE_SCORE = "DiceXScore";
	
	public DriodDiceXDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSON);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL( "create table if not exists " + DB_TABLE_SCORE + 
						"(scoredate text primary key," +
						"score text not null" +
						")" );
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		arg0.execSQL( "drop table if exists " + DB_TABLE_SCORE );
		onCreate(arg0);
	}
}
