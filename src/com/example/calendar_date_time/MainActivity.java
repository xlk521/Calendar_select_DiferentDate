package com.example.calendar_date_time;

import java.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	//����1
	private String startDate = null;
	//����2
	private String waterUpDate = null;
	//����1
	private EditText show_startDate = null;
	//����2
	private EditText upwaterDate = null;

	private Message msg = new Message();

	private static final int SHOW_DATAPICK = 0;
	private static final int DATE_DIALOG_ID = 1;
	private static final int SHOW_TIMEPICK = 2;
	private static final int TIME_DIALOG_ID = 3;
	private int mYear;
	private int mMonth;
	private int mDay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ȡ���
		show_startDate = (EditText)findViewById(R.id.showdate);
		upwaterDate = (EditText)findViewById(R.id.waterdate);
		//��ʼ������
		setDateTime();
		//Ϊ�����ı��༭�����ӵ����Ӧ
		show_startDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				msg = new Message();
				if (show_startDate.equals((EditText) v)) {
					msg.what = MainActivity.SHOW_DATAPICK;
				}
				MainActivity.this.dateandtimeHandler.sendMessage(msg);
			}
		});
		upwaterDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				msg = new Message();
				if (upwaterDate.equals((EditText) v)) {
					msg.what = MainActivity.SHOW_TIMEPICK;
				}
				MainActivity.this.dateandtimeHandler.sendMessage(msg);
			}
		});
	}
	/**
     * �򿪶Ի���ʱ�ĳ�ʼ��
     */
	private void setDateTime(){
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		updateDateDisplay();
		updateDateDisplay2();
	}
	 /**
     * ����������ʾ
     */
	private void updateDateDisplay(){
		show_startDate.setText(new StringBuilder().append(mYear).append("-")
	           .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
	           .append((mDay < 10) ? "0" + mDay : mDay));
	}
	private void updateDateDisplay2(){
		upwaterDate.setText(new StringBuilder().append(mYear).append("-")
	           .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
	           .append((mDay < 10) ? "0" + mDay : mDay));
	}
	/**
     * ���ڿؼ����¼�
     */
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
	       mYear = year;
	       mMonth = monthOfYear;
	       mDay = dayOfMonth;
	       updateDateDisplay();
	   }
	};
	private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
	       mYear = year;
	       mMonth = monthOfYear;
	       mDay = dayOfMonth;
	       updateDateDisplay2();
	   }
	};
	/**
     * ���ڸ���
     */
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DATE_DIALOG_ID:
		case TIME_DIALOG_ID:
			((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
			break;
		}
	}
	/**
     * ������ʾ����
     */
	Handler dateandtimeHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case MainActivity.SHOW_DATAPICK:
					showDialog(DATE_DIALOG_ID);
					break;
				case MainActivity.SHOW_TIMEPICK:
					showDialog(TIME_DIALOG_ID);
					break;
			}
		}
	};
	
	/**
     * ���ڵ����Ի���
     * 
     */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,mDay);
		case TIME_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener2, mYear, mMonth,mDay);
		}
		return dialog;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
