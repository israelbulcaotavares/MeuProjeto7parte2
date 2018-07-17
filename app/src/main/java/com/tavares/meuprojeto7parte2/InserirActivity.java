package com.tavares.meuprojeto7parte2;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tavares.meuprojeto7parte2.data.LivroContract;

public class InserirActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_PET_LOADER = 0;

    private Uri mCurrentPetUri;
    private EditText mTituloEditText;
    private EditText mAutorEditText;
    private EditText mPrecoEditText;
    private EditText mQuantidadeEditText;
    private EditText mNomeFornecedorEditText;
    private EditText mTelefoneFornecedorEditText;
    // private EditText mBreedEditText;


    //private int mGender = LivroContract.PetEntry.GENDER_UNKNOWN;

    private boolean mPetHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mPetHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        Intent intent = getIntent();
        if (intent != null) {
            mCurrentPetUri = intent.getData();
            if (mCurrentPetUri == null) {
                setTitle(getString(R.string.editor_activity_title_new_pet));
                invalidateOptionsMenu();
            } else {
                setTitle(getString(R.string.editor_activity_title_edit_pet));
                getLoaderManager().initLoader(EXISTING_PET_LOADER, null, this);
            }
        }

        mTituloEditText = (EditText) findViewById(R.id.edit_titulo);
        mAutorEditText = (EditText) findViewById(R.id.edit_autor);
        mPrecoEditText = (EditText) findViewById(R.id.edit_preco);
        mQuantidadeEditText = (EditText) findViewById(R.id.edit_quantidade);
        mNomeFornecedorEditText = (EditText) findViewById(R.id.edit_nome_fornecedor);
        mTelefoneFornecedorEditText = (EditText) findViewById(R.id.edit_telefone_fornecedor);


        mTituloEditText.setOnTouchListener(mTouchListener);
        mAutorEditText.setOnTouchListener(mTouchListener);
        mPrecoEditText.setOnTouchListener(mTouchListener);
        mQuantidadeEditText.setOnTouchListener(mTouchListener);
        mNomeFornecedorEditText.setOnTouchListener(mTouchListener);
        mTelefoneFornecedorEditText.setOnTouchListener(mTouchListener);


        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        //ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
        //      R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // mGenderSpinner.setAdapter(genderSpinnerAdapter);

        /*


        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = LivroContract.PetEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = LivroContract.PetEntry.GENDER_FEMALE;
                    } else {
                        mGender = LivroContract.PetEntry.GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = LivroContract.PetEntry.GENDER_UNKNOWN;
            }
        });
         */

    }

    private void setPet() {
        String tituloString = mTituloEditText.getText().toString().trim();
        String autorString = mAutorEditText.getText().toString().trim();
        String precoString = mPrecoEditText.getText().toString().trim();
        String quantidadeString = mQuantidadeEditText.getText().toString().trim();
        String nomeFornecedor = mNomeFornecedorEditText.getText().toString().trim();
        String telefoneString = mTelefoneFornecedorEditText.getText().toString().trim();

        /*
        String weightString = mWeightEditText.getText().toString().trim();

        int weight = 0;
        if (!TextUtils.isEmpty(weightString)) {
            weight = Integer.parseInt(weightString);
        }

        if (mCurrentPetUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(breedString) &&
                TextUtils.isEmpty(weightString) && mGender == LivroContract.PetEntry.GENDER_UNKNOWN) {
            return;
        }
        */
        ContentValues values = new ContentValues();
        values.put(LivroContract.PetEntry.COLUNA_TITULO, tituloString);
        values.put(LivroContract.PetEntry.COLUNA_AUTOR, autorString);
        values.put(LivroContract.PetEntry.COLUNA_PRECO, precoString);
        values.put(LivroContract.PetEntry.COLUNA_QUANTIDADE, quantidadeString);
        values.put(LivroContract.PetEntry.COLUNA_NOME_FORNECEDOR, nomeFornecedor);
        values.put(LivroContract.PetEntry.COLUNA_TELEFONE_FORNECEDOR, telefoneString);

        //values.put(LivroContract.PetEntry.COLUMN_PET_WEIGHT, weight);

        if (mCurrentPetUri == null) {
            Uri uri = getContentResolver().insert(LivroContract.PetEntry.CONTENT_URI, values);
            if (uri == null) {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.pet_save), Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(mCurrentPetUri, values, null, null);
            if (rowsAffected == 0) {
                Toast.makeText(this, getString(R.string.error),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.pet_save),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mCurrentPetUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                setPet();
                finish();
                return true;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case android.R.id.home:
                if (!mPetHasChanged) {
                    NavUtils.navigateUpFromSameTask(InserirActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(InserirActivity.this);
                            }
                        };
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                LivroContract.PetEntry._ID,
                LivroContract.PetEntry.COLUNA_TITULO,
                LivroContract.PetEntry.COLUNA_AUTOR,
                LivroContract.PetEntry.COLUNA_PRECO,
                LivroContract.PetEntry.COLUNA_QUANTIDADE,
                LivroContract.PetEntry.COLUNA_NOME_FORNECEDOR,
                LivroContract.PetEntry.COLUNA_TELEFONE_FORNECEDOR};

        return new CursorLoader(this,
                mCurrentPetUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()) {
            int tituloColumnIndex = data.getColumnIndex(LivroContract.PetEntry.COLUNA_TITULO);
            int autorColumnIndex = data.getColumnIndex(LivroContract.PetEntry.COLUNA_AUTOR);
            int precoColumnIndex = data.getColumnIndex(LivroContract.PetEntry.COLUNA_PRECO);
            int quantidadeColumnIndex = data.getColumnIndex(LivroContract.PetEntry.COLUNA_QUANTIDADE);
            int nomeFornecedorColumnIndex = data.getColumnIndex(LivroContract.PetEntry.COLUNA_NOME_FORNECEDOR);
            int telefoneFornecedorColumnIndex = data.getColumnIndex(LivroContract.PetEntry.COLUNA_TELEFONE_FORNECEDOR);

            String titulo = data.getString(tituloColumnIndex);
            String autor = data.getString(autorColumnIndex);
            String preco = data.getString(precoColumnIndex);
            String quantidade = data.getString(quantidadeColumnIndex);
            String nome_fornecedor = data.getString(nomeFornecedorColumnIndex);
            String telefone_fornecedor = data.getString(telefoneFornecedorColumnIndex);

            mTituloEditText.setText(titulo);
            mAutorEditText.setText(autor);
            mPrecoEditText.setText(preco);
            mQuantidadeEditText.setText(quantidade);
            mNomeFornecedorEditText.setText(nome_fornecedor);
            mTelefoneFornecedorEditText.setText(telefone_fornecedor);

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mTituloEditText.setText("");
        mAutorEditText.setText("");
        mPrecoEditText.setText("");
        mQuantidadeEditText.setText("");
        mNomeFornecedorEditText.setText("");
        mTelefoneFornecedorEditText.setText("");
    }

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (!mPetHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deletePet();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /* Perform the deletion of the pet in the database.
     */
    private void deletePet() {
        if (mCurrentPetUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentPetUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, getString(R.string.editor_delete_pet_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_delete_pet_successful), Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}