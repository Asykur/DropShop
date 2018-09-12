package asykurkhamid.dropshop.SQLite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbExecuteLogic {

    public static void cekID(DBHelper dbHelper,String id,int qty,int price, int subTotal){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select*from tCart where productID = '"+id+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            updateDBPrice(dbHelper,id,qty,subTotal);
        }else {
            insertDBPrice(dbHelper,id,qty,price,subTotal);
        }
    }

    public static void insertDBPrice(DBHelper dbHelper,String id,int qty,int price, int subTotal){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.execSQL("" +
                "INSERT INTO tCart " +
                "(productID,qty,price,subTotal) " +
                "VALUES ('"+id+"','"+qty+"','"+price+"','"+subTotal+"')");
    }

    public static void updateDBPrice(DBHelper dbHelper,String id,int qty, int subTotal){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.execSQL("update tCart set qty = '"+qty+"', subTotal='"+subTotal+"' where productID='"+id+"'");
    }
    public static void insertTotalBayar(DBHelper dbHelper,int totalBayar){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.execSQL(""+ "INSERT INTO tCart" + "(TotalBayar)" +
        "VALUES ('"+totalBayar+"')");
    }

    public static void deleteProduk(DBHelper dbHelper,String pid) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from tCart where productID = '"+pid+"'");
    }

}
