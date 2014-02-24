package net.takoli.regexgen;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class DisplayCheatSheetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cheat_sheet);
	}
	
	@Override
	// Menu about
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true; }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
		//Log.i("about", "pressed");
		DialogFragment about = new AboutDialogFragment();
		about.show(getFragmentManager(), "test");
		return true;
    }
	
	@Override
	public void onBackPressed() {
		//Log.i("cheatSheet", "onBackPressed");
		finish();
		overridePendingTransition(R.anim.main_return, R.anim.cheat_exit);
	}
	
	public void backToMain(View view) {
		finish();
		overridePendingTransition(R.anim.main_return, R.anim.cheat_exit);
	}
}
