package application;

	//************ VG - KOMMENTAR, CONSTANT WITH FINAL *******************
	// I have choose to use a enum instead of a FINAL constant.  
	// It gives me more control of which values i have, in this case it is
	// restricted to FEMALE, MALE which make sense when i a cat only can have 
	// one sex. It also give me possibility to use in a very effective way 
    // with Switch case. 
	



enum Gender {
	
	FEMALE, MALE;

}
