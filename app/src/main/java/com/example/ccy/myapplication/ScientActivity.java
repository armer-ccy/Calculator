package com.example.ccy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ScientActivity extends AppCompatActivity implements View.OnClickListener {

	EditText display;
	EditText display_history;

	Button change;

	Button no0;
	Button no1;
	Button no2;
	Button no3;
	Button no4;
	Button no5;
	Button no6;
	Button no7;
	Button no8;
	Button no9;
	Button clean;
	Button backspace;
	Button per_cent;//%
	Button divide;///
	Button multiply;//*
	Button sub;//-
	Button add;//+
	Button bracket;//()
	Button point;//.
	Button equal;//=

	Button pai;
	Button sin;
	Button sqr;
	Button pow2;
	Button powy;
	Button cos;
	Button tan;
	Button ln;
	Button lg;
	Button e;

	List expression = new ArrayList();
	Op op = new Op();
	int Le = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scientific_calculator);
		display = (EditText) findViewById(R.id.display);
		display_history = (EditText) findViewById(R.id.display_history);

		display.clearFocus();//失去焦点，不弹出键盘
		display_history.clearFocus();

		change = (Button) findViewById(R.id.change);

		no0 = (Button) findViewById(R.id.no0);
		no1 = (Button) findViewById(R.id.no1);
		no2 = (Button) findViewById(R.id.no2);
		no3 = (Button) findViewById(R.id.no3);
		no4 = (Button) findViewById(R.id.no4);
		no5 = (Button) findViewById(R.id.no5);
		no6 = (Button) findViewById(R.id.no6);
		no7 = (Button) findViewById(R.id.no7);
		no8 = (Button) findViewById(R.id.no8);
		no9 = (Button) findViewById(R.id.no9);
		clean = (Button) findViewById(R.id.clean);
		backspace = (Button) findViewById(R.id.backspace);
		per_cent = (Button) findViewById(R.id.per_cent);//%
		divide = (Button) findViewById(R.id.divide);///
		multiply = (Button) findViewById(R.id.multiply);//*
		sub = (Button) findViewById(R.id.sub);//-
		add = (Button) findViewById(R.id.add);//+
		bracket = (Button) findViewById(R.id.bracket);//()
		point = (Button) findViewById(R.id.point);//.
		equal = (Button) findViewById(R.id.equal);//=

		pai = (Button) findViewById(R.id.pi);
		sin = (Button) findViewById(R.id.sin);
		sqr = (Button) findViewById(R.id.sqrt);
		pow2 = (Button) findViewById(R.id.pow2);
		powy = (Button) findViewById(R.id.powy);
		cos = (Button) findViewById(R.id.cos);
		tan = (Button) findViewById(R.id.tan);
		ln = (Button) findViewById(R.id.ln);
		lg = (Button) findViewById(R.id.lg);
		e = (Button) findViewById(R.id.e);

		change.setOnClickListener(this);

		no0.setOnClickListener(this);
		no1.setOnClickListener(this);
		no2.setOnClickListener(this);
		no3.setOnClickListener(this);
		no4.setOnClickListener(this);
		no5.setOnClickListener(this);
		no6.setOnClickListener(this);
		no7.setOnClickListener(this);
		no8.setOnClickListener(this);
		no9.setOnClickListener(this);
		clean.setOnClickListener(this);
		backspace.setOnClickListener(this);
		per_cent.setOnClickListener(this);
		divide.setOnClickListener(this);
		multiply.setOnClickListener(this);
		sub.setOnClickListener(this);
		add.setOnClickListener(this);
		bracket.setOnClickListener(this);
		point.setOnClickListener(this);
		equal.setOnClickListener(this);

		pai.setOnClickListener(this);
		sin.setOnClickListener(this);
		sqr.setOnClickListener(this);
		pow2.setOnClickListener(this);
		powy.setOnClickListener(this);
		cos.setOnClickListener(this);
		tan.setOnClickListener(this);
		ln.setOnClickListener(this);
		lg.setOnClickListener(this);
		e.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//display_history.setMovementMethod(ScrollingMovementMethod.getInstance());//支持滑动
		switch(v.getId()) {
			case R.id.change:
				Intent intent = new Intent(ScientActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
				break;
			case R.id.no0:
				op.add_CE(0);
				break;
			case R.id.no1:
				op.add_CE(1);
				break;
			case R.id.no2:
				op.add_CE(2);
				break;
			case R.id.no3:
				op.add_CE(3);
				break;
			case R.id.no4:
				op.add_CE(4);
				break;
			case R.id.no5:
				op.add_CE(5);
				break;
			case R.id.no6:
				op.add_CE(6);
				break;
			case R.id.no7:
				op.add_CE(7);
				break;
			case R.id.no8:
				op.add_CE(8);
				break;
			case R.id.no9:
				op.add_CE(9);
				break;
			case R.id.clean:
				op.clean();
				break;
			case R.id.backspace:
				op.back();
				break;
			case R.id.per_cent:
				op.add_CE("%");
				break;
			case R.id.divide:
				op.add_CE("÷");
				break;
			case R.id.multiply:
				op.add_CE("×");
				break;
			case R.id.sub:
				op.add_CE("-");
				break;
			case R.id.add:
				op.add_CE("+");
				break;
			case R.id.bracket:
				op.add_CE("()");
				break;
			case R.id.point:
				op.add_CE(".");
				break;
			case R.id.equal:
				break;

			case R.id.pi:
				op.add_CE("π");
				break;
			case R.id.sin:
				op.add_CE("sin");
				break;
			case R.id.sqrt:
				op.add_CE("√");
				break;
			case R.id.pow2:
				op.add_CE("²");
				break;
			case R.id.powy:
				op.add_CE("^");
				break;
			case R.id.cos:
				op.add_CE("cos");
				break;
			case R.id.tan:
				op.add_CE("tan");
				break;
			case R.id.ln:
				op.add_CE("ln");
				break;
			case R.id.lg:
				op.add_CE("lg");
				break;
			case R.id.e:
				op.add_CE("e");
				break;
			default:
				break;
		}
		if(v.getId() == R.id.equal){
			String result = op.equal();
			if(!"".equals(result)) display.setText(result);
			else display.setText(op.to_string());
		} else display.setText(op.to_string());
		display_history.setText(op.to_string_history());
	}
}
