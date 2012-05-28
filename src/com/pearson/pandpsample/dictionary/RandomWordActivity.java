package com.pearson.pandpsample.dictionary;

import com.pearson.pandpsample.dictionary.remote.LongmanAPIHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RandomWordActivity extends Activity {

	private TextView tvWord;
	private ProgressDialog progressDialog;

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnGetRandomWord = (Button) findViewById(R.id.btn_get_random_word);
        tvWord = (TextView) findViewById(R.id.tv_word_label);
        
        btnGetRandomWord.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showProgressDialog();
				new RetrieveDictionaryEntryTask().execute(null);
			}
		});
        
    }
    
    private void completeEntryLoad(DictionaryEntry entry) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
		if (entry != null) {
			tvWord.setText(entry.toString());
		} else {
			tvWord.setText("Formatting error in returned response. Please try again.");
		}
    }
    
	private void showProgressDialog() {
		progressDialog = ProgressDialog.show(this, "", "Getting Random Word...", true);
	}

    
    /**
     * AsyncTask for retrieval of RandomWord.
     */
     class RetrieveDictionaryEntryTask extends AsyncTask<Void, Void, DictionaryEntry> {
         @Override
         protected DictionaryEntry doInBackground(Void... arg0) {
         	try {
 				return new LongmanAPIHelper().getRandomDictionaryEntry();
 			} catch (Exception e) {
 				Log.w(Settings.LOG_TAG, e.getClass().getSimpleName() + ", " + e.getMessage());
 				return null;
 			}
         }

         @Override
         protected void onPostExecute(DictionaryEntry entry) {
             completeEntryLoad(entry);
         }

     }

}