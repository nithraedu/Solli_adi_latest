package nit_app;

/**
 * 
 * @author மயூரேசன்
 * @குறிப்பு யுனிக்கோட் முறையில் இருக்கும் சொற்கள் வசனங்களை பாமினி, திஸ்கி, டாப், டாம் போன்ற 
 * குறிமுறைகளுக்கு மாற்றியமைக்க இந்த செயலி உங்களுக்கு உதவும்
 *
 */
public class CodetoTamilUtil {
	/**
	 * பாமினி முறையில் அமைந்த குறியேற்றம்
	 */
	public final static int BAMINI = 0;
	

	public final static int TAM = 1;
	
	 
	 
 
	 
 
	
	/**
	 * இந்த method இன் மூலம் நீங்கள் யுனிக்கோட் குறிமுறையில் இருந்து உங்களுக்குத் 
	 * தேவையான குறிமுறைக்கு சொற்கள், வசனங்களை மாற்றிக் கொள்ளலாம்.
	 * @param encodCode - எந்தக் குறிமுறைக்கு மாற்ற விரும்புகின்றீர்கள் என்பதை இங்கே குறிப்பிட வேண்டும்.
	 * உதாரணமாக பாமினி முறை என்றால் TamilUtil.BAMINI என்றோ அல்லது திஸ்கி முறைமை என்றால் TamilUtil.TSCII
	 * என்றோ குறிப்பிடலாம்.
	 * 
	 * @param unicodeStr யுனிக்கோட் சொற்கள் வசனங்கள்
	 * @return யுனிக்கோடில் இருந்து நீங்கள் குறிப்பிட்ட குறிமுறைக்கு மாற்றப்பட்ட சொற்கள், வசனங்கள் உங்களுக்கு வந்து கிடைக்கும்.
	 */
	public static String convertToTamil(int encodCode, String unicodeStr){
		String convertedStr = "";
		CodetoTamilUtil tu = new CodetoTamilUtil();
		if(encodCode== CodetoTamilUtil.BAMINI){
			convertedStr = tu.convertToBamini(unicodeStr);
		}
		else if(encodCode==CodetoTamilUtil.TAM){
			convertedStr = tu.convertToTam(unicodeStr);
		}
 		 
		return convertedStr;
	}
	 
	
	 
	 
	private String convertToBamini(String unicodeStr){
		
		unicodeStr = unicodeStr.replace("=","ஸ்ரீ");
		
		unicodeStr = unicodeStr.replace("n[s","ஜௌ");
		unicodeStr = unicodeStr.replace("N[h","ஜோ");
		unicodeStr = unicodeStr.replace("n[h","ஜொ");
		unicodeStr = unicodeStr.replace("[h","ஜா");
		unicodeStr = unicodeStr.replace("[p","ஜி");
		unicodeStr = unicodeStr.replace("[P","ஜீ");
		unicodeStr = unicodeStr.replace("[{","ஜு");
		unicodeStr = unicodeStr.replace("[_","ஜூ");
		unicodeStr = unicodeStr.replace("n[","ஜெ");
		unicodeStr = unicodeStr.replace("N[","ஜே");
		unicodeStr = unicodeStr.replace("i[","ஜை");
		unicodeStr = unicodeStr.replace("[;","ஜ்");
		unicodeStr = unicodeStr.replace("[","ஜ");
		unicodeStr = unicodeStr.replace("nfs","கௌ");
		unicodeStr = unicodeStr.replace("Nfh","கோ");
		unicodeStr = unicodeStr.replace("nfh","கொ");
		unicodeStr = unicodeStr.replace("fh","கா");
		unicodeStr = unicodeStr.replace("fp","கி");
		unicodeStr = unicodeStr.replace("fP","கீ");
		unicodeStr = unicodeStr.replace("F","கு");
		unicodeStr = unicodeStr.replace("$","கூ");
		unicodeStr = unicodeStr.replace("nf","கெ");
		unicodeStr = unicodeStr.replace("Nf","கே");
		unicodeStr = unicodeStr.replace("if","கை");
		unicodeStr = unicodeStr.replace("f;","க்");
		unicodeStr = unicodeStr.replace("f","க");
		unicodeStr = unicodeStr.replace("nqs","ஙௌ");
		unicodeStr = unicodeStr.replace("Nqh","ஙோ");
		unicodeStr = unicodeStr.replace("nqh","ஙொ");
		unicodeStr = unicodeStr.replace("qh","ஙா");
		unicodeStr = unicodeStr.replace("qp","ஙி");
		unicodeStr = unicodeStr.replace("qP","ஙீ");
		unicodeStr = unicodeStr.replace("qு","ஙு");
		unicodeStr = unicodeStr.replace("q+","ஙூ");
		unicodeStr = unicodeStr.replace("nq","ஙெ");
		unicodeStr = unicodeStr.replace("Nq","ஙே");
		unicodeStr = unicodeStr.replace("iq","ஙை");
		unicodeStr = unicodeStr.replace("q;","ங்");
		unicodeStr = unicodeStr.replace("q","ங");
		unicodeStr = unicodeStr.replace("nrs","சௌ");
		unicodeStr = unicodeStr.replace("Nrh","சோ");
		unicodeStr = unicodeStr.replace("nrh","சொ");
		unicodeStr = unicodeStr.replace("rh","சா");
		unicodeStr = unicodeStr.replace("rp","சி");
		unicodeStr = unicodeStr.replace("rP","சீ");
		unicodeStr = unicodeStr.replace("R","சு");
		unicodeStr = unicodeStr.replace("#","சூ"); 
		unicodeStr = unicodeStr.replace("nr","செ");
		unicodeStr = unicodeStr.replace("Nr","சே");
		unicodeStr = unicodeStr.replace("ir","சை");
		unicodeStr = unicodeStr.replace("r;","ச்");
		unicodeStr = unicodeStr.replace("r","ச");
		unicodeStr = unicodeStr.replace("nQs","ஞௌ");
		unicodeStr = unicodeStr.replace("NQh","ஞோ");
		unicodeStr = unicodeStr.replace("nQh","ஞொ");
		unicodeStr = unicodeStr.replace("Qh","ஞா");
		unicodeStr = unicodeStr.replace("Qp","ஞி");
		unicodeStr = unicodeStr.replace("QP","ஞீ");
		unicodeStr = unicodeStr.replace("Qு","ஞு");
		unicodeStr = unicodeStr.replace("Qூ","ஞூ");
		unicodeStr = unicodeStr.replace("nQ","ஞெ");
		unicodeStr = unicodeStr.replace("NQ","ஞே");
		unicodeStr = unicodeStr.replace("iQ","ஞை");
		unicodeStr = unicodeStr.replace("Q;","ஞ்");
		unicodeStr = unicodeStr.replace("Q","ஞ");
		unicodeStr = unicodeStr.replace("nls","டௌ");
		unicodeStr = unicodeStr.replace("Nlh","டோ");
		unicodeStr = unicodeStr.replace("nlh","டொ");
		unicodeStr = unicodeStr.replace("lh","டா");
		unicodeStr = unicodeStr.replace("b","டி");
		unicodeStr = unicodeStr.replace("B","டீ");
		unicodeStr = unicodeStr.replace("L","டு");
		unicodeStr = unicodeStr.replace("^","டூ");
		unicodeStr = unicodeStr.replace("nl","டெ");
		unicodeStr = unicodeStr.replace("Nl","டே");
		unicodeStr = unicodeStr.replace("il","டை");
		unicodeStr = unicodeStr.replace("l;","ட்");
		unicodeStr = unicodeStr.replace("l","ட");
		unicodeStr = unicodeStr.replace("nzs","ணௌ");
		unicodeStr = unicodeStr.replace("Nzh","ணோ");
		unicodeStr = unicodeStr.replace("nzh","ணொ");
		unicodeStr = unicodeStr.replace("zh","ணா");
		unicodeStr = unicodeStr.replace("zp","ணி");
		unicodeStr = unicodeStr.replace("zP","ணீ");
		unicodeStr = unicodeStr.replace("Z","ணு");
		unicodeStr = unicodeStr.replace("Z}","ணூ");
		unicodeStr = unicodeStr.replace("nz","ணெ");
		unicodeStr = unicodeStr.replace("Nz","ணே");
		unicodeStr = unicodeStr.replace("iz","ணை");
		unicodeStr = unicodeStr.replace("z;","ண்");
		unicodeStr = unicodeStr.replace("z","ண");
		unicodeStr = unicodeStr.replace("njs","தௌ");
		unicodeStr = unicodeStr.replace("Njh","தோ");
		unicodeStr = unicodeStr.replace("njh","தொ");
		unicodeStr = unicodeStr.replace("jh","தா");
		unicodeStr = unicodeStr.replace("jp","தி");
		unicodeStr = unicodeStr.replace("jP","தீ");
		unicodeStr = unicodeStr.replace("J}","தூ");
		unicodeStr = unicodeStr.replace("J","து");
		unicodeStr = unicodeStr.replace("nj","தெ");
		unicodeStr = unicodeStr.replace("Nj","தே");
		unicodeStr = unicodeStr.replace("ij","தை");
		unicodeStr = unicodeStr.replace("j;","த்");
		unicodeStr = unicodeStr.replace("j","த");
		unicodeStr = unicodeStr.replace("nes","நௌ");
		unicodeStr = unicodeStr.replace("Neh","நோ");
		unicodeStr = unicodeStr.replace("neh","நொ");
		unicodeStr = unicodeStr.replace("eh","நா");
		unicodeStr = unicodeStr.replace("ep","நி");
		unicodeStr = unicodeStr.replace("eP","நீ");
		unicodeStr = unicodeStr.replace("E","நு");
		unicodeStr = unicodeStr.replace("E}","நூ");
		unicodeStr = unicodeStr.replace("ne","நெ");
		unicodeStr = unicodeStr.replace("Ne","நே");
		unicodeStr = unicodeStr.replace("ie","நை");
		unicodeStr = unicodeStr.replace("e;","ந்");
		unicodeStr = unicodeStr.replace("e","ந");
		unicodeStr = unicodeStr.replace("nds","னௌ");
		unicodeStr = unicodeStr.replace("Ndh","னோ");
		unicodeStr = unicodeStr.replace("ndh","னொ");
		unicodeStr = unicodeStr.replace("dh","னா");
		unicodeStr = unicodeStr.replace("dp","னி");
		unicodeStr = unicodeStr.replace("dP","னீ");
		unicodeStr = unicodeStr.replace("D","னு");
		unicodeStr = unicodeStr.replace("D}","னூ");
		unicodeStr = unicodeStr.replace("nd","னெ");
		unicodeStr = unicodeStr.replace("Nd","னே");
		unicodeStr = unicodeStr.replace("id","னை");
		unicodeStr = unicodeStr.replace("d;","ன்");
		unicodeStr = unicodeStr.replace("d","ன");
		unicodeStr = unicodeStr.replace("ngs","பௌ");
		unicodeStr = unicodeStr.replace("Ngh","போ");
		unicodeStr = unicodeStr.replace("ngh","பொ");
		unicodeStr = unicodeStr.replace("gh","பா");
		unicodeStr = unicodeStr.replace("gp","பி");
		unicodeStr = unicodeStr.replace("gP","பீ");
		unicodeStr = unicodeStr.replace("G","பு");
		unicodeStr = unicodeStr.replace("g+","பூ"); 
		unicodeStr = unicodeStr.replace("ng","பெ");
		unicodeStr = unicodeStr.replace("Ng","பே");
		unicodeStr = unicodeStr.replace("ig","பை");
		unicodeStr = unicodeStr.replace("g;","ப்");
		unicodeStr = unicodeStr.replace("g","ப");
		unicodeStr = unicodeStr.replace("nks","மௌ");
		unicodeStr = unicodeStr.replace("Nkh","மோ");
		unicodeStr = unicodeStr.replace("nkh","மொ");
		unicodeStr = unicodeStr.replace("kh","மா");
		unicodeStr = unicodeStr.replace("kp","மி");
		unicodeStr = unicodeStr.replace("kP","மீ");
		unicodeStr = unicodeStr.replace("K","மு");
		unicodeStr = unicodeStr.replace("%","மூ");
		unicodeStr = unicodeStr.replace("nk","மெ");
		unicodeStr = unicodeStr.replace("Nk","மே");
		unicodeStr = unicodeStr.replace("ik","மை");
		unicodeStr = unicodeStr.replace("k;","ம்");
		unicodeStr = unicodeStr.replace("k","ம");
		unicodeStr = unicodeStr.replace("nas","யௌ");
		unicodeStr = unicodeStr.replace("Nah","யோ");
		unicodeStr = unicodeStr.replace("nah","யொ");
		unicodeStr = unicodeStr.replace("ah","யா");
		unicodeStr = unicodeStr.replace("ap","யி");
		unicodeStr = unicodeStr.replace("aP","யீ");
		unicodeStr = unicodeStr.replace("A","யு");
		unicodeStr = unicodeStr.replace("a+","யூ");
		unicodeStr = unicodeStr.replace("na","யெ");
		unicodeStr = unicodeStr.replace("Na","யே");
		unicodeStr = unicodeStr.replace("ia","யை");
		unicodeStr = unicodeStr.replace("a;","ய்");
		unicodeStr = unicodeStr.replace("a","ய");
		unicodeStr = unicodeStr.replace("nus","ரௌ");
		unicodeStr = unicodeStr.replace("Nuh","ரோ");
		unicodeStr = unicodeStr.replace("nuh","ரொ");
		unicodeStr = unicodeStr.replace("uh","ரா");
		unicodeStr = unicodeStr.replace("up","ரி");
		unicodeStr = unicodeStr.replace("uP","ரீ");
		unicodeStr = unicodeStr.replace("U","ரு");
		unicodeStr = unicodeStr.replace("&","ரூ"); 
		unicodeStr = unicodeStr.replace("nu","ரெ");
		unicodeStr = unicodeStr.replace("Nu","ரே");
		unicodeStr = unicodeStr.replace("iu","ரை");
		unicodeStr = unicodeStr.replace("H","ர்");
		unicodeStr = unicodeStr.replace("u","ர");
		unicodeStr = unicodeStr.replace("nys","லௌ");
		unicodeStr = unicodeStr.replace("Nyh","லோ");
		unicodeStr = unicodeStr.replace("nyh","லொ");
		unicodeStr = unicodeStr.replace("yh","லா");
		unicodeStr = unicodeStr.replace("yp","லி");
		unicodeStr = unicodeStr.replace("yP","லீ");
		unicodeStr = unicodeStr.replace("Y","லு");
		unicodeStr = unicodeStr.replace("Y}","லூ");
		unicodeStr = unicodeStr.replace("ny","லெ");
		unicodeStr = unicodeStr.replace("Ny","லே");
		unicodeStr = unicodeStr.replace("iy","லை");
		unicodeStr = unicodeStr.replace("y;","ல்");
		unicodeStr = unicodeStr.replace("y","ல");
		unicodeStr = unicodeStr.replace("nss","ளௌ");
		unicodeStr = unicodeStr.replace("Nsh","ளோ");
		unicodeStr = unicodeStr.replace("nsh","ளொ");
		unicodeStr = unicodeStr.replace("sh","ளா");
		unicodeStr = unicodeStr.replace("sp","ளி");
		unicodeStr = unicodeStr.replace("sP","ளீ");
		unicodeStr = unicodeStr.replace("S","ளு");
		unicodeStr = unicodeStr.replace("S+","ளூ"); 
		unicodeStr = unicodeStr.replace("ns","ளெ");
		unicodeStr = unicodeStr.replace("Ns","ளே");
		unicodeStr = unicodeStr.replace("is","ளை");
		unicodeStr = unicodeStr.replace("s;","ள்");
		unicodeStr = unicodeStr.replace("s","ள");
		unicodeStr = unicodeStr.replace("nts","வௌ");
		unicodeStr = unicodeStr.replace("Nth","வோ");
		unicodeStr = unicodeStr.replace("nth","வொ");
		unicodeStr = unicodeStr.replace("th","வா");
		unicodeStr = unicodeStr.replace("tp","வி");
		unicodeStr = unicodeStr.replace("tP","வீ");
		unicodeStr = unicodeStr.replace("T","வு");
		unicodeStr = unicodeStr.replace("t+","வூ");
		unicodeStr = unicodeStr.replace("nt","வெ");
		unicodeStr = unicodeStr.replace("Nt","வே");
		unicodeStr = unicodeStr.replace("it","வை");
		unicodeStr = unicodeStr.replace("t;","வ்");
		unicodeStr = unicodeStr.replace("t","வ");
		unicodeStr = unicodeStr.replace("nos","ழௌ");
		unicodeStr = unicodeStr.replace("Noh","ழோ");
		unicodeStr = unicodeStr.replace("noh","ழொ");
		unicodeStr = unicodeStr.replace("oh","ழா");
		unicodeStr = unicodeStr.replace("op","ழி");
		unicodeStr = unicodeStr.replace("oP","ழீ");
		unicodeStr = unicodeStr.replace("O","ழு");
		unicodeStr = unicodeStr.replace("*","ழூ");
		unicodeStr = unicodeStr.replace("no","ழெ");
		unicodeStr = unicodeStr.replace("No","ழே");
		unicodeStr = unicodeStr.replace("io","ழை");
		unicodeStr = unicodeStr.replace("o;","ழ்");
		unicodeStr = unicodeStr.replace("o","ழ");
		unicodeStr = unicodeStr.replace("nws","றௌ");
		unicodeStr = unicodeStr.replace("Nwh","றோ");
		unicodeStr = unicodeStr.replace("nwh","றொ");
		unicodeStr = unicodeStr.replace("wh","றா");
		unicodeStr = unicodeStr.replace("wp","றி");
		unicodeStr = unicodeStr.replace("wP","றீ");
		unicodeStr = unicodeStr.replace("W","று");
		unicodeStr = unicodeStr.replace("W}","றூ");
		unicodeStr = unicodeStr.replace("nw","றெ");
		unicodeStr = unicodeStr.replace("Nw","றே");
		unicodeStr = unicodeStr.replace("iw","றை");
		unicodeStr = unicodeStr.replace("w;","ற்");
		unicodeStr = unicodeStr.replace("w","ற");
		unicodeStr = unicodeStr.replace("n`s","ஹௌ");
		unicodeStr = unicodeStr.replace("N`h","ஹோ");
		unicodeStr = unicodeStr.replace("n`h","ஹொ");
		unicodeStr = unicodeStr.replace("`p","ஹி");
		unicodeStr = unicodeStr.replace("`P","ஹீ");
		unicodeStr = unicodeStr.replace("`{","ஹு");
		unicodeStr = unicodeStr.replace("`_","ஹூ");
		unicodeStr = unicodeStr.replace("n`","ஹெ");
		unicodeStr = unicodeStr.replace("N`","ஹே");
		unicodeStr = unicodeStr.replace("i`","ஹை");
		unicodeStr = unicodeStr.replace("`;","ஹ்");
		unicodeStr = unicodeStr.replace("`","ஹ");
		unicodeStr = unicodeStr.replace("`h","ஹா");
		unicodeStr = unicodeStr.replace("n~s","ஷௌ");
		unicodeStr = unicodeStr.replace("N~h","ஷோ");
		unicodeStr = unicodeStr.replace("n~h","ஷொ");
		unicodeStr = unicodeStr.replace("~h","ஷா");
		unicodeStr = unicodeStr.replace("~p","ஷி");
		unicodeStr = unicodeStr.replace("~P","ஷீ");
		unicodeStr = unicodeStr.replace("~{","ஷு");
		unicodeStr = unicodeStr.replace("~_","ஷூ");
		unicodeStr = unicodeStr.replace("n~","ஷெ");
		unicodeStr = unicodeStr.replace("N~","ஷே");
		unicodeStr = unicodeStr.replace("i~","ஷை");
		unicodeStr = unicodeStr.replace("~;","ஷ்");
		unicodeStr = unicodeStr.replace("~","ஷ");
		unicodeStr = unicodeStr.replace("n]s","ஸௌ");
		unicodeStr = unicodeStr.replace("N]h","ஸோ");
		unicodeStr = unicodeStr.replace("n]h","ஸொ");
		unicodeStr = unicodeStr.replace("]h","ஸா");
		unicodeStr = unicodeStr.replace("]p","ஸி");
		unicodeStr = unicodeStr.replace("]P","ஸீ");
		unicodeStr = unicodeStr.replace("]{","ஸு");
		unicodeStr = unicodeStr.replace("]_","ஸூ");
		unicodeStr = unicodeStr.replace("n]","ஸெ");
		unicodeStr = unicodeStr.replace("N]","ஸே");
		unicodeStr = unicodeStr.replace("i]","ஸை");
		unicodeStr = unicodeStr.replace("];","ஸ்");
		unicodeStr = unicodeStr.replace("]","ஸ");
		unicodeStr = unicodeStr.replace("m","அ");
		unicodeStr = unicodeStr.replace("M","ஆ");
		unicodeStr = unicodeStr.replace(",","இ");
		unicodeStr = unicodeStr.replace(">",",");
		unicodeStr = unicodeStr.replace("<","ஈ");
		unicodeStr = unicodeStr.replace("c","உ");
		unicodeStr = unicodeStr.replace("C","ஊ");
		unicodeStr = unicodeStr.replace("v","எ");
		unicodeStr = unicodeStr.replace("V","ஏ");
		unicodeStr = unicodeStr.replace("I","ஐ");
		unicodeStr = unicodeStr.replace("x","ஒ");
		unicodeStr = unicodeStr.replace("X","ஓ");
		unicodeStr = unicodeStr.replace("xs","ஔ");
		unicodeStr = unicodeStr.replace("/","ஃ");

		
		return unicodeStr;
	}
	
	
	
