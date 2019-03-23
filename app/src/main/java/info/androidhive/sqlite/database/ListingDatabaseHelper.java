package info.androidhive.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.sqlite.database.model.Listing;

public class ListingDatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "listings_db";


    public ListingDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Listing.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Listing.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertListing(String listing, String course, String priority) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Listing.COLUMN_STUDENT, listing);
        values.put(Listing.COLUMN_COURSE, course);
        values.put(Listing.COLUMN_PRIORITY, priority);


        // insert row
        long id = db.insert(Listing.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Listing getListing(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Listing.TABLE_NAME,
                new String[]{Listing.COLUMN_ID, Listing.COLUMN_STUDENT, Listing.COLUMN_COURSE, Listing.COLUMN_PRIORITY},
                Listing.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Listing listing = new Listing(
                cursor.getInt(cursor.getColumnIndex(Listing.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Listing.COLUMN_STUDENT)),
                cursor.getString(cursor.getColumnIndex(Listing.COLUMN_COURSE)),
                cursor.getString(cursor.getColumnIndex(Listing.COLUMN_PRIORITY)));

        // close the db connection
        cursor.close();

        return listing;
    }

    public List<Listing> getAllListings() {
        List<Listing> listings = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Listing.TABLE_NAME + " ORDER BY " +
                Listing.COLUMN_ID + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Listing listing = new Listing();
                listing.setId(cursor.getInt(cursor.getColumnIndex(Listing.COLUMN_ID)));
                listing.setStudent(cursor.getString(cursor.getColumnIndex(Listing.COLUMN_STUDENT)));
                listing.setCourse(cursor.getString(cursor.getColumnIndex(Listing.COLUMN_COURSE)));
                listing.setPriority(cursor.getString(cursor.getColumnIndex(Listing.COLUMN_PRIORITY)));

                listings.add(listing);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return listings;
    }

    public int getListingsCount() {
        String countQuery = "SELECT  * FROM " + Listing.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateListing(Listing listing) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Listing.COLUMN_STUDENT, listing.getStudent());

        // updating row
        return db.update(Listing.TABLE_NAME, values, Listing.COLUMN_ID + " = ?",
                new String[]{String.valueOf(listing.getId())});
    }

    public void deleteListing(Listing listing) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Listing.TABLE_NAME, Listing.COLUMN_ID + " = ?",
                new String[]{String.valueOf(listing.getId())});
        db.close();
    }
}
