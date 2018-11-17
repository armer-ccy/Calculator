package com.example.ccy.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Op {
	List expression = new ArrayList();
	List expression_history = new ArrayList();
	int Le = -1;

	public String to_string() {
		CalculatorElement ce = new CalculatorElement();
		String dis = new String();
		for(int i = 0; i <= Le; i++){
			ce = (CalculatorElement) expression.get(i);
			if(ce.getSign() == null)
				dis = dis + ce.getNumber();
			else
				dis = dis + ce.getSign();//+ce.isCount()+ce.getResult();
		}
		return dis;
	}

	public String to_string_history() {
		CalculatorElement ce = new CalculatorElement();
		String dis = new String();
		int l = expression_history.size() - 1;
		for(int i = 0; i <= l; i++){
			ce = (CalculatorElement) expression_history.get(i);
			if(ce.getSign() == null)
				dis = dis + ce.getNumber();
			else
				dis = dis + ce.getSign();
		}
		return dis;
	}

	public void add_CE(int no) {
		CalculatorElement ce = new CalculatorElement();
		Le = expression.size() - 1;
		if(Le == -1){
			CalculatorElement calculator_element = new CalculatorElement(no);
			expression.add(calculator_element);
			Le = expression.size() - 1;
		} else {
			ce = (CalculatorElement) expression.get(0);
			if(ce.isCount() && ce.getResult() == 0){
				clean();
				add_CE(no);
				return;
			}
			ce = (CalculatorElement) expression.get(Le);
			if(")".equals(ce.getSign()) || "%".equals(ce.getSign()) || "π".equals(ce.getSign()) || "e".equals(ce.getSign()))
				add_CE("×");
			if(ce.getSign() != null){
				CalculatorElement calculator_element = new CalculatorElement(no);
				expression.add(calculator_element);
				Le = expression.size() - 1;
			} else if(Le == 0){
				if("0".equals(ce.getNumber())){
					back();
					add_CE(no);
					return;
				} else {
					ce.number_add(no);
					return;
				}
			} else {
				CalculatorElement ce_last = (CalculatorElement) expression.get(Le - 1);
				if(!".".equals(ce_last.getSign()) && "0".equals(ce.getNumber())){
					back();
					add_CE(no);
					return;
				} else {
					ce.number_add(no);
					return;
				}
			}
		}
	}

	public void add_CE(long no) {
		CalculatorElement ce = new CalculatorElement();
		Le = expression.size() - 1;
		if(Le == -1){
			CalculatorElement calculator_element = new CalculatorElement(no);
			expression.add(calculator_element);
			Le = expression.size() - 1;
		} else {
			ce = (CalculatorElement) expression.get(Le);
			if(ce.getSign() != null){
				CalculatorElement calculator_element = new CalculatorElement(no);
				expression.add(calculator_element);
				Le = expression.size() - 1;
			} else ce.number_add(no);
		}
	}

	public void add_CE(String sign) {
		if("()".equals(sign)){
			CalculatorElement ce = new CalculatorElement();
			Le = expression.size() - 1;
			if(Le == -1)
				add_CE_start("(");
			else {
				ce = (CalculatorElement) expression.get(Le);
				if(ce.getSign() == null || "%".equals(ce.getSign()) || ")".equals(ce.getSign())
					   || "π".equals(ce.getSign()) || "e".equals(ce.getSign())){
					int priority = 0;
					int i = Le;
					while(i >= 0) {
						ce = (CalculatorElement) expression.get(i);
						if(ce.getPriority() != 99999 && ce.getPriority() != 0){
							priority = ce.getPriority();
							break;
						}
						i--;
					}
					if(priority >= 10)
						add_CE_end(")");
					else
						add_CE_start("(");
				} else
					add_CE_start("(");
			}
			return;
		}
		if("(".equals(sign) || "√".equals(sign) || "π".equals(sign) || "e".equals(sign)
			   || "sin".equals(sign) || "cos".equals(sign) || "tan".equals(sign)
			   || "ln".equals(sign) || "lg".equals(sign))
			add_CE_start(sign);
		else
			add_CE_end(sign);
	}

	public void add_CE_start(String sign) {
		CalculatorElement ce = new CalculatorElement();
		Le = expression.size() - 1;
		if(Le == -1){
			CalculatorElement calculator_element = new CalculatorElement(sign, 0);
			expression.add(calculator_element);
			Le = expression.size() - 1;
		} else {
			ce = (CalculatorElement) expression.get(0);
			if(ce.isCount() == true){
				clean();
				add_CE(sign);
				return;
			}
			ce = (CalculatorElement) expression.get(Le);
			if(!".".equals(ce.getSign())){
				if(!(ce.getSign() == null || ")".equals(ce.getSign()) || "%".equals(ce.getSign()) || "π".equals(ce.getSign())
					     || "e".equals(ce.getSign()))){
					int priority = 0;
					int i = Le;
					while(i >= 0) {
						ce = (CalculatorElement) expression.get(i);
						if(ce.getPriority() != 99999 && ce.getPriority() != 0){
							priority = ce.getPriority() / 10 * 10;
							break;
						}
						i--;
					}
					CalculatorElement calculator_element = new CalculatorElement(sign, priority);
					expression.add(calculator_element);
					Le = expression.size() - 1;
				} else {
					add_CE("×");
					add_CE(sign);
				}
			}
		}
		if(!"(".equals(sign) && !"√".equals(sign) && !"π".equals(sign) && !"e".equals(sign))
			add_CE("(");
	}

	public void add_CE_end(String sign) {
		CalculatorElement ce = new CalculatorElement();
		int priority = 0;
		Le = expression.size() - 1;
		if(Le != -1){
			ce = (CalculatorElement) expression.get(0);
			ce.setCount(false);
			int i = Le;
			while(i >= 0) {
				ce = (CalculatorElement) expression.get(i);
				if(ce.getSign() != null){
					priority = ce.getPriority() / 10 * 10;
					break;
				}
				i--;
			}
			if(".".equals(ce.getSign()) && ".".equals(sign)){} else {
				ce = (CalculatorElement) expression.get(Le);
				if("(".equals(ce.getSign()) && "-".equals(sign)){
					expression.add(new CalculatorElement(sign, priority + 6));
					Le = expression.size() - 1;
				} else if(ce.getSign() == null || "%".equals(ce.getSign()) || ")".equals(ce.getSign()) || "π".equals(ce.getSign()) || "e".equals(ce.getSign())){
					CalculatorElement calculator_element = new CalculatorElement(sign, priority);
					expression.add(calculator_element);
					Le = expression.size() - 1;
				} else if(".".equals(sign)){
					if("π".equals(ce.getSign()) || "e".equals(ce.getSign()))
						expression.add(new CalculatorElement("×", priority));
					expression.add(new CalculatorElement(0));
					expression.add(new CalculatorElement(".", priority));
					Le = expression.size() - 1;
				} else {
					back();
					add_CE(sign);
				}
			}
			if("^".equals(sign))
				add_CE("(");
		} else if("-".equals(sign)){
			expression.add(new CalculatorElement(sign, 6));
			Le = expression.size() - 1;
		} else if(".".equals(sign)){
			expression.add(new CalculatorElement(0));
			expression.add(new CalculatorElement(".", 0));
			Le = expression.size() - 1;
		}
	}

	public void clean() {
		Le = expression.size() - 1;
		if(Le > -1){
			expression.clear();
			Le = -1;
		} else {
			expression_history.clear();
		}
	}

	public void back() {
		CalculatorElement ce = new CalculatorElement();
		Le = expression.size() - 1;
		if(Le > -1){
			ce = (CalculatorElement) expression.get(0);
			ce.setCount(false);
			ce = (CalculatorElement) expression.get(Le);
			if(ce.getSign() == null){
				if(ce.getNumber().length() - 1 > 0)
					ce.setNumber(ce.getNumber().substring(0, ce.getNumber().length() - 1));
				else {
					expression.remove(Le);
					Le = expression.size() - 1;
				}
			} else if(Le > 0){
				ce = (CalculatorElement) expression.get(Le);
				CalculatorElement ce_front = (CalculatorElement) expression.get(Le - 1);
				if("(".equals(ce.getSign()) && ("sin".equals(ce_front.getSign()) || "cos".equals(ce_front.getSign()) ||
					                                "tan".equals(ce_front.getSign()) || "ln".equals(ce_front.getSign()) ||
					                                "lg".equals(ce_front.getSign()) || "^".equals(ce_front.getSign()))){
					expression.remove(Le);
					expression.remove(Le - 1);
					Le = expression.size() - 2;
				} else {
					expression.remove(Le);
					Le = expression.size() - 1;
				}
			} else {
				expression.remove(Le);
				Le = expression.size() - 1;
			}
		}
	}

	public String equal() {

		if(Le > 0){
			CalculatorElement ce = new CalculatorElement();
			CalculatorElement ce_realy = new CalculatorElement();
			CalculatorElement ce_front = new CalculatorElement();
			CalculatorElement ce_back = new CalculatorElement();
			ce = (CalculatorElement) expression.get(0);
			if(ce.isCount() == false){
				history();
				ce = (CalculatorElement) expression.get(Le);
				while(!(ce.getSign() == null || ")".equals(ce.getSign()) || "%".equals(ce.getSign())
					        || "π".equals(ce.getSign()) || "e".equals(ce.getSign()))) {
					Le--;
					return equal();
				}
				if(Le == -1) return "error";

				int bj = 0;
				int i = Le;
				int i_min = -1;
				int i_max = -1;
				int i_max_front = -1;
				int i_max_back = -1;
				int priority;
				int priority_min = 99999;
				int priority_max = 0;
				int priority_max_front = 0;
				int priority_max_back = 0;

				while(i >= 2) {
					ce = (CalculatorElement) expression.get(i);
					ce_front = (CalculatorElement) expression.get(i - 2);
					if("(".equals(ce_front.getSign()) && ")".equals(ce.getSign())){
						ce_front = (CalculatorElement) expression.get(i - 1);
						ce_front.setCount(true);
						ce_front.setPriority(ce.getPriority() / 10 * 10 + 11);
						ce_front.setResult(Integer.parseInt(ce_front.getNumber()));
					}
					i--;
				}

				ce = (CalculatorElement) expression.get(Le - 1);
				if("(".equals(ce.getSign())){
					ce_front = (CalculatorElement) expression.get(Le);
					ce_front.setCount(true);
					ce_front.setPriority(ce.getPriority() / 10 * 10 + 1);
					ce_front.setResult(Integer.parseInt(ce_front.getNumber()));
				}

				i = Le;
				while(i >= 0) {
					ce = (CalculatorElement) expression.get(i);
					priority = ce.getPriority();
					if(priority % 10 > 0 && priority < priority_min){
						priority_min = priority;
						i_min = i;
					}
					i--;
				}
				while(bj != 1) {
					bj = 1;
					i = 0;
					priority_max = 0;
					i_max = -1;
					while(i <= Le) {
						ce = (CalculatorElement) expression.get(i);
						priority = ce.getPriority();
						if(priority % 10 > 0 && !ce.isCount() && priority > priority_max){
							priority_max = priority;
							i_max = i;
							bj = 0;
						}
						i++;
					}
					if(bj == 0){
						ce_realy = (CalculatorElement) expression.get(i_max);
						i = i_max - 1;
						i_max_front = i;
						priority_max_front = 99999;
						while(i >= 0) {
							ce = (CalculatorElement) expression.get(i);
							priority = ce.getPriority();
							if(priority % 10 > 0 && ce.isCount() && priority >= priority_max && priority <=
								                                                                    priority_max_front){
								priority_max_front = priority;
								i_max_front = i;
							} else if(priority % 10 > 0 && !ce.isCount()){
								//i_max_front = i;
								break;
							}
							if(priority % 10 > 0 && ce.isCount() && priority_max_front == priority_max)
								break;
							i--;
						}
						if(i_max_front >= 0) ce_front = (CalculatorElement) expression.get(i_max_front);
						i = i_max + 1;
						i_max_back = i;
						priority_max_back = 99999;
						while(i <= Le) {
							ce = (CalculatorElement) expression.get(i);
							priority = ce.getPriority();
							if(priority % 10 > 0 && ce.isCount() && priority >= priority_max && priority <=
								                                                                    priority_max_back){
								priority_max_back = priority;
								i_max_back = i;
							} else if(priority % 10 > 0 && !ce.isCount()){
								//i_max_back = i;
								break;
							}
							i++;
						}
						if(i_max_back <= Le) ce_back = (CalculatorElement) expression.get(i_max_back);
						double result = 0;
						switch(ce_realy.getSign()) {
							case "+":
								result = (ce_front.getSign() == null ? Integer.parseInt(ce_front.getNumber()) : ce_front
									                                                                                .getResult
										                                                                                 ()) +
									         (ce_back.getSign() == null ? Integer.parseInt(ce_back
										                                                       .getNumber()) : ce_back
											                                                                       .getResult
												                                                                        ());
								break;
							case "-":
								if(ce_realy.getPriority() % 10 == 1)
									result = (ce_front.getSign() == null ? Integer.parseInt(ce_front.getNumber()) :
										          ce_front
											          .getResult
												           ()) -
										         (ce_back
											          .getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back
												                                                                       .getResult
													                                                                        ());
								else
									result = -(ce_back.getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back
										                                                                               .getResult
											                                                                                ());
								break;
							case "×":
								result = (ce_front.getSign() == null ? Integer.parseInt(ce_front.getNumber()) : ce_front
									                                                                                .getResult
										                                                                                 ()) *
									         (ce_back.getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back
										                                                                              .getResult
											                                                                               ());
								break;
							case "÷":
								result = (ce_front.getSign() == null ? Integer.parseInt(ce_front.getNumber()) : ce_front
									                                                                                .getResult
										                                                                                 ()) /
									         (ce_back
										          .getSign()
										          ==
										          null ? Integer.parseInt(ce_back.getNumber()) : ce_back.getResult());
								break;
							case "%":
								result = (ce_front.getSign() == null ? Integer.parseInt(ce_front.getNumber()) : ce_front
									                                                                                .getResult
										                                                                                 ()) /
									         100;
								break;
							case "√":
								result = ce_back.getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back
									                                                                             .getResult();
								if(result >= 0)
									result = Math.sqrt(result);
								else {
									return "error222!";
								}
								break;
							case ".":
								result = Integer.parseInt(ce_front.getNumber()) + Double.parseDouble("0." + ce_back
									                                                                            .getNumber
										                                                                             ());
								break;
							case "²":
								result = Math.pow((ce_front.getSign() == null ? Integer.parseInt(ce_front.getNumber()) :
									                   ce_front
										                   .getResult()), 2);
								break;
							case "^":
								result = Math.pow((ce_front.getSign() == null ? Integer.parseInt(ce_front.getNumber()) :
									                   ce_front
										                   .getResult()),
									(ce_back.getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back.getResult
										                                                                             ()));
								break;
							case "sin":
								result = ce_back.getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back
									                                                                             .getResult();
								result = Math.sin(result);
								break;
							case "cos":
								result = ce_back.getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back
									                                                                             .getResult();
								result = Math.cos(result);
								break;
							case "tan":
								result = ce_back.getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back
									                                                                             .getResult();
								if(result >= 0)
									result = Math.sqrt(result);
								else {
									return "error111!";
								}
								break;
							case "ln":
								result = ce_back.getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back
									                                                                             .getResult();
								if(result > 0)
									result = Math.log10(result) / Math.log10(Math.E);
								else {
									return "error963!";
								}
								break;
							case "lg":
								result = ce_back.getSign() == null ? Integer.parseInt(ce_back.getNumber()) : ce_back
									                                                                             .getResult();
								if(result > 0)
									result = Math.log10(result);
								else {
									return "error789!";
								}
								break;
							default:
								break;
						}
						ce_realy.setCount(true);
						ce_realy.setResult(result);
					}
					//bj = 1;
				}
				ce = (CalculatorElement) expression.get(i_min);
				double result = ce.getResult();
				history(result);
				return next(result);
				//return "";
			}
		} else if(Le == 0){
			CalculatorElement ce = new CalculatorElement();
			ce = (CalculatorElement) expression.get(0);
			if(ce.isCount() == false){
				if(ce.getSign() == null){
					history();
					long result = Integer.parseInt(ce.getNumber());
					next(result);
					return String.valueOf(result);
				} else if("π".equals(ce.getSign()) || "e".equals(ce.getSign())){
					history();
					double result = ce.getResult();
					next(result);
					return String.valueOf(result);
				} else {
					clean();
					return String.valueOf("error456!");
				}
			}
		}
		return "";
	}

	public void history() {
		CalculatorElement ce = new CalculatorElement();
		for(int i = 0; i <= Le; i++){
			ce = (CalculatorElement) expression.get(i);
			expression_history.add(ce);
		}
		expression_history.add(new CalculatorElement("\n", -10));
	}

	public void history(double result) {
		CalculatorElement ce = new CalculatorElement();
		long result_long = (long) result;
		double result_double = result;
		if(result_double < 0){
			expression_history.add(new CalculatorElement("-", 0));
			result_double = Math.abs(result_double);
		}
		expression_history.add(new CalculatorElement(Math.abs(result_long)));
		if(result != result_long){
			expression_history.add(new CalculatorElement(".", 0));
			String result_string = String.valueOf(result_double);
			expression_history.add(new CalculatorElement(result_string.substring(result_string.indexOf(".") + 1,
				result_string
					.length()
			)));
		}
		expression_history.add(new CalculatorElement("\n", -10));
	}

	public String next(double result) {
		CalculatorElement ce = new CalculatorElement();
		long result_long = (long) result;
		clean();
		//return String.valueOf(result==result_long?result_long:result);
		double result_double = result;
		if(result_double < 0){
			add_CE("-");
			result_double = Math.abs(result_double);
		}
		add_CE(Math.abs(result_long));
		if(result != result_long){
			add_CE(".");
			String result_string = String.valueOf(result_double);
			expression.add(new CalculatorElement(result_string.substring(result_string.indexOf(".") + 1, result_string
				                                                                                             .length())));
		}
		Le = expression.size() - 1;
		ce = (CalculatorElement) expression.get(0);
		ce.setCount(true);
		return result == result_long ? String.valueOf(result_long) : String.valueOf(result);
	}
}
