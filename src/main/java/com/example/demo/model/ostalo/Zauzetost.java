package com.example.demo.model.ostalo;

import java.util.Date;

public interface Zauzetost extends Comparable<Zauzetost>{
	
	public Date pocetak();
	public int sati();
	public int minute();
	public Date kraj();

}
