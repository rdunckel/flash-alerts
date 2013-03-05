package edu.wctc.android.flashalerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import edu.wctc.android.flashalerts.util.Utils;

public class MainActivty extends Activity implements OnClickListener {

	private String[] questions;
	private String[] answers;
	private int cardNumber;
	private int clickNumber;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_activty);

		cardNumber = 0;
		clickNumber = 0;

		questions = Utils.readLinesFromRawTextFile(getApplicationContext(),
				R.raw.questions);
		answers = Utils.readLinesFromRawTextFile(getApplicationContext(),
				R.raw.answers);

		textView = (TextView) findViewById(R.id.textView1);
		textView.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_activty, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (Utils.isEven(clickNumber)) {
			textView.setText(questions[cardNumber]);
		} else {
			// textView.setText(answers[cardNumber]);
			showDialog(answers[cardNumber]);

			if (cardNumber < (questions.length - 1)) {
				cardNumber++;
			} else {
				cardNumber = 0;
			}
		}

		clickNumber++;
	}

	private void showDialog(String message) {

		LayoutInflater inflater = LayoutInflater.from(this);
		final View addView = inflater.inflate(R.layout.dialog_answer, null);

		new AlertDialog.Builder(this).setTitle("Answer").setView(addView)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Ok - do nothing
					}
				}).setMessage(message).show();
	}
}
