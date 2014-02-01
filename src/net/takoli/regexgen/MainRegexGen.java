package net.takoli.regexgen;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainRegexGen extends FragmentActivity {

	static final String RT = "regextext";
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager; // will host the section contents
	TextView regExText;
	String sw;
	String cnt1, cnt2, cnt3, nCnt;
	String ew1, ew2, ew3, nEw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		// Create the adapter that will return a fragment for the sections
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		sw = "";
		cnt1 = cnt2 = cnt3 = nCnt = "";
		ew1 = ew2 = ew3 = nEw = "";		
		createRegex();
	}

	@Override
	// Menu about
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true; }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
		Log.i("about", "pressed");
		DialogFragment about = new AboutDialogFragment();
		about.show(getFragmentManager(), "test");
		return true;
    }

	// this creates the REGEX
	public void createRegex() {
		String cnt, ew;
		sw = withEscapes(sw);
		cnt = andsOrNots(withEscapes(cnt1), withEscapes(cnt2),
				withEscapes(cnt3), withEscapes(nCnt));
		ew = andsOrNots(withEscapes(ew1), withEscapes(ew2), withEscapes(ew3),
				withEscapes(nEw));
		regExText = (TextView) findViewById(R.id.regexText);
		regExText.setText("^" + sw + ".*" + cnt
				+ (cnt.compareTo("") == 0 ? "" : ".*") + ew + "$");
	}

	private String andsOrNots(String s1, String s2, String s3, String n) {
		String output = "";
		int count = 0;
		if (s1.compareTo("") != 0) {
			count++; 
			output = output + s1; }
		if (s2.compareTo("") != 0) {
			count++;
			if (count == 1)
				output = output + s2;
			else 
				output = output + "|" + s2; }
		if (s3.compareTo("") != 0) {
			count++;
			if (count == 1)
				output = output + s3;
			else 
				output = output + "|" + s3; }
		if (n.compareTo("") != 0) {
			count++;
			if (count == 1)
				output = "?!" + n;
			else 
				output = output + "|?!" + n; }
		if (count <= 1)
			return output;
		else
			return "(" + output + ")";
	}

	private String withEscapes(String orig) {
		String escaped = orig;
		return escaped;
	}
	
	public void onShareClick(View view) {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, regExText.getText());
		sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Created with RegExGen app");
		sendIntent.setType("text/plain");
		startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share_regex_to)));
	}
	
	public void showCheatSheet(View view) {
		Log.i("Cheat Sheet", "time to show it");
		Intent intent = new Intent(this, DisplayCheatSheetActivity.class);
		startActivityForResult(intent, RESULT_OK);
		overridePendingTransition(R.anim.cheat_enter, R.anim.main_exit);
	}

	// STARTS WITH FRAGMENT
	public void onSWFCheckboxClicked(View view) {
		boolean checked = ((CheckBox) view).isChecked();
		switch (view.getId()) {
		case R.id.startsAnything:
			if (checked) {
				((CheckBox) findViewById(R.id.upper_letter)).setChecked(false);
				((CheckBox) findViewById(R.id.lower_letter)).setChecked(false);
				((CheckBox) findViewById(R.id.number)).setChecked(false);
				((CheckBox) findViewById(R.id.symbol)).setChecked(false);
				((CheckBox) findViewById(R.id.startsWithCheckBox))
						.setChecked(false);
				sw = "";
			} else if (!((CheckBox) findViewById(R.id.upper_letter))
					.isChecked()
					&& !((CheckBox) findViewById(R.id.lower_letter))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.number)).isChecked()
					&& !((CheckBox) findViewById(R.id.symbol)).isChecked()
					&& !((CheckBox) findViewById(R.id.startsWithCheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.startsAnything)).setChecked(true);
				sw = "";
			}
			break;
		case R.id.upper_letter:
			if (checked) {
				((CheckBox) findViewById(R.id.startsAnything))
						.setChecked(false);
				((CheckBox) findViewById(R.id.startsWithCheckBox))
						.setChecked(false);
			}
			sw = SWFhelper();
			break;
		case R.id.lower_letter:
			if (checked) {
				((CheckBox) findViewById(R.id.startsAnything))
						.setChecked(false);
				((CheckBox) findViewById(R.id.startsWithCheckBox))
						.setChecked(false);
			}
			sw = SWFhelper();
			break;
		case R.id.number:
			if (checked) {
				((CheckBox) findViewById(R.id.startsAnything))
						.setChecked(false);
				((CheckBox) findViewById(R.id.startsWithCheckBox))
						.setChecked(false);
			}
			sw = SWFhelper();
			break;
		case R.id.symbol:
			if (checked) {
				((CheckBox) findViewById(R.id.startsAnything))
						.setChecked(false);
				((CheckBox) findViewById(R.id.startsWithCheckBox))
						.setChecked(false);
			}
			sw = SWFhelper();
			break;
		case R.id.startsWithCheckBox:
			if (checked) {
				((CheckBox) findViewById(R.id.startsAnything))
						.setChecked(false);
				((CheckBox) findViewById(R.id.upper_letter)).setChecked(false);
				((CheckBox) findViewById(R.id.lower_letter)).setChecked(false);
				((CheckBox) findViewById(R.id.number)).setChecked(false);
				((CheckBox) findViewById(R.id.symbol)).setChecked(false);
				EditText startsWithText = (EditText) findViewById(R.id.startsWithText);
				startsWithText.requestFocus();
				sw = startsWithText.getText().toString();
			} else {
				((CheckBox) findViewById(R.id.startsAnything)).setChecked(true);
				sw = "";
			}
			break;
		}
		createRegex();

	}

	private String SWFhelper() {
		String swh = "[";
		if (((CheckBox) findViewById(R.id.lower_letter)).isChecked())
			swh += "a-z";
		if (((CheckBox) findViewById(R.id.upper_letter)).isChecked())
			swh += "A-Z";
		if (((CheckBox) findViewById(R.id.number)).isChecked())
			swh += "0-9";
		if (((CheckBox) findViewById(R.id.symbol)).isChecked())
			swh += "!-/:-@\\[-`\\{-~";
		swh = swh + "]";
		if ((((CheckBox) findViewById(R.id.lower_letter)).isChecked()) == false
				&& (((CheckBox) findViewById(R.id.upper_letter)).isChecked()) == false
				&& (((CheckBox) findViewById(R.id.number)).isChecked()) == false
				&& (((CheckBox) findViewById(R.id.symbol)).isChecked()) == false) {
			((CheckBox) findViewById(R.id.startsAnything)).setChecked(true);
			return "";
		}
		return swh;
	}

	public void regSWFlisteners(View view) {
		Log.i("takoli", "regSWFlisteners");
		// LISTENER: startsWithText changes sent to regex right away
		((EditText) findViewById(R.id.startsWithText))
				.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						((CheckBox) findViewById(R.id.startsWithCheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.upper_letter))
								.setChecked(false);
						((CheckBox) findViewById(R.id.lower_letter))
								.setChecked(false);
						((CheckBox) findViewById(R.id.number))
								.setChecked(false);
						((CheckBox) findViewById(R.id.symbol))
								.setChecked(false);
						((CheckBox) findViewById(R.id.startsAnything))
								.setChecked(false);
						sw = s.toString();
						createRegex();
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						((CheckBox) findViewById(R.id.startsWithCheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.upper_letter))
								.setChecked(false);
						((CheckBox) findViewById(R.id.lower_letter))
								.setChecked(false);
						((CheckBox) findViewById(R.id.number))
								.setChecked(false);
						((CheckBox) findViewById(R.id.symbol))
								.setChecked(false);
						((CheckBox) findViewById(R.id.startsAnything))
								.setChecked(false);
						sw = s.toString();
						createRegex();
					}
				});
	}

	// CONTINUES WITH FRAGMENT
	public void onCFCheckboxClicked(View view) {
		boolean checked = ((CheckBox) view).isChecked();
		switch (view.getId()) {
		case R.id.contAnything:
			if (checked) {
				((CheckBox) findViewById(R.id.containsText1CheckBox))
						.setChecked(false);
				((CheckBox) findViewById(R.id.containsText2CheckBox))
						.setChecked(false);
				((CheckBox) findViewById(R.id.containsText3CheckBox))
						.setChecked(false);
				((CheckBox) findViewById(R.id.notContainsTextCheckBox))
						.setChecked(false);
				cnt1 = cnt2 = cnt3 = nCnt = "";
			} else if (!((CheckBox) findViewById(R.id.containsText1CheckBox))
					.isChecked()
					&& !((CheckBox) findViewById(R.id.containsText2CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.containsText3CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.notContainsTextCheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.contAnything)).setChecked(true);
				cnt1 = cnt2 = cnt3 = nCnt = "";
			}
			break;
		case R.id.containsText1CheckBox:
			if (checked) {
				((CheckBox) findViewById(R.id.contAnything)).setChecked(false);
				EditText contText = (EditText) findViewById(R.id.containsText1);
				contText.requestFocus();
				cnt1 = contText.getText().toString();
			} else {
				cnt1 = "";
				if (!((CheckBox) findViewById(R.id.containsText2CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.containsText3CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.notContainsTextCheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.contAnything)).setChecked(true);
				cnt2 = cnt3 = nCnt = ""; }
			}
			break;
		case R.id.containsText2CheckBox:
			if (checked) {
				((CheckBox) findViewById(R.id.contAnything)).setChecked(false);
				EditText contText = (EditText) findViewById(R.id.containsText2);
				contText.requestFocus();
				cnt2 = contText.getText().toString();
			} else {
				cnt2 = "";
				if (!((CheckBox) findViewById(R.id.containsText1CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.containsText3CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.notContainsTextCheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.contAnything)).setChecked(true);
				cnt1 = cnt3 = nCnt = ""; }
			}
			break;
		case R.id.containsText3CheckBox:
			if (checked) {
				((CheckBox) findViewById(R.id.contAnything)).setChecked(false);
				EditText contText = (EditText) findViewById(R.id.containsText3);
				contText.requestFocus();
				cnt3 = contText.getText().toString();
			} else {
				cnt3 = "";
				if (!((CheckBox) findViewById(R.id.containsText1CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.containsText2CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.notContainsTextCheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.contAnything)).setChecked(true);
				cnt1 = cnt2 = nCnt = ""; }
			}
			break;
		case R.id.notContainsTextCheckBox:
			if (checked) {
				((CheckBox) findViewById(R.id.contAnything)).setChecked(false);
				EditText contText = (EditText) findViewById(R.id.notContainsText);
				contText.requestFocus();
				nCnt = contText.getText().toString();
			} else {
				nCnt = "";
				if (!((CheckBox) findViewById(R.id.containsText1CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.containsText2CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.containsText3CheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.contAnything)).setChecked(true);
				cnt1 = cnt2 = cnt3; }
			}
			break;
		}
		// updates RegEx field
		createRegex();
	}

	public void regCFlisteners(View view) {
		Log.i("takoli", "regCFlisteners");
		// LISTENTER: containsText1 changes sent to regex right away
		((EditText) findViewById(R.id.containsText1))
				.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						((CheckBox) findViewById(R.id.containsText1CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.contAnything))
								.setChecked(false);
						cnt1 = s.toString();
						createRegex();
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						((CheckBox) findViewById(R.id.containsText1CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.contAnything))
								.setChecked(false);
						cnt1 = s.toString();
						createRegex();
					}
				});
		// LISTENTER: containsText2 changes sent to regex right away
		((EditText) findViewById(R.id.containsText2))
				.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						((CheckBox) findViewById(R.id.containsText2CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.contAnything))
								.setChecked(false);
						cnt2 = s.toString();
						createRegex();
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						((CheckBox) findViewById(R.id.containsText2CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.contAnything))
								.setChecked(false);
						cnt2 = s.toString();
						createRegex();
					}
				});
		// LISTENTER: containsText3 changes sent to regex right away
		((EditText) findViewById(R.id.containsText3))
				.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						((CheckBox) findViewById(R.id.containsText3CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.contAnything))
								.setChecked(false);
						cnt3 = s.toString();
						createRegex();
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						((CheckBox) findViewById(R.id.containsText3CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.contAnything))
								.setChecked(false);
						cnt3 = s.toString();
						createRegex();
					}
				});
		// LISTENTER: notContainsText changes sent to regex right away
		((EditText) findViewById(R.id.notContainsText))
				.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						((CheckBox) findViewById(R.id.notContainsTextCheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.contAnything))
								.setChecked(false);
						nCnt = s.toString();
						createRegex();
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						((CheckBox) findViewById(R.id.notContainsTextCheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.contAnything))
								.setChecked(false);
						nCnt = s.toString();
						createRegex();
					}
				});
	}

	// ENDS WITH FRAGMENT
	public void onEWFCheckboxClicked(View view) {
		boolean checked = ((CheckBox) view).isChecked();
		switch (view.getId()) {
		case R.id.endsAnything:
			if (checked) {
				((CheckBox) findViewById(R.id.endsText1CheckBox))
						.setChecked(false);
				((CheckBox) findViewById(R.id.endsText2CheckBox))
						.setChecked(false);
				((CheckBox) findViewById(R.id.endsText3CheckBox))
						.setChecked(false);
				((CheckBox) findViewById(R.id.notEndsTextCheckBox))
						.setChecked(false);
				ew1 = ew2 = ew3 = nEw = "";
			} else if (!((CheckBox) findViewById(R.id.endsText1CheckBox))
					.isChecked()
					&& !((CheckBox) findViewById(R.id.endsText2CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.endsText3CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.notEndsTextCheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.endsAnything)).setChecked(true);
				ew1 = ew2 = ew3 = nEw = "";
			}
			break;
		case R.id.endsText1CheckBox:
			if (checked) {
				((CheckBox) findViewById(R.id.endsAnything)).setChecked(false);
				EditText contText = (EditText) findViewById(R.id.endsText1);
				contText.requestFocus();
				ew1 = contText.getText().toString();
			} else if (!((CheckBox) findViewById(R.id.endsText2CheckBox))
					.isChecked()
					&& !((CheckBox) findViewById(R.id.endsText3CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.notEndsTextCheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.endsAnything)).setChecked(true);
				ew1 = ew2 = ew3 = nEw = "";
			}
			break;
		case R.id.endsText2CheckBox:
			if (checked) {
				((CheckBox) findViewById(R.id.endsAnything)).setChecked(false);
				EditText contText = (EditText) findViewById(R.id.endsText2);
				contText.requestFocus();
				ew2 = contText.getText().toString();
			} else if (!((CheckBox) findViewById(R.id.endsText1CheckBox))
					.isChecked()
					&& !((CheckBox) findViewById(R.id.endsText3CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.notEndsTextCheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.endsAnything)).setChecked(true);
				ew1 = ew2 = ew3 = nEw = "";
			}
			break;
		case R.id.endsText3CheckBox:
			if (checked) {
				((CheckBox) findViewById(R.id.endsAnything)).setChecked(false);
				EditText contText = (EditText) findViewById(R.id.endsText3);
				contText.requestFocus();
				ew3 = contText.getText().toString();
			} else if (!((CheckBox) findViewById(R.id.endsText1CheckBox))
					.isChecked()
					&& !((CheckBox) findViewById(R.id.endsText2CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.notEndsTextCheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.endsAnything)).setChecked(true);
				ew1 = ew2 = ew3 = nEw = "";
			}
			break;
		case R.id.notEndsTextCheckBox:
			if (checked) {
				((CheckBox) findViewById(R.id.endsAnything)).setChecked(false);
				EditText contText = (EditText) findViewById(R.id.notEndsText);
				contText.requestFocus();
				nEw = contText.getText().toString();
			} else if (!((CheckBox) findViewById(R.id.endsText1CheckBox))
					.isChecked()
					&& !((CheckBox) findViewById(R.id.endsText2CheckBox))
							.isChecked()
					&& !((CheckBox) findViewById(R.id.endsText3CheckBox))
							.isChecked()) {
				((CheckBox) findViewById(R.id.endsAnything)).setChecked(true);
				ew1 = ew2 = ew3 = nEw = "";
			}
			break;
		}
		// updates RegEx field
		createRegex();
	}

	public void regEWFlisteners(View view) {
		Log.i("takoli", "regEWFlisteners");
		// LISTENTER: endsText1 changes sent to regex right away
		((EditText) findViewById(R.id.endsText1))
				.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						((CheckBox) findViewById(R.id.endsText1CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.endsAnything))
								.setChecked(false);
						ew1 = s.toString();
						createRegex();
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						((CheckBox) findViewById(R.id.endsText1CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.endsAnything))
								.setChecked(false);
						ew1 = s.toString();
						createRegex();
					}
				});
		// LISTENTER: endsText2 changes sent to regex right away
		((EditText) findViewById(R.id.endsText2))
				.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						((CheckBox) findViewById(R.id.endsText2CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.endsAnything))
								.setChecked(false);
						ew2 = s.toString();
						createRegex();
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						((CheckBox) findViewById(R.id.endsText2CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.endsAnything))
								.setChecked(false);
						ew2 = s.toString();
						createRegex();
					}
				});
		// LISTENTER: endsText3 changes sent to regex right away
		((EditText) findViewById(R.id.endsText3))
				.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						((CheckBox) findViewById(R.id.endsText3CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.endsAnything))
								.setChecked(false);
						ew3 = s.toString();
						createRegex();
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						((CheckBox) findViewById(R.id.endsText3CheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.endsAnything))
								.setChecked(false);
						ew3 = s.toString();
						createRegex();
					}
				});
		// LISTENTER: notendsText changes sent to regex right away
		((EditText) findViewById(R.id.notEndsText))
				.addTextChangedListener(new TextWatcher() {
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						((CheckBox) findViewById(R.id.notEndsTextCheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.endsAnything))
								.setChecked(false);
						nEw = s.toString();
						createRegex();
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						((CheckBox) findViewById(R.id.notEndsTextCheckBox))
								.setChecked(true);
						((CheckBox) findViewById(R.id.endsAnything))
								.setChecked(false);
						nEw = s.toString();
						createRegex();
					}
				});
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			Fragment fragment = new SectionFragment();
			Bundle args = new Bundle();
			args.putInt(SectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			fragment.setUserVisibleHint(true);
			return fragment;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_section1);
			case 1:
				return getString(R.string.title_section2);
			case 2:
				return getString(R.string.title_section3);
			}
			return null;
		}
	}

	public static class SectionFragment extends Fragment {
		public static final String ARG_SECTION_NUMBER = "section_number";
		public int currentPage;
		public View thisView;

		public SectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			currentPage = getArguments().getInt(ARG_SECTION_NUMBER);
			// Log.i("onCreateView", "page: "+currentPage);
			thisView = null;
			switch (currentPage) {
			case 1:
				thisView = inflater.inflate(R.layout.starts_with_fragment,
						container, false);
				break;
			case 2: {
				thisView = inflater.inflate(R.layout.contains_fragment,
						container, false);
			}
				break;
			case 3:
				thisView = inflater.inflate(R.layout.ends_with_fragment,
						container, false);
				break;
			}
			return thisView;
		}

		@Override
		public void setUserVisibleHint(final boolean visible) {
			super.setUserVisibleHint(visible);
			if (visible && currentPage == 1) {
				Log.i("setUserVisibleHint", "suvh StartsWith");
				((TextView) thisView.findViewById(R.id.sideText1))
						.performClick();
			}
			if (visible && currentPage == 2) {
				Log.i("setUserVisibleHint", "suvh Contains");
				((TextView) thisView.findViewById(R.id.sideText2))
						.performClick();
			}
			if (visible && currentPage == 3) {
				Log.i("setUserVisibleHint", "suvh EndsWith");
				((TextView) thisView.findViewById(R.id.sideText3))
						.performClick();
			}
		}
	}
}
