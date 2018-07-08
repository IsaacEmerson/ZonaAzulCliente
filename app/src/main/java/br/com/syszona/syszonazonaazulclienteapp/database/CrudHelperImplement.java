package br.com.syszona.syszonazonaazulclienteapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.syszona.syszonazonaazulclienteapp.models.CreditCard;

public class CrudHelperImplement implements CRUDHelper<CreditCard> {

    DbHandler handler = null;

    public CrudHelperImplement(DbHandler handler){
        this.handler = handler;
    }

    @Override
    public void create(CreditCard obj) {
        SQLiteDatabase db= this.handler.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put("name",obj.getName());
        values.put("month",obj.getName());
        values.put("year",obj.getName());
        values.put("cvv",obj.getName());
        values.put("parcels",obj.getName());
        values.put("error",obj.getName());
        values.put("token",obj.getName());
        values.put("cardNumber",obj.getName());

        db.insert(DbHandler.T_CREDIT_CARD,null,values);
        db.close();

    }

    @Override
    public CreditCard findById(int id) {
        SQLiteDatabase db= this.handler.getReadableDatabase();
        String[] projection = {"id","name","month","year","cvv","parcels","error","token","cardNumber"};
        String selection =  "id = ?";
        String[] selectionArgs = {String.valueOf(id)};

        //String sortOrder = "id DESC";

        Cursor c = db.query(DbHandler.T_CREDIT_CARD,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if(c!=null){
            c.moveToFirst();
        }

        CreditCard card = new CreditCard();
        card.setId(c.getInt(0));
        card.setName(c.getString(1));
        card.setMonth(c.getString(2));
        card.setYear(c.getString(3));
        card.setCvv(c.getString(4));
        card.setParcels(c.getInt(5));
        card.setError(c.getString(6));
        card.setToken(c.getString(7));
        card.setCardNumber(c.getString(8));

        return card;
    }

    @Override
    public List<CreditCard> findAll() {
        List<CreditCard> list = new ArrayList<CreditCard>();

        SQLiteDatabase db = this.handler.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+DbHandler.T_CREDIT_CARD,null);

        if(c.moveToFirst()){
            do{
                CreditCard card = new CreditCard();
                card.setId(c.getInt(0));
                card.setName(c.getString(1));
                card.setMonth(c.getString(2));
                card.setYear(c.getString(3));
                card.setCvv(c.getString(4));
                card.setParcels(c.getInt(5));
                card.setError(c.getString(6));
                card.setToken(c.getString(7));
                card.setCardNumber(c.getString(8));
                list.add(card);
            }while (c.moveToNext());
        }

            return list;
    }

    @Override
    public int update(CreditCard obj) {
        return 0;
    }

    @Override
    public void delete(CreditCard obj) {

    }
}
