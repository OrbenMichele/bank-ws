package com.morben.bank.ws.shared;

public class LimiteContaPorScore {

	public static Double getLimite(int score) {
		switch(score) {
			case 0:
				return 0.0;
			case 1:
				return 0.0;
			case 2:
				return 1000.0;
			case 3:
				return 1000.0;
			case 4:
				return 1000.0;
			case 5:
				return 1000.0;
			case 6:
				return 2000.0;
			case 7:
				return 2000.0;
			case 8:
				return 2000.0;
			case 9:
				return 5000.0;
			default:
				return 0.0;
		}
	}
	
}

