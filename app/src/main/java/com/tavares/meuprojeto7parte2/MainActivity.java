package com.tavares.meuprojeto7parte2;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tavares.meuprojeto7parte2.adapter.PetCursorAdapter;
import com.tavares.meuprojeto7parte2.data.PetContract;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PET_LOADER = 0;

    private PetCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InserirActivity.class);
                startActivity(intent);
            }
        });

        ListView petListView =  findViewById(R.id.list);

      //  View emptyView = findViewById(R.id.empty_view);
        //petListView.setEmptyView(emptyView);

        mCursorAdapter = new PetCursorAdapter(this, null);
        petListView.setAdapter(mCursorAdapter);

        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, InserirActivity.class);
                Uri currentPetUri = ContentUris.withAppendedId(PetContract.PetEntry.CONTENT_URI, id);
                intent.setData(currentPetUri);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(PET_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertPet();
                return true;
            case R.id.action_delete_all_entries:
                deleteAllPets();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertPet() {
        ContentValues values = new ContentValues();
        values.put(PetContract.PetEntry.COLUNA_TITULO, "INVESTIDOR INTELIGENTE");
        values.put(PetContract.PetEntry.COLUNA_AUTOR, "Benjamin Graham");
        values.put(PetContract.PetEntry.COLUNA_PRECO, "R$0,00");
        values.put(PetContract.PetEntry.COLUNA_QUANTIDADE, "130");
        values.put(PetContract.PetEntry.COLUNA_NOME_FORNECEDOR, "130");
        values.put(PetContract.PetEntry.COLUNA_TELEFONE_FORNECEDOR, "75454634662");

        // Uri uri = getContentResolver().insert(PetContract.PetEntry.CONTENT_URI, values);
    }

    /* Helper method to delete all pets in the database.
     */
    private void deleteAllPets() {
        int rowsDeleted = getContentResolver().delete(PetContract.PetEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from pet database");
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUNA_TITULO,
                PetContract.PetEntry.COLUNA_AUTOR,
                PetContract.PetEntry.COLUNA_PRECO,
                PetContract.PetEntry.COLUNA_QUANTIDADE,
                PetContract.PetEntry.COLUNA_NOME_FORNECEDOR,
                PetContract.PetEntry.COLUNA_TELEFONE_FORNECEDOR};

        return new CursorLoader(this,
                PetContract.PetEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}