	private String convertToTam(String unicodeStr){/*
		
		unicodeStr = unicodeStr.replace("ÿ","=");
		unicodeStr = unicodeStr.replace(",",",");
		unicodeStr = unicodeStr.replace("ªü÷","n[s");
		unicodeStr = unicodeStr.replace("«ü£","N[h");
		unicodeStr = unicodeStr.replace("ªü£","n[h");
		unicodeStr = unicodeStr.replace("ü£","[h");
		unicodeStr = unicodeStr.replace("T","[p");
		unicodeStr = unicodeStr.replace("p","[P");
		unicodeStr = unicodeStr.replace("ü§","[{");
		unicodeStr = unicodeStr.replace("ü¨","[_");
		unicodeStr = unicodeStr.replace("ªü","n[");
		unicodeStr = unicodeStr.replace("«ü","N[");
		unicodeStr = unicodeStr.replace("¬ü","i[");
		unicodeStr = unicodeStr.replace("x","[;");
		unicodeStr = unicodeStr.replace("ü","[");
		unicodeStr = unicodeStr.replace("ªè÷","nfs");
		unicodeStr = unicodeStr.replace("«è£","Nfh");
		unicodeStr = unicodeStr.replace("ªè£","nfh");
		unicodeStr = unicodeStr.replace("è£","fh");
		unicodeStr = unicodeStr.replace("A","fp");
		unicodeStr = unicodeStr.replace("W","fP");
		unicodeStr = unicodeStr.replace("°","F");
		unicodeStr = unicodeStr.replace("Ã","$");
		unicodeStr = unicodeStr.replace("ªè","nf");
		unicodeStr = unicodeStr.replace("«è","Nf");
		unicodeStr = unicodeStr.replace("¬è","if");
		unicodeStr = unicodeStr.replace("‚","f;");
		unicodeStr = unicodeStr.replace("è","f");
		unicodeStr = unicodeStr.replace("ªé÷","nqs");
		unicodeStr = unicodeStr.replace("«é£","Nqh");
		unicodeStr = unicodeStr.replace("ªé£","nqh");
		unicodeStr = unicodeStr.replace("é£","qh");
		unicodeStr = unicodeStr.replace("B","qp");
		unicodeStr = unicodeStr.replace("X","qP");
		unicodeStr = unicodeStr.replace("±","qு");
		unicodeStr = unicodeStr.replace("Ä","q+");
		unicodeStr = unicodeStr.replace("ªé","nq");
		unicodeStr = unicodeStr.replace("«é","Nq");
		unicodeStr = unicodeStr.replace("¬é","iq");
		unicodeStr = unicodeStr.replace("ƒ","q;");
		unicodeStr = unicodeStr.replace("é","q");
		unicodeStr = unicodeStr.replace("ªê÷","nrs");
		unicodeStr = unicodeStr.replace("«ê£","Nrh");
		unicodeStr = unicodeStr.replace("ªê£","nrh");
		unicodeStr = unicodeStr.replace("ê£","rh");
		unicodeStr = unicodeStr.replace("C","rp");
		unicodeStr = unicodeStr.replace("Y","rP");
		unicodeStr = unicodeStr.replace("²","R");
		unicodeStr = unicodeStr.replace("Å","#"); 
		unicodeStr = unicodeStr.replace("ªê","nr");
		unicodeStr = unicodeStr.replace("«ê","Nr");
		unicodeStr = unicodeStr.replace("¬ê","ir");
		unicodeStr = unicodeStr.replace("„","r;");
		unicodeStr = unicodeStr.replace("ê","r");
		unicodeStr = unicodeStr.replace("ªë÷","nQs");
		unicodeStr = unicodeStr.replace("«ë£","NQh");
		unicodeStr = unicodeStr.replace("ªë£","nQh");
		unicodeStr = unicodeStr.replace("ë£","Qh");
		unicodeStr = unicodeStr.replace("D","Qp");
		unicodeStr = unicodeStr.replace("Z","QP");
		unicodeStr = unicodeStr.replace("³","Qு");
		unicodeStr = unicodeStr.replace("Æ","Qூ");
		unicodeStr = unicodeStr.replace("ªë","nQ");
		unicodeStr = unicodeStr.replace("«ë","NQ");
		unicodeStr = unicodeStr.replace("¬ë","iQ");
		unicodeStr = unicodeStr.replace("…","Q;");
		unicodeStr = unicodeStr.replace("ë","Q");
		unicodeStr = unicodeStr.replace("ªì÷","nls");
		unicodeStr = unicodeStr.replace("«ì£","Nlh");
		unicodeStr = unicodeStr.replace("ªì£","nlh");
		unicodeStr = unicodeStr.replace("ì£","lh");
		unicodeStr = unicodeStr.replace("®","b");
		unicodeStr = unicodeStr.replace("¯","B");
		unicodeStr = unicodeStr.replace("´","L");
		unicodeStr = unicodeStr.replace("Ç","^");
		unicodeStr = unicodeStr.replace("«ì","Nl");
		unicodeStr = unicodeStr.replace("ªì","nl");
		unicodeStr = unicodeStr.replace("¬ì","il");
		unicodeStr = unicodeStr.replace("†","l;");
		unicodeStr = unicodeStr.replace("ì","l");
		unicodeStr = unicodeStr.replace("ªí÷","nzs");
		unicodeStr = unicodeStr.replace("«í£","Nzh");
		unicodeStr = unicodeStr.replace("ªí£","nzh");
		unicodeStr = unicodeStr.replace("í£","zh");
		unicodeStr = unicodeStr.replace("E","zp");
		unicodeStr = unicodeStr.replace("a","zP");
		unicodeStr = unicodeStr.replace("µ","Z");
		unicodeStr = unicodeStr.replace("È","Z}");
		unicodeStr = unicodeStr.replace("ªí","nz");
		unicodeStr = unicodeStr.replace("«í","Nz");
		unicodeStr = unicodeStr.replace("¬í","iz");
		unicodeStr = unicodeStr.replace("‡","z;");
		unicodeStr = unicodeStr.replace("í","z");
		unicodeStr = unicodeStr.replace("ªî÷","njs");
		unicodeStr = unicodeStr.replace("«î£","Njh");
		unicodeStr = unicodeStr.replace("ªî£","njh");
		unicodeStr = unicodeStr.replace("î£","jh");
		unicodeStr = unicodeStr.replace("F","jp");
		unicodeStr = unicodeStr.replace("b","jP");
		unicodeStr = unicodeStr.replace("É","J}");
		unicodeStr = unicodeStr.replace("¶","J");
		unicodeStr = unicodeStr.replace("ªî","nj");
		unicodeStr = unicodeStr.replace("«î","Nj");
		unicodeStr = unicodeStr.replace("¬î","ij");
		unicodeStr = unicodeStr.replace("ˆ","j;");
		unicodeStr = unicodeStr.replace("î","j");
		unicodeStr = unicodeStr.replace("ªï÷","nes");
		unicodeStr = unicodeStr.replace("«ï£","Neh");
		unicodeStr = unicodeStr.replace("ªï£","neh");
		unicodeStr = unicodeStr.replace("ï£","eh");
		unicodeStr = unicodeStr.replace("G","ep");
		unicodeStr = unicodeStr.replace("c","eP");
		unicodeStr = unicodeStr.replace("¸","E");
		unicodeStr = unicodeStr.replace("Ë","E}");
		unicodeStr = unicodeStr.replace("ªï","ne");
		unicodeStr = unicodeStr.replace("«ï","Ne");
		unicodeStr = unicodeStr.replace("¬ï","ie");
		unicodeStr = unicodeStr.replace("‰","e;");
		unicodeStr = unicodeStr.replace("ï","e");
		unicodeStr = unicodeStr.replace("ªù÷","nds");
		unicodeStr = unicodeStr.replace("«ù£","Ndh");
		unicodeStr = unicodeStr.replace("ªù£","ndh");
		unicodeStr = unicodeStr.replace("ù£","dh");
		unicodeStr = unicodeStr.replace("Q","dp");
		unicodeStr = unicodeStr.replace("m","dP");
		unicodeStr = unicodeStr.replace("Â","D");
		unicodeStr = unicodeStr.replace("Û","D}");
		unicodeStr = unicodeStr.replace("ªù","nd");
		unicodeStr = unicodeStr.replace("«ù","Nd");
		unicodeStr = unicodeStr.replace("¬ù","id");
		unicodeStr = unicodeStr.replace("¡","d;");
		unicodeStr = unicodeStr.replace("ù","d");
		unicodeStr = unicodeStr.replace("ªð÷","ngs");
		unicodeStr = unicodeStr.replace("«ð£","Ngh");
		unicodeStr = unicodeStr.replace("ªð£","ngh");
		unicodeStr = unicodeStr.replace("ð£","gh");
		unicodeStr = unicodeStr.replace("H","gp");
		unicodeStr = unicodeStr.replace("d","gP");
		unicodeStr = unicodeStr.replace("¹","G");
		unicodeStr = unicodeStr.replace("Ì","g+"); 
		unicodeStr = unicodeStr.replace("ªð","ng");
		unicodeStr = unicodeStr.replace("«ð","Ng");
		unicodeStr = unicodeStr.replace("¬ð","ig");
		unicodeStr = unicodeStr.replace("Š","g;");
		unicodeStr = unicodeStr.replace("ð","g");
		unicodeStr = unicodeStr.replace("ªñ÷","nks");
		unicodeStr = unicodeStr.replace("«ñ£","Nkh");
		unicodeStr = unicodeStr.replace("ªñ£","nkh");
		unicodeStr = unicodeStr.replace("ñ£","kh");
		unicodeStr = unicodeStr.replace("I","kp");
		unicodeStr = unicodeStr.replace("e","kP");
		unicodeStr = unicodeStr.replace("º","K");
		unicodeStr = unicodeStr.replace("Í","%");
		unicodeStr = unicodeStr.replace("ªñ","nk");
		unicodeStr = unicodeStr.replace("«ñ","Nk");
		unicodeStr = unicodeStr.replace("¬ñ","ik");
		unicodeStr = unicodeStr.replace("‹","k;");
		unicodeStr = unicodeStr.replace("ñ","k");
		unicodeStr = unicodeStr.replace("ªò÷","nas");
		unicodeStr = unicodeStr.replace("«ò£","Nah");
		unicodeStr = unicodeStr.replace("ªò£","nah");
		unicodeStr = unicodeStr.replace("ò£","ah");
		unicodeStr = unicodeStr.replace("J","ap");
		unicodeStr = unicodeStr.replace("f","aP");
		unicodeStr = unicodeStr.replace("»","A");
		unicodeStr = unicodeStr.replace("Î","a+"); 
		unicodeStr = unicodeStr.replace("ªò","na");
		unicodeStr = unicodeStr.replace("«ò","Na");
		unicodeStr = unicodeStr.replace("¬ò","ia");
		unicodeStr = unicodeStr.replace("Œ","a;");
		unicodeStr = unicodeStr.replace("ò","a");
		unicodeStr = unicodeStr.replace("ªó÷","nus");
		unicodeStr = unicodeStr.replace("«ó£","Nuh");
		unicodeStr = unicodeStr.replace("ªó£","nuh");
		unicodeStr = unicodeStr.replace("ó£","uh");
		unicodeStr = unicodeStr.replace("K","up");
		unicodeStr = unicodeStr.replace("g","uP");
		unicodeStr = unicodeStr.replace("¼","U");
		unicodeStr = unicodeStr.replace("Ï","&"); 
		unicodeStr = unicodeStr.replace("ªó","nu");
		unicodeStr = unicodeStr.replace("«ó","Nu");
		unicodeStr = unicodeStr.replace("¬ó","iu");
		unicodeStr = unicodeStr.replace("˜","H");
		unicodeStr = unicodeStr.replace("ó","u");
		unicodeStr = unicodeStr.replace("ªô÷","nys");
		unicodeStr = unicodeStr.replace("«ô£","Nyh");
		unicodeStr = unicodeStr.replace("ªô£","nyh");
		unicodeStr = unicodeStr.replace("ô£","yh");
		unicodeStr = unicodeStr.replace("L","yp");
		unicodeStr = unicodeStr.replace("h","yP");
		unicodeStr = unicodeStr.replace("½","Y");
		unicodeStr = unicodeStr.replace("Ö","Y}");
		unicodeStr = unicodeStr.replace("ªô","ny");
		unicodeStr = unicodeStr.replace("«ô","Ny");
		unicodeStr = unicodeStr.replace("¬ô","iy");
		unicodeStr = unicodeStr.replace("™","y;");
		unicodeStr = unicodeStr.replace("ô","y");
		unicodeStr = unicodeStr.replace("ª÷÷","nss");
		unicodeStr = unicodeStr.replace("«÷£","Nsh");
		unicodeStr = unicodeStr.replace("ª÷£","nsh");
		unicodeStr = unicodeStr.replace("÷£","sh");
		unicodeStr = unicodeStr.replace("O","sp");
		unicodeStr = unicodeStr.replace("k","sP");
		unicodeStr = unicodeStr.replace("À","S");
		unicodeStr = unicodeStr.replace("Ù","s+"); 
		unicodeStr = unicodeStr.replace("ª÷","ns");
		unicodeStr = unicodeStr.replace("«÷","Ns");
		unicodeStr = unicodeStr.replace("¬÷","is");
		unicodeStr = unicodeStr.replace("œ","s;");
		unicodeStr = unicodeStr.replace("÷","s");
		unicodeStr = unicodeStr.replace("ªõ÷","nts");
		unicodeStr = unicodeStr.replace("«õ£","Nth");
		unicodeStr = unicodeStr.replace("ªõ£","nth");
		unicodeStr = unicodeStr.replace("õ£","th");
		unicodeStr = unicodeStr.replace("M","tp");
		unicodeStr = unicodeStr.replace("i","tP");
		unicodeStr = unicodeStr.replace("¾","T");
		unicodeStr = unicodeStr.replace("×","t+");
		unicodeStr = unicodeStr.replace("ªõ","nt");
		unicodeStr = unicodeStr.replace("«õ","Nt");
		unicodeStr = unicodeStr.replace("¬õ","it");
		unicodeStr = unicodeStr.replace("š","t;");
		unicodeStr = unicodeStr.replace("õ","t");
		unicodeStr = unicodeStr.replace("ªö÷","nos");
		unicodeStr = unicodeStr.replace("«ö£","Noh");
		unicodeStr = unicodeStr.replace("ªö£","noh");
		unicodeStr = unicodeStr.replace("ö£","oh");
		unicodeStr = unicodeStr.replace("N","op");
		unicodeStr = unicodeStr.replace("j","oP");
		unicodeStr = unicodeStr.replace("¿","O");
		unicodeStr = unicodeStr.replace("Ø","*");
		unicodeStr = unicodeStr.replace("ªö","no");
		unicodeStr = unicodeStr.replace("«ö","No");
		unicodeStr = unicodeStr.replace("¬ö","io");
		unicodeStr = unicodeStr.replace("›","o;");
		unicodeStr = unicodeStr.replace("ö","o");
		unicodeStr = unicodeStr.replace("ªø÷","nws");
		unicodeStr = unicodeStr.replace("«ø£","Nwh");
		unicodeStr = unicodeStr.replace("ªø£","nwh");
		unicodeStr = unicodeStr.replace("ø£","wh");
		unicodeStr = unicodeStr.replace("P","wp");
		unicodeStr = unicodeStr.replace("l","wP");
		unicodeStr = unicodeStr.replace("Á","W");
		unicodeStr = unicodeStr.replace("Ú","W}");
		unicodeStr = unicodeStr.replace("ªø","nw");
		unicodeStr = unicodeStr.replace("«ø","Nw");
		unicodeStr = unicodeStr.replace("¬ø","iw");
		unicodeStr = unicodeStr.replace("Ÿ","w;");
		unicodeStr = unicodeStr.replace("ø","w");
		unicodeStr = unicodeStr.replace("ªý÷","n`s");
		unicodeStr = unicodeStr.replace("«ý£","N`h");
		unicodeStr = unicodeStr.replace("ªý£","n`h");
		unicodeStr = unicodeStr.replace("U","`p");
		unicodeStr = unicodeStr.replace("q","`P");
		unicodeStr = unicodeStr.replace("ý§","`{");
		unicodeStr = unicodeStr.replace("ý¨","`_");
		unicodeStr = unicodeStr.replace("ªý","n`");
		unicodeStr = unicodeStr.replace("«ý","N`");
		unicodeStr = unicodeStr.replace("¬ý","i`");
		unicodeStr = unicodeStr.replace("y","`;");
		unicodeStr = unicodeStr.replace("ý","`");
		unicodeStr = unicodeStr.replace("ý£","`h");
		unicodeStr = unicodeStr.replace("ªû÷","n~s");
		unicodeStr = unicodeStr.replace("«û£","N~h");
		unicodeStr = unicodeStr.replace("ªû£","n~h");
		unicodeStr = unicodeStr.replace("û£","~s");
		unicodeStr = unicodeStr.replace("S","~p");
		unicodeStr = unicodeStr.replace("o","~P");
		unicodeStr = unicodeStr.replace("û§","~{");
		unicodeStr = unicodeStr.replace("û¨","~_");
		unicodeStr = unicodeStr.replace("ªû","n~");
		unicodeStr = unicodeStr.replace("«û","N~");
		unicodeStr = unicodeStr.replace("¬û","i~");
		unicodeStr = unicodeStr.replace("w","~;");
		unicodeStr = unicodeStr.replace("û","~");
		unicodeStr = unicodeStr.replace("ªú÷","n]s");
		unicodeStr = unicodeStr.replace("«ú£","N]h");
		unicodeStr = unicodeStr.replace("ªú£","n]h");
		unicodeStr = unicodeStr.replace("ú£","]h");
		unicodeStr = unicodeStr.replace("R","]p");
		unicodeStr = unicodeStr.replace("n","]P");
		unicodeStr = unicodeStr.replace("ú§","]{");
		unicodeStr = unicodeStr.replace("ú¨","]_");
		unicodeStr = unicodeStr.replace("ªú","n]");
		unicodeStr = unicodeStr.replace("«ú","N]");
		unicodeStr = unicodeStr.replace("¬ú","i]");
		unicodeStr = unicodeStr.replace("v","];");
		unicodeStr = unicodeStr.replace("ú","]");
		unicodeStr = unicodeStr.replace("Ü","m");
		unicodeStr = unicodeStr.replace("Ý","M");
		unicodeStr = unicodeStr.replace("Þ",",");
		unicodeStr = unicodeStr.replace("ß","<");
		unicodeStr = unicodeStr.replace("à","c");
		unicodeStr = unicodeStr.replace("á","C");
		unicodeStr = unicodeStr.replace("â","v");
		unicodeStr = unicodeStr.replace("ã","V");
		unicodeStr = unicodeStr.replace("ä","I");
		unicodeStr = unicodeStr.replace("å","x");
		unicodeStr = unicodeStr.replace("æ","X");
		unicodeStr = unicodeStr.replace("å÷","xs");
		unicodeStr = unicodeStr.replace("ç","/");

		
		return unicodeStr;
	
	
	
	
	
	
	
	*/
		

		String TamilText[] = {"þ", "þ£", "¬þ", "V", "r", "þ§", "þ¨", "ªþ", "«þ", "ªþ£",
				"«þ£", "ªþ÷", "z", "ªü÷", "«ü£", "ªü£", "ü£", "T", "p", "ü§",
				"ü¨", "ªü", "«ü", "¬ü", "ªü£", "x", "ü", "ªè÷", "«è£", "ªè£",
				"è£", "A", "W", "°", "Ã", "ªè", "«è", "¬è", "‚", "è",
				"ªé÷", "«é£", "ªé£", "é£", "B", "X", "±", "Ä", "ªé", "«é",
				"¬é", "ƒ", "é", "ªê÷", "«ê£", "ªê£", "ê£", "C", "Y", "²",
				"Å", "ªê", "«ê", "¬ê", "„", "ê", "ªë÷", "«ë£", "ªë£", "ë£",
				"D", "Z", "³", "Æ", "ªë", "«ë", "¬ë", "…", "ë", "ªì÷",
				"«ì£", "ªì£", "ì£", "®", "¯", "´", "Ç", "ªì", "«ì", "¬ì",
				"†", "ì", "ªí÷", "«í£", "ªí£", "í£", "E", "a", "µ", "È",
				"ªí", "«í", "¬í", "‡", "í", "ªî÷", "«î£", "ªî£", "î£", "F",
				"b", "¶", "É", "ªî", "«î", "¬î", "ˆ", "î", "ªï÷", "«ï£",
				"ªï£", "ï£", "G", "c", "¸", "Ë", "ªï", "«ï", "¬ï", "ªï£",
				"‰", "ï", "ªù÷", "«ù£", "ªù£", "ù£", "Q", "m", "Â", "Û",
				"ªù", "«ù", "¬ù", "ªù£", "¡", "ù", "ªð÷", "«ð£", "ªð£", "ð£",
				"H", "d", "¹", "Ì", "ªð", "«ð", "¬ð", "ªð£", "Š", "ð",
				"ªñ÷", "«ñ£", "ªñ£", "ñ£", "I", "e", "º", "Í", "ªñ", "«ñ",
				"¬ñ", "ªñ£", "‹", "ñ", "ªò÷", "«ò£", "ªò£", "ò£", "J", "f",
				"»", "Î", "ªò", "«ò", "¬ò", "ªò£", "Œ", "ò", "ªó÷", "«ó£",
				"ªó£", "ó£", "K", "g", "¼", "Ï", "ªó", "«ó", "¬ó", "˜",
				"ó", "ªô÷", "«ô£", "ªô£", "ô£", "L", "h", "½", "Ö", "ªô",
				"«ô", "¬ô", "™", "ô", "ª÷÷", "«÷£", "ª÷£", "÷£", "O", "k",
				"À", "Ù", "ª÷", "«÷", "¬÷", "œ", "÷", "ªõ÷", "«õ£", "ªõ£",
				"õ£", "M", "i", "¾", "×", "ªõ", "«õ", "¬õ", "ªõ£", "š",
				"õ", "ªö÷", "«ö£", "ªö£", "ö£", "N", "j", "¿", "Ø", "ªö",
				"«ö", "¬ö", "›", "ö", "ªø÷", "«ø£", "ªø£", "ø£", "P", "l",
				"Á", "Ú", "ªø", "«ø", "¬ø", "ªø£", "Ÿ", "ø", "ªý÷", "«ý£",
				"ªý£", "ý£", "U", "q", "ªý", "«ý", "¬ý", "y", "ý", "ªû÷",
				"«û£", "ªû£", "û£", "S", "o", "ªû", "«û", "¬û", "ªû£", "w",
				"û", "ªú÷", "«ú£", "ªú£", "ú£", "R", "n", "ªú", "«ú", "¬ú",
				"v", "ú", "Ü", "Ý", "Þ", "ß", "à", "á", "â", "ã",
				"ä", "å", "æ", "å÷", "ç", "ÿ", "ƒ"};

		String TamilReplace[] = {"க்ஷ", "க்ஷா", "க்ஷை", "க்ஷி", "க்ஷீ", "க்ஷு", "க்ஷூ", "க்ஷெ", "க்ஷே", "க்ஷொ",
				"க்ஷோ", "க்ஷௌ", "க்ஷ்", "ஜௌ", "ஜோ", "ஜொ", "ஜா", "ஜி", "ஜீ", "ஜு",
				"ஜூ", "ஜெ", "ஜே", "ஜை", "ஜொ", "ஜ்", "ஜ", "கௌ", "கோ", "கொ",
				"கா", "கி", "கீ", "கு", "கூ", "கெ", "கே", "கை", "க்", "க",
				"ஙௌ", "ஙோ", "ஙொ", "ஙா", "ஙி", "ஙீ", "ஙு", "ஙூ", "ஙெ", "ஙே",
				"ஙை", "ங்", "ங", "சௌ", "சோ", "சொ", "சா", "சி", "சீ", "சு",
				"சூ", "செ", "சே", "சை", "ச்", "ச", "ஞௌ", "ஞோ", "ஞொ", "ஞா",
				"ஞி", "ஞீ", "ஞு", "ஞூ", "ஞெ", "ஞே", "ஞை", "ஞ்", "ஞ", "டௌ",
				"டோ", "டொ", "டா", "டி", "டீ", "டு", "டூ", "டெ", "டே", "டை",
				"ட்", "ட", "ணௌ", "ணோ", "ணொ", "ணா", "ணி", "ணீ", "ணு", "ணூ",
				"ணெ", "ணே", "ணை", "ண்", "ண", "தௌ", "தோ", "தொ", "தா", "தி",
				"தீ", "து", "தூ", "தெ", "தே", "தை", "த்", "த", "நௌ", "நோ",
				"நொ", "நா", "நி", "நீ", "நு", "நூ", "நெ", "நே", "நை", "நொ",
				"ந்", "ந", "னௌ", "னோ", "னொ", "னா", "னி", "னீ", "னு", "னூ",
				"னெ", "னே", "னை", "னொ", "ன்", "ன", "பௌ", "போ", "பொ", "பா",
				"பி", "பீ", "பு", "பூ", "பெ", "பே", "பை", "பொ", "ப்", "ப",
				"மௌ", "மோ", "மொ", "மா", "மி", "மீ", "மு", "மூ", "மெ", "மே",
				"மை", "மொ", "ம்", "ம", "யௌ", "யோ", "ய", "யா", "யி", "யீ",
				"யு", "யூ", "யெ", "யே", "யை", "யொ", "ய்", "ய", "ரௌ", "ரோ",
				"ரொ", "ரா", "ரி", "ரீ", "ரு", "ரூ", "ரெ", "ரே", "ரை", "ர்",
				"ர", "லௌ", "லோ", "லொ", "லா", "லி", "லீ", "லு", "லூ", "லெ",
				"லே", "லை", "ல்", "ல", "ளௌ", "ளோ", "ள", "ளா", "ளி", "ளீ",
				"ளு", "ளூ", "ளெ", "ளே", "ளை", "ள்", "ள", "வௌ", "வோ", "வொ",
				"வா", "வி", "வீ", "வு", "வூ", "வெ", "வே", "வை", "வொ", "வ்",
				"வ", "ழௌ", "ழோ", "ழொ", "ழா", "ழி", "ழீ", "ழு", "ழூ", "ழெ",
				"ழே", "ழை", "ழ்", "ழ", "றௌ", "றோ", "றொ", "றா", "றி", "றீ",
				"று", "றூ", "றெ", "றே", "றை", "றொ", "ற்", "ற", "ஹௌ", "ஹோ",
				"ஹொ", "ஹா", "ஹி", "ஹீ", "ஹெ", "ஹே", "ஹை", "ஹ்", "ஹ", "ஷௌ",
				"ஷோ", "ஷொ", "ஷா", "ஷி", "ஷீ", "ஷெ", "ஷே", "ஷை", "ஷொ", "ஷ்",
				"ஷ", "ஸௌ", "ஸோ", "ஸொ", "ஸா", "ஸி", "ஸீ", "ஸெ", "ஸே", "ஸை",
				"ஸ்", "ஸ", "அ", "ஆ", "இ", "ஈ", "உ", "ஊ", "எ", "ஏ",
				"ஐ", "ஒ", "ஓ", "ஔ", "ஃ", "ஸ்ரீ", "‘"};

		int count = 0;
		while (count < TamilText.length) {
			unicodeStr = unicodeStr.replaceAll(TamilText[count], TamilReplace[count]);
			count++;
		}
		return unicodeStr;
		
	
	
	}





	
	
	
	
	
	
}
