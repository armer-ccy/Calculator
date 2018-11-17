package com.example.ccy.myapplication;

public class CalculatorElement {
	private String number;//数字
	private String sign;//符号
	private int priority;//优先级
	private boolean count;//是否计算过
	private double result;//结果

	public CalculatorElement() {}

	public CalculatorElement(String number) {
		CE_update(number);
	}

	public CalculatorElement(long number) {
		CE_update(number);
	}

	public CalculatorElement(String sign, int priority) {
		CE_update(sign, priority);
	}

	public void init() {
		this.number = "";
		this.sign = null;
		priority = 0;
		count = false;
		result = 0;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setNumber(long number) {
		this.number = number + "";
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result += result;
	}

	public void number_add(String number) {
		this.number = this.number + number;
	}

	public void number_add(long number) {
		this.number = this.number + number;
	}

	public void CE_update(String number) {
		this.number = number;
		this.sign = null;
		priority = 0;
		count = false;
		result = 0;
	}

	public void CE_update(long number) {
		this.number = number + "";
		this.sign = null;
		priority = 0;
		count = false;
		result = 0;
	}

	public void CE_update(String sign, int priority) {
		this.sign = sign;
		this.number = "";
		count = false;
		result = 0;
		switch(sign) {
			case "+":
				this.priority = priority + 1;
				break;
			case "-":
				this.priority = priority + 1;
				break;
			case "×":
				this.priority = priority + 2;
				break;
			case "÷":
				this.priority = priority + 2;
				break;
			case "%":
				this.priority = priority + 7;
				break;
			case "(":
				this.priority = priority + 10;
				break;
			case ")":
				this.priority = priority - 10;
				break;
			case "^":
				this.priority = priority + 4;
				break;
			case "√":
				this.priority = priority + 3;
				break;
			case ".":
				this.priority = priority + 9;
				break;
			case "π":
				this.priority = priority + 9;
				this.result=Math.PI;
				break;
			case "e":
				this.priority = priority + 9;
				this.result=Math.E;
				break;
			default:
				this.priority = priority + 7;
				break;
		}
	}
}
