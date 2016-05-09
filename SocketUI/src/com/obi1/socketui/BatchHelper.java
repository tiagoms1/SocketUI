package com.obi1.socketui;

import java.util.HashMap;


public class BatchHelper {
	
	public static String generateOperacao() {
		double rnd = Math.random();
		long opp = Math.round(rnd * 2);
		return String.valueOf(opp);
	}

	public static String generateChave() {
		double rnd = Math.random();
		long chave = Math.round(rnd * 100);
		return String.valueOf(chave);
	}

	
	public static String generateNome() {
		if (names == null) initNames();
		double rnd = Math.random();
		long key = Math.round(rnd * 30);
		return names.get(key);
	}

	public static String generateValue() {
		long time = System.currentTimeMillis();
		return String.valueOf(time);
	}
	
	private static HashMap<Long, String> names;
	private static void initNames() {
		names = new HashMap<Long, String>();
		names.put(new Long(0), "Maria");
		names.put(new Long(1), "Bruna");
		names.put(new Long(2), "Andrea");
		names.put(new Long(3), "Fernanda");
		names.put(new Long(4), "Mara");
		names.put(new Long(5), "Sibele");
		names.put(new Long(6), "Flavia");
		names.put(new Long(7), "Beatriz");
		names.put(new Long(8), "Juliana");
		names.put(new Long(9), "Paula");
		names.put(new Long(10), "Keila");
		names.put(new Long(11), "Raquel");
		names.put(new Long(12), "Marcia");
		names.put(new Long(13), "Aline");
		names.put(new Long(14), "Jaqueline");
		names.put(new Long(15), "Sandra");
		names.put(new Long(16), "Amanda");
		names.put(new Long(17), "Rafaela");
		names.put(new Long(18), "Luana");
		names.put(new Long(19), "Tamires");
		names.put(new Long(20), "Claudia");
		names.put(new Long(21), "Patricia");
		names.put(new Long(22), "Cida");
		names.put(new Long(23), "Luiza");
		names.put(new Long(24), "Gabriela");
		names.put(new Long(25), "Roberta");
		names.put(new Long(26), "Lucia");
		names.put(new Long(27), "Sueli");
		names.put(new Long(28), "Carolina");
		names.put(new Long(29), "Damares");
		names.put(new Long(30), "Luciana");
	}
}
