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
import edu.wctc.android.flashalerts.util.FileUtils;

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

		questions = FileUtils.readLinesFromRawTextFile(getApplicationContext(),
				R.raw.questions);
		answers = FileUtils.readLinesFromRawTextFile(getApplicationContext(),
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
		if (clickNumber == 0) {
			cardNumber--;
			advanceCard();
			clickNumber++;
		} else {
			showDialog(answers[cardNumber]);
		}
	}

	private void advanceCard() {
		if (cardNumber < (questions.length - 1)) {
			cardNumber++;
		} else {
			cardNumber = 0;
		}
		textView.setText(questions[cardNumber]);
	}

	private void showDialog(String message) {

		LayoutInflater inflater = LayoutInflater.from(this);
		final View addView = inflater.inflate(R.layout.dialog_answer, null);

		new AlertDialog.Builder(this)
				.setTitle("Answer")
				.setView(addView)
				.setPositiveButton("Next Question",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								advanceCard();
							}
						}).setMessage(message).show();
	}
}
